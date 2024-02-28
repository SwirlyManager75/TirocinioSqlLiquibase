package com.tirocinio.service.Associate;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.impl.BiglietteriaDAOimpl;
import com.tirocinio.dao.impl.MuseoDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietteria;
import com.tirocinio.model.Museo;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AssociateBiglietteriaToMuseoService implements MuseoGenericService {

    private final BiglietteriaDAOimpl biglietteriaDAO;
    private final MuseoDAOimpl museoDAO;

    public AssociateBiglietteriaToMuseoService() {
        this.biglietteriaDAO = new BiglietteriaDAOimpl();
        this.museoDAO = new MuseoDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws  ServiceException {
        // Cerco il Museo con il codice fornito
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        Map<Object, Object> output = new HashMap<>();

        try  {

            int codMuseo= (Integer)input.get("museo");
            int codBiglietteria=(Integer)input.get("museo");
            
            Museo museo = museoDAO.getMuseumById(connection, codMuseo);
            Biglietteria biglietteria= biglietteriaDAO.getBiglietteriaById(connection, codBiglietteria);

            if (museo != null && biglietteria != null) {
                
                // Inserisco la Biglietteria nel database
                ret=biglietteriaDAO.associateWithMuseum(connection, biglietteria,museo);
                output.put("AssociateBiglietteriaToMuseo", ret);
                 connection.commit();
                return output;

            } else {
                // Museo non trovato
                System.out.println("Museo non trovato con codice: " + codMuseo);
                output.put("AssociateBiglietteriaToMuseo", false);

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