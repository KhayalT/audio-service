package com.service.audioservice.service.impl;

import com.jcraft.jsch.ChannelSftp;
import com.service.audioservice.entities.AudioFile;
import com.service.audioservice.repository.AudioRepository;
import com.service.audioservice.service.AudioService;
import com.service.audioservice.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.charset.Charset;
import java.util.*;

@RequiredArgsConstructor
@Service
public class AudioServiceImpl implements AudioService {

    private final ConnectionService connectionService;
    private final AudioRepository audioRepository;

    @Override
    public List<AudioFile> getAllAudioWithEmployeeId(Long employeeId, Long connectionId) {
        return audioRepository.getAllByEmployeeId(Long.valueOf(employeeId).intValue());
    }

    public Vector<ChannelSftp.LsEntry> getAllAudioWithEmployeeIdRaw(Long employeeId, Long connectionId) {
        Vector<ChannelSftp.LsEntry> files = connectionService.getFilesWithName(connectionId, String.valueOf(employeeId));
        List<AudioFile> audioFiles = audioRepository.getAllByEmployeeId(Long.valueOf(employeeId).intValue());

        saveAudioIfNotExist(employeeId, files, audioFiles);

        return files;
    }

    @SneakyThrows
    @Override
    public AudioFile saveAudio(Long employeeId, Long connectionId, MultipartFile file) {
        AudioFile audioFile = AudioFile.builder()
                .employeeId(Long.valueOf(employeeId).intValue())
                .fileName(file.getOriginalFilename())
                .filePath("")
                .content(file.getResource().getContentAsByteArray())
                .metadata("")
                .build();

        audioRepository.save(audioFile);

        connectionService.saveFile(employeeId, connectionId, file);

        return audioFile;
    }

    private void saveAudioIfNotExist(Long employeeId, Vector<ChannelSftp.LsEntry> files, List<AudioFile> audioFiles) {
        for (ChannelSftp.LsEntry file : files) {
            String fileName = file.getFilename();

            boolean fileExists = audioFiles.stream()
                    .anyMatch(audioFile -> audioFile.getFileName().equals(fileName)
                            && audioFile.getEmployeeId().equals(employeeId));

            if (!fileExists) {
                AudioFile newAudioFile = new AudioFile();
                newAudioFile.setFileName(fileName);
                newAudioFile.setEmployeeId(Long.valueOf(employeeId).intValue());
                newAudioFile.setFilePath(file.getLongname());

                audioRepository.save(newAudioFile);
                audioFiles.add(newAudioFile);
            }
        }
    }
}
