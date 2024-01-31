package com.tirocinio.service.Update;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.model.Biglietteria;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateBiglietteriaService {

    private final BiglietteriaDAO biglietteriaDAO;

    public UpdateBiglietteriaService( ) {
        this.biglietteriaDAO = new BiglietteriaDAO();
    }

    public boolean execute(Biglietteria biglietteria) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try {
            ret = biglietteriaDAO.updateBiglietteria(connection, biglietteria);
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
