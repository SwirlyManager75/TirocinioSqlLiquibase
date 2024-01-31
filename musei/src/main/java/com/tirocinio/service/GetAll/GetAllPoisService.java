package com.tirocinio.service.GetAll;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.PoiDAO;
import com.tirocinio.model.Poi;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllPoisService {

    private final PoiDAO poiDAO;

    public GetAllPoisService( ) {
        this.poiDAO = new PoiDAO();
    }

    public List<Poi> execute() {
        try (Connection connection = ConnectionManager.getConnection()) {
            return poiDAO.getAllPois(connection);
        } catch (SQLException e) {
            
            e.printStackTrace();
            return null;
        }
    }
}
