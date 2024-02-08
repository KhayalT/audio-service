package com.service.audioservice.service;

import com.service.audioservice.dao.request.SaveAudioRequest;
import com.service.audioservice.dao.response.AudioFileResponse;
import com.service.audioservice.entities.AudioFile;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AudioService {
    List<AudioFile> getAllAudioWithEmployeeId(Long employeeId, Long connectionId);

}
