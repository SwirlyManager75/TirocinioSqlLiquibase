package com.tirocinio.dao.Interfaces;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Citta;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.util.List;



public interface MuseoDAO {

     String SELECT_ALL_MUSEUMS = "SELECT * FROM Museo";
     String SELECT_MUSEUM_BY_ID = "SELECT * FROM Museo WHERE Cod_M = ?";
     String INSERT_MUSEUM = "INSERT INTO Museo (Nome, Via) VALUES (?, ?)";
     String UPDATE_MUSEUM = "UPDATE Museo SET Nome = ?, Via = ? WHERE Cod_M = ?";
     String DELETE_MUSEUM = "DELETE FROM Museo WHERE Cod_M = ?";
     String ASSOC_MUSEUM = "UPDATE Museo SET Cod_E_Ci = ? WHERE Cod_M = ?";
     String LASTKEY_MUSEUM = "SELECT * FROM MUSEO WHERE Cod_M = (SELECT MAX(Cod_M) FROM MUSEO)";



    public int getLastKey(Connection connection) throws DAOException;

    public List<Museo> getAllMuseums(Connection connection) throws DAOException ;

    public Museo getMuseumById(Connection connection, int museumId) throws DAOException ;

    public Museo addMuseum(Connection connection, Museo museum) throws DAOException ;

    public Museo updateMuseum(Connection connection, Museo museum) throws DAOException ;

    public boolean deleteMuseum(Connection connection, int museumId) throws DAOException ;

    public boolean associateWithCity(Connection connection, Museo museo, Citta citta) throws DAOException ;

    public List<Museo> search(Connection connection, Museo criteria) throws DAOException ;

    
}