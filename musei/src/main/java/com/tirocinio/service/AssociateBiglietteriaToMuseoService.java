package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.model.Biglietteria;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.SQLException;

public class AssociateBiglietteriaToMuseoService {

    private final BiglietteriaDAO biglietteriaDAO;
    private final MuseoDAO museoDAO;

    public AssociateBiglietteriaToMuseoService() {
        this.biglietteriaDAO = new BiglietteriaDAO();
        this.museoDAO = new MuseoDAO();
    }

    public boolean execute(int codBiglietteria, int codMuseo) throws SQLException {
        // Cerco il Museo con il codice fornito
        Connection connection = ConnectionManager.getConnection();
        try  {
            connection.setAutoCommit(false);

            Museo museo = museoDAO.getMuseumById(connection, codMuseo);
            Biglietteria biglietteria= biglietteriaDAO.getBiglietteriaById(connection, codBiglietteria);

            if (museo != null && biglietteria != null) {
                
                // Inserisco la Biglietteria nel database
                 biglietteriaDAO.associateWithMuseum(connection, biglietteria,museo);
                 connection.commit();
                return true;

            } else {
                // Museo non trovato
                System.out.println("Museo non trovato con codice: " + codMuseo);
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