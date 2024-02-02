package com.tirocinio.service.Associate;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Dipendente;
import com.tirocinio.model.Museo;


import java.sql.Connection;
import java.sql.SQLException;


public class AssociateDipendenteToMuseoService {

    private final DipendenteDAO dipendenteDAO;
    private final MuseoDAO museoDAO;
  

    public AssociateDipendenteToMuseoService( ) {
        this.dipendenteDAO = new DipendenteDAO();
        this.museoDAO = new MuseoDAO();
    }

    public boolean execute(int codDipendente, int codMuseo) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try 
        {

            Museo museo = museoDAO.getMuseumById(connection, codMuseo);
            Dipendente dipendente = dipendenteDAO.getDipendenteById(connection, codDipendente);

            if ( museo != null && dipendente != null) {

                // Inserisco il Dipendente nel database
                ret=dipendenteDAO.associateWithMuseum(connection, dipendente,museo);
                 connection.commit();
                return ret;

            } else {
                // Città o Museo non trovato
                System.out.println(" Dipendete o Museo non trovato con codice: " + codDipendente + " o " + codMuseo);
                return false;
            }
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
