package com.tirocinio.service.Associate;

import com.tirocinio.dao.OperaDAO;
import com.tirocinio.exceptions.DAOException;
import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.model.Opera;
import com.tirocinio.model.Artista;

import java.sql.Connection;
import java.sql.SQLException;

public class AssociateOperaToMuseoService {

    private final OperaDAO operaDAO;
    private final ArtistaDAO artistaDAO;

    public AssociateOperaToMuseoService() {
        this.operaDAO = new OperaDAO();
        this.artistaDAO= new ArtistaDAO();
    }

    public boolean execute(int codOp,int codAr) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try  {

            Opera opera = operaDAO.getOperaById(connection, codOp);
            Artista artista = artistaDAO.getArtistaById(connection, codAr);

            if (artista != null && opera != null) {

                // Inserisci l'Opera nel database
                ret=operaDAO.associateWithArtist(connection, opera,artista);
                 connection.commit();
                return ret;

            } else {
                // Museo non trovato, gestisci la situazione di conseguenza
                System.out.println("Artista o Opera non trovato con codice: " + codAr+" o "+codOp);
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