package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllCitiesService {

    private final CittaDAO cittaDAO;

    public GetAllCitiesService( ) {
        this.cittaDAO = new CittaDAO();
    }

    public List<Citta> execute() {
        try (Connection connection = ConnectionManager.getConnection()) 
        {
            return cittaDAO.getAllCities(connection);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }
}
