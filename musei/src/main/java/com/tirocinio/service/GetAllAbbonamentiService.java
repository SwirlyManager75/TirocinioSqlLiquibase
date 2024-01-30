package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.model.Abbonamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllAbbonamentiService {

    private final AbbonamentoDAO abbonamentoDAO;
    

    public GetAllAbbonamentiService( ) {
        this.abbonamentoDAO = new AbbonamentoDAO();
    }

    public List<Abbonamento> execute() {
        try (Connection connection = ConnectionManager.getConnection()) {
            return abbonamentoDAO.getAllAbbonamenti(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        
    }
}
