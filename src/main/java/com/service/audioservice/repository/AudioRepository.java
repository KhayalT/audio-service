package com.service.audioservice.repository;

import com.service.audioservice.dao.response.AudioFileResponse;
import com.service.audioservice.entities.AudioFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AudioRepository extends JpaRepository<AudioFile, Long> {

    List<AudioFile> getAllByEmployeeId(Integer employeeId);
}
