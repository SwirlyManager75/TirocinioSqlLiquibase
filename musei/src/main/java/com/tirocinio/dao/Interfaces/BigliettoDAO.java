package com.tirocinio.dao.Interfaces;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietteria;
import com.tirocinio.model.Biglietto;
import com.tirocinio.model.Cliente;

import java.sql.Connection;
import java.util.List;



public interface BigliettoDAO {

     String SELECT_ALL_BIGLIETTI = "SELECT * FROM Biglietto";
     String SELECT_BIGLIETTO_BY_ID = "SELECT * FROM Biglietto WHERE Cod_Bi = ?";
     String INSERT_BIGLIETTO = "INSERT INTO Biglietto (Prezzo, Tipo, Data) VALUES (?, ?, ?)";
     String UPDATE_BIGLIETTO = "UPDATE Biglietto SET Prezzo = ?, Tipo = ?, Data = ? WHERE Cod_Bi = ?";
     String DELETE_BIGLIETTO = "DELETE FROM Biglietto WHERE Cod_Bi = ?";
     String ASSOC_CLIENTE = "UPDATE Biglietto SET Cod_E_Cli = ? WHERE Cod_Bi = ?";
     String ASSOC_BIGLIETTERIA = "UPDATE Biglietto SET Cod_E_B = ? WHERE Cod_Bi = ?";
     String LASTKEY_BIGLIETTO = "SELECT * FROM BIGLIETTO WHERE Cod_Bi = (SELECT MAX(Cod_Bi) FROM BIGLIETTO)";



    public int getLastKey(Connection connection) throws DAOException;

    public List<Biglietto> getAllBiglietti(Connection connection) throws DAOException ;

    public Biglietto getBigliettoById(Connection connection, int bigliettoId) throws DAOException; 

    public Biglietto addBiglietto(Connection connection, Biglietto biglietto) throws DAOException ;

    public Biglietto updateBiglietto(Connection connection, Biglietto biglietto) throws DAOException ;

    public boolean deleteBiglietto(Connection connection, int bigliettoId) throws DAOException ;

    public List<Biglietto> search(Connection connection, Biglietto criteria) throws DAOException ;

    public boolean associateWithClient(Connection connection, Biglietto biglietto, Cliente cliente) throws DAOException ;

    public boolean associateWithTicketOffice(Connection connection,Biglietto biglietto, Biglietteria biglietteria) throws DAOException ;

    
}