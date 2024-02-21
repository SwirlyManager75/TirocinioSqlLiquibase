package com.tirocinio.service.Update;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietto;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateBigliettoService {

    private final BigliettoDAO bigliettoDAO;

    public UpdateBigliettoService( ) {
        this.bigliettoDAO = new BigliettoDAO();
    }

    public Biglietto execute(Biglietto biglietto) throws  ServiceException {
        Connection connection = ConnectionManager.getConnection();
        Biglietto ret;
        try {
            ret= bigliettoDAO.updateBiglietto(connection, biglietto);
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
