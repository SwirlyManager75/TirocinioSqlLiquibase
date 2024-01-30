package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchMuseumService {

    private final MuseoDAO museoDAO;

    public SearchMuseumService() {
        this.museoDAO = new MuseoDAO();
    }

    public List<Museo> execute(Museo criteria) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return museoDAO.search(connection, criteria);
        } catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }
}
