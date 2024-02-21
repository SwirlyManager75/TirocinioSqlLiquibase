package com.tirocinio.service.Insert;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateCityService {

    private final CittaDAO cittaDAO;
   

    public CreateCityService() {
        this.cittaDAO = new CittaDAO();
        
    }

    public int execute(Citta city) throws ServiceException {

        Connection connection = ConnectionManager.getConnection();
        int ret;
        try 
        {             
            ret=cittaDAO.addCity(connection, city);           
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
