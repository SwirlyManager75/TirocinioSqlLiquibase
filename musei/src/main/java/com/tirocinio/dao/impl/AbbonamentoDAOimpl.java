package com.tirocinio.dao.impl;
import com.tirocinio.dao.Interfaces.AbbonamentoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Abbonamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AbbonamentoDAOimpl implements AbbonamentoDAO {

    private static final String SELECT_ALL_ABBONAMENTI = "SELECT * FROM Abbonamento";
    private static final String SELECT_ABBONAMENTO_BY_ID = "SELECT * FROM Abbonamento WHERE Cod_Ab = ?";
    private static final String INSERT_ABBONAMENTO = "INSERT INTO Abbonamento (Tipo, Descrizione, Prezzo) VALUES (?, ?, ?)";
    private static final String UPDATE_ABBONAMENTO = "UPDATE Abbonamento SET Tipo = ?, Descrizione = ?, Prezzo = ? WHERE Cod_Ab = ?";
    private static final String DELETE_ABBONAMENTO = "DELETE FROM Abbonamento WHERE Cod_Ab = ?";
    private static final String LASTKEY_ABBONAMENTO = "SELECT * FROM Abbonamento WHERE Cod_Ab = (SELECT MAX(Cod_Ab) FROM Abbonamento)";

    private static final Logger logger= LogManager.getLogger(AbbonamentoDAOimpl.class);

    public int getLastKey(Connection connection) throws DAOException{
        Abbonamento abb=null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(LASTKEY_ABBONAMENTO)) {
            ResultSet resultSet = preparedStatement.executeQuery();
           abb =  mapResultSetToAbbonamento(resultSet);
            logger.info("Ultimo id recuperato");
           return abb.getCodAb();
           
        } catch (SQLException e) {
            logger.error("SQLError "+e.getMessage());
            throw new DAOException("Errore durante il recupero dell'ultimo id Abbonamento", e);
        }
        catch(Exception e)
        {
            logger.error("Errore generico "+e.getMessage());

            throw new DAOException("Errore generico durante il recupero dell'ultimo id Abbonamento", e);

        }
    }

    public List<Abbonamento> getAllAbbonamenti(Connection connection) throws DAOException {
        List<Abbonamento> abbonamenti = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ABBONAMENTI);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                abbonamenti.add(mapResultSetToAbbonamento(resultSet));
            }
        } catch (SQLException e) {
                logger.error(" Errore durante il recupero degli abbonamenti"+e.getMessage());
                throw new DAOException("Errore durante il recupero degli abbonamenti", e);
                //return false;
        }
        catch(Exception e)
        {
            logger.error(" Errore generico durante il recupero degli abbonamenti"+e.getMessage());

            throw new DAOException("Errore generico durante il recupero degli abbonamenti", e);

        }

        logger.info("SUCCESS: lettura di tutti gli abbonamenti, righe: "+abbonamenti.size());
        return abbonamenti;
    }

    public Abbonamento getAbbonamentoById(Connection connection, int abbonamentoId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ABBONAMENTO_BY_ID)) {

            preparedStatement.setInt(1, abbonamentoId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("SUCCESS: Lettura dell'abbonamento con ID:"+abbonamentoId);

                    return mapResultSetToAbbonamento(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Errore durante l'ottenimento del abbonamento con id: "+abbonamentoId+" ,errore:"+e.getMessage());
            throw new DAOException("Errore durante l'ottenimento del abbonamento con id: "+abbonamentoId, e);
            //return false;
        }
        catch(Exception e)
        {
            logger.error("Errore generico durante l'ottenimento del abbonamento con id: "+abbonamentoId+" ,errore:"+e.getMessage());

            throw new DAOException("Errore generico l'ottenimento del abbonamento con id: "+abbonamentoId, e);

        }
        return null;
    }

    public Abbonamento addAbbonamento(Connection connection, Abbonamento abbonamento) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ABBONAMENTO)) {

            preparedStatement.setString(1, convertTipoToDatabase(abbonamento.getTipo()));
            preparedStatement.setString(2, abbonamento.getDescrizione());
            preparedStatement.setFloat(3, abbonamento.getPrezzo());

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS: inserito abbonamento , rows:"+rowsAffected);

            abbonamento.setCodAb(getLastKey(connection));
            return abbonamento;
            
        }catch (SQLException e) {
            logger.error("SqlError");
            throw new DAOException("Errore durante l'aggiunta dell'abbonamento "+abbonamento.getDescrizione(), e);
            //return false;
        }
        catch(Exception e)
        {
            logger.error("");

            throw new DAOException("Errore generico durante l'aggiunta dell'abbonamento", e);

        }
    }

    public Abbonamento updateAbbonamento(Connection connection, Abbonamento abbonamento) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ABBONAMENTO)) {

            preparedStatement.setString(1, convertTipoToDatabase(abbonamento.getTipo()));
            preparedStatement.setString(2, abbonamento.getDescrizione());
            preparedStatement.setFloat(3, abbonamento.getPrezzo());
            preparedStatement.setInt(4, abbonamento.getCodAb());

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS: aggiornato abbonamento ,rows:"+rowsAffected);

            return abbonamento;
        }catch (SQLException e) {
            logger.error("");

            throw new DAOException("Errore durante l'aggiornamento dell'abbonamento con id"+abbonamento.getCodAb(), e);
            //return false;
        }
        catch(Exception e)
        {
            logger.error("");

            throw new DAOException("Errore generico durante l'aggiornamento dell'abbonamento con id"+abbonamento.getCodAb(), e);

        }
    }

    public boolean deleteAbbonamento(Connection connection, int abbonamentoId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ABBONAMENTO)) {

            preparedStatement.setInt(1, abbonamentoId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("");

            throw new DAOException("Errore durante la canancellazione dell'abbonamento con id:"+abbonamentoId, e);
            //return false;
        }
        catch(Exception e)
        {
            logger.error("");

            throw new DAOException("Errore generico durante la canancellazione dell'abbonamento con id:"+abbonamentoId, e);

        }
    }

    public List<Abbonamento> search(Connection connection, Abbonamento criteria) throws DAOException {
        List<Abbonamento> matchingAbbonamenti = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Abbonamento WHERE 1=1");

        if (criteria.getTipo() != null) {
            queryBuilder.append(" AND Tipo = ?");
        }
        if (criteria.getDescrizione() != null) {
            queryBuilder.append(" AND Descrizione LIKE ?");
        }
        if (criteria.getPrezzo() != 0) {
            queryBuilder.append(" AND Prezzo = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            if (criteria.getTipo() != null) {
                preparedStatement.setString(parameterIndex++, convertTipoToDatabase(criteria.getTipo()));
            }
            if (criteria.getDescrizione() != null) {
                preparedStatement.setString(parameterIndex++, "%" + criteria.getDescrizione() + "%");
            }
            if (criteria.getPrezzo() != 0) {
                preparedStatement.setFloat(parameterIndex++, criteria.getPrezzo());
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingAbbonamenti.add(mapResultSetToAbbonamento(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("");

            throw new DAOException("Errore durante la ricerca dell'abbonamento con criteri ", e);
            //return false;
        }
        catch(Exception e)
        {
            logger.error("");

            throw new DAOException("Errore generico durante la ricerca dell'abbonamento con criteri ", e);

        }

        logger.info("SUCCESS:");

        return matchingAbbonamenti;
    }

    private Abbonamento mapResultSetToAbbonamento(ResultSet resultSet) throws SQLException {
        Abbonamento abbonamento = new Abbonamento();
        abbonamento.setCodAb(resultSet.getInt("Cod_Ab"));
        abbonamento.setTipo(convertDatabaseToTipo(resultSet.getString("Tipo")));
        abbonamento.setDescrizione(resultSet.getString("Descrizione"));
        abbonamento.setPrezzo(resultSet.getFloat("Prezzo"));
        return abbonamento;
    }

    private String convertTipoToDatabase(Abbonamento.TipoAbbonamento tipo) {
        return tipo.name();
    }

    private Abbonamento.TipoAbbonamento convertDatabaseToTipo(String tipoString) {
        return Abbonamento.TipoAbbonamento.valueOf(tipoString);
    }
}