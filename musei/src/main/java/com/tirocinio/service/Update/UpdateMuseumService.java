package com.tirocinio.service.Update;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateMuseumService {

    private final MuseoDAO museoDAO;

    public UpdateMuseumService() {
        this.museoDAO = new MuseoDAO();
    }

    public boolean execute(Museo museum) throws  ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try {
            ret = museoDAO.updateMuseum(connection, museum);
            connection.commit();
            return ret;
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
