package com.tirocinio.service.Delete;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ArtistaDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteArtistaService {

    private final ArtistaDAO artistaDAO;
    
    public DeleteArtistaService( ) {
        this.artistaDAO = new ArtistaDAO();
    }

    public boolean execute(int artistaId) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        try 
        {
            artistaDAO.deleteArtista(connection, artistaId);           
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
