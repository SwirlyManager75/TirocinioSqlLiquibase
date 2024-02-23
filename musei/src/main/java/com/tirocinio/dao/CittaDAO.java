package com.tirocinio.dao;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface CittaDAO {

    String SELECT_ALL_CITIES = "SELECT * FROM Citta";
    String SELECT_CITY_BY_ID = "SELECT * FROM Citta WHERE Cod_Ci = ?";
    String INSERT_CITY = "INSERT INTO Citta (Nome, Provincia) VALUES (?, ?)";
    String UPDATE_CITY = "UPDATE Citta SET Nome = ?, Provincia = ? WHERE Cod_Ci = ?";
    String DELETE_CITY = "DELETE FROM Citta WHERE Cod_Ci = ?";
    String LASTKEY_CITTA = "SELECT * FROM CITTA WHERE Cod_Ci = (SELECT MAX(Cod_Ci) FROM CITTA)";

    public int getLastKey(Connection connection) throws DAOException;

    public List<Citta> getAllCities(Connection connection) throws DAOException;

    public Citta getCityById(Connection connection,int cityId) throws DAOException;

    public int addCity(Connection connection,Citta city) throws DAOException;

    public Citta updateCity(Connection connection,Citta city) throws DAOException;

    public boolean deleteCity(Connection connection,int cityId) throws DAOException;

    public List<Citta> search(Connection connection, Citta criteria) throws DAOException;

}