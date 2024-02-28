package com.tirocinio.dao.Interfaces;
import java.sql.Connection;
import java.sql.SQLException;


import java.util.Map;


import com.tirocinio.exceptions.DAOException;


public interface Abbonamento_BiglietteriaDAO {


     String INSERT_ABBONAMENTO_BIGLIETTERIA = "INSERT INTO Abbonamento_Biglietteria (Cod_E_A, Cod_E_B) VALUES (?, ?)";
     String DELETE_ABBONAMENTO_BIGLIETTERIA = "DELETE FROM Abbonamento_Biglietteria WHERE Cod_AB = ?";

    public boolean addAbbonamentoBiglietteria(Connection connection, int Cod_Ab, int Cod_B) throws DAOException;

    public boolean deleteAbbonamentoBiglietteria(Connection connection, int abbonamentoBiglietteriaId) throws DAOException ;

    public boolean updateAbbonamentoBiglietteria(Connection connection, Integer Cod_E_A,Integer Cod_E_B, Integer Cod_AB ) throws DAOException ;

    public Map<Object, Object> leggiBiglietteriePerAbbonamento(Connection connection,int codiceAbbonamento) throws SQLException, DAOException ;

    public Map<Object, Object> leggiAbbonamentiPerBiglietteria(Connection connection,int codiceBiglietteria) throws SQLException, DAOException;

    public Map<Object, Object> getAllAbbonementiBiglietterie(Connection connection) throws SQLException, DAOException ;


    
}