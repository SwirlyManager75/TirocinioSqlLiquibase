package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AudioDAO;
import com.tirocinio.model.Audio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllAudiosService {

    private final AudioDAO audioDAO;

    public GetAllAudiosService( ) {
        this.audioDAO = new AudioDAO();
    }

    public List<Audio> execute() {
        try (Connection connection = ConnectionManager.getConnection()) {
            return audioDAO.getAllAudios(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
