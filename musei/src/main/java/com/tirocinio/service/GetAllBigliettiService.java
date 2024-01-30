package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.model.Biglietto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllBigliettiService {

    private final BigliettoDAO bigliettoDAO;

    public GetAllBigliettiService( ) {
        this.bigliettoDAO = new BigliettoDAO();
    }

    public List<Biglietto> execute() {
        try (Connection connection = ConnectionManager.getConnection()) {
            return bigliettoDAO.getAllBiglietti(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
