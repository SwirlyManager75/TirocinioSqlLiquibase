package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteCityService {

    private final CittaDAO cittaDAO;
   

    public DeleteCityService() {
        this.cittaDAO = new CittaDAO();

    }

    public boolean execute(int cityId) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        try 
        {
            connection.setAutoCommit(false);
            cittaDAO.deleteCity(connection, cityId);           
            connection.commit();
            return true;
        } catch (SQLException e) {
            
            e.printStackTrace();
            connection.rollback();
            
        }
        finally
        {
            connection.close();
        }
        
        return false;
         
    }
}
