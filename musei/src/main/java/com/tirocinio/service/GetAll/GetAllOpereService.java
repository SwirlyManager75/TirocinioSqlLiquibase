package com.tirocinio.service.GetAll;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Opera;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.OperaDAO;
import com.tirocinio.dao.impl.OperaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllOpereService implements MuseoGenericService {

    private final OperaDAO operaDAO;

    public GetAllOpereService( ) {
        this.operaDAO = new OperaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        Map<Object, Object> output = new HashMap<>();

        try (Connection connection = ConnectionManager.getConnection()) {
            List<Opera> list = operaDAO.getAllOpere(connection);
            output.put("GetAllOpere", list);
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
