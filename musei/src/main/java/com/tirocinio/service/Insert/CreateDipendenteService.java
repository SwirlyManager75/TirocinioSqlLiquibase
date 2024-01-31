package com.tirocinio.service.Insert;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.model.Dipendente;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateDipendenteService {

    private final DipendenteDAO dipendenteDAO;
  

    public CreateDipendenteService( ) {
        this.dipendenteDAO = new DipendenteDAO();
    }

    public boolean execute(Dipendente dipendente) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        try 
        {
            dipendenteDAO.addDipendente(connection, dipendente);          
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
