package com.tirocinio.service;

import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.model.Biglietteria;

import java.sql.Connection;
import java.util.List;

public class GetAllBiglietterieService {

    private final BiglietteriaDAO biglietteriaDAO;
    private final Connection connection;

    public GetAllBiglietterieService(Connection connection) {
        this.biglietteriaDAO = new BiglietteriaDAO();
        this.connection = connection;
    }

    public List<Biglietteria> execute() {
        return biglietteriaDAO.getAllBiglietterie(connection);
    }
}
