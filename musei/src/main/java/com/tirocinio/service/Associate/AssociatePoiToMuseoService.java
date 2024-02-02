package com.tirocinio.service.Associate;

import com.tirocinio.dao.PoiDAO;
import com.tirocinio.exceptions.DAOException;
import com.google.protobuf.ServiceException;
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

    public boolean execute( int museoId, int poiId) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try{

            Museo museo = museoDAO.getMuseumById(connection, museoId);
            Poi poi = poiDAO.getPoiById(connection, poiId);
            if (museo != null && poi != null)
            {
                // Aggiorno il Poi nel database
                ret=poiDAO.associateWithMuseum(connection,poi,museo);
                 connection.commit();
                return ret;

            } else {
                // Museo non trovato
                System.out.println("Museo non trovato con ID: " + museoId);
                return false;
            }
        }catch (SQLException | DAOException e) 
        {
            
            try {
                connection.rollback();
            } 
            catch (SQLException e1) 
            {
                e1.printStackTrace();
            } 
            throw new ServiceException("In execute - DAOException ");
            
        }
        finally
        {
            try 
            {
                connection.close();
            } catch (SQLException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}