package com.tirocinio.service.Associate;

import java.sql.Connection;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.impl.Abbonamento_BiglietteriaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.service.MuseoGenericService;

public class AssociateAbbonamentoToBiglietteriaService implements MuseoGenericService {

    private final Abbonamento_BiglietteriaDAOimpl abbonamentoBiglietteriaDAOimpl;
    
    public AssociateAbbonamentoToBiglietteriaService () {
    	this.abbonamentoBiglietteriaDAOimpl = new Abbonamento_BiglietteriaDAOimpl();
    }
    
    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
    	        Connection connection = ConnectionManager.getConnection();

    	try {
			abbonamentoBiglietteriaDAOimpl.addAbbonamentoBiglietteria(connection, 0, 0);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (Exception e) {
			throw new ServiceException("Errore generico durante Associazione di abbonamento alla biglietteria", e);
		}
    	
    	
    	return null;
    }

}
