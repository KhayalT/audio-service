package com.service.audioservice.service;

import com.jcraft.jsch.ChannelSftp;
import com.service.audioservice.entities.AudioFile;

import java.io.File;
import java.util.List;
import java.util.Vector;

public interface AudioService {
    List<AudioFile> getAllAudioWithEmployeeId(Long employeeId, Long connectionId);
    Vector<ChannelSftp.LsEntry> getAllAudioWithEmployeeIdRaw(Long employeeId, Long connectionId);
    AudioFile saveAudio(Long employeeId, Long connectionId, File file);

}
