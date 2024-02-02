package com.tirocinio.service.Associate;

import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.exceptions.DAOException;
import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Dipendente;

import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;


public class AssociateDipendenteToCittaService {

    private final DipendenteDAO dipendenteDAO;
    private final CittaDAO cittaDAO;

    public AssociateDipendenteToCittaService( ) {
        this.dipendenteDAO = new DipendenteDAO();
        this.cittaDAO = new CittaDAO();
    }

    public boolean execute(int codDipendente, int codCitta) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try {

            Citta citta = cittaDAO.getCityById(connection, codCitta);
            Dipendente dipendente = dipendenteDAO.getDipendenteById(connection, codDipendente);
            // Cerco il Museo con il codice fornito

            if (citta != null && dipendente !=null) {
                // Inserisco il Dipendente nel database
                ret=dipendenteDAO.associateWithCity(connection, dipendente,citta);
                 connection.commit();
                return ret;

            } else {
                // Città o Museo non trovato
                System.out.println("Città o Dipedente non trovato con codice: " + codCitta + " o " + codDipendente);
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
