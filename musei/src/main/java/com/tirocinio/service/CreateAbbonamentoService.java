package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.model.Abbonamento;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateAbbonamentoService {

    private final AbbonamentoDAO abbonamentoDAO;
    

    public CreateAbbonamentoService() {
        this.abbonamentoDAO = new AbbonamentoDAO();
        
    }

    public boolean execute(Abbonamento abbonamento) throws SQLException {
        Connection connection = ConnectionManager.getConnection();

        try 
        {
            connection.setAutoCommit(false);
            abbonamentoDAO.addAbbonamento(connection,abbonamento);
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
