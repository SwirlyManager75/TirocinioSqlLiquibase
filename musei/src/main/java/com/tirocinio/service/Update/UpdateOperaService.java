package com.tirocinio.service.Update;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.OperaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Opera;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateOperaService {

    private final OperaDAO operaDAO;

    public UpdateOperaService() {
        this.operaDAO = new OperaDAO();
    }

    public Opera execute(Opera opera) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        Opera ret;
        try {
            ret = operaDAO.updateOpera(connection, opera);
            connection.commit();
            return ret;
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
