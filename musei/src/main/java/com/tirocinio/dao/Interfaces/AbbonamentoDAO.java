package com.tirocinio.dao.Interfaces;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Abbonamento;

import java.sql.Connection;
import java.util.List;



public interface AbbonamentoDAO {

     String SELECT_ALL_ABBONAMENTI = "SELECT * FROM Abbonamento";
     String SELECT_ABBONAMENTO_BY_ID = "SELECT * FROM Abbonamento WHERE Cod_Ab = ?";
     String INSERT_ABBONAMENTO = "INSERT INTO Abbonamento (Tipo, Descrizione, Prezzo) VALUES (?, ?, ?)";
     String UPDATE_ABBONAMENTO = "UPDATE Abbonamento SET Tipo = ?, Descrizione = ?, Prezzo = ? WHERE Cod_Ab = ?";
     String DELETE_ABBONAMENTO = "DELETE FROM Abbonamento WHERE Cod_Ab = ?";
     String LASTKEY_ABBONAMENTO = "SELECT * FROM Abbonamento WHERE Cod_Ab = (SELECT MAX(Cod_Ab) FROM Abbonamento)";

    

    public int getLastKey(Connection connection) throws DAOException;

    public List<Abbonamento> getAllAbbonamenti(Connection connection) throws DAOException ;

    public Abbonamento getAbbonamentoById(Connection connection, int abbonamentoId) throws DAOException ;

    public Abbonamento addAbbonamento(Connection connection, Abbonamento abbonamento) throws DAOException ;

    public Abbonamento updateAbbonamento(Connection connection, Abbonamento abbonamento) throws DAOException ;

    public boolean deleteAbbonamento(Connection connection, int abbonamentoId) throws DAOException ;

    public List<Abbonamento> search(Connection connection, Abbonamento criteria) throws DAOException ;

}