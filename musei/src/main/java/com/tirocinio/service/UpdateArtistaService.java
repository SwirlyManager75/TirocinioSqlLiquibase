package com.tirocinio.service;

import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.model.Artista;

import java.sql.Connection;

public class UpdateArtistaService {

    private final ArtistaDAO artistaDAO;
    private final Connection connection;

    public UpdateArtistaService(Connection connection) {
        this.artistaDAO = new ArtistaDAO();
        this.connection = connection;
    }

    public boolean execute(Artista artista) {
        return artistaDAO.updateArtista(connection, artista);
    }
}
