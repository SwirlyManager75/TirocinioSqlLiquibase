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

    public boolean execute(Poi poi) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try {
            ret = poiDAO.updatePoi(connection, poi);
            connection.commit();
            return ret;
        } catch (SQLException | DAOException e) 
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
