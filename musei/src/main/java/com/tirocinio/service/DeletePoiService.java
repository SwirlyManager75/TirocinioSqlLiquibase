package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.PoiDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DeletePoiService {

    private final PoiDAO poiDAO;
    

    public DeletePoiService( ) {
        this.poiDAO = new PoiDAO();
       
    }

    public boolean execute(int poiId) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        try 
        {
            connection.setAutoCommit(false);
            poiDAO.deletePoi(connection, poiId);        
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
