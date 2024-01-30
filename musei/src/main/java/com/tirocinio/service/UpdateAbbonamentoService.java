package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.model.Abbonamento;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateAbbonamentoService {

    private final AbbonamentoDAO abbonamentoDAO;

    public UpdateAbbonamentoService( ) {
        this.abbonamentoDAO = new AbbonamentoDAO();
       
    }

    public boolean execute(Abbonamento abbonamento) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return abbonamentoDAO.updateAbbonamento(connection, abbonamento);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
