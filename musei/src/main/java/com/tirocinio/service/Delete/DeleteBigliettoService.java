package com.tirocinio.service.Delete;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BigliettoDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteBigliettoService {

    private final BigliettoDAO bigliettoDAO;
    

    public DeleteBigliettoService( ) {
        this.bigliettoDAO = new BigliettoDAO();
     
    }

    public boolean execute(int bigliettoId) throws SQLException {
        Connection connection = ConnectionManager.getConnection();

        try 
        {
            bigliettoDAO.deleteBiglietto(connection, bigliettoId);          
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
