package com.tirocinio.dao.Interfaces;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Audio;
import com.tirocinio.model.Poi;

import java.sql.Connection;
import java.util.List;



public interface AudioDAO {

     String SELECT_ALL_AUDIOS = "SELECT * FROM Audio";
     String SELECT_AUDIO_BY_ID = "SELECT * FROM Audio WHERE Cod_Au = ?";
     String INSERT_AUDIO = "INSERT INTO Audio (URL, Cod_E_Poi) VALUES (?, ?)";
     String UPDATE_AUDIO = "UPDATE Audio SET URL = ? WHERE Cod_Au = ?";
     String DELETE_AUDIO = "DELETE FROM Audio WHERE Cod_Au = ?";
     String ASSOC_POI = "UPDATE Audio SET Cod_E_Poi = ? WHERE Cod_Au = ?";
     String LASTKEY_AUDIO = "SELECT * FROM Audio WHERE Cod_Au = (SELECT MAX(Cod_Au) FROM Audio)";


    public int getLastKey(Connection connection) throws DAOException;

    public List<Audio> getAllAudios(Connection connection) throws DAOException ;

    public Audio getAudioById(Connection connection, int audioId) throws DAOException;

    public Audio addAudio(Connection connection, Audio audio) throws DAOException;

    public Audio updateAudio(Connection connection, Audio audio) throws DAOException;

    public boolean deleteAudio(Connection connection, int audioId) throws DAOException ;

    public List<Audio> search(Connection connection, Audio criteria) throws DAOException ;

    public boolean associateWithPoi(Connection connection, Audio audio, Poi poi) throws DAOException ;


}