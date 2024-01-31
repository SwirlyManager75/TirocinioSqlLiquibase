package com.tirocinio.service.Update;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.model.Abbonamento;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateAbbonamentoService {

    private final AbbonamentoDAO abbonamentoDAO;

    public UpdateAbbonamentoService( ) {
        this.abbonamentoDAO = new AbbonamentoDAO();
       
    }

    public boolean execute(Abbonamento abbonamento) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try{
            ret= abbonamentoDAO.updateAbbonamento(connection, abbonamento);
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
