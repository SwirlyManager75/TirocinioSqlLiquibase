package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateCityService {

    private final CittaDAO cittaDAO;

    public UpdateCityService( ) {
        this.cittaDAO = new CittaDAO();
    }

    public boolean execute(Citta city) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return cittaDAO.updateCity(connection, city);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
