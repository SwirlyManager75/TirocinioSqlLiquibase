package com.tirocinio.service.Update;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.OperaDAO;
import com.tirocinio.dao.impl.OperaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Opera;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UpdateOperaService  implements MuseoGenericService{

    private final OperaDAO operaDAO;

    public UpdateOperaService() {
        this.operaDAO = new OperaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        Opera ret;
        Map<Object, Object> output = new HashMap<>();

        try {
            ret = operaDAO.updateOpera(connection, (Opera)input.get("opera"));
            output.put("UpdateOpera", ret);
            connection.commit();
            return output;
            
        } catch (DAOException e) 
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
