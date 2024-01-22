package com.tirocinio.service;

import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.model.Biglietto;
import com.tirocinio.model.Biglietteria;

import java.sql.Connection;
import java.sql.Date;

public class AssociateBigliettoToBiglietteriaService {

    private final BigliettoDAO bigliettoDAO;
    private final BiglietteriaDAO biglietteriaDAO;
    private final Connection connection;

    public AssociateBigliettoToBiglietteriaService(Connection connection) {
        this.bigliettoDAO = new BigliettoDAO();
        this.biglietteriaDAO = new BiglietteriaDAO();
        this.connection = connection;
    }

    public boolean execute(float prezzo, String tipo, Date data, int codCliente, int codBiglietteria) {
        // Cerco la Biglietteria con il codice fornito
        Biglietteria biglietteria = biglietteriaDAO.getBiglietteriaById(connection, codBiglietteria);

        if (biglietteria != null) {
            // Creo un nuovo oggetto Biglietto e associalo alla Biglietteria
            Biglietto biglietto = new Biglietto();
            biglietto.setPrezzo(prezzo);
            biglietto.setTipo(Biglietto.TipoBiglietto.valueOf(tipo)); // Assumi che il tipo sia un'enumerazione
            biglietto.setData(data);
            biglietto.setBiglietteria(biglietteria);

            // altri attributi del Biglietto se necessario

            // Inserisco il Biglietto nel database
            return bigliettoDAO.addBiglietto(connection, biglietto);
        } else {
            // Biglietteria non trovata
            System.out.println("Biglietteria non trovata con codice: " + codBiglietteria);
            return false;
        }
    }
}
