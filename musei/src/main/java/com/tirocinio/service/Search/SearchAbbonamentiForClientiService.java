package com.tirocinio.service.Search;

import java.sql.Connection;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Cliente_AbbonamentoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;

public class SearchAbbonamentiForClientiService {

    private final Cliente_AbbonamentoDAO cliente_AbbonamentoDAO;

    public SearchAbbonamentiForClientiService()
    {
        this.cliente_AbbonamentoDAO= new Cliente_AbbonamentoDAO();
    }

     public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
    	        Connection connection = ConnectionManager.getConnection();

    	try {
            //TODO set the ints from the map
			 cliente_AbbonamentoDAO.leggiAbbonamentiPerCliente(connection, 0);
             
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (Exception e) {
			throw new ServiceException("Errore generico durante Associazione di abbonamento alla biglietteria", e);
		}
    	
    	
    	return null;
    }

}
