package com.tirocinio.service;

import com.tirocinio.dao.BiglietteriaDAO;

import java.sql.Connection;

public class DeleteBiglietteriaService {

    private final BiglietteriaDAO biglietteriaDAO;
    private final Connection connection;

    public DeleteBiglietteriaService(Connection connection) {
        this.biglietteriaDAO = new BiglietteriaDAO();
        this.connection = connection;
    }

    public boolean execute(int biglietteriaId) {
        return biglietteriaDAO.deleteBiglietteria(connection, biglietteriaId);
    }
}
