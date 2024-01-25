package com.tirocinio.service;

import com.tirocinio.dao.OperaDAO;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.model.Opera;
import com.tirocinio.model.Museo;

import java.sql.Connection;

public class AssociateOperaToArtistaService {

    private final OperaDAO operaDAO;
    private final MuseoDAO museoDAO;
    private final Connection connection;

    public AssociateOperaToArtistaService(Connection connection) {
        this.operaDAO = new OperaDAO();
        this.museoDAO = new MuseoDAO();
        this.connection = connection;
    }

    public boolean execute(int codOp,int codMuseo) {
        // Cerca il Museo con il codice fornito
        Museo museo = museoDAO.getMuseumById(connection, codMuseo);
        Opera opera = operaDAO.getOperaById(connection, codOp);

        if (museo != null && opera !=null) {
           
            // Inserisci l'Opera nel database
            return operaDAO.associateWithMuseo(connection, opera,museo);
        } else {
            // Museo non trovato, gestisci la situazione di conseguenza
            System.out.println("Museo non trovato con codice: " + codMuseo);
            return false;
        }
    }
}
