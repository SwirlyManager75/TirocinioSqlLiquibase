package com.tirocinio.dao.Interfaces;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Artista;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.util.List;



public interface ArtistaDAO {

     String SELECT_ALL_ARTISTI = "SELECT * FROM Artista";
     String SELECT_ARTISTA_BY_ID = "SELECT * FROM Artista WHERE Cod_Ar = ?";
     String INSERT_ARTISTA = "INSERT INTO Artista (Nome, Cognome, Data_Nascita, In_vita) VALUES (?, ?, ?, ?)";
     String UPDATE_ARTISTA = "UPDATE Artista SET Nome = ?, Cognome = ?, Data_Nascita = ?, In_vita = ? WHERE Cod_Ar = ?";
     String DELETE_ARTISTA = "DELETE FROM Artista WHERE Cod_Ar = ?";
     String ASSOC_CITTA = "UPDATE Artista SET Cod_E_Ci = ? WHERE Cod_Ar = ?";
     String LASTKEY_ARTISTA = "SELECT * FROM Artista WHERE Cod_Ar = (SELECT MAX(Cod_Ar) FROM Artista)";

    public int getLastKey(Connection connection) throws DAOException;

    public List<Artista> getAllArtisti(Connection connection) throws DAOException;

    public Artista getArtistaById(Connection connection, int artistaId) throws DAOException ;

    public Artista addArtista(Connection connection, Artista artista) throws DAOException;

    public Artista updateArtista(Connection connection, Artista artista) throws DAOException;

    public boolean deleteArtista(Connection connection, int artistaId) throws DAOException;

    public List<Artista> search(Connection connection, Artista criteria) throws DAOException ;

    public boolean associateWithCity(Connection connection, Artista artista, Citta citta) throws DAOException ;

}