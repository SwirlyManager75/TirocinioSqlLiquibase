package com.tirocinio.model;

public class Poi {

    private Integer codPoi;
    private String descrizione;
    private Integer codEM; // Codice del museo associato al Point of Interest (Poi)
    private Museo museo; // Riferimento all'oggetto Museo

    // Costruttore vuoto
    public Poi() {}

    // Costruttore con parametri
    public Poi(String descrizione, Integer codEM) {
        this.descrizione = descrizione;
        this.codEM = codEM;
    }

    // Metodi getter e setter per ogni attributo
    public Integer getCodPoi() {
        return codPoi;
    }

    public void setCodPoi(Integer codPoi) {
        this.codPoi = codPoi;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Integer getCodEM() {
        return codEM;
    }

    public void setCodEM(Integer codEM) {
        this.codEM = codEM;
    }

    public Museo getMuseo() {
        return museo;
    }

    public void setMuseo(Museo museo) {
        this.museo = museo;
    }

}
