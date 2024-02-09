package com.service.audioservice.service.impl;

import com.jcraft.jsch.*;
import com.service.audioservice.entities.SftpConnection;
import com.service.audioservice.repository.SftpConnectionRepository;
import com.service.audioservice.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Vector;

@RequiredArgsConstructor
@Service
public class SftpConnectionServiceImpl implements ConnectionService {

    private final SftpConnectionRepository sftpConnectionRepository;

    @Override
    public Vector<ChannelSftp.LsEntry> getFilesWithName(Long connectionId, String fileName) {
        SftpConnection connection = sftpConnectionRepository.getReferenceById(connectionId);
        Session session = getSession(connection);
        ChannelSftp channel = getChannel(session);
        return getFilesNamesFromSFTP(channel, connection.getBaseDirectory(), fileName);
    }

    @Override
    public void saveFile(Long employeeId, Long connectionId, File file) {
        SftpConnection connection = sftpConnectionRepository.getReferenceById(connectionId);
        Session session = getSession(connection);
        ChannelSftp channel = getChannel(session);
        putFileToSFTP(channel, connection.getBaseDirectory(), String.valueOf(employeeId), file);
    }

    @SneakyThrows
    private Session getSession(SftpConnection connection){
        String host = connection.getHost();
        String user = connection.getUser();
        Integer port = connection.getPort();
        JSch jsch = new JSch();
        try {
            Session session = jsch.getSession(user, host, port);
            session.setPassword(connection.getPassword());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            return session;
        } catch (JSchException e) {
           throw new Exception(e.getMessage());
        }
    }

    @SneakyThrows
    private ChannelSftp getChannel(Session session){
        ChannelSftp channelSftp = null;
        if (session != null && session.isConnected()){
            try {
                channelSftp = (ChannelSftp) session.openChannel("sftp");
                channelSftp.connect();
            } catch (JSchException e) {
                throw new Exception(e.getMessage());
            }
        }
        return channelSftp;
    }

    @SneakyThrows
    private Vector<ChannelSftp.LsEntry> getFilesNamesFromSFTP(ChannelSftp channelSftp, String remoteDirectory, String filePrefixAndExtension){
        Vector<ChannelSftp.LsEntry> files = new Vector<>();
        if(channelSftp != null && channelSftp.isConnected()){
            try {
                channelSftp.cd(remoteDirectory);
                channelSftp.cd(filePrefixAndExtension);
                files = channelSftp.ls("*.wav");
            } catch (SftpException e) {
                throw new Exception(e.getMessage());
            }
        }
        return files;
    }

    @SneakyThrows
    private void putFileToSFTP(ChannelSftp channelSftp, String remoteDirectory, String filePrefixAndExtension, File file){
        if(channelSftp != null && channelSftp.isConnected()){
            try {
                channelSftp.put(file.getAbsolutePath(), remoteDirectory + '/' + filePrefixAndExtension + file.getName());
            } catch (SftpException e) {
                throw new Exception(e.getMessage());
            }
        }
    }
}
