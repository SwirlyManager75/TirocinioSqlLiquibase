package com.tirocinio.service.Insert;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Abbonamento;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateAbbonamentoService {

    private final AbbonamentoDAO abbonamentoDAO;
    

    public CreateAbbonamentoService() {
        this.abbonamentoDAO = new AbbonamentoDAO();
        
    }

    public boolean execute(Abbonamento abbonamento) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try 
        {
            ret=abbonamentoDAO.addAbbonamento(connection,abbonamento);
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
