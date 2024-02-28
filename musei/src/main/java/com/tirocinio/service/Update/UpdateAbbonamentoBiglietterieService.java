package com.tirocinio.service.Update;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.impl.Abbonamento_BiglietteriaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.service.MuseoGenericService;

public class UpdateAbbonamentoBiglietterieService  implements MuseoGenericService{

     private final Abbonamento_BiglietteriaDAOimpl abbonamento_BiglietteriaDAO;

    public UpdateAbbonamentoBiglietterieService()
    {
        this.abbonamento_BiglietteriaDAO= new Abbonamento_BiglietteriaDAOimpl();
    }

     public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
    	        Connection connection = ConnectionManager.getConnection();
				Map<Object, Object> output = new HashMap<>();

    	try {
            //TODO set the ints from the map
			output.put("UpdateAbbonamentoBiglietterie",abbonamento_BiglietteriaDAO.updateAbbonamentoBiglietteria(connection, (Integer)input.get("Cod_E_A"), (Integer)input.get("Cod_E_B"), (Integer)input.get("Cod_AB")));
			return output;
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (Exception e) {
			throw new ServiceException("Errore generico durante Associazione di abbonamento alla biglietteria", e);
		}
    	
    	
    }

}
