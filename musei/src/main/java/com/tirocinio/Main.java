package com.tirocinio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.model.Abbonamento;
import com.tirocinio.model.Artista;
import com.tirocinio.model.Audio;
import com.tirocinio.model.Biglietteria;
import com.tirocinio.model.Biglietto;
import com.tirocinio.model.Citta;
import com.tirocinio.model.Cliente;
import com.tirocinio.model.Dipendente;
import com.tirocinio.model.Museo;
import com.tirocinio.model.Opera;
import com.tirocinio.model.Poi;
import com.tirocinio.service.MuseoGenericService;
import com.tirocinio.service.Delete.DeleteAbbonamentoBiglietterieService;
import com.tirocinio.service.Delete.DeleteAbbonamentoService;
import com.tirocinio.service.Delete.DeleteArtistaService;
import com.tirocinio.service.Delete.DeleteAudioService;
import com.tirocinio.service.Delete.DeleteBiglietteriaService;
import com.tirocinio.service.Delete.DeleteBigliettoService;
import com.tirocinio.service.Delete.DeleteCityService;
import com.tirocinio.service.Delete.DeleteClienteAbbonamentoService;
import com.tirocinio.service.Delete.DeleteClienteService;
import com.tirocinio.service.Delete.DeleteDipendenteService;
import com.tirocinio.service.Delete.DeleteMuseumService;
import com.tirocinio.service.Delete.DeleteOperaService;
import com.tirocinio.service.Delete.DeletePoiService;
import com.tirocinio.service.GetAll.GetAllAbbonamentiService;
import com.tirocinio.service.GetAll.GetAllArtistiService;
import com.tirocinio.service.GetAll.GetAllAudiosService;
import com.tirocinio.service.GetAll.GetAllBiglietterieService;
import com.tirocinio.service.GetAll.GetAllBigliettiService;
import com.tirocinio.service.GetAll.GetAllCitiesService;
import com.tirocinio.service.GetAll.GetAllClientiService;
import com.tirocinio.service.GetAll.GetAllDipendentiService;
import com.tirocinio.service.GetAll.GetAllMuseumsService;
import com.tirocinio.service.GetAll.GetAllOpereService;
import com.tirocinio.service.GetAll.GetAllPoisService;
import com.tirocinio.service.GetById.GetByIdAbbonamentoService;
import com.tirocinio.service.GetById.GetByIdArtistiService;
import com.tirocinio.service.GetById.GetByIdAudiosService;
import com.tirocinio.service.GetById.GetByIdBiglietterieService;
import com.tirocinio.service.GetById.GetByIdBigliettoService;
import com.tirocinio.service.GetById.GetByIdCityService;
import com.tirocinio.service.GetById.GetByIdClienteService;
import com.tirocinio.service.GetById.GetByIdDipendenteService;
import com.tirocinio.service.GetById.GetByIdMuseoService;
import com.tirocinio.service.GetById.GetByIdOperaService;
import com.tirocinio.service.GetById.GetByIdPoiService;
import com.tirocinio.service.Insert.CreateAbbonamentoService;
import com.tirocinio.service.Insert.CreateAbbonamentoToBiglietteriaService;
import com.tirocinio.service.Insert.CreateArtistaService;
import com.tirocinio.service.Insert.CreateAudioService;
import com.tirocinio.service.Insert.CreateBiglietteriaService;
import com.tirocinio.service.Insert.CreateBigliettoService;
import com.tirocinio.service.Insert.CreateCityService;
import com.tirocinio.service.Insert.CreateClienteService;
import com.tirocinio.service.Insert.CreateClienteToAbbonamentoService;
import com.tirocinio.service.Insert.CreateDipendenteService;
import com.tirocinio.service.Insert.CreateMuseumService;
import com.tirocinio.service.Insert.CreateOperaService;
import com.tirocinio.service.Insert.CreatePoiService;
import com.tirocinio.service.Search.SearchAbbonamentiForBiglietteriaService;
import com.tirocinio.service.Search.SearchAbbonamentiForClienteService;
import com.tirocinio.service.Search.SearchAbbonamentoService;
import com.tirocinio.service.Search.SearchArtistaService;
import com.tirocinio.service.Search.SearchAudioService;
import com.tirocinio.service.Search.SearchBiglietteriaService;
import com.tirocinio.service.Search.SearchBiglietterieForAbbonamentoService;
import com.tirocinio.service.Search.SearchBigliettoService;
import com.tirocinio.service.Search.SearchCityService;
import com.tirocinio.service.Search.SearchClienteService;
import com.tirocinio.service.Search.SearchClientiForAbbonamento;
import com.tirocinio.service.Search.SearchDipendenteService;
import com.tirocinio.service.Search.SearchMuseumService;
import com.tirocinio.service.Search.SearchOperaService;
import com.tirocinio.service.Search.SearchPoiService;
import com.tirocinio.service.Update.UpdateAbbonamentoService;
import com.tirocinio.service.Update.UpdateArtistaService;
import com.tirocinio.service.Update.UpdateAudioService;
import com.tirocinio.service.Update.UpdateBiglietteriaService;
import com.tirocinio.service.Update.UpdateBigliettoService;
import com.tirocinio.service.Update.UpdateCityService;
import com.tirocinio.service.Update.UpdateAbbonamentoBiglietterieService;
import com.tirocinio.service.Update.UpdateAbbonamentoForClienteService;
import com.tirocinio.service.Update.UpdateClienteService;
import com.tirocinio.service.Update.UpdateDipendenteService;
import com.tirocinio.service.Update.UpdateMuseumService;
import com.tirocinio.service.Update.UpdateOperaService;
import com.tirocinio.service.Update.UpdatePoiService;

public class Main 
{
    private static Map<String,String> serviceMap = new HashMap<>();

