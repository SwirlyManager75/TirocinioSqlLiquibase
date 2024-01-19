package com.tirocinio.service;

import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.model.Museo;

import java.sql.Connection;

public class CreateMuseumService {

    private final MuseoDAO museoDAO;
    private final Connection connection;

    public CreateMuseumService(Connection connection) {
        this.museoDAO = new MuseoDAO();
        this.connection = connection;
    }

    public boolean execute(Museo museum) {
        return museoDAO.addMuseum(connection, museum);
    }
}
