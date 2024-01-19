package com.tirocinio.service;

import com.tirocinio.dao.AbbonamentoDAO;

import java.sql.Connection;

public class DeleteAbbonamentoService {

    private final AbbonamentoDAO abbonamentoDAO;
    private final Connection connection;

    public DeleteAbbonamentoService(Connection connection) {
        this.abbonamentoDAO = new AbbonamentoDAO();
        this.connection = connection;
    }

    public boolean execute(int abbonamentoId) {
        return abbonamentoDAO.deleteAbbonamento(connection, abbonamentoId);
    }
}
