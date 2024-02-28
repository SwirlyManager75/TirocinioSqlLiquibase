package com.tirocinio.service.GetById;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.AbbonamentoDAO;
import com.tirocinio.dao.impl.AbbonamentoDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Abbonamento;
import com.tirocinio.service.MuseoGenericService;

public class GetByIdAbbonamentoService  implements MuseoGenericService{

    private final AbbonamentoDAO abbonamentoDAO;
    

    public GetByIdAbbonamentoService( ) {
        this.abbonamentoDAO = new AbbonamentoDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input ) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        Map<Object, Object> output = new HashMap<>();

        
        try 
        {
            int codiceAbbonamento= (Integer)input.get("abbonamento");
            Abbonamento ret= abbonamentoDAO.getAbbonamentoById(connection, codiceAbbonamento);
            output.put("GetByIdAbbonamento", ret);
            return output;
        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }

    


}
