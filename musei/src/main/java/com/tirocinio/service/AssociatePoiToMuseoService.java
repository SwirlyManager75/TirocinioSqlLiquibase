package com.tirocinio.service;

import com.tirocinio.dao.PoiDAO;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.model.Poi;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.SQLException;

public class AssociatePoiToMuseoService{

    private final PoiDAO poiDAO;
    private final MuseoDAO museoDAO;

    public AssociatePoiToMuseoService( ) {
        this.poiDAO = new PoiDAO();
        this.museoDAO = new MuseoDAO();
    }

    public boolean execute( int museoId, int poiId) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try{
            connection.setAutoCommit(false);

            Museo museo = museoDAO.getMuseumById(connection, museoId);
            Poi poi = poiDAO.getPoiById(connection, poiId);
            if (museo != null && poi != null)
            {
                // Aggiorno il Poi nel database
                 poiDAO.associateWithMuseum(connection,poi,museo);
                 connection.commit();
                return true;

            } else {
                // Museo non trovato
                System.out.println("Museo non trovato con ID: " + museoId);
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