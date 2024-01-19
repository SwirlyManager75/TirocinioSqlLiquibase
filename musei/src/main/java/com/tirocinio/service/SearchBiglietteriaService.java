package com.tirocinio.service;

import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.model.Biglietteria;

import java.sql.Connection;
import java.util.List;

public class SearchBiglietteriaService {

    private final BiglietteriaDAO biglietteriaDAO;
    private final Connection connection;

    public SearchBiglietteriaService(Connection connection) {
        this.biglietteriaDAO = new BiglietteriaDAO();
        this.connection = connection;
    }

    public List<Biglietteria> execute(Biglietteria criteria) {
        return biglietteriaDAO.search(connection, criteria);
    }
}
