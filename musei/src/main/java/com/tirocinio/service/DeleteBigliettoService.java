package com.tirocinio.service;

import com.tirocinio.dao.BigliettoDAO;

import java.sql.Connection;

public class DeleteBigliettoService {

    private final BigliettoDAO bigliettoDAO;
    private final Connection connection;

    public DeleteBigliettoService(Connection connection) {
        this.bigliettoDAO = new BigliettoDAO();
        this.connection = connection;
    }

    public boolean execute(int bigliettoId) {
        return bigliettoDAO.deleteBiglietto(connection, bigliettoId);
    }
}
