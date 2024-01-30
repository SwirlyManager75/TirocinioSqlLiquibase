package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AudioDAO;
import com.tirocinio.model.Audio;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateAudioService {

    private final AudioDAO audioDAO;

    public UpdateAudioService() {
        this.audioDAO = new AudioDAO();
    }

    public boolean execute(Audio audio) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return audioDAO.updateAudio(connection, audio);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
