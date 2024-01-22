package com.tirocinio.service;

import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Dipendente;
import com.tirocinio.model.Museo;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.Date;

public class AssociateDipendenteToMuseoAndCittaService {

    private final DipendenteDAO dipendenteDAO;
    private final MuseoDAO museoDAO;
    private final CittaDAO cittaDAO;
    private final Connection connection;

    public AssociateDipendenteToMuseoAndCittaService(Connection connection) {
        this.dipendenteDAO = new DipendenteDAO();
        this.museoDAO = new MuseoDAO();
        this.cittaDAO = new CittaDAO();
        this.connection = connection;
    }

    public boolean execute(String nome, Date dataNascita, String codFiscale, String cellulare, int codCitta, int codMuseo) {
        // Cerco la Città con il codice fornito
        Citta citta = cittaDAO.getCityById(connection, codCitta);

        // Cerco il Museo con il codice fornito
        Museo museo = museoDAO.getMuseumById(connection, codMuseo);

        if (citta != null && museo != null) {
            // Creo un nuovo oggetto Dipendente e associalo alla Città e al Museo
            Dipendente dipendente = new Dipendente();
            dipendente.setNome(nome);
            dipendente.setDataNascita(dataNascita);
            dipendente.setCodiceFiscale(codFiscale);
            dipendente.setCellulare(cellulare);
            dipendente.setCodECi(citta.getCodCi());
            dipendente.setCodEM(museo.getCodM());

            // altri attributi del Dipendente se necessario

            // Inserisco il Dipendente nel database
            return dipendenteDAO.addDipendente(connection, dipendente);
        } else {
            // Città o Museo non trovato
            System.out.println("Città o Museo non trovato con codice: " + codCitta + " o " + codMuseo);
            return false;
        }
    }
}
