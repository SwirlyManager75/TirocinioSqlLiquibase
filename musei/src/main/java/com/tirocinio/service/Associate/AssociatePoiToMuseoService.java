package com.tirocinio.service.Associate;

import com.tirocinio.dao.Interfaces.MuseoDAO;
import com.tirocinio.dao.Interfaces.PoiDAO;
import com.tirocinio.dao.impl.MuseoDAOimpl;
import com.tirocinio.dao.impl.PoiDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.model.Poi;
import com.tirocinio.service.MuseoGenericService;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AssociatePoiToMuseoService implements MuseoGenericService{

    private final PoiDAO poiDAO;
    private final MuseoDAO museoDAO;

    public AssociatePoiToMuseoService( ) {
        this.poiDAO = new PoiDAOimpl();
        this.museoDAO = new MuseoDAOimpl();
    }

    public Map<Object, Object> execute( Map<Object, Object> input) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        Map<Object, Object> output = new HashMap<>();


        try{

            int codMuseo=(Integer)input.get("museo");
            int codPoi=(Integer)input.get("poi");

            Museo museo = museoDAO.getMuseumById(connection, codMuseo);
            Poi poi = poiDAO.getPoiById(connection, codPoi);
            if (museo != null && poi != null)
            {
                // Aggiorno il Poi nel database
                ret=poiDAO.associateWithMuseum(connection,poi,museo);
                output.put("AssociatePoiToMuseo", ret);

                connection.commit();
                return output;

            } else {
                // Museo non trovato
                System.out.println("Museo non trovato con ID: " + codMuseo);
                output.put("AssociatePoiToMuseo", false);

                return output;
            }
        }catch (DAOException e) 
        {
            
            try {
                connection.rollback();
            } 
            catch (SQLException e1) 
            {
                throw new ServiceException("Rollback non eseguito - errore",e1);
            } 
            throw new ServiceException(e);
            
        }
        catch(Exception e)
        {
            throw new ServiceException("Errore generico in commit",e);
        }
        finally
        {
            try 
            {
                connection.close();
            } catch (SQLException e) 
            {
                
                throw new ServiceException("Errore durante la chiusura della connessione",e);
            }
        }
    }
}