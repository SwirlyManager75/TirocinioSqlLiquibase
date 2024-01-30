package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.OperaDAO;
import com.tirocinio.model.Opera;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateOperaService {

    private final OperaDAO operaDAO;
    

    public CreateOperaService() {
        this.operaDAO = new OperaDAO();
        
    }

    public boolean execute(Opera opera) throws SQLException {
        Connection connection = ConnectionManager.getConnection();

        try 
        {
            connection.setAutoCommit(false);
            operaDAO.addOpera(connection, opera);            
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
