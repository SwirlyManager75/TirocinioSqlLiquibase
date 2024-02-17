package com.tirocinio.service.Update;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.PoiDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Poi;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdatePoiService {

    private final PoiDAO poiDAO;

    public UpdatePoiService() {
        this.poiDAO = new PoiDAO();
    }

    public Poi execute(Poi poi) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        Poi ret;
        try {
            ret = poiDAO.updatePoi(connection, poi);
            connection.commit();
            return ret;
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
