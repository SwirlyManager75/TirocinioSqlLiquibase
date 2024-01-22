package com.tirocinio.service;

import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Artista;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.Date;

public class AssociateArtistaToCittaService {

    private final ArtistaDAO artistaDAO;
    private final CittaDAO cittaDAO;
    private final Connection connection;

    public AssociateArtistaToCittaService(Connection connection) {
        this.artistaDAO = new ArtistaDAO();
        this.cittaDAO = new CittaDAO();
        this.connection = connection;
    }

    public boolean execute(String nome, String cognome, Date dataNascita, boolean inVita, int codCitta) {
        // Cerco la Città con il codice fornito
        Citta citta = cittaDAO.getCityById(connection, codCitta);

        if (citta != null) {
            // Creo un nuovo oggetto Artista e associalo alla Città
            Artista artista = new Artista();
            artista.setNome(nome);
            artista.setCognome(cognome);
            artista.setDataNascita(dataNascita);
            artista.setInVita(inVita);
            artista.setCodECi(citta.getCodCi());

            // altri attributi dell'Artista se necessario

            // Inserisco l'Artista nel database
            return artistaDAO.addArtista(connection, artista);
        } else {
            // Città non trovata
            System.out.println("Città non trovata con codice: " + codCitta);
            return false;
        }
    }
}