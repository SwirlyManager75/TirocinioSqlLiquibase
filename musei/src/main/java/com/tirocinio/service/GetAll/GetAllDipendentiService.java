package com.tirocinio.service.GetAll;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Dipendente;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.DipendenteDAO;
import com.tirocinio.dao.impl.DipendenteDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllDipendentiService implements MuseoGenericService {

    private final DipendenteDAO dipendenteDAO;
    

    public GetAllDipendentiService( ) {
        this.dipendenteDAO = new DipendenteDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        Map<Object, Object> output = new HashMap<>();


        try (Connection connection = ConnectionManager.getConnection()) {
            List<Dipendente> list = dipendenteDAO.getAllDipendenti(connection);
            output.put("GetAllDipendenti", list);
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
