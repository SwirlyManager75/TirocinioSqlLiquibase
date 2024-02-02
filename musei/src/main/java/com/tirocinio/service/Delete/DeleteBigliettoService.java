package com.tirocinio.service.Delete;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.exceptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteBigliettoService {

    private final BigliettoDAO bigliettoDAO;
    

    public DeleteBigliettoService( ) {
        this.bigliettoDAO = new BigliettoDAO();
     
    }

    public boolean execute(int bigliettoId) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try 
        {
            ret=bigliettoDAO.deleteBiglietto(connection, bigliettoId);          
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
