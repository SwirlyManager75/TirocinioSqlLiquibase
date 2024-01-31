package com.tirocinio.service.Insert;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.model.Biglietto;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateBigliettoService {

    private final BigliettoDAO bigliettoDAO;

    public CreateBigliettoService( ) {
        this.bigliettoDAO = new BigliettoDAO();
        
    }

    public boolean execute(Biglietto biglietto) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        try 
        {
            bigliettoDAO.addBiglietto(connection, biglietto);            
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
