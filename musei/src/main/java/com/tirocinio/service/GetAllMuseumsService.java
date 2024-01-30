package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllMuseumsService {

    private final MuseoDAO museoDAO;
    

    public GetAllMuseumsService() {
        this.museoDAO = new MuseoDAO();
        
    }

    public List<Museo> execute() {
        try (Connection connection = ConnectionManager.getConnection()) {
            return museoDAO.getAllMuseums(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
