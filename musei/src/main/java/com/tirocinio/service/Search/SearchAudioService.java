package com.tirocinio.service.Search;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AudioDAO;
import com.tirocinio.model.Audio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchAudioService {

    private final AudioDAO audioDAO;

    public SearchAudioService( ) {
        this.audioDAO = new AudioDAO();
    }

    public List<Audio> execute(Audio criteria) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return audioDAO.search(connection, criteria);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
