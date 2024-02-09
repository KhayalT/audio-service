package com.service.audioservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "audio_files")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AudioFile {
    @Id
    @SequenceGenerator(
        name = "audio_file_id",
        sequenceName = "audio_file_id"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "audio_file_id"
    )

    private Integer id;

    private Integer employeeId;

    private String fileName;

    private String filePath;

    private String metadata;

    private byte[] content;
}
