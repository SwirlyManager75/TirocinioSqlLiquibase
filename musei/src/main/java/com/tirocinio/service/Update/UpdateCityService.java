package com.tirocinio.service.Update;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateCityService {

    private final CittaDAO cittaDAO;

    public UpdateCityService( ) {
        this.cittaDAO = new CittaDAO();
    }

    public boolean execute(Citta city) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try{
            ret= cittaDAO.updateCity(connection, city);
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
