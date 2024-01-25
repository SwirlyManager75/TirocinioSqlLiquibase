package com.tirocinio.service;

import com.tirocinio.dao.OperaDAO;
import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.model.Opera;
import com.tirocinio.model.Artista;

import java.sql.Connection;

public class AssociateOperaToMuseoService {

    private final OperaDAO operaDAO;
    private final ArtistaDAO artistaDAO;
    private final Connection connection;

    public AssociateOperaToMuseoService(Connection connection) {
        this.operaDAO = new OperaDAO();
        this.artistaDAO= new ArtistaDAO();
        this.connection = connection;
    }

    public boolean execute(int codOp,int codAr) {
        // Cerca il Museo con il codice fornito
        Opera opera = operaDAO.getOperaById(connection, codOp);
        Artista artista = artistaDAO.getArtistaById(connection, codAr);

        if (artista != null && opera != null) {

            // Inserisci l'Opera nel database
            return operaDAO.associateWithArtist(connection, opera,artista);
        } else {
            // Museo non trovato, gestisci la situazione di conseguenza
            System.out.println("Artista o Opera non trovato con codice: " + codAr+" o "+codOp);
            return false;
        }
    }
}
