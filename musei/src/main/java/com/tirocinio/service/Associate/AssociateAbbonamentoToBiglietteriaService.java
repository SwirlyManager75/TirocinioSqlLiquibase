package com.tirocinio.service.Associate;

import java.sql.Connection;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Abbonamento_BiglietteriaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;

public class AssociateAbbonamentoToBiglietteriaService {

    private final Abbonamento_BiglietteriaDAO abbonamentoBiglietteriaDAO;
    
    public AssociateAbbonamentoToBiglietteriaService () {
    	this.abbonamentoBiglietteriaDAO = new Abbonamento_BiglietteriaDAO();
    }
    
    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
    	        Connection connection = ConnectionManager.getConnection();

    	try {
			abbonamentoBiglietteriaDAO.addAbbonamentoBiglietteria(connection, 0, 0);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (Exception e) {
			throw new ServiceException("Errore generico durante Associazione di abbonamento alla biglietteria", e);
		}
    	
    	
    	return null;
    }

}
