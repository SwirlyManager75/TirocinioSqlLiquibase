package com.tirocinio.service.Delete;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.DipendenteDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteDipendenteService {

    private final DipendenteDAO dipendenteDAO;
    

    public DeleteDipendenteService() {
        this.dipendenteDAO = new DipendenteDAO();
        
    }

    public boolean execute(int dipendenteId) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        try 
        {
            dipendenteDAO.deleteDipendente(connection, dipendenteId);           
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
