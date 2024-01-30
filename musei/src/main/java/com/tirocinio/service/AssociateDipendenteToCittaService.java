package com.tirocinio.service;

import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Dipendente;

import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;


public class AssociateDipendenteToCittaService {

    private final DipendenteDAO dipendenteDAO;
    private final CittaDAO cittaDAO;

    public AssociateDipendenteToCittaService( ) {
        this.dipendenteDAO = new DipendenteDAO();
        this.cittaDAO = new CittaDAO();
    }

    public boolean execute(int codDipendente, int codCitta) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try {
            connection.setAutoCommit(false);

            Citta citta = cittaDAO.getCityById(connection, codCitta);
            Dipendente dipendente = dipendenteDAO.getDipendenteById(connection, codDipendente);
            // Cerco il Museo con il codice fornito

            if (citta != null && dipendente !=null) {
                // Inserisco il Dipendente nel database
                 dipendenteDAO.associateWithCity(connection, dipendente,citta);
                 connection.commit();
                return true;

            } else {
                // Città o Museo non trovato
                System.out.println("Città o Dipedente non trovato con codice: " + codCitta + " o " + codDipendente);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }
        finally
        {
            connection.close();
        }
    }
}
