package com.tirocinio.dao.Interfaces;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietteria;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.util.List;



public interface BiglietteriaDAO {

     String SELECT_ALL_BIGLIETTERIE = "SELECT * FROM Biglietteria";
     String SELECT_BIGLIETTERIA_BY_ID = "SELECT * FROM Biglietteria WHERE Cod_B = ?";
     String INSERT_BIGLIETTERIA = "INSERT INTO Biglietteria (Ora_Ap, Ora_Ch, Mod_Pag) VALUES (?, ?, ?)";
     String UPDATE_BIGLIETTERIA = "UPDATE Biglietteria SET Ora_Ap = ?, Ora_Ch = ?, Mod_Pag = ? WHERE Cod_B = ?";
     String DELETE_BIGLIETTERIA = "DELETE FROM Biglietteria WHERE Cod_B = ?";
     String ASSOC_MUSEO= "UPDATE Biglietteria SET Cod_E_M = ? WHERE Cod_B = ?" ;
     String LASTKEY_BIGLIETTERIA = "SELECT * FROM BIGLIETTERIA WHERE Cod_B = (SELECT MAX(Cod_B) FROM BIGLIETTEREIA)";


    //TODO AGGIUNGERE LOGICA PER LEGARE BIGLIETTERIE AD ABBONAMENTI (SI USA LA TABELLA ABBONAMENTI_BIGLIETTERIE)
    
    public int getLastKey(Connection connection) throws DAOException;

    public List<Biglietteria> getAllBiglietterie(Connection connection) throws DAOException ;

    public Biglietteria getBiglietteriaById(Connection connection, int biglietteriaId) throws DAOException ;

    public Biglietteria addBiglietteria(Connection connection, Biglietteria biglietteria) throws DAOException;

    public Biglietteria updateBiglietteria(Connection connection, Biglietteria biglietteria) throws DAOException;

    public boolean deleteBiglietteria(Connection connection, int biglietteriaId) throws DAOException ;

    public List<Biglietteria> search(Connection connection, Biglietteria criteria) throws DAOException ;

    public boolean associateWithMuseum(Connection connection, Biglietteria biglietteria, Museo museo) throws DAOException ;

}