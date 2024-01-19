package com.tirocinio.service;

import com.tirocinio.dao.AudioDAO;
import com.tirocinio.model.Audio;

import java.sql.Connection;

public class UpdateAudioService {

    private final AudioDAO audioDAO;
    private final Connection connection;

    public UpdateAudioService(Connection connection) {
        this.audioDAO = new AudioDAO();
        this.connection = connection;
    }

    public boolean execute(Audio audio) {
        return audioDAO.updateAudio(connection, audio);
    }
}
