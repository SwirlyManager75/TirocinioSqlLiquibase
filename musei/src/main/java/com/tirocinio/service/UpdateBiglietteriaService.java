package com.tirocinio.service;

import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.model.Biglietteria;

import java.sql.Connection;

public class UpdateBiglietteriaService {

    private final BiglietteriaDAO biglietteriaDAO;
    private final Connection connection;

    public UpdateBiglietteriaService(Connection connection) {
        this.biglietteriaDAO = new BiglietteriaDAO();
        this.connection = connection;
    }

    public boolean execute(Biglietteria biglietteria) {
        return biglietteriaDAO.updateBiglietteria(connection, biglietteria);
    }
}
