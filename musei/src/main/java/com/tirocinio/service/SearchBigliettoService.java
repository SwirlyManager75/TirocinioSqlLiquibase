package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.model.Biglietto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchBigliettoService {

    private final BigliettoDAO bigliettoDAO;

    public SearchBigliettoService( ) {
        this.bigliettoDAO = new BigliettoDAO();
    }

    public List<Biglietto> execute(Biglietto criteria) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return bigliettoDAO.search(connection, criteria);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
