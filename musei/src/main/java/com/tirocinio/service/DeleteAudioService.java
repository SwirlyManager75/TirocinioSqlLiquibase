package com.tirocinio.service;

import com.tirocinio.dao.AudioDAO;

import java.sql.Connection;

public class DeleteAudioService {

    private final AudioDAO audioDAO;
    private final Connection connection;

    public DeleteAudioService(Connection connection) {
        this.audioDAO = new AudioDAO();
        this.connection = connection;
    }

    public boolean execute(int audioId) {
        return audioDAO.deleteAudio(connection, audioId);
    }
}
