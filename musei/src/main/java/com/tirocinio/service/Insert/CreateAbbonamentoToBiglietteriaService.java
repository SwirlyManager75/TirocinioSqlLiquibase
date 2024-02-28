package com.tirocinio.service.Insert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.impl.Abbonamento_BiglietteriaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.service.MuseoGenericService;

public class CreateAbbonamentoToBiglietteriaService implements MuseoGenericService {

	@Override
	public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
		Connection connection = ConnectionManager.getConnection();

		int id1 = (Integer) input.get("input1");
		int id2 = (Integer) input.get("input2");

//        int ret;
		try {
			Abbonamento_BiglietteriaDAOimpl abbBigDAO = new Abbonamento_BiglietteriaDAOimpl();
			// ret =
			abbBigDAO.addAbbonamentoBiglietteria(connection, id1, id2);

			Map<Object, Object> retVal = new HashMap<>();
			// retVal.put()

			connection.commit();

			return retVal;
		} catch (DAOException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					throw new ServiceException("Rollback non eseguito - errore", e1);
				}
			}
			throw new ServiceException(e);

		} catch (Exception e) {
			throw new ServiceException("Errore generico durante l'inserimento dell'abbonamento e biglietteria", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ServiceException("Errore durante la chiusura della connessione", e);
				}
			}
		}
	}

}
