package com.tirocinio.service.Update;

import com.tirocinio.exceptions.ServiceException;
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

    public Artista execute(Artista artista) throws  ServiceException {
        Connection connection = ConnectionManager.getConnection();
        Artista ret;
        try {
            ret= artistaDAO.updateArtista(connection, artista);
            connection.commit();
            return ret;
        }catch (DAOException e) 
        {
            
            try {
                connection.rollback();
            } 
            catch (SQLException e1) 
            {
                throw new ServiceException("Rollback non eseguito - errore",e1);
            } 
            throw new ServiceException(e);
            
        }
        catch(Exception e)
        {
            throw new ServiceException("Errore generico in commit",e);
        }
        finally
        {
            try 
            {
                connection.close();
            } catch (SQLException e) 
            {
                
                throw new ServiceException("Errore durante la chiusura della connessione",e);
            }
        }
    }
}
