package com.tirocinio.service.Insert;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.DipendenteDAO;
import com.tirocinio.dao.impl.DipendenteDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Dipendente;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CreateDipendenteService  implements MuseoGenericService {

    private final DipendenteDAO dipendenteDAO;
  

    public CreateDipendenteService( ) {
        this.dipendenteDAO = new DipendenteDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {

        Connection connection = ConnectionManager.getConnection();
        Dipendente ret;
        Map<Object, Object> output=new HashMap<>();

        try 
        {
            ret=dipendenteDAO.addDipendente(connection, (Dipendente)input.get("dipendente")); 
            output.put("CreateDipendente", ret);       
            connection.commit();
            return output;
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
