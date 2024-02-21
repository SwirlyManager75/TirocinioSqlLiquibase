package com.tirocinio.service.Delete;

import com.tirocinio.exceptions.ServiceException;
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
