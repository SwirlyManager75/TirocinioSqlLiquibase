package com.tirocinio.service;

import com.tirocinio.dao.Abbonamento_BiglietteriaDAO;
import com.tirocinio.model.Abbonamento;
import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.model.Abbonamento_Biglietteria;
import com.tirocinio.model.Biglietteria;

import java.sql.Connection;

public class UpdateAbbonamento_BiglietteriaService {

    private final Abbonamento_BiglietteriaDAO abbonamentoBiglietteriaDAO;
    private final AbbonamentoDAO abbonamentoDAO;
    private final BiglietteriaDAO biglietteriaDAO;
    private final Connection connection;

    public UpdateAbbonamento_BiglietteriaService(Connection connection) {
        this.abbonamentoBiglietteriaDAO = new Abbonamento_BiglietteriaDAO();
        this.abbonamentoDAO= new AbbonamentoDAO();
        this.biglietteriaDAO= new BiglietteriaDAO();
        this.connection = connection;
    }

    public boolean execute(int codAbbonamentoBiglietteria, int nuovoCodAbbonamento, int nuovoCodBiglietteria) {
        // Cerca l'associazione tra abbonamento e biglietteria con il codice fornito
        Abbonamento_Biglietteria abbonamentoBiglietteria = abbonamentoBiglietteriaDAO.getAbbonamentoBiglietteriaById(connection, codAbbonamentoBiglietteria);

        if (abbonamentoBiglietteria != null) {
            // Cerca l'abbonamento con il nuovo codice
            Abbonamento nuovoAbbonamento = abbonamentoDAO.getAbbonamentoById(connection, nuovoCodAbbonamento);

            // Cerca la biglietteria con il nuovo codice
            Biglietteria nuovaBiglietteria = biglietteriaDAO.getBiglietteriaById(connection, nuovoCodBiglietteria);

            if (nuovoAbbonamento != null && nuovaBiglietteria != null) {
                // Aggiorna le chiavi esterne nella tabella Abbonamento_Biglietteria
                abbonamentoBiglietteria.setCodEA(nuovoAbbonamento.getCodAb());
                abbonamentoBiglietteria.setCodEB(nuovaBiglietteria.getCodB());

                // Eseguo l'aggiornamento nel database
                return abbonamentoBiglietteriaDAO.updateAbbonamentoBiglietteria(connection, abbonamentoBiglietteria);
            } else {
                // Nuovo abbonamento o nuova biglietteria non trovato
                System.out.println("Nuovo abbonamento o nuova biglietteria non trovato con codice: " + nuovoCodAbbonamento + " o " + nuovoCodBiglietteria);
                return false;
            }
        } else {
            // Associazione non trovata
            System.out.println("Associazione tra abbonamento e biglietteria non trovata con codice: " + codAbbonamentoBiglietteria);
            return false;
        }
    }
}
