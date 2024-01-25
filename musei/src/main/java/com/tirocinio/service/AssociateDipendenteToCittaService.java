package com.tirocinio.service;

import com.tirocinio.dao.DipendenteDAO;

import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Dipendente;

import com.tirocinio.model.Citta;

import java.sql.Connection;


public class AssociateDipendenteToCittaService {

    private final DipendenteDAO dipendenteDAO;
    private final CittaDAO cittaDAO;
    private final Connection connection;

    public AssociateDipendenteToCittaService(Connection connection) {
        this.dipendenteDAO = new DipendenteDAO();
        this.cittaDAO = new CittaDAO();
        this.connection = connection;
    }

    public boolean execute(int codDipendente, int codCitta) {
        // Cerco la Città con il codice fornito
        Citta citta = cittaDAO.getCityById(connection, codCitta);
        Dipendente dipendente = dipendenteDAO.getDipendenteById(connection, codDipendente);
        // Cerco il Museo con il codice fornito

        if (citta != null && dipendente !=null) {
            // Inserisco il Dipendente nel database
            return dipendenteDAO.associateWithCity(connection, dipendente,citta);
        } else {
            // Città o Museo non trovato
            System.out.println("Città o Dipedente non trovato con codice: " + codCitta + " o " + codDipendente);
            return false;
        }
    }
}
