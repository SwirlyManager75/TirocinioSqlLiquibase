package com.tirocinio.service.Insert;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.ArtistaDAO;
import com.tirocinio.dao.impl.ArtistaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Artista;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CreateArtistaService implements MuseoGenericService {

    private final ArtistaDAO artistaDAO;
    

    public CreateArtistaService() {
        this.artistaDAO = new ArtistaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {

        Connection connection = ConnectionManager.getConnection();
        Artista ret;
        Map<Object, Object> output=new HashMap<>();

        
        try 
        {

            ret=artistaDAO.addArtista(connection, (Artista)input.get("artista"));
            output.put("CreateArtista", ret);
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
