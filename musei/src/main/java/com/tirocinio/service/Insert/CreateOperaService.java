package com.tirocinio.service.Insert;


import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.OperaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Opera;

import java.sql.Connection;
import java.sql.SQLException;
 

public class CreateOperaService {

    private final OperaDAO operaDAO;
    

    public CreateOperaService() {
        this.operaDAO = new OperaDAO();
        
    }

    public int execute(Opera opera) throws  ServiceException {
        Connection connection = ConnectionManager.getConnection();
        int ret;
        try 
        {
            ret=operaDAO.addOpera(connection, opera);            
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
