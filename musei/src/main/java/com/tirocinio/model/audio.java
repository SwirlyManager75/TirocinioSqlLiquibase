package com.tirocinio.model;

public class audio {

    private Integer codAu;
    private String url;
    private Integer codEPoi; // Codice del Point of Interest (Poi) associato all'audio
    private Poi poi; // Riferimento all'oggetto Poi

    // Costruttore vuoto 
    public audio() {}

    // Costruttore con parametri
    public audio(String url, Integer codEPoi) {
        this.url = url;
        this.codEPoi = codEPoi;
    }

    // Metodi getter e setter per ogni attributo
    public Integer getCodAu() {
        return codAu;
    }

    public void setCodAu(Integer codAu) {
        this.codAu = codAu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCodEPoi() {
        return codEPoi;
    }

    public void setCodEPoi(Integer codEPoi) {
        this.codEPoi = codEPoi;
    }

    public Poi getPoi() {
        return poi;
    }

    public void setPoi(Poi poi) {
        this.poi = poi;
    }

    
}
