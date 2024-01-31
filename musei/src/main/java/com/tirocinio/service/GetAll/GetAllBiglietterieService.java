package com.tirocinio.service.GetAll;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.model.Biglietteria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllBiglietterieService {

    private final BiglietteriaDAO biglietteriaDAO;

    public GetAllBiglietterieService( ) {
        this.biglietteriaDAO = new BiglietteriaDAO();
    }

    public List<Biglietteria> execute() {
        try (Connection connection = ConnectionManager.getConnection()) {
            return biglietteriaDAO.getAllBiglietterie(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
