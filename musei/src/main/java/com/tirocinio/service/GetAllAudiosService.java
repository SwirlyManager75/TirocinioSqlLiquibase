package com.tirocinio.service;

import com.tirocinio.dao.AudioDAO;
import com.tirocinio.model.Audio;

import java.sql.Connection;
import java.util.List;

public class GetAllAudiosService {

    private final AudioDAO audioDAO;
    private final Connection connection;

    public GetAllAudiosService(Connection connection) {
        this.audioDAO = new AudioDAO();
        this.connection = connection;
    }

    public List<Audio> execute() {
        return audioDAO.getAllAudios(connection);
    }
}
