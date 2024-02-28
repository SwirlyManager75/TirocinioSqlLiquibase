package com.tirocinio.service.Search;

import java.sql.Connection;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.impl.Abbonamento_BiglietteriaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.service.MuseoGenericService;

public class SearchBiglietterieForAbbonamentoService implements MuseoGenericService {

     private final Abbonamento_BiglietteriaDAOimpl abbonamento_BiglietteriaDAO;

    public SearchBiglietterieForAbbonamentoService()
    {
        this.abbonamento_BiglietteriaDAO= new Abbonamento_BiglietteriaDAOimpl();
    }

     public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
    	        Connection connection = ConnectionManager.getConnection();

    	try {
            //TODO set the ints from the map
			return abbonamento_BiglietteriaDAO.leggiBiglietteriePerAbbonamento(connection, (Integer)input.get("abbonamento"));
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (Exception e) {
			throw new ServiceException("Errore generico durante Associazione di abbonamento alla biglietteria", e);
		}
    	
    	
    }

}
