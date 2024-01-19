package com.tirocinio.service;

import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.model.Abbonamento;

import java.sql.Connection;
import java.util.List;

public class SearchAbbonamentoService {

    private final AbbonamentoDAO abbonamentoDAO;
    private final Connection connection;

    public SearchAbbonamentoService(Connection connection) {
        this.abbonamentoDAO = new AbbonamentoDAO();
        this.connection = connection;
    }

    public List<Abbonamento> execute(Abbonamento criteria) {
        return abbonamentoDAO.search(connection, criteria);
    }
}
