package com.tirocinio.service.Update;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.Cliente_AbbonamentoDAO;
import com.tirocinio.dao.impl.Cliente_AbbonamentoDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.service.MuseoGenericService;

public class UpdateAbbonamentoForClienteService  implements MuseoGenericService{

    private final Cliente_AbbonamentoDAO cliente_AbbonamentoDAO;

    public UpdateAbbonamentoForClienteService()
    {
        this.cliente_AbbonamentoDAO= new Cliente_AbbonamentoDAOimpl();
    }

     public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
    	        Connection connection = ConnectionManager.getConnection();
				Map<Object, Object> output = new HashMap<>();

    	try {
            //TODO set the ints from the map
			output.put("UpdateAbbonamentoCliente", cliente_AbbonamentoDAO.updateClienteAbbonamento(connection, (Integer)input.get("Cod_Cli"), (Integer)input.get("Old_Cod_Ab"), (Integer)input.get("New_Cod_Ab")));
			return output;
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (Exception e) {
			throw new ServiceException("Errore generico durante Associazione di abbonamento alla biglietteria", e);
		}
    	
    	
    }
}
