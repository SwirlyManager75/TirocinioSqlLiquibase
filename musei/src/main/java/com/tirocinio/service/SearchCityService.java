package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchCityService {

    private final CittaDAO cittaDAO;

    public SearchCityService() {
        this.cittaDAO = new CittaDAO();
    }

    public List<Citta> execute(Citta criteria) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return cittaDAO.search(connection, criteria);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}