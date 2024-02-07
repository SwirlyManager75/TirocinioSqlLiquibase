package com.tirocinio.service.Associate;

import java.util.Map;

import com.tirocinio.dao.Abbonamento_BiglietteriaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;

public class AssociateAbbonamentoToBiglietteriaService {

    private final Abbonamento_BiglietteriaDAO abbonamentoBiglietteriaDAO;
    
    public AssociateAbbonamentoToBiglietteriaService () {
    	this.abbonamentoBiglietteriaDAO = new Abbonamento_BiglietteriaDAO();
    }
    
    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
    	
    	try {
			abbonamentoBiglietteriaDAO.addAbbonamentoBiglietteria(null, 0, 0);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (Exception e) {
			throw new ServiceException("il mio messaggio custom", e);
		}
    	
    	
    	return null;
    }

}
