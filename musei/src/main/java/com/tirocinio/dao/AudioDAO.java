package com.tirocinio.dao;

import com.tirocinio.model.Audio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AudioDAO {

    private static final String SELECT_ALL_AUDIOS = "SELECT * FROM Audio";
    private static final String SELECT_AUDIO_BY_ID = "SELECT * FROM Audio WHERE Cod_Au = ?";
    private static final String INSERT_AUDIO = "INSERT INTO Audio (URL, Cod_E_Poi) VALUES (?, ?)";
    private static final String UPDATE_AUDIO = "UPDATE Audio SET URL = ?, Cod_E_Poi = ? WHERE Cod_Au = ?";
    private static final String DELETE_AUDIO = "DELETE FROM Audio WHERE Cod_Au = ?";

    public List<Audio> getAllAudios(Connection connection) {
        List<Audio> audios = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AUDIOS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                audios.add(mapResultSetToAudio(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisci l'eccezione in modo appropriato per la tua applicazione
        }
        return audios;
    }

    public Audio getAudioById(Connection connection, int audioId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUDIO_BY_ID)) {

            preparedStatement.setInt(1, audioId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAudio(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisci l'eccezione in modo appropriato per la tua applicazione
        }
        return null;
    }

    public boolean addAudio(Connection connection, Audio audio) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUDIO)) {

            preparedStatement.setString(1, audio.getUrl());
            preparedStatement.setInt(2, audio.getCodEPoi());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisci l'eccezione in modo appropriato per la tua applicazione
            return false;
        }
    }

    public boolean updateAudio(Connection connection, Audio audio) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUDIO)) {

            preparedStatement.setString(1, audio.getUrl());
            preparedStatement.setInt(2, audio.getCodEPoi());
            preparedStatement.setInt(3, audio.getCodAu());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisci l'eccezione in modo appropriato per la tua applicazione
            return false;
        }
    }

    public boolean deleteAudio(Connection connection, int audioId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AUDIO)) {

            preparedStatement.setInt(1, audioId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisci l'eccezione in modo appropriato per la tua applicazione
            return false;
        }
    }

    public List<Audio> search(Connection connection, Audio criteria) {
        List<Audio> matchingAudios = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Audio WHERE 1=1");

        // Aggiungi i criteri alla query dinamicamente se sono presenti nella classe criteria
        if (criteria.getUrl() != null) {
            queryBuilder.append(" AND URL LIKE ?");
        }
        if (criteria.getCodEPoi() != null) {
            queryBuilder.append(" AND Cod_E_Poi = ?");
        }
        // Aggiungi altri criteri secondo necessità

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            // Imposta i parametri dinamici nella query
            if (criteria.getUrl() != null) {
                preparedStatement.setString(parameterIndex++, "%" + criteria.getUrl() + "%");
            }
            if (criteria.getCodEPoi() != null) {
                preparedStatement.setInt(parameterIndex++, criteria.getCodEPoi());
            }
            // Imposta altri parametri secondo necessità

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingAudios.add(mapResultSetToAudio(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisci l'eccezione in modo appropriato per la tua applicazione
        }

        return matchingAudios;
    }

    private Audio mapResultSetToAudio(ResultSet resultSet) throws SQLException {
        Audio audio = new Audio();
        audio.setCodAu(resultSet.getInt("Cod_Au"));
        audio.setUrl(resultSet.getString("URL"));
        audio.setCodEPoi(resultSet.getInt("Cod_E_Poi"));
        return audio;
    }
}