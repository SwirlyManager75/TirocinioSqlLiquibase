package com.tirocinio.service.Search;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.BiglietteriaDAO;
import com.tirocinio.dao.impl.BiglietteriaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietteria;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchBiglietteriaService implements MuseoGenericService {

    private final BiglietteriaDAO biglietteriaDAO;

    public SearchBiglietteriaService( ) {
        this.biglietteriaDAO = new BiglietteriaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
                Map<Object, Object> output=new HashMap<>();

        try (Connection connection = ConnectionManager.getConnection()) {
            List<Biglietteria> list = biglietteriaDAO.search(connection, (Biglietteria)input.get("biglietteria"));
            output.put("SearchBiglietteria", list);
            return output;
            
        } catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        catch(Exception e)
        {
            throw new ServiceException("Errore generico durante la execute di ",e);
        }
    }

   
}
