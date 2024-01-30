package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.model.Artista;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateArtistaService {

    private final ArtistaDAO artistaDAO;

    public UpdateArtistaService( ) {
        this.artistaDAO = new ArtistaDAO();
    }

    public boolean execute(Artista artista) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return artistaDAO.updateArtista(connection, artista);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
