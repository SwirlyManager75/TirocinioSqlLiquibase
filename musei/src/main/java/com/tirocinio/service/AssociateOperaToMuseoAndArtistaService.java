package com.tirocinio.service;

import com.tirocinio.dao.OperaDAO;
import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.model.Opera;
import com.tirocinio.model.Artista;
import com.tirocinio.model.Museo;

import java.sql.Connection;

public class AssociateOperaToMuseoAndArtistaService {

    private final OperaDAO operaDAO;
    private final MuseoDAO museoDAO;
    private final ArtistaDAO artistaDAO;
    private final Connection connection;

    public AssociateOperaToMuseoAndArtistaService(Connection connection) {
        this.operaDAO = new OperaDAO();
        this.museoDAO = new MuseoDAO();
        this.artistaDAO= new ArtistaDAO();
        this.connection = connection;
    }

    public boolean execute(String nomeOpera, String descrizione, int codMuseo,int codAr) {
        // Cerca il Museo con il codice fornito
        Museo museo = museoDAO.getMuseumById(connection, codMuseo);
        Artista artista = artistaDAO.getArtistaById(connection, codAr);

        if (museo != null) {
            // Crea un nuovo oggetto Opera e associalo al Museo
            Opera opera = new Opera();
            opera.setNome(nomeOpera);
            opera.setDescrizione(descrizione);
            opera.setCodEM(museo.getCodM());
            opera.setCodEAr(artista.getCodAr());

            // Puoi impostare altri attributi dell'Opera se necessario

            // Inserisci l'Opera nel database
            return operaDAO.addOpera(connection, opera);
        } else {
            // Museo non trovato, gestisci la situazione di conseguenza
            System.out.println("Museo non trovato con codice: " + codMuseo);
            return false;
        }
    }
}
