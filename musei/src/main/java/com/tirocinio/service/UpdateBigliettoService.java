package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.model.Biglietto;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateBigliettoService {

    private final BigliettoDAO bigliettoDAO;

    public UpdateBigliettoService( ) {
        this.bigliettoDAO = new BigliettoDAO();
    }

    public boolean execute(Biglietto biglietto) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return bigliettoDAO.updateBiglietto(connection, biglietto);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
