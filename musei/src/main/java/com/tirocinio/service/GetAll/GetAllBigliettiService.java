package com.tirocinio.service.GetAll;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Biglietto;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.BigliettoDAO;
import com.tirocinio.dao.impl.BigliettoDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllBigliettiService implements MuseoGenericService {

    private final BigliettoDAO bigliettoDAO;

    public GetAllBigliettiService( ) {
        this.bigliettoDAO = new BigliettoDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        Map<Object, Object> output = new HashMap<>();

        try (Connection connection = ConnectionManager.getConnection()) {
            List<Biglietto> list = bigliettoDAO.getAllBiglietti(connection);
            output.put("GetAllBiglietti", list);
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
