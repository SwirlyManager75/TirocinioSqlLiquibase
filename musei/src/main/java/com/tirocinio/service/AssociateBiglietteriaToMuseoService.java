package com.tirocinio.service;

import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.model.Biglietteria;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.Time;

public class AssociateBiglietteriaToMuseoService {

    private final BiglietteriaDAO biglietteriaDAO;
    private final MuseoDAO museoDAO;
    private final Connection connection;

    public AssociateBiglietteriaToMuseoService(Connection connection) {
        this.biglietteriaDAO = new BiglietteriaDAO();
        this.museoDAO = new MuseoDAO();
        this.connection = connection;
    }

    public boolean execute(Time oraApertura, Time oraChiusura, String modalitaPagamento, int codMuseo) {
        // Cerco il Museo con il codice fornito
        Museo museo = museoDAO.getMuseumById(connection, codMuseo);

        if (museo != null) {
            // Creo un nuovo oggetto Biglietteria e associalo al Museo
            Biglietteria biglietteria = new Biglietteria();
            biglietteria.setOraApertura(oraApertura);
            biglietteria.setOraChiusura(oraChiusura);
            biglietteria.setModPag(Biglietteria.ModalitaPagamento.valueOf(modalitaPagamento));
            biglietteria.setCodEM(museo.getCodM());

            // attributi della Biglietteria se necessario

            // Inserisco la Biglietteria nel database
            return biglietteriaDAO.addBiglietteria(connection, biglietteria);
        } else {
            // Museo non trovato
            System.out.println("Museo non trovato con codice: " + codMuseo);
            return false;
        }
    }
}