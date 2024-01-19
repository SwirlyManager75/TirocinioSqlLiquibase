package com.tirocinio.service;

import com.tirocinio.dao.Abbonamento_BiglietteriaDAO;
import com.tirocinio.model.Abbonamento_Biglietteria;

import java.sql.Connection;

public class CreateAbbonamento_BiglietteriaService {

    private final Abbonamento_BiglietteriaDAO abbonamentoBiglietteriaDAO;
    private final Connection connection;

    public CreateAbbonamento_BiglietteriaService(Connection connection) {
        this.abbonamentoBiglietteriaDAO = new Abbonamento_BiglietteriaDAO();
        this.connection = connection;
    }

    public boolean execute(Abbonamento_Biglietteria abbonamentoBiglietteria) {
        return abbonamentoBiglietteriaDAO.addAbbonamentoBiglietteria(connection, abbonamentoBiglietteria);
    }
}
