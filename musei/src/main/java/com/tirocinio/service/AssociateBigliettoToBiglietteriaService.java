package com.tirocinio.service;

import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.model.Biglietto;
import com.tirocinio.model.Biglietteria;

import java.sql.Connection;

public class AssociateBigliettoToBiglietteriaService {

    private final BigliettoDAO bigliettoDAO;
    private final BiglietteriaDAO biglietteriaDAO;
    private final Connection connection;

    public AssociateBigliettoToBiglietteriaService(Connection connection) {
        this.bigliettoDAO = new BigliettoDAO();
        this.biglietteriaDAO = new BiglietteriaDAO();
        this.connection = connection;
    }

    public boolean execute(int codBiglietto, int codBiglietteria) {
        // Cerco la Biglietteria con il codice fornito
        Biglietteria biglietteria = biglietteriaDAO.getBiglietteriaById(connection, codBiglietteria);
        Biglietto biglietto = bigliettoDAO.getBigliettoById(connection, codBiglietto);

        if (biglietteria != null && biglietto != null) {
            // Inserisco il Biglietto nel database
            return bigliettoDAO.associateWithTicketOffice(connection, biglietto,biglietteria);
        } else {
            // Biglietteria non trovata
            System.out.println("Biglietteria non trovata con codice: " + codBiglietteria);
            return false;
        }
    }
}
