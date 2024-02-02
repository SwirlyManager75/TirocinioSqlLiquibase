package com.tirocinio.service.Insert;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietto;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateBigliettoService {

    private final BigliettoDAO bigliettoDAO;

    public CreateBigliettoService( ) {
        this.bigliettoDAO = new BigliettoDAO();
        
    }

    public boolean execute(Biglietto biglietto) throws ServiceException {

        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try 
        {
            ret=bigliettoDAO.addBiglietto(connection, biglietto);            
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
