package com.tirocinio.service.Delete;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.OperaDAO;
import com.tirocinio.exceptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteOperaService {

    private final OperaDAO operaDAO;
    

    public DeleteOperaService() {
        this.operaDAO = new OperaDAO();
       
    }

    public boolean execute(int operaId) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try 
        {
            ret=operaDAO.deleteOpera(connection, operaId);           
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
