package com.tirocinio.service.Delete;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.service.MuseoGenericService;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.ArtistaDAO;
import com.tirocinio.dao.impl.ArtistaDAOimpl;
import com.tirocinio.exceptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DeleteArtistaService implements MuseoGenericService {

    private final ArtistaDAO artistaDAO;
    
    public DeleteArtistaService( ) {
        this.artistaDAO = new ArtistaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {

        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        Map<Object, Object> output = new HashMap<>();


        try 
        {
            int codArtista= (Integer)input.get("artista");
            ret=artistaDAO.deleteArtista(connection, codArtista); 
            output.put("DeleteArtista", ret);          
            connection.commit();

            return output;
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
