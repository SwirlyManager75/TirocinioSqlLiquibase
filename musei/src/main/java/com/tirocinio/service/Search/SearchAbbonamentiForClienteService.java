package com.tirocinio.service.Search;

import java.sql.Connection;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.Cliente_AbbonamentoDAO;
import com.tirocinio.dao.impl.Cliente_AbbonamentoDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.service.MuseoGenericService;

public class SearchAbbonamentiForClienteService  implements MuseoGenericService{

    private final Cliente_AbbonamentoDAO cliente_AbbonamentoDAO;

    public SearchAbbonamentiForClienteService()
    {
        this.cliente_AbbonamentoDAO= new Cliente_AbbonamentoDAOimpl();
    }

     public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
    	        Connection connection = ConnectionManager.getConnection();

    	try {
            //TODO set the ints from the map
			return cliente_AbbonamentoDAO.leggiAbbonamentiPerCliente(connection, (Integer)input.get("cliente"));
             
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (Exception e) {
			throw new ServiceException("Errore generico durante Associazione di abbonamento alla biglietteria", e);
		}
    	
    	
    	 
    }

}