    private static Map<Object, Object> callService(String serviceName, Map<Object, Object> myInput) throws ServiceException 
    {
		Map<Object, Object> myOutput = new HashMap<>();
		try {
			Class clazz = Class.forName(serviceMap.get(serviceName));
			MuseoGenericService myService = (MuseoGenericService) clazz.newInstance();
			myOutput = myService.execute(myInput);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
        
		return myOutput;
	}

    private static void initServiceMap() {
		serviceMap.put("DeleteAbbonamentoService","com.tirocinio.service.Delete.DeleteAbbonamentoService");
		serviceMap.put("DeleteAbbonanentoBiglietterieService", "com.tirocinio.service.Delete.DeleteAbbonamentoBiglietterieService");
        serviceMap.put("DeleteArtistaService", "com.tirocinio.service.Delete.DeleteArtistaService");
        serviceMap.put("DeleteAudioService", "com.tirocinio.service.Delete.DeleteAudioService");
        serviceMap.put("DeleteBiglietteriaService", "com.tirocinio.service.Delete.DeleteBiglietteriaService");
        serviceMap.put("DeleteBigliettoService", "com.tirocinio.service.Delete.DeleteBigliettoService");
        serviceMap.put("DeleteCityService", "com.tirocinio.service.Delete.DeleteCityService");
        serviceMap.put("DeleteClienteAbbonamentoService", "com.tirocinio.service.Delete.DeleteClienteAbbonamentoService");
        serviceMap.put("DeleteClienteService", "com.tirocinio.service.Delete.DeleteClienteService");
        serviceMap.put("DeleteDipendenteService", "com.tirocinio.service.Delete.DeleteDipendenteService");
        serviceMap.put("DeleteMuseumService", "com.tirocinio.service.Delete.DeleteMuseumService");
        serviceMap.put("DeleteOperaService", "com.tirocinio.service.Delete.DeleteOperaService");
        serviceMap.put("DeletePoiService", "com.tirocinio.service.Delete.DeletePoiService");
        serviceMap.put("GetAllAbbonamentiService", "com.tirocinio.service.GetAll.GetAllAbbonamentiService");
        serviceMap.put("GetAllArtistiService", "com.tirocinio.service.GetAll.GetAllArtistiService");
        serviceMap.put("GetAllAudiosService", "com.tirocinio.service.GetAll.GetAllAudiosService");
        serviceMap.put("GetAllBiglietterieService", "com.tirocinio.service.GetAll.GetAllBiglietterieService");
        serviceMap.put("GetAllBigliettiService", "com.tirocinio.service.GetAll.GetAllBigliettiService");
        serviceMap.put("GetAllCitiesService", "com.tirocinio.service.GetAll.GetAllCitiesService");
        serviceMap.put("GetAllClientiService", "com.tirocinio.service.GetAll.GetAllClientiService");
        serviceMap.put("GetAllDipendentiService", "com.tirocinio.service.GetAll.GetAllDipendentiService");
        serviceMap.put("GetAllMuseumService", "com.tirocinio.service.GetAll.GetAllMuseumService");
        serviceMap.put("GetAllOpereService", "com.tirocinio.service.GetAll.GetAllOpereService");
        serviceMap.put("GetAllPoisService", "com.tirocinio.service.GetAll.GetAllPoisService");
        serviceMap.put("GetByIdAbbonamentoService", "com.tirocinio.service.GetById.GetByIdAbbonamentoService");
        serviceMap.put("GetByIdArtistiService", "com.tirocinio.service.GetById.GetByIdArtistiService");
        serviceMap.put("GetByIdAudiosService", "com.tirocinio.service.GetById.GetByIdAudiosService");
        serviceMap.put("GetByIdBiglietterieService", "com.tirocinio.service.GetById.GetByIdBiglietterieService");
        serviceMap.put("GetByIdCityService", "com.tirocinio.service.GetById.GetByIdCityService");
        serviceMap.put("GetByIdClienteService", "com.tirocinio.service.GetById.GetByIdClienteService");
        serviceMap.put("GetByIdMuseoService", "com.tirocinio.service.GetById.GetByIdMuseoService");
        serviceMap.put("GetByIdOperaService", "com.tirocinio.service.GetById.GetByIdOperaService");
        serviceMap.put("GetByIdPoiService", "com.tirocinio.service.GetById.GetByIdPoiService");
        serviceMap.put("CreateAbbonamentoService", "com.tirocinio.service.Create.CreateAbbonamentoService");
        serviceMap.put("CreateAbbonamentoToBiglietteriaService", "com.tirocinio.service.Create.CreateAbbonamentoToBiglietteriaService");
        serviceMap.put("CreateArtistaService", "com.tirocinio.service.Create.CreateArtistaService");
        serviceMap.put("CreateAudioService", "com.tirocinio.service.Create.CreateAudioService");
        serviceMap.put("CreateBiglietteriaService", "com.tirocinio.service.Create.CreateBiglietteriaService");
        serviceMap.put("CreateBigliettoService", "com.tirocinio.service.Create.CreateBigliettoService");
        serviceMap.put("CreateCityService", "com.tirocinio.service.Create.CreateCityService");
        serviceMap.put("CreateClienteService", "com.tirocinio.service.Create.CreateClienteService");
        serviceMap.put("CreateClienteToAbbonamentoService", "com.tirocinio.service.Create.CreateClienteToAbbonamentoService");
        serviceMap.put("CreateMuseumService", "com.tirocinio.service.Create.CreateMuseumService");
        serviceMap.put("CreateOperaService", "com.tirocinio.service.Create.CreateOperaService");
        serviceMap.put("CreatePoiService", "com.tirocinio.service.Create.CreatePoiService");
        serviceMap.put("SearchAbbonamentiForBiglietteriaService", "com.tirocinio.service.Search.SearchAbbonamentiForBiglietteriaService");
        serviceMap.put("SearchAbbonamentiForClienteService", "com.tirocinio.service.Search.SearchAbbonamentiForClienteService");
        serviceMap.put("SearchAbbonamentoService", "com.tirocinio.service.Search.SearchAbbonamentoService");
        serviceMap.put("SearchArtistaService", "com.tirocinio.service.Search.SearchArtistaService");
        serviceMap.put("SearchAudioService", "com.tirocinio.service.Search.SearchAudioService");
        serviceMap.put("SearchBiglietteriaService", "com.tirocinio.service.Search.SearchBiglietteriaService");
        serviceMap.put("SearchBiglietterieForAbbonamentoService", "com.tirocinio.service.Search.SearchBiglietterieForAbbonamentoService");
        serviceMap.put("SearchBigliettoService", "com.tirocinio.service.Search.SearchBigliettoService");
        serviceMap.put("SearchCityService", "com.tirocinio.service.Search.SearchCityService");
        serviceMap.put("SearchClienteService", "com.tirocinio.service.Search.SearchClienteService");
        serviceMap.put("SearchClienteForAbbonamentoService", "com.tirocinio.service.Search.SearchClienteForAbbonamentoService");
        serviceMap.put("SearchDipendenteService", "com.tirocinio.service.Search.SearchDipendenteService");
        serviceMap.put("SearchMuseumService", "com.tirocinio.service.Search.SearchMuseumService");
        serviceMap.put("SearchOperaService", "com.tirocinio.service.Search.SearchOperaService");
        serviceMap.put("SearchPoiService", "com.tirocinio.service.Search.SearchPoiService");
        serviceMap.put("UpdateAbbonamentoBiglietterieService", "com.tirocinio.service.Update.UpdateAbbonamentoBiglietterieService");
        serviceMap.put("UpdateAbbonamentoForClienteService", "com.tirocinio.service.Update.UpdateAbbonamentoForClienteService");
        serviceMap.put("UpdateAbbonamentoService", "com.tirocinio.service.Update.UpdateAbbonamentoService");
        serviceMap.put("UpdateArtistaService", "com.tirocinio.service.Update.UpdateArtistaService");
        serviceMap.put("UpdateAudioService", "com.tirocinio.service.Update.UpdateAudioService");
        serviceMap.put("UpdateBiglietteriaService", "com.tirocinio.service.Update.UpdateBiglietteriaService");
        serviceMap.put("UpdateBigliettoService", "com.tirocinio.service.Update.UpdateBigliettoService");
        serviceMap.put("UpdateCityService", "com.tirocinio.service.Update.UpdateCityService");
        serviceMap.put("UpdateClienteService", "com.tirocinio.service.Update.UpdateClienteService");
        serviceMap.put("UpdateDipendenteService", "com.tirocinio.service.Update.UpdateDipendenteService");
        serviceMap.put("UpdateMuseoService", "com.tirocinio.service.Update.UpdateMuseoService");
        serviceMap.put("UpdateDipendenteService", "com.tirocinio.service.Update.UpdateDipendenteService");
        serviceMap.put("UpdateMuseumService", "com.tirocinio.service.Update.UpdateMuseumService");
        serviceMap.put("UpdateOperaService", "com.tirocinio.service.Update.UpdateOperaService");
        serviceMap.put("UpdatePoiService", "com.tirocinio.service.Update.UpdatePoiService");
	}
    public static void main(String[] args) throws NumberFormatException, IOException, SQLException 
    {
        //Inizializzo la connessione
        System.out.println(System.getProperty("user.dir"));

        Logger logger=LogManager.getLogger(Main.class);

        Connection connection=null;
        try
        {
        connection = ConnectionManager.getConnection();
        connection.setAutoCommit(false);
        }
        catch(SQLException e)
        {
            logger.error("Impossibile stabilire una connession con il database");
        } 

        initServiceMap();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        int scelta=0;
        int subscleta=0;
        String arr[] = {"Audio","POI","Museo","Citta","Cliente","Dipendente","Artista","Opere","Biglietteria","Biglietto","Abbonamento","Abbonamento_Biglietteria","Cliente_Abbonamento"};
        Map<Object, Object> myInput = new HashMap();
		Map<Object, Object> myOutput = new HashMap();

        do
        {
            System.out.println("1)Audio");
            System.out.println("2)POI");
            System.out.println("3)Museo");
            System.out.println("4)Citta");
            System.out.println("5)Cliente");
            System.out.println("6)Dipendente");
            System.out.println("7)Artista");
            System.out.println("8)Opere");
            System.out.println("9)Biglietteria");
            System.out.println("10)Biglietto");
            System.out.println("11)Abbonamento");
            System.out.println("12)Abbonamento_Biglietteria");
            System.out.println("13)Cliente_Abbonamento");
            System.out.println("14)Esci");

            scelta= Integer.parseInt(in.readLine());
            subscleta=0;
            switch (scelta) 
            {
    
                    case 1:

                            do 
                            {
                                System.out.println("1)Insert "+ arr[scelta-1]);
                                System.out.println("2)Update  "+ arr[scelta-1]);
                                System.out.println("3)Delete  "+ arr[scelta-1]);
                                System.out.println("4)Get  "+ arr[scelta-1]);
                                System.out.println("5)Get All  "+ arr[scelta-1]);
                                System.out.println("6)Search  "+ arr[scelta-1]);
                                System.out.println("7)Esci");

                                Audio audio = new Audio();
                                List<Audio> audioList = new ArrayList<>();
                                

                                subscleta= Integer.parseInt(in.readLine());
                                switch (subscleta) {
                                    case 1:
                                            
                                        System.out.println("Inserisci la URL dell'audio");
                                        audio.setUrl(in.readLine());

                                        try {
                                            myOutput=callService("CreateAudioService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                    break;

                                    case 2:

                                        System.out.println("Inserisci la URL modificata");
                                        audio.setUrl(in.readLine());
                                        System.out.println("Inserisci il codice dell'audio per la ricerca");
                                        audio.setCodAu(Integer.parseInt(in.readLine()));

                                        try {
                                            myOutput=callService("UpdateAudioService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }


                                    break;

                                    case 3:

                                        System.out.println("Inserisci il codice dell'audio per la cancellazione");
                                        audio.setCodAu(Integer.parseInt(in.readLine()));

                                        try {
                                            myOutput=callService("DeleteAudioService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }


                                    break;

                                    case 4:

                                        System.out.println("Inserisci il codice dell'audio per la ricerca");
                                        audio.setCodAu(Integer.parseInt(in.readLine()));

                                        try {
                                            myOutput=callService("GetByIdAudioService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }


                                        System.out.println("Audio.URL: " + audio.getUrl());
                                    break;

                                    case 5:

                                        try {
                                            myOutput=callService("GetAllAudioService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }


                                        for(Audio aud : audioList)
                                        {
                                            System.out.println("Audio.Cod_Au:"+aud.getCodAu()+"  Audio.Url:"+aud.getUrl());
                                        }

                                    break;

                                    case 6:
                                        SearchAudioService searchAudioService = new SearchAudioService();
                                        
                                        try {
                                            myOutput=callService("SearchAudioService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                        for(Audio aud : audioList)
                                        {
                                            System.out.println("Audio.Cod_Au:"+aud.getCodAu()+"  Audio.Url:"+aud.getUrl());
                                        }
                                    break;
                                
                                    default:

                                    break;
                                }
                                
                            } while (subscleta < 7);
                    
                    break;

                    case 2:
                        
                            do 
                            {
                                System.out.println("1)Insert  "+ arr[scelta-1]);
                                System.out.println("2)Update  "+ arr[scelta-1]);
                                System.out.println("3)Delete  "+ arr[scelta-1]);
                                System.out.println("4)Get  "+ arr[scelta-1]);
                                System.out.println("5)Get All  "+ arr[scelta-1]);
                                System.out.println("6)Search  "+ arr[scelta-1]);
                                System.out.println("7)Esci");

                                Poi poi= new Poi();
                                List<Poi> poiList= new ArrayList<>();

                                subscleta= Integer.parseInt(in.readLine());
                                switch (subscleta) {
                                    case 1:
                                        System.out.println("Inserisci la descriozione dell'POI");
                                        poi.setDescrizione(in.readLine());

                                        try {
                                            myOutput=callService("CreatePoiService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                    break;

                                    case 2:
                                        System.out.println("Inserisci la Descrizione del POI modificata");
                                        poi.setDescrizione(in.readLine());
                                        System.out.println("Inserisci il codice del POI per la ricerca");
                                        poi.setCodPoi(Integer.parseInt(in.readLine()));

                                         try {
                                            myOutput=callService("UpdatePoiService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    break;

                                    case 3:
                                        System.out.println("Inserisci il codice del POI per la cancellazione");
                                        poi.setCodPoi(Integer.parseInt(in.readLine()));

                                        try {
                                            myOutput=callService("DeletePoiService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    break;

                                    case 4:
                                        System.out.println("Inserisci il codice del POI per la ricerca");
                                        poi.setCodPoi(Integer.parseInt(in.readLine()));

                                        try {
                                            myOutput=callService("GetByIdPoiService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    break;

                                    case 5:
                                        try {
                                            myOutput=callService("GetAllPoiService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                        for(Poi poi2 : poiList)
                                        {
                                            System.out.println("Poi.CodPoi:"+poi2.getCodPoi()+"  Poi.Descrizione:"+poi2.getDescrizione());
                                        }
                                        
                                    break;

                                    case 6:
                                        SearchPoiService searchPoiService = new SearchPoiService();
                                        System.out.println("Inserisci la stringa che funge da criterio (si cerca l'occorrenza della stringa dentro la Descrizione)");

                                        poi.setDescrizione(in.readLine());

                                        try {
                                            myOutput=callService("SearchPoiService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                        for(Poi poi2 : poiList)
                                        {
                                            System.out.println("Poi.CodPoi:"+poi2.getCodPoi()+"  Poi.Descrizione:"+poi2.getDescrizione());
                                        }
                                    break;
                                
                                    default:

                                    break;
                                }
                                
                            } while (subscleta < 7);
                    
                    break;

                    case 3:
                            do 
                            {
                                System.out.println("1)Insert  "+ arr[scelta-1]);
                                System.out.println("2)Update  "+ arr[scelta-1]);
                                System.out.println("3)Delete  "+ arr[scelta-1]);
                                System.out.println("4)Get  "+ arr[scelta-1]);
                                System.out.println("5)Get All  "+ arr[scelta-1]);
                                System.out.println("6)Search  "+ arr[scelta-1]);
                                System.out.println("7)Esci");

                                Museo museo = new Museo();
                                List<Museo> museoList = new ArrayList<>();

                                subscleta= Integer.parseInt(in.readLine());
                                switch (subscleta) {
                                    case 1:
                                        System.out.println("Inserisci il nome del Museo");
                                        museo.setNome(in.readLine());
                                        System.out.println("Inserisci la via del Museo se ne sei a conoscenza");
                                        museo.setVia(in.readLine());

                                        try {
                                            myOutput=callService("CreateMuseumService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    break;

                                    case 2:
                                        System.out.println("Inserisci il nome sostituitivo del Museo ");
                                        museo.setNome(in.readLine());
                                        System.out.println("Inserisci la via sostituitiva del Museo se ne sei a conoscenza");
                                        museo.setVia(in.readLine());

                                        try {
                                            myOutput=callService("UpdateMuseumService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    break;

                                    case 3:
                                        System.out.println("Inserisci il codice del Museo per la cancellazione");
                                        museo.setCodM(Integer.parseInt(in.readLine()));

                                        try {
                                            myOutput=callService("DeleteMuseumService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    break;

                                    case 4:
                                        System.out.println("Inserisci il codice del Museo per la ricerca");
                                        museo.setCodM(Integer.parseInt(in.readLine()));

                                        try {
                                            myOutput=callService("GetByIdMuseumService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                        
                                        System.out.println("Museo.CodM:" + museo.getCodM()+"Museo.Nome:"+museo.getNome()+"Museo.Via:"+museo.getVia());
                                    break;

                                    case 5:
                                        try {
                                            myOutput=callService("GetAllMuseumService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                        for(Museo museo2  : museoList)
                                        {
                                            System.out.println("Museo.CodM:" + museo2.getCodM()+"Museo.Nome:"+museo2.getNome()+"Museo.Via:"+museo2.getVia());
                                        }
                                        
                                    break;

                                    case 6:
                                        try {
                                            myOutput=callService("SearchMuseumService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                        for(Museo museo2  : museoList)
                                        {
                                            System.out.println("Museo.CodM:" + museo2.getCodM()+"Museo.Nome:"+museo2.getNome()+"Museo.Via:"+museo2.getVia());
                                        }
                                    break;
                                
                                    default:

                                    break;
                                }
                                
                            } while (subscleta < 7);
                    
                    break;

                    case 4:
                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update "+ arr[scelta-1]);
                                    System.out.println("3)Delete "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");
                                    
                                    Citta citta = new Citta();
                                    List<Citta> cittaList = new ArrayList<>();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) {
                                        case 1:
                                            System.out.println("Inserisci il nome della Citta");
                                            citta.setNome(in.readLine());
                                            System.out.println("Inserisci true se la citta è una provincia");
                                            citta.setProvincia(Boolean.parseBoolean(in.readLine())); //inserire true nella stringa
    
                                            try {
                                                myOutput=callService("CreateCityService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 2:
                                            System.out.println("Inserisci il nome sostituitivo della Città");
                                            citta.setNome(in.readLine());
                                            System.out.println("Inserisci true se la Città è una provincia");
                                            citta.setProvincia(Boolean.parseBoolean(in.readLine())); //inserire true nella stringa

                                            try {
                                                myOutput=callService("UpdateCityService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice della Città per la cancellazione");
                                            citta.setCodCi(Integer.parseInt(in.readLine()));
    
                                            try {
                                                myOutput=callService("DeleteCityService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice della Città per la ricerca");
                                            citta.setCodCi(Integer.parseInt(in.readLine()));
    
                                            try {
                                                myOutput=callService("GetByIdCityService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            System.out.println("Citta.CodCi:" + citta.getCodCi()+"Citta.Nome:"+citta.getNome()+"Citta.Provincia:"+citta.isProvincia());
                                        break;
    
                                        case 5:
                                            try {
                                                myOutput=callService("GetAllCityService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
                                            for(Citta citta2  : cittaList)
                                            {
                                                System.out.println("Citta.CodCi:" + citta2.getCodCi()+"Citta.Nome:"+citta2.getNome()+"Citta.Provincia:"+citta2.isProvincia());
                                            }
                                            
                                        break;
    
                                        case 6:
                                            SearchCityService searchCityService = new SearchCityService();

                                            System.out.println("Inserisci la stringa che funge da criterio (si cerca l'occorrenza della stringa dentro il Nome)");
                                            citta.setNome(in.readLine());
                                            System.out.println("Inserisci se la Città è una provincia (si cerca l'occorrenza della stringa dentro la Via)");
                                            citta.setProvincia(Boolean.parseBoolean(in.readLine()));
    
                                            try {
                                                myOutput=callService("SearchCityService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
                                            for(Citta citta2  : cittaList)
                                            {
                                                System.out.println("Citta.CodCi:" + citta2.getCodCi()+"Citta.Nome:"+citta2.getNome()+"Citta.Provincia:"+citta2.isProvincia());
                                            }
                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break; 

                    case 5:
                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    Cliente cliente = new Cliente();
                                    List<Cliente> clientList = new ArrayList<>();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) {
                                        case 1:
                                            System.out.println("Inserisci il nome del Cliente");
                                            cliente.setNome(in.readLine());
                                            System.out.println("Inserisci il cognome del Cliente se pervenuto");
                                            cliente.setCognome(in.readLine());
                                            System.out.println("Inserisci il cellulare del Cliente se pervenuto");
                                            cliente.setCellulare(in.readLine());
                                            System.out.println("Inserisci la mail del Cliente se pervenuta");
                                            cliente.setMail(in.readLine());
                                            
                                            try {
                                                myOutput=callService("CreateClienteService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 2:
                                            System.out.println("Inserisci il nome sostituitivo del Cliente");
                                            cliente.setNome(in.readLine());
                                            System.out.println("Inserisci il cognome sostituitivo del Cliente se pervenuto");
                                            cliente.setCognome(in.readLine());
                                            System.out.println("Inserisci il cellulare sostituitivo del Cliente se pervenuto");
                                            cliente.setCellulare(in.readLine());
                                            System.out.println("Inserisci la mail sostituitiva del Cliente se pervenuta");
                                            cliente.setMail(in.readLine());
                                            
                                            try {
                                                myOutput=callService("UpdateClienteService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice del Cliente per la cancellazione");
                                            cliente.setCodCli(Integer.parseInt(in.readLine()));
    
                                            try {
                                                myOutput=callService("DeleteClienteService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice del Cliente per la ricerca");
                                            cliente.setCodCli(Integer.parseInt(in.readLine()));

                                            try {
                                                myOutput=callService("GetByIdClienteService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            
                                            System.out.println("Cliente.CodCli:" + cliente.getCodCli()+" Cliente.Nome:"+cliente.getNome()+" Cliente.Cognome:"+cliente.getCognome()+"Cliente.Cellulare:"+cliente.getCellulare()+"Cliente.Mail:"+cliente.getMail());
                                        break;
    
                                        case 5:
                                            try {
                                                myOutput=callService("GetAllClientiService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
                                            for(Cliente cliente2  : clientList)
                                            {
                                                System.out.println("Cliente.CodCli:" + cliente2.getCodCli()+" Cliente.Nome:"+cliente2.getNome()+" Cliente.Cognome:"+cliente2.getCognome()+"Cliente.Cellulare:"+cliente2.getCellulare()+"Cliente.Mail:"+cliente2.getMail());
                                            }
                                            
                                        break;
    
                                        case 6:
                                            SearchClienteService searchClienteService = new SearchClienteService();

                                            System.out.println("Inserimento Criteri di ricerca");
                                            System.out.println("Inserisci il nome del Cliente");
                                            cliente.setNome(in.readLine());
                                            System.out.println("Inserisci il cognome del Cliente se pervenuto");
                                            cliente.setCognome(in.readLine());
                                            System.out.println("Inserisci il cellulare del Cliente se pervenuto");
                                            cliente.setCellulare(in.readLine());
                                            System.out.println("Inserisci la mail del Cliente se pervenuta");
                                            cliente.setMail(in.readLine());
    
                                            try {
                                                myOutput=callService("SearchClienteService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
                                            for(Cliente cliente2  : clientList)
                                            {
                                                System.out.println("Cliente.CodCli:" + cliente2.getCodCli()+" Cliente.Nome:"+cliente2.getNome()+" Cliente.Cognome:"+cliente2.getCognome()+"Cliente.Cellulare:"+cliente2.getCellulare()+"Cliente.Mail:"+cliente2.getMail());
                                            }
                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 6:
                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    Dipendente dipendente = new Dipendente();
                                    List<Dipendente> dipList = new ArrayList<>();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) {
                                        case 1:
                                            System.out.println("Inserisci il nome del Dipendente");
                                            dipendente.setNome(in.readLine());
                                            System.out.println("Inserisci il Codice Fiscale del Dipendente se pervenuto");
                                            dipendente.setCodiceFiscale(in.readLine());
                                            System.out.println("Inserisci il cellulare del Dipendente se pervenuto");
                                            dipendente.setCellulare(in.readLine());
                                            System.out.println("Inserisci la data di nascita nel formato 'yyyy-MM-dd' del Dipendente");
                                            dipendente.setDataNascita(Date.valueOf(in.readLine()));
                                            
                                            try {
                                                myOutput=callService("CreateDipendenteService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 2:
                                            
                                            System.out.println("Inserisci il nome sostituitivo del Dipendente");
                                            dipendente.setNome(in.readLine());
                                            System.out.println("Inserisci il Codice Fiscale sostituitivo del Dipendente se pervenuto");
                                            dipendente.setCodiceFiscale(in.readLine());
                                            System.out.println("Inserisci il cellulare sostituitivo del Dipendente se pervenuto");
                                            dipendente.setCellulare(in.readLine());
                                            System.out.println("Inserisci la data di nascita sostituitiva nel formato 'yyyy-MM-dd' del Dipendente se pervenuta");
                                            dipendente.setDataNascita(Date.valueOf(in.readLine()));
                                            
                                             try {
                                                myOutput=callService("UpdateDipendenteService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice del Dipendente per la cancellazione");
                                            dipendente.setCodD(Integer.parseInt(in.readLine()));
    
                                            try {
                                                myOutput=callService("DeleteDipendenteService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice del Dipendente");
                                            dipendente.setCodD(Integer.parseInt(in.readLine()));

                                            try {
                                                myOutput=callService("GetByIdDipendenteService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            
                                            System.out.println("Dipedente.CodCli:" + dipendente.getCodD()+" Dipedente.Nome:"+dipendente.getNome()+" Dipedente.CodiceFiscale:"+dipendente.getCodiceFiscale()+"Dipedente.Cellulare:"+dipendente.getCellulare()+"Dipedente.Data_Di_Nascita:"+dipendente.getDataNascita());

                                        break;
    
                                        case 5:
                                            try {
                                                myOutput=callService("GetAllDipendentiService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
                                            for(Dipendente dip  : dipList)
                                            {
                                                System.out.println("Dipedente.CodCli:" + dip.getCodD()+" Dipedente.Nome:"+dip.getNome()+" Dipedente.CodiceFiscale:"+dip.getCodiceFiscale()+"Dipedente.Cellulare:"+dip.getCellulare()+"Dipedente.Data_Di_Nascita:"+dip.getDataNascita());
                                            }
                                            
                                        break;
    
                                        case 6:
                                            

                                            System.out.println("Inserimento Criteri di ricerca");
                                            System.out.println("Inserisci il nome del Dipendente");
                                            dipendente.setNome(in.readLine());
                                            System.out.println("Inserisci il Codice Fiscale del Dipendente se pervenuto");
                                            dipendente.setCodiceFiscale(in.readLine());
                                            System.out.println("Inserisci il cellulare del Dipendente se pervenuto");
                                            dipendente.setCellulare(in.readLine());
                                            System.out.println("Inserisci la data di nascita nel formato 'yyyy-MM-dd' del Dipendente");
                                            dipendente.setDataNascita(Date.valueOf(in.readLine()));
    
                                            try {
                                                myOutput=callService("SearchDipendenteService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
                                            for(Dipendente dip  : dipList)
                                            {
                                                System.out.println("Dipedente.CodCli:" + dip.getCodD()+" Dipedente.Nome:"+dip.getNome()+" Dipedente.CodiceFiscale:"+dip.getCodiceFiscale()+"Dipedente.Cellulare:"+dip.getCellulare()+"Dipedente.Data_Di_Nascita:"+dip.getDataNascita());
                                            }
                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 7:
                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    Artista artista = new Artista();
                                    List<Artista> artList= new ArrayList<>();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) 
                                    {
                                        case 1:
                                            System.out.println("Inserisci il nome del Artista");
                                            artista.setNome(in.readLine());
                                            System.out.println("Inserisci il cognome del Artista");
                                            artista.setCognome(in.readLine());
                                            System.out.println("Inserisci true se l'Artista è in vita ");
                                            artista.setInVita(Boolean.parseBoolean(in.readLine()));
                                            System.out.println("Inserisci la data di nascita del Artista se pervenuta");
                                            artista.setDataNascita(Date.valueOf(in.readLine()));
                                            
                                            try {
                                                myOutput=callService("CreateArtistaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 2:
                                            System.out.println("Inserisci il nome sostituitivo del Artista");
                                            artista.setNome(in.readLine());
                                            System.out.println("Inserisci il cognome sostituitivo del Artista");
                                            artista.setCognome(in.readLine());
                                            System.out.println("Inserisci true se l'Artista è in vita ");
                                            artista.setInVita(Boolean.parseBoolean(in.readLine()));
                                            System.out.println("Inserisci la data di nascita sostituitiva del Artista se pervenuta");
                                            artista.setDataNascita(Date.valueOf(in.readLine()));
                                            
                                            try {
                                                myOutput=callService("UpdateArtistaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice del Artista per la cancellazione");
                                            artista.setCodAr(Integer.parseInt(in.readLine()));
    
                                            try {
                                                myOutput=callService("DeleteArtistaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice del Artista per la ricerca");
                                            artista.setCodAr(Integer.parseInt(in.readLine()));

                                            try {
                                                myOutput=callService("GetByIdArtistaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            
                                            System.out.println("Artista.CodAr:" + artista.getCodAr()+" Artista.Nome:"+artista.getNome()+" Artista.Cognome:"+artista.getCognome()+"Artista.In_Vita:"+artista.isInVita()+"Artista.Data_Nascita:"+artista.getDataNascita());
                                        break;
    
                                        case 5:
                                            try {
                                                myOutput=callService("GetAllArtistiService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            for(Artista art : artList)
                                            {
                                                System.out.println("|Artista.CodAr:" + art.getCodAr()+"| Artista.Nome:"+art.getNome()+"| Artista.Cognome:"+art.getCognome()+" |Artista.In_Vita:"+art.isInVita()+" |Artista.Data_Nascita:"+art.getDataNascita());
                                            }
                                            
                                        break;
    
                                        case 6:
                                            System.out.println("Inserisci il nome del Artista");
                                            artista.setNome(in.readLine());
                                            System.out.println("Inserisci il cognome del Artista");
                                            artista.setCognome(in.readLine());
                                            System.out.println("Inserisci true se l'Artista è in vita ");
                                            artista.setInVita(Boolean.parseBoolean(in.readLine()));
                                            System.out.println("Inserisci la data di nascita del Artista se pervenuta");
                                            artista.setDataNascita(Date.valueOf(in.readLine()));
    
                                            try {
                                                myOutput=callService("SearchArtistaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
                                            for(Artista art : artList)
                                            {
                                                System.out.println("|Artista.CodAr:" + art.getCodAr()+"| Artista.Nome:"+art.getNome()+"| Artista.Cognome:"+art.getCognome()+" |Artista.In_Vita:"+art.isInVita()+" |Artista.Data_Nascita:"+art.getDataNascita());
                                            }

                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 8:
                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");
                                    
                                    Opera opera = new Opera();
                                    List<Opera> operList = new ArrayList<>();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) 
                                    {
                                        case 1:

                                            System.out.println("Inserisci il nome dell'Opera");
                                            opera.setNome(in.readLine());
                                            System.out.println("Inserisci la descrizione dell'Opera");
                                            opera.setDescrizione(in.readLine());

                                            try {
                                                myOutput=callService("CreateOperaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 2:

                                            System.out.println("Inserisci il nome sostituitivo dell'Opera");
                                            opera.setNome(in.readLine());
                                            System.out.println("Inserisci la descrizione sostituitiva dell'Opera");
                                            opera.setDescrizione(in.readLine());
                                            
                                            try {
                                                myOutput=callService("UpdateOperaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice dell'Opera  per la cancellazione");
                                            opera.setCodO(Integer.parseInt(in.readLine()));
    
                                            try {
                                                myOutput=callService("DeleteOperaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice dell'Opera per la ricerca");
                                            opera.setCodO(Integer.parseInt(in.readLine()));

                                            try {
                                                myOutput=callService("GetByIdOperaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            
                                            System.out.println("Opera.CodO:" + opera.getCodO()+" Opera.Nome:"+opera.getNome()+" Opera.Descrizione:"+opera.getDescrizione());
                                        break;
    
                                        case 5:
                                            try {
                                                myOutput=callService("GetAllOpereService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            for(Opera oper : operList)
                                            {
                                                System.out.println("|Oper.CodO:" + oper.getCodO()+"| Artista.Nome:"+oper.getNome()+"| Opera.Descrizione:"+oper.getDescrizione());
                                            }
                                            
                                        break;
    
                                        case 6:
                                            try {
                                                myOutput=callService("SearchOperaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

    
                                            for(Opera oper : operList)
                                            {
                                                System.out.println("|Oper.CodO:" + oper.getCodO()+"| Artista.Nome:"+oper.getNome()+"| Opera.Descrizione:"+oper.getDescrizione());
                                            }

                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                } while (subscleta < 7);
                    
                    break;

                    case 9:
                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");
                                    
                                    Biglietteria biglietteria = new Biglietteria();
                                    List<Biglietteria> biglietteriaList = new ArrayList<>();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) 
                                    {
                                        case 1:

                                            System.out.println("Inserisci la modalita di pagamento della Biglietteria");
                                            biglietteria.setModPag(Biglietteria.ModalitaPagamento.valueOf(in.readLine()));
                                            System.out.println("Inserisci l'orario di apertura della Biglietteria in hh-mm-ss");
                                            biglietteria.setOraApertura(Time.valueOf(in.readLine()));
                                            System.out.println("Inserisci l'orario di chiusura della Biglietteria in hh-mm-ss");
                                            biglietteria.setOraChiusura(Time.valueOf(in.readLine()));
                                            
                                            try {
                                                myOutput=callService("CreateBiglietteriaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }


                                        break;
    
                                        case 2:

                                            System.out.println("Inserisci la modalita di pagamento sostituitiva della Biglietteria");
                                            biglietteria.setModPag(Biglietteria.ModalitaPagamento.valueOf(in.readLine()));
                                            System.out.println("Inserisci l'orario di apertura sostituitiva della Biglietteria in hh-mm-ss");
                                            biglietteria.setOraApertura(Time.valueOf(in.readLine()));
                                            System.out.println("Inserisci l'orario di chiusura sostituitiva della Biglietteria in hh-mm-ss");
                                            biglietteria.setOraChiusura(Time.valueOf(in.readLine()));
                                            
                                            try {
                                                myOutput=callService("UpdateBiglietteriaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }


                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice della Biglietteria per la cancellazione");
                                            biglietteria.setCodB(Integer.parseInt(in.readLine()));
    
                                            try {
                                                myOutput=callService("DeleteBiglietteriaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice della Biglietteria per la ricerca");
                                            biglietteria.setCodB(Integer.parseInt(in.readLine()));

                                            try {
                                                myOutput=callService("GetByIdBiglietteriaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        
                                            System.out.println("Biglietteria.CodB:" + biglietteria.getCodB()+" Biglietteria.Modalità_Pagamento:"+biglietteria.getModPag()+" Biglietteria.Ora_Apertura:"+biglietteria.getOraApertura()+" Biglietteria.Ora_Chiusura:"+biglietteria.getOraChiusura());
                                        break;
    
                                        case 5:
                                            try {
                                                myOutput=callService("GetAllBiglietteriaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                            for(Biglietteria bigl : biglietteriaList)
                                            {
                                                System.out.println("|Biglietteria.CodB:" + bigl.getCodB()+" |Biglietteria.Modalità_Pagamento:"+bigl.getModPag()+" |Biglietteria.Ora_Apertura:"+bigl.getOraApertura()+" |Biglietteria.Ora_Chiusura:"+bigl.getOraChiusura());
                                            }
                                            
                                        break;
    
                                        case 6:
                                            

                                            System.out.println("Inserisci la modalita di pagamento della Biglietteria");
                                            biglietteria.setModPag(Biglietteria.ModalitaPagamento.valueOf(in.readLine()));
                                            System.out.println("Inserisci l'orario di apertura della Biglietteria in hh-mm-ss");
                                            biglietteria.setOraApertura(Time.valueOf(in.readLine()));
                                            System.out.println("Inserisci l'orario di chiusura della Biglietteria in hh-mm-ss");
                                            biglietteria.setOraChiusura(Time.valueOf(in.readLine()));

                                            try {
                                                myOutput=callService("SearchBiglietteriaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

    
                                            for(Biglietteria bigl : biglietteriaList)
                                            {
                                                System.out.println("|Biglietteria.CodB:" + bigl.getCodB()+" |Biglietteria.Modalità_Pagamento:"+bigl.getModPag()+" |Biglietteria.Ora_Apertura:"+bigl.getOraApertura()+" |Biglietteria.Ora_Chiusura:"+bigl.getOraChiusura());
                                            }

                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 10:
                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    Biglietto biglietto = new Biglietto();
                                    List<Biglietto> biglList= new ArrayList<>();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) 
                                    {
                                        case 1:

                                            System.out.println("Inserisci la data di acquisto del Biglietto");
                                            biglietto.setData(Date.valueOf(in.readLine()));
                                            System.out.println("Inserisci Il prezzo del biglietto");
                                            biglietto.setPrezzo(Float.parseFloat(in.readLine()));
                                            System.out.println("Inserisci il tipo del biglietto "); //inserire i tipi disponibili per ricordare
                                            biglietto.setTipo(Biglietto.TipoBiglietto.valueOf(in.readLine()));
                                            
                                            try {
                                                myOutput=callService("CreateBigliettoService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }


                                        break;
    
                                        case 2:

                                            System.out.println("Inserisci la data di acquisto sostituitiva del Biglietto");
                                            biglietto.setData(Date.valueOf(in.readLine()));
                                            System.out.println("Inserisci Il prezzo sostituitivo del biglietto");
                                            biglietto.setPrezzo(Float.parseFloat(in.readLine()));
                                            System.out.println("Inserisci il tipo sostituitivo del biglietto "); //inserire i tipi disponibili per ricordare
                                            biglietto.setTipo(Biglietto.TipoBiglietto.valueOf(in.readLine()));
                                            
                                            try {
                                                myOutput=callService("UpdateBigliettoService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }


                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice del Biglietto ");
                                            biglietto.setCodBi(Integer.parseInt(in.readLine()));
    
                                            try {
                                                myOutput=callService("DeleteBigliettoService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice della Biglietteria per la ricerca");
                                            biglietto.setCodBi(Integer.parseInt(in.readLine()));

                                            try {
                                                myOutput=callService("GetByIdBigliettoService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        
                                            System.out.println("Biglietto.CodBi:" + biglietto.getCodBi()+" Biglietto.Tipo_Biglietto:"+biglietto.getTipo()+" Biglietto.Prezzo:"+biglietto.getPrezzo()+" Biglietto.Data:"+biglietto.getData());
                                        break;
    
                                        case 5:
                                            try {
                                                myOutput=callService("GetAllBigliettoService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                            for(Biglietto bigl : biglList)
                                            {
                                                System.out.println("|Biglietto.CodBi:" + bigl.getCodBi()+" |Biglietto.Tipo_Biglietto:"+bigl.getTipo()+" |Biglietto.Prezzo:"+bigl.getPrezzo()+" |Biglietto.Data:"+bigl.getData());
                                            }
                                            
                                        break;
    
                                        case 6:
                                            SearchBigliettoService searchBigliettoService = new SearchBigliettoService();

                                            System.out.println("Inserisci la data di acquisto del Biglietto");
                                            biglietto.setData(Date.valueOf(in.readLine()));
                                            System.out.println("Inserisci Il prezzo del biglietto");
                                            biglietto.setPrezzo(Float.parseFloat(in.readLine()));
                                            System.out.println("Inserisci il tipo del biglietto "); //inserire i tipi disponibili per ricordare
                                            biglietto.setTipo(Biglietto.TipoBiglietto.valueOf(in.readLine()));

                                            try {
                                                myOutput=callService("SearchBigliettoService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

    
                                            for(Biglietto bigl : biglList)
                                            {
                                                System.out.println("|Biglietto.CodBi:" + bigl.getCodBi()+" |Biglietto.Tipo_Biglietto:"+bigl.getTipo()+" |Biglietto.Prezzo:"+bigl.getPrezzo()+" |Biglietto.Data:"+bigl.getData());
                                            }

                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 11:
                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    Abbonamento abbonamento = new Abbonamento();
                                    List<Abbonamento> abbList= new ArrayList<>();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) 
                                    {
                                        case 1:

                                            System.out.println("Inserisci il tipo dell'Abbonamento");
                                            abbonamento.setTipo(Abbonamento.TipoAbbonamento.valueOf(in.readLine()));
                                            System.out.println("Inserisci la descrizione dell'Abbonamento");
                                            abbonamento.setDescrizione(in.readLine());
                                            System.out.println("Inserisci il prezzo dell'Abbonamento "); //inserire i tipi disponibili per ricordare
                                            abbonamento.setPrezzo(Float.parseFloat(in.readLine()));
                                            
                                            try {
                                                myOutput=callService("CreateAbbonamentoService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }


                                        break;
    
                                        case 2:

                                            System.out.println("Inserisci il tipo sostituitivo dell'Abbonamento");
                                            abbonamento.setTipo(Abbonamento.TipoAbbonamento.valueOf(in.readLine()));
                                            System.out.println("Inserisci la descrizione sostituitiva dell'Abbonamento");
                                            abbonamento.setDescrizione(in.readLine());
                                            System.out.println("Inserisci il prezzo sostituitivo dell'Abbonamento "); //inserire i tipi disponibili per ricordare
                                            abbonamento.setPrezzo(Float.parseFloat(in.readLine()));
                                                
                                            try {
                                                myOutput=callService("UpdateAbbonamentoService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }


                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice dell'Abbonamento per la cancellazione");
                                            abbonamento.setCodAb(Integer.parseInt(in.readLine()));
    
                                            try {
                                                myOutput=callService("DeleteAbbonamentoService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice dell'Abbonamento per la ricerca ");
                                            abbonamento.setCodAb(Integer.parseInt(in.readLine()));

                                            try {
                                                myOutput=callService("GetByIdAbbonamentoService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        
                                            System.out.println("Abbonamento.CodAb:" + abbonamento.getCodAb()+" Abbonamento.Tipo_Biglietto:"+abbonamento.getTipo()+" Abbonamento.Prezzo:"+abbonamento.getPrezzo());
                                        break;
    
                                        case 5:
                                            try {
                                                myOutput=callService("GetAllAbbonamentiService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                            for(Abbonamento abb : abbList)
                                            {
                                                System.out.println("|Abbonamento.CodAb:" + abb.getCodAb()+" |Abbonamento.Tipo_Biglietto:"+abb.getTipo()+" |Abbonamento.Prezzo:"+abb.getPrezzo());
                                            }
                                            
                                        break;
    
                                        case 6:

                                            System.out.println("Inserisci il tipo dell'Abbonamento");
                                            abbonamento.setTipo(Abbonamento.TipoAbbonamento.valueOf(in.readLine()));
                                            System.out.println("Inserisci la descrizione dell'Abbonamento");
                                            abbonamento.setDescrizione(in.readLine());
                                            System.out.println("Inserisci il prezzo dell'Abbonamento "); //inserire i tipi disponibili per ricordare
                                            abbonamento.setPrezzo(Float.parseFloat(in.readLine()));

                                            try {
                                                myOutput=callService("SearchAbbonamentoService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

    
                                            for(Abbonamento abb : abbList)
                                            {
                                                System.out.println("|Abbonamento.CodAb:" + abb.getCodAb()+" |Abbonamento.Tipo_Biglietto:"+abb.getTipo()+" |Abbonamento.Prezzo:"+abb.getPrezzo());
                                            }

                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 12:
                    {   
                        int Cod_Ab,Cod_Bi;
                        do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete   "+ arr[scelta-1]);
                                    System.out.println("4)Get All Biglietterie per Abbonamento  ");
                                    System.out.println("5)Get All Abbonamenti per Biglietteria ");
                                    System.out.println("6)Esci  ");

                                    Biglietteria bigl=new Biglietteria();
                                    Abbonamento abb=new Abbonamento();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) 
                                    {
                                        case 1:
                                            System.out.println("Inserisci il codice abbonamento da associare");
                                            bigl.setCodB(Integer.parseInt(in.readLine()));
                                            System.out.println("Inserisci il codice della biglietteria da associare");
                                            abb.setCodAb(Integer.parseInt(in.readLine()));

                                            try {
                                                myOutput=callService("CreateAbbonamentoService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }


                                        break;
    
                                        case 2:

                                        System.out.println("Inserisci il codice abbonamento da modificare");
                                            Cod_Ab = Integer.parseInt(in.readLine());
                                            System.out.println("Inserisci il codice della biglietteria da modificare");
                                            Cod_Bi = Integer.parseInt(in.readLine());
                                            System.out.println("Inserisci il codice abbonamento di rimpiazzo");
                                            int Cod_Abmod = Integer.parseInt(in.readLine());
                                            System.out.println("Inserisci il codice della biglietteria di rimpiazzo");
                                            int Cod_Bimod = Integer.parseInt(in.readLine());

                                            try {
                                                myOutput=callService("UpdateAbbonamentoBiglietteriaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }


                                        break;
    
                                        case 3:

                                        System.out.println("Inserisci il codice abbonamento da cancellare");
                                            Cod_Ab = Integer.parseInt(in.readLine());
                                            System.out.println("Inserisci il codice della biglietteria da cancellare");
                                            Cod_Bi = Integer.parseInt(in.readLine());
                                            try {
                                                myOutput=callService("DeleteAbbonamentoBiglietteriaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                            
                                        break;
    
                                        case 4:

                                        
                                        System.out.println("Inserisci il codice della biglietteria");
                                        Cod_Bi = Integer.parseInt(in.readLine());

                                            try {
                                                myOutput=callService("SearchAbbonamentiForBiglietteriaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                            
                                        break;
    
                                        case 5:

                                        System.out.println("Inserisci il codice della biglietteria");
                                        Cod_Bi = Integer.parseInt(in.readLine());

                                            try {
                                                myOutput=callService("SearchBiglietterieForAbbonamentoService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                             
                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 6);
                    }

                    case 13:
                    {
                        //TODO inserire le map come input
                        int Cod_Cli,Cod_Ab;
                        do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete   "+ arr[scelta-1]);
                                    System.out.println("4)Get All Clienti per Abbonamento  ");
                                    System.out.println("5)Get All Abbonamenti per Cliente ");
                                    System.out.println("6)Esci  ");

                                    
                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) 
                                    {
                                        case 1:

                                        System.out.println("Inserisci il codice abbonamento da associare");
                                        Cod_Ab = Integer.parseInt(in.readLine());
                                        System.out.println("Inserisci il codice del cliente da associare");
                                        Cod_Cli = Integer.parseInt(in.readLine());

                                            try {
                                                myOutput=callService("CreateAbbonamentoToBiglietteriaService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }


                                        break;
    
                                        case 2:

                                        System.out.println("Inserisci il codice abbonamento da modificare");
                                        Cod_Ab = Integer.parseInt(in.readLine());
                                        System.out.println("Inserisci il nuovo codice abbonamento da associare");
                                        int Cod_AbNuovo = Integer.parseInt(in.readLine());
                                        System.out.println("Inserisci il codice del cliente");
                                        Cod_Cli = Integer.parseInt(in.readLine());

                                            try {
                                                myOutput=callService("UpdateAbbonamentoForClienteService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }


                                        break;
    
                                        case 3:
                                            
                                        System.out.println("Inserisci il codice abbonamento da cancellare");
                                        Cod_Ab = Integer.parseInt(in.readLine());
                                        System.out.println("Inserisci il codice del cliente da cancellare");
                                        Cod_Cli = Integer.parseInt(in.readLine());

                                            try {
                                                myOutput=callService("DeleteClienteAbbonamentoService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }


                                        break;
    
                                        case 4:

                                        System.out.println("Inserisci il codice abbonamento da cancellare");
                                        Cod_Ab = Integer.parseInt(in.readLine());

                                        try {
                                            myOutput=callService("SearchAbbonamentiForClienteService", myInput);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                            
                                        break;
    
                                        case 5:

                                        
                                        System.out.println("Inserisci il codice del cliente da cancellare");
                                        Cod_Cli = Integer.parseInt(in.readLine());

                                            try {
                                                myOutput=callService("SearchClientiForAbbonamentoService", myInput);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                             
                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 6);
                    }

                    case 14:
                                System.out.println("Quitting");
                    break;

                    default:
                    System.out.println("Scelta non valida");
                    break;
            }

        }while(scelta <14);
    }
}

