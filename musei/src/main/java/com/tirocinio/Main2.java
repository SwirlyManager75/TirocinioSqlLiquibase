package com.tirocinio;

import java.util.HashMap;
import java.util.Map;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.service.MuseoGenericService;

public class Main2 {

	static Map<String, String> serviceMap = new HashMap<>();

	private static void initServiceMap() {
		serviceMap.put("ABBONAMENTO_BIGLIETTERIA_SERVICE_CREATE",
				"com.tirocinio.service.Insert.CreateAbbonamentoToBiglietteriaService");
		serviceMap.put("Nicholas", "com.tirocinio.service.Insert.NicholasService");
		// serviceMap.put("ABBONAMENTO_BIGLIETTERIA_SERVICE_DELETE",
		// "com.tirocinio.service....");
		// serviceMap.put("ABBONAMENTO_BIGLIETTERIA_SERVICE_SEARCH",
		// "com.tirocinio.service....");
	}

	public static void main(String[] args) {

		initServiceMap();

		Map<Object, Object> myInput = new HashMap();
		Map<Object, Object> myOutput = new HashMap();

		// .....

		try {

			int scelta = 2;
			if (scelta == 1) {
				myInput.put("input1", 1);
				myInput.put("input2", 2);
				myOutput = callService("ABBONAMENTO_BIGLIETTERIA_SERVICE_CREATE", myInput);
				// myOutput
			} else if (scelta == 2) {
				myInput.put("input1", 10);
				myInput.put("input2", 20);
				myOutput = callService("Nicholas", myInput);
				// myOutput
			} else {
				//
			}

		} catch (ServiceException serExc) {
			// logger.error("Si è verificato un erorre", serExc);
			System.err.println(
					"Si è verificato un errore, rivolgersi all'amministratore riportando il seguente messaggio: "
							+ serExc.getMessage());
		}

	}

	private static Map<Object, Object> callService(String serviceName, Map<Object, Object> myInput)
			throws ServiceException {
		Map<Object, Object> myOutput = new HashMap<>();
		try {
			Class clazz = Class.forName(serviceMap.get(serviceName));
			MuseoGenericService myService = (MuseoGenericService) clazz.newInstance();
			myOutput = myService.execute(myInput);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myOutput;
	}

}
