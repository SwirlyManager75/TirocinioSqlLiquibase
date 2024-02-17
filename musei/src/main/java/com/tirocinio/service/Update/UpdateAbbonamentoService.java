package com.tirocinio.service.Update;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Abbonamento;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateAbbonamentoService {

    private final AbbonamentoDAO abbonamentoDAO;

    public UpdateAbbonamentoService( ) {
        this.abbonamentoDAO = new AbbonamentoDAO();
       
    }

    public Abbonamento execute(Abbonamento abbonamento) throws  ServiceException {
        Connection connection = ConnectionManager.getConnection();
        Abbonamento ret;
        try{
            ret= abbonamentoDAO.updateAbbonamento(connection, abbonamento);
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
