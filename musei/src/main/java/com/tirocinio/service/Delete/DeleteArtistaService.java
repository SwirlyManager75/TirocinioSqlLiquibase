package com.tirocinio.service.Delete;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.exceptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteArtistaService {

    private final ArtistaDAO artistaDAO;
    
    public DeleteArtistaService( ) {
        this.artistaDAO = new ArtistaDAO();
    }

    public boolean execute(int artistaId) throws ServiceException {

        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try 
        {
            ret=artistaDAO.deleteArtista(connection, artistaId);           
            connection.commit();
            return ret;
        }catch (SQLException | DAOException e) 
        {
            
            try {
                connection.rollback();
            } 
            catch (SQLException e1) 
            {
                e1.printStackTrace();
            } 
            throw new ServiceException("In execute - DAOException ");
            
        }
        finally
        {
            try 
            {
                connection.close();
            } catch (SQLException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
         
    }
}
