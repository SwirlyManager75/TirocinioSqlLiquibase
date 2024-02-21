package com.tirocinio.service.Delete;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.exceptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteDipendenteService {

    private final DipendenteDAO dipendenteDAO;
    

    public DeleteDipendenteService() {
        this.dipendenteDAO = new DipendenteDAO();
        
    }

    public boolean execute(int dipendenteId) throws ServiceException {

        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try 
        {
            ret=dipendenteDAO.deleteDipendente(connection, dipendenteId);           
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
