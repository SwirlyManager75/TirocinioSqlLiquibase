package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Artista;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;

public class AssociateArtistaToCittaService {

    private final ArtistaDAO artistaDAO;
    private final CittaDAO cittaDAO;

    public AssociateArtistaToCittaService( ) {
        this.artistaDAO = new ArtistaDAO();
        this.cittaDAO = new CittaDAO();
    }

    public boolean execute(int codAr, int codCitta) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        // Cerco la Città con il codice fornito
        try {
            connection.setAutoCommit(false);

            Citta citta = cittaDAO.getCityById(connection, codCitta);
            Artista artista = artistaDAO.getArtistaById(connection, codAr);
            if (citta != null && artista != null) {
                
                // Inserisco l'Artista nel database
                artistaDAO.addArtista(connection, artista);
                connection.commit();
                return true;

            } else {
                // Città non trovata
                System.out.println("Città non trovata con codice: " + codCitta);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }
    }
}