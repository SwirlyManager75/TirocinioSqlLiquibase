package com.tirocinio.service;

import com.tirocinio.dao.AudioDAO;
import com.tirocinio.model.Audio;

import java.sql.Connection;
import java.util.List;

public class SearchAudioService {

    private final AudioDAO audioDAO;
    private final Connection connection;

    public SearchAudioService(Connection connection) {
        this.audioDAO = new AudioDAO();
        this.connection = connection;
    }

    public List<Audio> execute(Audio criteria) {
        return audioDAO.search(connection, criteria);
    }
}
