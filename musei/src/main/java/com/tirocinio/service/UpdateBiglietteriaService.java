package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.model.Biglietteria;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateBiglietteriaService {

    private final BiglietteriaDAO biglietteriaDAO;

    public UpdateBiglietteriaService( ) {
        this.biglietteriaDAO = new BiglietteriaDAO();
    }

    public boolean execute(Biglietteria biglietteria) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return biglietteriaDAO.updateBiglietteria(connection, biglietteria);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
