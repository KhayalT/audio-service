package com.service.audioservice.controller.v1;

import com.jcraft.jsch.ChannelSftp;
import com.service.audioservice.dao.request.SaveAudioRequest;
import com.service.audioservice.entities.AudioFile;
import com.service.audioservice.service.AudioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Vector;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class AudioFileController {

    private final AudioService audioService;

    @GetMapping("/employee/{id}/audio")
    public ResponseEntity<List<AudioFile>> list(@PathVariable Long id, @RequestHeader Long connectionId){
        return ResponseEntity.ok(audioService.getAllAudioWithEmployeeId(id, connectionId));
    }

    @GetMapping("/employee/{id}/audio-raw")
    public ResponseEntity<Vector<ChannelSftp.LsEntry>> listRaw(@PathVariable Long id, @RequestHeader Long connectionId){
        return ResponseEntity.ok(audioService.getAllAudioWithEmployeeIdRaw(id, connectionId));
    }

    @PostMapping("/employee/{id}/audio")
    public ResponseEntity<AudioFile> save(@PathVariable Long id, @Valid @ModelAttribute SaveAudioRequest request,
                                          @RequestHeader Long connectionId){
        return ResponseEntity.ok(audioService.saveAudio(id, connectionId, request.getFile()));
    }
}
