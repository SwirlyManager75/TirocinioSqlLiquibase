package com.tirocinio.service.Update;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.model.Dipendente;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateDipendenteService {

    private final DipendenteDAO dipendenteDAO;

    public UpdateDipendenteService( ) {
        this.dipendenteDAO = new DipendenteDAO();
    }

    public boolean execute(Dipendente dipendente) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try {
            ret= dipendenteDAO.updateDipendente(connection, dipendente);
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
