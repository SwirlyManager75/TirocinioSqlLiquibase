package com.tirocinio.service.Insert;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.model.Artista;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateArtistaService {

    private final ArtistaDAO artistaDAO;
    

    public CreateArtistaService() {
        this.artistaDAO = new ArtistaDAO();
    }

    public boolean execute(Artista artista) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        try 
        {
            artistaDAO.addArtista(connection, artista);
            connection.commit();
        } catch (SQLException e) {
            
            e.printStackTrace();
            connection.rollback();
        } 
        return artistaDAO.addArtista(connection, artista);
    }
}
