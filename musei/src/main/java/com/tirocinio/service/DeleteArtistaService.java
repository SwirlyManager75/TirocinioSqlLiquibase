package com.tirocinio.service;

import com.tirocinio.dao.ArtistaDAO;

import java.sql.Connection;

public class DeleteArtistaService {

    private final ArtistaDAO artistaDAO;
    private final Connection connection;

    public DeleteArtistaService(Connection connection) {
        this.artistaDAO = new ArtistaDAO();
        this.connection = connection;
    }

    public boolean execute(int artistaId) {
        return artistaDAO.deleteArtista(connection, artistaId);
    }
}
