package com.tirocinio.service;

import com.tirocinio.dao.Abbonamento_BiglietteriaDAO;

import java.sql.Connection;

public class DeleteAbbonamento_BiglietteriaService {

    private final Abbonamento_BiglietteriaDAO abbonamentoBiglietteriaDAO;
    private final Connection connection;

    public DeleteAbbonamento_BiglietteriaService(Connection connection) {
        this.abbonamentoBiglietteriaDAO = new Abbonamento_BiglietteriaDAO();
        this.connection = connection;
    }

    public boolean execute(int abbonamentoBiglietteriaId) {
        return abbonamentoBiglietteriaDAO.deleteAbbonamentoBiglietteria(connection, abbonamentoBiglietteriaId);
    }
}
