package com.service.audioservice.service;

import com.jcraft.jsch.ChannelSftp;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Vector;

public interface ConnectionService {

    Vector<ChannelSftp.LsEntry> getFilesWithName(Long connectionId, String name);
    void saveFile(Long employeeId, Long connectionId, MultipartFile file);
}
