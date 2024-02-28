package com.tirocinio.service.Associate;

import com.tirocinio.dao.Interfaces.CittaDAO;
import com.tirocinio.dao.Interfaces.MuseoDAO;
import com.tirocinio.dao.impl.CittaDAOimpl;
import com.tirocinio.dao.impl.MuseoDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.model.Museo;
import com.tirocinio.service.MuseoGenericService;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AssociateMuseoToCittaService implements MuseoGenericService {

    private final MuseoDAO museoDAO;
    private final CittaDAO cittaDAO;

    public AssociateMuseoToCittaService( ) {
        this.museoDAO = new MuseoDAOimpl();
        this.cittaDAO = new CittaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        Map<Object, Object> output = new HashMap<>();


        try {

            int codCitta=(Integer)input.get("citta");
            int codMuseo=(Integer)input.get("museo");

            Citta citta = cittaDAO.getCityById(connection, codCitta);
            Museo museo = museoDAO.getMuseumById(connection, codMuseo);
            if (citta != null && museo != null) {
                // Inserisci il Museo nel database
                ret=museoDAO.associateWithCity(connection, museo,citta);
                output.put("AssociateMuseoToCitta", ret);
                 connection.commit();
                return output;

            } else {
                // Città non trovata
                System.out.println("Città non trovata con codice: " + codCitta);
                output.put("AssociateMuseoToCitta", false);

                return output;
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