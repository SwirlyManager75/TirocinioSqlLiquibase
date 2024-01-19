package com.tirocinio.service;

import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Citta;

import java.sql.Connection;

public class CreateCityService {

    private final CittaDAO cittaDAO;
    private final Connection connection;

    public CreateCityService(Connection connection) {
        this.cittaDAO = new CittaDAO();
        this.connection = connection;
    }

    public boolean execute(Citta city) {
        return cittaDAO.addCity(connection, city);
    }
}
