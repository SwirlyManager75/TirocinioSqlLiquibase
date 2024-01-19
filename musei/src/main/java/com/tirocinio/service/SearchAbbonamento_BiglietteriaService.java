package com.tirocinio.service;

import com.tirocinio.dao.Abbonamento_BiglietteriaDAO;
import com.tirocinio.model.Abbonamento_Biglietteria;

import java.sql.Connection;
import java.util.List;

public class SearchAbbonamento_BiglietteriaService {

    private final Abbonamento_BiglietteriaDAO abbonamentoBiglietteriaDAO;
    private final Connection connection;

    public SearchAbbonamento_BiglietteriaService(Connection connection) {
        this.abbonamentoBiglietteriaDAO = new Abbonamento_BiglietteriaDAO();
        this.connection = connection;
    }

    public List<Abbonamento_Biglietteria> execute(Abbonamento_Biglietteria criteria) {
        return abbonamentoBiglietteriaDAO.search(connection, criteria);
    }
}
