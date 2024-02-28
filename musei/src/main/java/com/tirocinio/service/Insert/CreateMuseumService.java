package com.tirocinio.service.Insert;

import com.tirocinio.exceptions.ServiceException;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.MuseoDAO;
import com.tirocinio.dao.impl.MuseoDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Museo;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CreateMuseumService  implements MuseoGenericService{

    private final MuseoDAO museoDAO;
    

    public CreateMuseumService( ) {
        this.museoDAO = new MuseoDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {

        Connection connection = ConnectionManager.getConnection();
        Museo ret;
        Map<Object, Object> output=new HashMap<>();

        try 
        {
            ret=museoDAO.addMuseum(connection, (Museo)input.get("museo"));
            output.put("CreateMuseo", ret);
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
