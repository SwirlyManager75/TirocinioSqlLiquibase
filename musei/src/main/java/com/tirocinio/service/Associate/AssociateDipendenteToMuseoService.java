package com.tirocinio.service.Associate;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.impl.DipendenteDAOimpl;
import com.tirocinio.dao.impl.MuseoDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Dipendente;
import com.tirocinio.model.Museo;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class AssociateDipendenteToMuseoService implements MuseoGenericService {

    private final DipendenteDAOimpl dipendenteDAO;
    private final MuseoDAOimpl museoDAO;
  

    public AssociateDipendenteToMuseoService( ) {
        this.dipendenteDAO = new DipendenteDAOimpl();
        this.museoDAO = new MuseoDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        Map<Object, Object> output = new HashMap<>();


        try 
        {

            int codMuseo=(Integer)input.get("museo");
            int codDipendente=(Integer)input.get("dipendente");

            Museo museo = museoDAO.getMuseumById(connection, codMuseo);
            Dipendente dipendente = dipendenteDAO.getDipendenteById(connection, codDipendente);

            if ( museo != null && dipendente != null) {

                // Inserisco il Dipendente nel database
                ret=dipendenteDAO.associateWithMuseum(connection, dipendente,museo);
                output.put("AssociateDipendenteToMuseo", ret);
                 connection.commit();
                return output;

            } else {
                // Città o Museo non trovato
                System.out.println(" Dipendete o Museo non trovato con codice: " + codDipendente + " o " + codMuseo);
                output.put("AssociateDipendenteToMuseo", false);

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
