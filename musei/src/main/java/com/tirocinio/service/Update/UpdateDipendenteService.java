package com.tirocinio.service.Update;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Dipendente;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateDipendenteService {

    private final DipendenteDAO dipendenteDAO;

    public UpdateDipendenteService( ) {
        this.dipendenteDAO = new DipendenteDAO();
    }

    public Dipendente execute(Dipendente dipendente) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        Dipendente ret;
        try {
            ret= dipendenteDAO.updateDipendente(connection, dipendente);
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
