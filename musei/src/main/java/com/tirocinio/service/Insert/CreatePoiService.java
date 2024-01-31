package com.tirocinio.service.Insert;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.PoiDAO;
import com.tirocinio.model.Poi;

import java.sql.Connection;
import java.sql.SQLException;

public class CreatePoiService {

    private final PoiDAO poiDAO;
   

    public CreatePoiService( ) {
        this.poiDAO = new PoiDAO();
    }

    public boolean execute(Poi poi) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        try 
        {
            poiDAO.addPoi(connection, poi);           
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
