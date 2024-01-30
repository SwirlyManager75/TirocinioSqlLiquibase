package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AbbonamentoDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteAbbonamentoService {

    private final AbbonamentoDAO abbonamentoDAO;
    

    public DeleteAbbonamentoService( ) {
        this.abbonamentoDAO = new AbbonamentoDAO();
        
    }

    public boolean execute(int abbonamentoId) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        try 
        {
            connection.setAutoCommit(false);
            abbonamentoDAO.deleteAbbonamento(connection, abbonamentoId);           
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
