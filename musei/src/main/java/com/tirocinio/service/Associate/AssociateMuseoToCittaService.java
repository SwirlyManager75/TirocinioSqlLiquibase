package com.tirocinio.service.Associate;

import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Museo;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;

public class AssociateMuseoToCittaService {

    private final MuseoDAO museoDAO;
    private final CittaDAO cittaDAO;

    public AssociateMuseoToCittaService( ) {
        this.museoDAO = new MuseoDAO();
        this.cittaDAO = new CittaDAO();
    }

    public boolean execute(int codMuseo, int codCitta) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try {

            Citta citta = cittaDAO.getCityById(connection, codCitta);
            Museo museo = museoDAO.getMuseumById(connection, codMuseo);
            if (citta != null && museo != null) {
                // Inserisci il Museo nel database
                ret=museoDAO.associateWithCity(connection, museo,citta);
                 connection.commit();
                return ret;

            } else {
                // Città non trovata
                System.out.println("Città non trovata con codice: " + codCitta);
                return false;
            }
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