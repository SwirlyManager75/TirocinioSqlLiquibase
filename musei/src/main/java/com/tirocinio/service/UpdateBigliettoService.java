package com.tirocinio.service;

import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.model.Biglietto;

import java.sql.Connection;

public class UpdateBigliettoService {

    private final BigliettoDAO bigliettoDAO;
    private final Connection connection;

    public UpdateBigliettoService(Connection connection) {
        this.bigliettoDAO = new BigliettoDAO();
        this.connection = connection;
    }

    public boolean execute(Biglietto biglietto) {
        return bigliettoDAO.updateBiglietto(connection, biglietto);
    }
}
