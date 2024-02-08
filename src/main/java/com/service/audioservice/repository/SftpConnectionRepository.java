package com.service.audioservice.repository;

import com.service.audioservice.entities.SftpConnection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SftpConnectionRepository extends JpaRepository<SftpConnection, Long> {
}
