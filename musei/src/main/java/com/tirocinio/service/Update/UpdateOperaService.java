package com.tirocinio.service.Update;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.OperaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Opera;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateOperaService {

    private final OperaDAO operaDAO;

    public UpdateOperaService() {
        this.operaDAO = new OperaDAO();
    }

    public boolean execute(Opera opera) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try {
            ret = operaDAO.updateOpera(connection, opera);
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
