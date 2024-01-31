package com.tirocinio.service.Search;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.model.Artista;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchArtistaService {

    private final ArtistaDAO artistaDAO;

    public SearchArtistaService( ) {
        this.artistaDAO = new ArtistaDAO();
    }

    public List<Artista> execute(Artista criteria) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return artistaDAO.search(connection, criteria);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
