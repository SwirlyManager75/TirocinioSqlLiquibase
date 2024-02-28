package com.tirocinio.service.GetAll;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Abbonamento;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.AbbonamentoDAO;
import com.tirocinio.dao.impl.AbbonamentoDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllAbbonamentiService implements MuseoGenericService {

    private final AbbonamentoDAO abbonamentoDAO;
    

    public GetAllAbbonamentiService( ) {
        this.abbonamentoDAO = new AbbonamentoDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        Map<Object, Object> output = new HashMap<>();

        try (Connection connection = ConnectionManager.getConnection()) {
            List<Abbonamento> list= abbonamentoDAO.getAllAbbonamenti(connection);
            output.put("GetAllAbbonamenti", list);
            return output;
        }catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        catch(Exception e)
        {
            throw new ServiceException("Errore generico durante la execute di ",e);
        }
        
        
    }

   
}
