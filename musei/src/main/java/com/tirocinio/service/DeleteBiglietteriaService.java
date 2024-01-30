package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BiglietteriaDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteBiglietteriaService {

    private final BiglietteriaDAO biglietteriaDAO;
    

    public DeleteBiglietteriaService( ) {
        this.biglietteriaDAO = new BiglietteriaDAO();
       
    }

    public boolean execute(int biglietteriaId) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        try 
        {
            connection.setAutoCommit(false);
            biglietteriaDAO.deleteBiglietteria(connection, biglietteriaId);            
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
