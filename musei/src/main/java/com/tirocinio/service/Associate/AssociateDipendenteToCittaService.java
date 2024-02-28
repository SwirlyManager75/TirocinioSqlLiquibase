package com.tirocinio.service.Associate;

import com.tirocinio.dao.impl.CittaDAOimpl;
import com.tirocinio.dao.impl.DipendenteDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.model.Dipendente;
import com.tirocinio.service.MuseoGenericService;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class AssociateDipendenteToCittaService implements MuseoGenericService {

    private final DipendenteDAOimpl dipendenteDAO;
    private final CittaDAOimpl cittaDAO;

    public AssociateDipendenteToCittaService( ) {
        this.dipendenteDAO = new DipendenteDAOimpl();
        this.cittaDAO = new CittaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        Map<Object, Object> output = new HashMap<>();


        try {

            int codCitta=(Integer)input.get("citta");
            int codDipendente=(Integer)input.get("dipendente");

            Citta citta = cittaDAO.getCityById(connection, codCitta);
            Dipendente dipendente = dipendenteDAO.getDipendenteById(connection, codDipendente);
            // Cerco il Museo con il codice fornito

            if (citta != null && dipendente !=null) {
                // Inserisco il Dipendente nel database
                ret=dipendenteDAO.associateWithCity(connection, dipendente,citta);
                output.put("AssociateDipendenteToCitta", ret);
                 connection.commit();
                return output;

            } else {
                // Città o Museo non trovato
                System.out.println("Città o Dipedente non trovato con codice: " + codCitta + " o " + codDipendente);
                output.put("AssociateDipendenteToCitta", false);

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
