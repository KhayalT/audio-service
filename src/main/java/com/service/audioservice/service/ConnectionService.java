package com.service.audioservice.service;

import com.jcraft.jsch.ChannelSftp;
import org.springframework.stereotype.Service;

import java.util.Vector;

public interface ConnectionService {

    Vector<ChannelSftp.LsEntry> getFilesWithName(Long connectionId, String name);
}
