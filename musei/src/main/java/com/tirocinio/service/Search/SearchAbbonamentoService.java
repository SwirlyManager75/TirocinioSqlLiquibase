package com.tirocinio.service.Search;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.AbbonamentoDAO;
import com.tirocinio.dao.impl.AbbonamentoDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Abbonamento;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchAbbonamentoService implements MuseoGenericService {

    private final AbbonamentoDAO abbonamentoDAO;
   

    public SearchAbbonamentoService() {
        this.abbonamentoDAO = new AbbonamentoDAOimpl();

    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {

        Map<Object, Object> output=new HashMap<>();

        try (Connection connection = ConnectionManager.getConnection()) {
            
            List<Abbonamento> list = abbonamentoDAO.search(connection, (Abbonamento)input.get("biglietteria"));
            output.put("SearchAbbonamento", list);
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
