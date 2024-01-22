package com.tirocinio.service;

import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.dao.Abbonamento_BiglietteriaDAO;
import com.tirocinio.model.Abbonamento;
import com.tirocinio.model.Biglietteria;
import com.tirocinio.model.Abbonamento_Biglietteria;

import java.sql.Connection;

public class AssociateAbbonamentoToBiglietteriaService {

    private final AbbonamentoDAO abbonamentoDAO;
    private final BiglietteriaDAO biglietteriaDAO;
    private final Abbonamento_BiglietteriaDAO abbonamentoBiglietteriaDAO;
    private final Connection connection;

    public AssociateAbbonamentoToBiglietteriaService(Connection connection) {
        this.abbonamentoDAO = new AbbonamentoDAO();
        this.biglietteriaDAO = new BiglietteriaDAO();
        this.abbonamentoBiglietteriaDAO = new Abbonamento_BiglietteriaDAO();
        this.connection = connection;
    }

    public boolean execute(int codAbbonamento, int codBiglietteria) {
        // Cerco l'Abbonamento con il codice fornito
        Abbonamento abbonamento = abbonamentoDAO.getAbbonamentoById(connection, codAbbonamento);

        // Cerco la Biglietteria con il codice fornito
        Biglietteria biglietteria = biglietteriaDAO.getBiglietteriaById(connection, codBiglietteria);

        if (abbonamento != null && biglietteria != null) {
            // Creo un nuovo oggetto AbbonamentoBiglietteria e associo all'Abbonamento e alla Biglietteria
            Abbonamento_Biglietteria abbonamentoBiglietteria = new Abbonamento_Biglietteria();
            abbonamentoBiglietteria.setCodEA(abbonamento.getCodAb());
            abbonamentoBiglietteria.setCodEB(biglietteria.getCodB());

            // altri attributi di AbbonamentoBiglietteria se necessario

            // Inserisco l'AbbonamentoBiglietteria nel database
            return abbonamentoBiglietteriaDAO.addAbbonamentoBiglietteria(connection, abbonamentoBiglietteria);
        } else {
            // Abbonamento o Biglietteria non trovato
            System.out.println("Abbonamento o Biglietteria non trovato con codice: " + codAbbonamento + " o " + codBiglietteria);
            return false;
        }
    }
}
