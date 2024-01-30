package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateCityService {

    private final CittaDAO cittaDAO;
   

    public CreateCityService() {
        this.cittaDAO = new CittaDAO();
        
    }

    public boolean execute(Citta city) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        try 
        {
            connection.setAutoCommit(false);
             
            cittaDAO.addCity(connection, city);           
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
