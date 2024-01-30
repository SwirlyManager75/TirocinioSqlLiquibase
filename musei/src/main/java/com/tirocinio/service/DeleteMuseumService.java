package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.MuseoDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteMuseumService {

    private final MuseoDAO museoDAO;
    

    public DeleteMuseumService() {
        this.museoDAO = new MuseoDAO();
        ;
    }

    public boolean execute(int museumId) throws SQLException {
        Connection connection = ConnectionManager.getConnection();

        try 
        {
            connection.setAutoCommit(false);
            museoDAO.deleteMuseum(connection, museumId);            
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
