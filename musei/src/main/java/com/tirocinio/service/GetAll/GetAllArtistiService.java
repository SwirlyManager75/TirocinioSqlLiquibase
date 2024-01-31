package com.tirocinio.service.GetAll;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.model.Artista;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllArtistiService {

    private final ArtistaDAO artistaDAO;

    public GetAllArtistiService( ) {
        this.artistaDAO = new ArtistaDAO();
        
    }

    public List<Artista> execute() {
        try (Connection connection = ConnectionManager.getConnection()) {
            return artistaDAO.getAllArtisti(connection);
        } catch (SQLException e) {
            
            e.printStackTrace();
            return null;
        }
    }
}
