package com.service.audioservice.dao.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
public class SaveAudioRequest {
    @NotNull(message = "File can't be empty")
    private MultipartFile file;
}
