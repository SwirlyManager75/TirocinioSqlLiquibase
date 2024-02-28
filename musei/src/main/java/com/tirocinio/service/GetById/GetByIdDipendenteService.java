package com.tirocinio.service.GetById;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.DipendenteDAO;
import com.tirocinio.dao.impl.DipendenteDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Dipendente;
import com.tirocinio.service.MuseoGenericService;

public class GetByIdDipendenteService  implements MuseoGenericService{

    private final DipendenteDAO dipendenteDAO;
    

    public GetByIdDipendenteService( ) {
        this.dipendenteDAO = new DipendenteDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        Map<Object, Object> output = new HashMap<>();

        
        try 
        {
            int codDipendente = (Integer)input.get("dipendente");
            Dipendente ret= dipendenteDAO.getDipendenteById(connection, codDipendente);
            output.put("GetByIdDipendente", ret);
            return output;
        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }

    

}
