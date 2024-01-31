package com.tirocinio.service.Insert;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.model.Biglietteria;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateBiglietteriaService {

    private final BiglietteriaDAO biglietteriaDAO;
    

    public CreateBiglietteriaService() {
        this.biglietteriaDAO = new BiglietteriaDAO();
    }

    public boolean execute(Biglietteria biglietteria) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        try 
        {
            biglietteriaDAO.addBiglietteria(connection, biglietteria);            
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
