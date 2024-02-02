package com.tirocinio.service.GetAll;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Dipendente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllDipendentiService {

    private final DipendenteDAO dipendenteDAO;
    

    public GetAllDipendentiService( ) {
        this.dipendenteDAO = new DipendenteDAO();
    }

    public List<Dipendente> execute() throws ServiceException {

        try (Connection connection = ConnectionManager.getConnection()) {
            return dipendenteDAO.getAllDipendenti(connection);
        }catch (SQLException | DAOException e) 
        {
            throw new ServiceException("In execute - DAOException ");
        }
    }
}
