package com.tirocinio.service.Associate;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.impl.CittaDAOimpl;
import com.tirocinio.dao.Interfaces.CittaDAO;
import com.tirocinio.dao.impl.ArtistaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Artista;
import com.tirocinio.model.Citta;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AssociateArtistaToCittaService implements MuseoGenericService {

    private final ArtistaDAOimpl artistaDAO;
    private final CittaDAO cittaDAO;

    public AssociateArtistaToCittaService( ) {
        this.artistaDAO = new ArtistaDAOimpl();
        this.cittaDAO = new CittaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        Map<Object, Object> output = new HashMap<>();
        // Cerco la Città con il codice fornito
        try {
            boolean ret;
            
            Citta citta = cittaDAO.getCityById(connection, (Integer)input.get("citta"));
            Artista artista = artistaDAO.getArtistaById(connection, (Integer)input.get("artista"));

            if (citta != null && artista != null) {
                
                // Inserisco l'Artista nel database
                ret=artistaDAO.associateWithCity(connection, artista,citta);
                output.put("AssociateArtistaToCitta", ret);
                connection.commit();
                return output;

            } else {
                // Città non trovata
                System.out.println("Città non trovata con codice: " + (Integer)input.get("citta"));

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