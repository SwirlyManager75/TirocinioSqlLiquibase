package com.tirocinio.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Biglietteria {

     public enum ModalitaPagamento {
        CONTANTE, ELETTRONICO, MISTO
    }

    private Integer codB;
    private Time oraApertura;
    private Time oraChiusura;
    private ModalitaPagamento modPag;
   
    private Museo museo; // Riferimento all'oggetto Museo

    private List<Abbonamento> abbonamenti = new ArrayList<>();
    private List<Biglietto> biglietti = new ArrayList<>();

    // Costruttore vuoto 
    public Biglietteria() {}

    // Costruttore con parametri
    public Biglietteria(Time oraApertura, Time oraChiusura, ModalitaPagamento modPag) {
        this.oraApertura = oraApertura;
        this.oraChiusura = oraChiusura;
        this.modPag = modPag;
        
    }

    // Metodi getter e setter per ogni attributo
    public Integer getCodB() {
        return codB;
    }

    public void setCodB(Integer codB) {
        this.codB = codB;
    }

    public Time getOraApertura() {
        return oraApertura;
    }

    public void setOraApertura(Time oraApertura) {
        this.oraApertura = oraApertura;
    }

    public Time getOraChiusura() {
        return oraChiusura;
    }

    public void setOraChiusura(Time oraChiusura) {
        this.oraChiusura = oraChiusura;
    }

    public ModalitaPagamento getModPag() {
        return modPag;
    }

    public void setModPag(ModalitaPagamento modPag) {
        this.modPag = modPag;
    }


    public Museo getMuseo() {
        return museo;
    }

    public void setMuseo(Museo museo) {
        this.museo = museo;
    }

    public void aggiungiAbbonamento(Abbonamento abbonamento) {
        abbonamenti.add(abbonamento);
        abbonamento.getBiglietterie().add(this);
    }

    public void rimuoviAbbonamento(Abbonamento abbonamento) {
        abbonamenti.remove(abbonamento);
        abbonamento.getBiglietterie().remove(this);
    }

    public List<Abbonamento> getAbbonamenti() {
        return abbonamenti;
    }

    public void aggiungiBiglietto(Biglietto biglietto) {
        biglietti.add(biglietto);
        biglietto.setBiglietteria(this);
    }

    public void rimuoviBiglietto(Biglietto biglietto) {
        biglietti.remove(biglietto);
        biglietto.setBiglietteria(null);
    }

    public List<Biglietto> getBiglietti()
    {
        return biglietti;
    }

}
