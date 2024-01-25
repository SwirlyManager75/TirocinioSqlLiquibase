package com.tirocinio.model;

public class Poi {

    private Integer codPoi;
    private String descrizione;
    
    private Museo museo; // Riferimento all'oggetto Museo
    private Audio audio;
    // Costruttore vuoto
    public Poi() {}

    // Costruttore con parametri
    public Poi(String descrizione, Integer codEM) {
        this.descrizione = descrizione;
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

    public Museo getMuseo() {
        return museo;
    }

    public void setMuseo(Museo museo) {
        this.museo = museo;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

}
