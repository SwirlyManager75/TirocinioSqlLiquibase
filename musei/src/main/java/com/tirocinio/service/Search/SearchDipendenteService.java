package com.tirocinio.service.Search;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Dipendente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchDipendenteService {

    private final DipendenteDAO dipendenteDAO;

    public SearchDipendenteService( ) {
        this.dipendenteDAO = new DipendenteDAO();
        
    }

    public List<Dipendente> execute(Dipendente criteria) throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return dipendenteDAO.search(connection, criteria);
        }catch (SQLException |DAOException e) 
        {
            throw new ServiceException(e);
        }
        catch(Exception e)
        {
            throw new ServiceException("Errore generico durante la execute di ",e);
        }
    }
}
