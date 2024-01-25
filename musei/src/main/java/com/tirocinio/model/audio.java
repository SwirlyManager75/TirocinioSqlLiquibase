package com.tirocinio.model;

public class Audio {

    private Integer codAu;
    private String url;
    
    private Poi poi; // Riferimento all'oggetto Poi

    // Costruttore vuoto 
    public Audio() {}

    // Costruttore con parametri
    public Audio(String url) {
        this.url = url;
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

    public Poi getPoi() {
        return poi;
    }

    public void setPoi(Poi poi) {
        this.poi = poi;
    }

    
}
