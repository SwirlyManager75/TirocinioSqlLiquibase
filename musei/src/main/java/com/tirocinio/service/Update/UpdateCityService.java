package com.tirocinio.service.Update;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateCityService {

    private final CittaDAO cittaDAO;

    public UpdateCityService( ) {
        this.cittaDAO = new CittaDAO();
    }

    public boolean execute(Citta city) throws  ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try{
            ret= cittaDAO.updateCity(connection, city);
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
