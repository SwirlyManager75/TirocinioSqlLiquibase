package com.tirocinio.service.Update;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.BiglietteriaDAO;
import com.tirocinio.dao.impl.BiglietteriaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietteria;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UpdateBiglietteriaService  implements MuseoGenericService{

    private final BiglietteriaDAO biglietteriaDAO;

    public UpdateBiglietteriaService( ) {
        this.biglietteriaDAO = new BiglietteriaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws  ServiceException {
        Connection connection = ConnectionManager.getConnection();
        Biglietteria ret;
        Map<Object, Object> output = new HashMap<>();


        try {
            ret = biglietteriaDAO.updateBiglietteria(connection, (Biglietteria)input.get("biglietteria"));
            output.put("UpdateBiglietteria", ret);
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
