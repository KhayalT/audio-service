package com.service.audioservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "sftp_connections")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SftpConnection {
    @Id
    @SequenceGenerator(
        name = "connection_id",
        sequenceName = "connection_id"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "connection_id"
    )
    private Integer id;

    private String host;

    private Integer port;

    private String user;

    private String password;

    private String baseDirectory;
}
