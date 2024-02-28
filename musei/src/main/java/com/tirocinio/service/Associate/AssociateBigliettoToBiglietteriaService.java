package com.tirocinio.service.Associate;

import com.tirocinio.dao.impl.BigliettoDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.impl.BiglietteriaDAOimpl;
import com.tirocinio.model.Biglietto;
import com.tirocinio.service.MuseoGenericService;
import com.tirocinio.model.Biglietteria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AssociateBigliettoToBiglietteriaService implements MuseoGenericService {

    private final BigliettoDAOimpl bigliettoDAO;
    private final BiglietteriaDAOimpl biglietteriaDAO;

    public AssociateBigliettoToBiglietteriaService() {
        this.bigliettoDAO = new BigliettoDAOimpl();
        this.biglietteriaDAO = new BiglietteriaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        // Cerco la Biglietteria con il codice fornito
        
    	Connection connection = null;
        boolean ret;
        Map<Object, Object> output = new HashMap<>();


        try {
        	
        	connection = ConnectionManager.getConnection();

            int codBiglietteria=(Integer)input.get("biglietteria");
            int codBiglietto=(Integer)input.get("biglietto");

            Biglietteria biglietteria = biglietteriaDAO.getBiglietteriaById(connection, codBiglietteria);
            Biglietto biglietto = bigliettoDAO.getBigliettoById(connection, codBiglietto);

            if (biglietteria != null && biglietto != null) {
                // Inserisco il Biglietto nel database
                ret=bigliettoDAO.associateWithTicketOffice(connection, biglietto,biglietteria);
                output.put("AssociateBigliettoToBiglietteria", ret);
                 connection.commit();
                return output;

            } else {
                // Biglietteria non trovata
                System.out.println("Biglietteria non trovata con codice: " + codBiglietteria);
                output.put("AssociateBigliettoToBiglietteria", false);

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
