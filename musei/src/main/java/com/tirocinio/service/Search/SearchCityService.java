package com.tirocinio.service.Search;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.CittaDAO;
import com.tirocinio.dao.impl.CittaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Citta;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCityService implements MuseoGenericService {

    private final CittaDAO cittaDAO;

    public SearchCityService() {
        this.cittaDAO = new CittaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
                Map<Object, Object> output=new HashMap<>();

        try (Connection connection = ConnectionManager.getConnection()) {
            List<Citta> list = cittaDAO.search(connection, (Citta)input.get("citta"));
            output.put("SearchCity", list);
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