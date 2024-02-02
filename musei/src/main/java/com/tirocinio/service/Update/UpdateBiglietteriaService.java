package com.tirocinio.service.Update;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietteria;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateBiglietteriaService {

    private final BiglietteriaDAO biglietteriaDAO;

    public UpdateBiglietteriaService( ) {
        this.biglietteriaDAO = new BiglietteriaDAO();
    }

    public boolean execute(Biglietteria biglietteria) throws  ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try {
            ret = biglietteriaDAO.updateBiglietteria(connection, biglietteria);
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
