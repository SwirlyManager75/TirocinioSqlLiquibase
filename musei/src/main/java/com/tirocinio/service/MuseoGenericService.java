package com.tirocinio.service;

import java.util.Map;

import com.tirocinio.exceptions.ServiceException;

public interface MuseoGenericService  {
	
	Map<Object, Object> execute (Map<Object, Object> input) throws ServiceException;

}
