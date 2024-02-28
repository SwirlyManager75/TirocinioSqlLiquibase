package com.tirocinio.service.Associate;

import com.tirocinio.dao.impl.OperaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.impl.MuseoDAOimpl;
import com.tirocinio.model.Opera;
import com.tirocinio.service.MuseoGenericService;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AssociateOperaToMuseoService implements MuseoGenericService {

    private final OperaDAOimpl operaDAO;
    private final MuseoDAOimpl museoDAO;

    public AssociateOperaToMuseoService( ) {
        this.operaDAO = new OperaDAOimpl();
        this.museoDAO = new MuseoDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        Map<Object, Object> output = new HashMap<>();


        try  {

            int codMuseo=(Integer)input.get("museo");
            int codOpera= (Integer)input.get("opera");

            Museo museo = museoDAO.getMuseumById(connection, codMuseo);
            Opera opera = operaDAO.getOperaById(connection, codOpera);

            if (museo != null && opera !=null) {
               
                // Inserisci l'Opera nel database
                ret=operaDAO.associateWithMuseo(connection, opera,museo);
                output.put("AssociateOperaToMuseo", ret);
                 connection.commit();
                return output;

            } else {
                // Museo non trovato, gestisci la situazione di conseguenza
                System.out.println("Museo non trovato con codice: " + codMuseo);
                output.put("AssociateOperaToMuseo", false);

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
