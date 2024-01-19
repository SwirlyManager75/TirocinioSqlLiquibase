package com.tirocinio.service;

import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.util.List;

public class GetAllCitiesService {

    private final CittaDAO cittaDAO;
    private final Connection connection;

    public GetAllCitiesService(Connection connection) {
        this.cittaDAO = new CittaDAO();
        this.connection = connection;
    }

    public List<Citta> execute() {
        return cittaDAO.getAllCities(connection);
    }
}
