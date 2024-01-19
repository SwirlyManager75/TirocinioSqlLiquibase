package com.tirocinio.service;

import com.tirocinio.dao.Abbonamento_BiglietteriaDAO;
import com.tirocinio.model.Abbonamento_Biglietteria;

import java.sql.Connection;
import java.util.List;

public class GetAllAbbonamenti_BiglietterieService {

    private final Abbonamento_BiglietteriaDAO abbonamentoBiglietteriaDAO;
    private final Connection connection;

    public GetAllAbbonamenti_BiglietterieService(Connection connection) {
        this.abbonamentoBiglietteriaDAO = new Abbonamento_BiglietteriaDAO();
        this.connection = connection;
    }

    public List<Abbonamento_Biglietteria> execute() {
        return abbonamentoBiglietteriaDAO.getAllAbbonamentiBiglietterie(connection);
    }
}
