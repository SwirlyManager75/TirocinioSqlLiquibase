package com.tirocinio.service;

import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Artista;
import com.tirocinio.model.Citta;

import java.sql.Connection;

public class AssociateArtistaToCittaService {

    private final ArtistaDAO artistaDAO;
    private final CittaDAO cittaDAO;
    private final Connection connection;

    public AssociateArtistaToCittaService(Connection connection) {
        this.artistaDAO = new ArtistaDAO();
        this.cittaDAO = new CittaDAO();
        this.connection = connection;
    }

    public boolean execute(int codAr, int codCitta) {
        // Cerco la Città con il codice fornito
        Citta citta = cittaDAO.getCityById(connection, codCitta);
        Artista artista = artistaDAO.getArtistaById(connection, codAr);
        if (citta != null && artista != null) {
            
            // Inserisco l'Artista nel database
            return artistaDAO.addArtista(connection, artista);
        } else {
            // Città non trovata
            System.out.println("Città non trovata con codice: " + codCitta);
            return false;
        }
    }
}