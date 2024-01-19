package com.tirocinio.service;

import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.model.Abbonamento;

import java.sql.Connection;
import java.util.List;

public class GetAllAbbonamentiService {

    private final AbbonamentoDAO abbonamentoDAO;
    private final Connection connection;

    public GetAllAbbonamentiService(Connection connection) {
        this.abbonamentoDAO = new AbbonamentoDAO();
        this.connection = connection;
    }

    public List<Abbonamento> execute() {
        return abbonamentoDAO.getAllAbbonamenti(connection);
    }
}
