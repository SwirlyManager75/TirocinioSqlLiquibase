package com.tirocinio.dao.Interfaces;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Artista;
import com.tirocinio.model.Museo;
import com.tirocinio.model.Opera;

import java.sql.Connection;
import java.util.List;



public interface OperaDAO {

    String SELECT_ALL_OPERE = "SELECT * FROM Opera";
    String SELECT_OPERA_BY_ID = "SELECT * FROM Opera WHERE Cod_O = ?";
     String INSERT_OPERA = "INSERT INTO Opera (Nome, Descrizione) VALUES (?, ?)";
     String UPDATE_OPERA = "UPDATE Opera SET Nome = ?, Descrizione = ? WHERE Cod_O = ?";
     String DELETE_OPERA = "DELETE FROM Opera WHERE Cod_O = ?";
     String ASSOC_ARTISTA= "UPDATE Opera SET Cod_E_Ar = ? WHERE Cod_O = ?";
     String ASSOC_MUSEO = "UPDATE Opera SET Cod_E_Ci = ? WHERE Cod_O = ?";
     String LASTKEY_OPERA = "SELECT * FROM OPERA WHERE Cod_O = (SELECT MAX(Cod_O) FROM OPERA)";



    public int getLastKey(Connection connection) throws DAOException;

    public List<Opera> getAllOpere(Connection connection) throws DAOException ;

    public Opera getOperaById(Connection connection, int operaId) throws DAOException ;

    public Opera addOpera(Connection connection, Opera opera) throws DAOException ;

    public Opera updateOpera(Connection connection, Opera opera) throws DAOException ;

    public boolean deleteOpera(Connection connection, int operaId) throws DAOException ;

    public List<Opera> search(Connection connection, Opera criteria) throws DAOException ;

    public boolean associateWithMuseo(Connection connection, Opera opera, Museo museo) throws DAOException ;

    public boolean associateWithArtist(Connection connection, Opera opera, Artista artista) throws DAOException ;
   
}