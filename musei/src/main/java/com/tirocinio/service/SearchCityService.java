package com.tirocinio.service;

import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.util.List;

public class SearchCityService {

    private final CittaDAO cittaDAO;
    private final Connection connection;

    public SearchCityService(Connection connection) {
        this.cittaDAO = new CittaDAO();
        this.connection = connection;
    }

    public List<Citta> execute(Citta criteria) {
        return cittaDAO.search(connection, criteria);
    }
}