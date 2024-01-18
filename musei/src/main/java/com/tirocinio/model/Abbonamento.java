package com.tirocinio.model;

public class Abbonamento {

    public enum TipoAbbonamento {
        BASE, AVANZATO, PREMIUM
    }

    private Integer codAb;
    private TipoAbbonamento tipo;
    private String descrizione;
    private float prezzo;

    // Costruttore vuoto 
    public Abbonamento() {}

    // Costruttore con parametri
    public Abbonamento(TipoAbbonamento tipo, String descrizione, float prezzo) {
        this.tipo = tipo;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
    }

    // Metodi getter e setter per ogni attributo
    public Integer getCodAb() {
        return codAb;
    }

    public void setCodAb(Integer codAb) {
        this.codAb = codAb;
    }

    public TipoAbbonamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoAbbonamento tipo) {
        this.tipo = tipo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

}
