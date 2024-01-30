package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.OperaDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteOperaService {

    private final OperaDAO operaDAO;
    

    public DeleteOperaService() {
        this.operaDAO = new OperaDAO();
       
    }

    public boolean execute(int operaId) throws SQLException {
        Connection connection = ConnectionManager.getConnection();

        try 
        {
            connection.setAutoCommit(false);
            operaDAO.deleteOpera(connection, operaId);           
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
