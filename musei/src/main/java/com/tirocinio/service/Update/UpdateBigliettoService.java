package com.tirocinio.service.Update;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.model.Biglietto;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateBigliettoService {

    private final BigliettoDAO bigliettoDAO;

    public UpdateBigliettoService( ) {
        this.bigliettoDAO = new BigliettoDAO();
    }

    public boolean execute(Biglietto biglietto) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try {
            ret= bigliettoDAO.updateBiglietto(connection, biglietto);
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
