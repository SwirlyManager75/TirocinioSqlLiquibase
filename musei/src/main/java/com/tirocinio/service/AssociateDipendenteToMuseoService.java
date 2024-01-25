package com.tirocinio.service;

import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.dao.MuseoDAO;

import com.tirocinio.model.Dipendente;
import com.tirocinio.model.Museo;


import java.sql.Connection;


public class AssociateDipendenteToMuseoService {

    private final DipendenteDAO dipendenteDAO;
    private final MuseoDAO museoDAO;
  
    private final Connection connection;

    public AssociateDipendenteToMuseoService(Connection connection) {
        this.dipendenteDAO = new DipendenteDAO();
        this.museoDAO = new MuseoDAO();
        this.connection = connection;
    }

    public boolean execute(int codDipendente, int codMuseo) {
       
        // Cerco il Museo con il codice fornito
        Museo museo = museoDAO.getMuseumById(connection, codMuseo);
        Dipendente dipendente = dipendenteDAO.getDipendenteById(connection, codDipendente);

        if ( museo != null && dipendente != null) {

            // Inserisco il Dipendente nel database
            return dipendenteDAO.associateWithMuseum(connection, dipendente,museo);
        } else {
            // Città o Museo non trovato
            System.out.println(" Dipendete o Museo non trovato con codice: " + codDipendente + " o " + codMuseo);
            return false;
        }
    }
}
