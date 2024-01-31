package com.tirocinio.service.Update;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.model.Artista;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateArtistaService {

    private final ArtistaDAO artistaDAO;

    public UpdateArtistaService( ) {
        this.artistaDAO = new ArtistaDAO();
    }

    public boolean execute(Artista artista) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try {
            ret= artistaDAO.updateArtista(connection, artista);
            connection.commit();
            return ret;
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
