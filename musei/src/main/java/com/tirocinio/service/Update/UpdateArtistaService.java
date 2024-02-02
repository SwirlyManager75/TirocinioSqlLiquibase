package com.tirocinio.service.Update;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Artista;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateArtistaService {

    private final ArtistaDAO artistaDAO;

    public UpdateArtistaService( ) {
        this.artistaDAO = new ArtistaDAO();
    }

    public boolean execute(Artista artista) throws  ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try {
            ret= artistaDAO.updateArtista(connection, artista);
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
