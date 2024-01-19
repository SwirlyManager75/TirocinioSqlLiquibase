package com.tirocinio.service;

import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.model.Biglietto;

import java.sql.Connection;
import java.util.List;

public class GetAllBigliettiService {

    private final BigliettoDAO bigliettoDAO;
    private final Connection connection;

    public GetAllBigliettiService(Connection connection) {
        this.bigliettoDAO = new BigliettoDAO();
        this.connection = connection;
    }

    public List<Biglietto> execute() {
        return bigliettoDAO.getAllBiglietti(connection);
    }
}
