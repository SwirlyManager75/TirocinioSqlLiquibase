package com.tirocinio.service;

import com.tirocinio.dao.OperaDAO;
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

    public boolean execute(int codOp,int codAr) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try  {
            connection.setAutoCommit(false);

            Opera opera = operaDAO.getOperaById(connection, codOp);
            Artista artista = artistaDAO.getArtistaById(connection, codAr);

            if (artista != null && opera != null) {

                // Inserisci l'Opera nel database
                 operaDAO.associateWithArtist(connection, opera,artista);
                 connection.commit();
                return true;

            } else {
                // Museo non trovato, gestisci la situazione di conseguenza
                System.out.println("Artista o Opera non trovato con codice: " + codAr+" o "+codOp);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }
        finally
        {
            connection.close();
        }
    }
}
