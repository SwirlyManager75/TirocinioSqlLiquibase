package com.tirocinio.service;

import com.tirocinio.dao.CittaDAO;

import java.sql.Connection;

public class DeleteCityService {

    private final CittaDAO cittaDAO;
    private final Connection connection;

    public DeleteCityService(Connection connection) {
        this.cittaDAO = new CittaDAO();
        this.connection = connection;
    }

    public boolean execute(int cityId) {
        return cittaDAO.deleteCity(connection, cityId);
    }
}
