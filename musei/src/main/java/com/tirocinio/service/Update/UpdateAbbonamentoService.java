package com.tirocinio.service.Update;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Abbonamento;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateAbbonamentoService {

    private final AbbonamentoDAO abbonamentoDAO;

    public UpdateAbbonamentoService( ) {
        this.abbonamentoDAO = new AbbonamentoDAO();
       
    }

    public boolean execute(Abbonamento abbonamento) throws  ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try{
            ret= abbonamentoDAO.updateAbbonamento(connection, abbonamento);
            connection.commit();
            return ret;
        } catch (SQLException | DAOException e) 
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
