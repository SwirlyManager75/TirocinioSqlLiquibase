package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateMuseumService {

    private final MuseoDAO museoDAO;
    

    public CreateMuseumService( ) {
        this.museoDAO = new MuseoDAO();
    }

    public boolean execute(Museo museum) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        try 
        {
            connection.setAutoCommit(false);
            museoDAO.addMuseum(connection, museum);
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
