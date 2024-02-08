package com.service.audioservice.controller.v1;

import com.service.audioservice.dao.request.GetAudioRequest;
import com.service.audioservice.dao.response.AudioFileResponse;
import com.service.audioservice.entities.AudioFile;
import com.service.audioservice.service.AudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class AudioFileController {

    private final AudioService audioService;

    @PostMapping("/employee/{id}/audio")
    public ResponseEntity<List<AudioFile>> index(@PathVariable Long id, @RequestBody GetAudioRequest request){
        return ResponseEntity.ok(audioService.getAllAudioWithEmployeeId(id, request.getConnectionId()));
    }
}
