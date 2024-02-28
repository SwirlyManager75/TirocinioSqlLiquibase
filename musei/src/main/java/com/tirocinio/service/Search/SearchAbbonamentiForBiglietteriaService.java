package com.tirocinio.service.Search;

import java.sql.Connection;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.impl.Abbonamento_BiglietteriaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.service.MuseoGenericService;

public class SearchAbbonamentiForBiglietteriaService  implements MuseoGenericService{

     private final Abbonamento_BiglietteriaDAOimpl abbonamento_BiglietteriaDAO;

    public SearchAbbonamentiForBiglietteriaService()
    {
        this.abbonamento_BiglietteriaDAO= new Abbonamento_BiglietteriaDAOimpl();
    }

     public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
    	        Connection connection = ConnectionManager.getConnection();

    	try {
            
			return abbonamento_BiglietteriaDAO.leggiAbbonamentiPerBiglietteria(connection, (Integer)input.get("biglietteria"));
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (Exception e) {
			throw new ServiceException("Errore generico durante Associazione di abbonamento alla biglietteria", e);
		}
    	
    	
    }

}
