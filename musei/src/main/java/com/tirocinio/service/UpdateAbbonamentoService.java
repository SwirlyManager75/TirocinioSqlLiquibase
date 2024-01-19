package com.tirocinio.service;

import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.model.Abbonamento;

import java.sql.Connection;

public class UpdateAbbonamentoService {

    private final AbbonamentoDAO abbonamentoDAO;
    private final Connection connection;

    public UpdateAbbonamentoService(Connection connection) {
        this.abbonamentoDAO = new AbbonamentoDAO();
        this.connection = connection;
    }

    public boolean execute(Abbonamento abbonamento) {
        return abbonamentoDAO.updateAbbonamento(connection, abbonamento);
    }
}
