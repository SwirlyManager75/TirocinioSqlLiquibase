package com.tirocinio.service.Update;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.OperaDAO;
import com.tirocinio.model.Opera;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateOperaService {

    private final OperaDAO operaDAO;

    public UpdateOperaService() {
        this.operaDAO = new OperaDAO();
    }

    public boolean execute(Opera opera) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try {
            ret = operaDAO.updateOpera(connection, opera);
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
