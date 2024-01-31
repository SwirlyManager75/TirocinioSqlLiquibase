package com.tirocinio.service.Associate;

import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Museo;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;

public class AssociateMuseoToCittaService {

    private final MuseoDAO museoDAO;
    private final CittaDAO cittaDAO;

    public AssociateMuseoToCittaService( ) {
        this.museoDAO = new MuseoDAO();
        this.cittaDAO = new CittaDAO();
    }

    public boolean execute(int codMuseo, int codCitta) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try {

            Citta citta = cittaDAO.getCityById(connection, codCitta);
            Museo museo = museoDAO.getMuseumById(connection, codMuseo);
            if (citta != null && museo != null) {
                // Inserisci il Museo nel database
                 museoDAO.associateWithCity(connection, museo,citta);
                 connection.commit();
                return true;

            } else {
                // Città non trovata
                System.out.println("Città non trovata con codice: " + codCitta);
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