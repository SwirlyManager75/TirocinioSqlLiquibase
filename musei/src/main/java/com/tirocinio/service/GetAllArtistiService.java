package com.tirocinio.service;

import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.model.Artista;

import java.sql.Connection;
import java.util.List;

public class GetAllArtistiService {

    private final ArtistaDAO artistaDAO;
    private final Connection connection;

    public GetAllArtistiService(Connection connection) {
        this.artistaDAO = new ArtistaDAO();
        this.connection = connection;
    }

    public List<Artista> execute() {
        return artistaDAO.getAllArtisti(connection);
    }
}
