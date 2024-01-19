package com.tirocinio.service;

import com.tirocinio.dao.MuseoDAO;

import java.sql.Connection;

public class DeleteMuseumService {

    private final MuseoDAO museoDAO;
    private final Connection connection;

    public DeleteMuseumService(Connection connection) {
        this.museoDAO = new MuseoDAO();
        this.connection = connection;
    }

    public boolean execute(int museumId) {
        return museoDAO.deleteMuseum(connection, museumId);
    }
}
