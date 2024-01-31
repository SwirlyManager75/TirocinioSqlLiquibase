package com.tirocinio.service.Associate;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.dao.MuseoDAO;

import com.tirocinio.model.Dipendente;
import com.tirocinio.model.Museo;


import java.sql.Connection;
import java.sql.SQLException;


public class AssociateDipendenteToMuseoService {

    private final DipendenteDAO dipendenteDAO;
    private final MuseoDAO museoDAO;
  

    public AssociateDipendenteToMuseoService( ) {
        this.dipendenteDAO = new DipendenteDAO();
        this.museoDAO = new MuseoDAO();
    }

    public boolean execute(int codDipendente, int codMuseo) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try 
        {

            Museo museo = museoDAO.getMuseumById(connection, codMuseo);
            Dipendente dipendente = dipendenteDAO.getDipendenteById(connection, codDipendente);

            if ( museo != null && dipendente != null) {

                // Inserisco il Dipendente nel database
                 dipendenteDAO.associateWithMuseum(connection, dipendente,museo);
                 connection.commit();
                return true;

            } else {
                // Città o Museo non trovato
                System.out.println(" Dipendete o Museo non trovato con codice: " + codDipendente + " o " + codMuseo);
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
