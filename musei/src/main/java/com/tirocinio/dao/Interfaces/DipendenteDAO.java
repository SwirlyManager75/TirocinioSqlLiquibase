package com.tirocinio.dao.Interfaces;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Citta;
import com.tirocinio.model.Dipendente;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.util.List;



public interface DipendenteDAO {

     String SELECT_ALL_DIPENDENTI = "SELECT * FROM Dipendente";
     String SELECT_DIPENDENTE_BY_ID = "SELECT * FROM Dipendente WHERE Cod_D = ?";
     String INSERT_DIPENDENTE = "INSERT INTO Dipendente (Nome, Data_Nascita, CF, Cellulare) VALUES (?, ?, ?, ?)";
     String UPDATE_DIPENDENTE = "UPDATE Dipendente SET Nome = ?, Data_Nascita = ?, CF = ?, Cellulare = ? WHERE Cod_D = ?";
     String DELETE_DIPENDENTE = "DELETE FROM Dipendente WHERE Cod_D = ?";
     String ASSOC_MUSEO = "UPDATE Dipendente SET Cod_E_M = ? WHERE Cod_D = ?";
     String ASSOC_DIPENDENTE = "UPDATE Dipendente SET Cod_E_Ci = ? WHERE Cod_D = ?";
     String LASTKEY_DIPEDENTE = "SELECT * FROM Abbonamento WHERE Cod_D = (SELECT MAX(Cod_D) FROM Abbonamento)";

    public int getLastKey(Connection connection) throws DAOException;

    public List<Dipendente> getAllDipendenti(Connection connection) throws DAOException ;

    public Dipendente getDipendenteById(Connection connection, int dipendenteId) throws DAOException ;

    public Dipendente addDipendente(Connection connection, Dipendente dipendente) throws DAOException ;

    public Dipendente updateDipendente(Connection connection, Dipendente dipendente) throws DAOException ;

    public boolean deleteDipendente(Connection connection, int dipendenteId) throws DAOException ;

    public List<Dipendente> search(Connection connection, Dipendente criteria) throws DAOException ;

    public boolean associateWithCity(Connection connection, Dipendente dipendente, Citta citta) throws DAOException ;

    public boolean associateWithMuseum(Connection connection, Dipendente dipendente, Museo museo) throws DAOException ;
}