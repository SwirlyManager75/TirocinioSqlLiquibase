package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateMuseumService {

    private final MuseoDAO museoDAO;

    public UpdateMuseumService() {
        this.museoDAO = new MuseoDAO();
    }

    public boolean execute(Museo museum) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return museoDAO.updateMuseum(connection, museum);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
