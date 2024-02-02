package com.tirocinio.service.Delete;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.exceptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteCityService {

    private final CittaDAO cittaDAO;
   

    public DeleteCityService() {
        this.cittaDAO = new CittaDAO();

    }

    public boolean execute(int cityId) throws ServiceException {

        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try 
        {
            ret=cittaDAO.deleteCity(connection, cityId);           
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
