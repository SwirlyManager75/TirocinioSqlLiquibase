package com.tirocinio.service.Update;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.BigliettoDAO;
import com.tirocinio.dao.impl.BigliettoDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietto;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UpdateBigliettoService  implements MuseoGenericService{

    private final BigliettoDAO bigliettoDAO;

    public UpdateBigliettoService( ) {
        this.bigliettoDAO = new BigliettoDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws  ServiceException {
        Connection connection = ConnectionManager.getConnection();
        Biglietto ret;
        Map<Object, Object> output = new HashMap<>();

        try {
            ret= bigliettoDAO.updateBiglietto(connection, (Biglietto)input.get("biglietto"));
            output.put("UpdateBiglietto", ret);
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
