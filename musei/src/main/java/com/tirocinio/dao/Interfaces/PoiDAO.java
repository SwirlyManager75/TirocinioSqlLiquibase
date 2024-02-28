package com.tirocinio.dao.Interfaces;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Museo;
import com.tirocinio.model.Poi;

import java.sql.Connection;
import java.util.List;




public interface PoiDAO {

     String SELECT_ALL_POIS = "SELECT * FROM Poi";
     String SELECT_POI_BY_ID = "SELECT * FROM Poi WHERE Cod_Poi = ?";
     String INSERT_POI = "INSERT INTO Poi (Descrizione) VALUES (?)";
     String UPDATE_POI = "UPDATE Poi SET Descrizione = ? WHERE Cod_Poi = ?";
     String DELETE_POI = "DELETE FROM Poi WHERE Cod_Poi = ?";
     String ASSOC_MUSEO =  "UPDATE Poi SET Cod_E_M = ? WHERE Cod_Poi = ?";
     String GETLAST_POI = "SELECT * FROM Poi WHERE Cod_Poi = (SELECT MAX(Cod_Poi) FROM Poi) ";

    public int getLastKey(Connection connection) throws DAOException;

    public List<Poi> getAllPois(Connection connection) throws DAOException ;

    public Poi getPoiById(Connection connection, int poiId) throws DAOException ;

    public Poi getLastPoi(Connection connection) throws DAOException;

    public Poi addPoi(Connection connection, Poi poi) throws DAOException ;

    public Poi updatePoi(Connection connection, Poi poi) throws DAOException ;

    public boolean deletePoi(Connection connection, int poiId) throws DAOException ;

    public List<Poi> search(Connection connection, Poi criteria) throws DAOException ;

    public boolean associateWithMuseum(Connection connection, Poi poi, Museo museo) throws DAOException ;

}