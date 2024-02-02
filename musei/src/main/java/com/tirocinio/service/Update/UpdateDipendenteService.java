package com.tirocinio.service.Update;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Dipendente;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateDipendenteService {

    private final DipendenteDAO dipendenteDAO;

    public UpdateDipendenteService( ) {
        this.dipendenteDAO = new DipendenteDAO();
    }

    public boolean execute(Dipendente dipendente) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try {
            ret= dipendenteDAO.updateDipendente(connection, dipendente);
            connection.commit();
            return ret;
        }catch (SQLException | DAOException e) 
        {
            
            try {
                connection.rollback();
            } 
            catch (SQLException e1) 
            {
                e1.printStackTrace();
            } 
            throw new ServiceException("In execute - DAOException ");
            
        }
        finally
        {
            try 
            {
                connection.close();
            } catch (SQLException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
