package com.tirocinio.model;

import java.sql.Time;

public class Biglietteria {

     public enum ModalitaPagamento {
        CONTANTE, ELETTRONICO, MISTO
    }

    private Integer codB;
    private Time oraApertura;
    private Time oraChiusura;
    private ModalitaPagamento modPag;
    private Integer codEM; // Codice del Museo associato alla biglietteria
    private Museo museo; // Riferimento all'oggetto Museo

    // Costruttore vuoto 
    public Biglietteria() {}

    // Costruttore con parametri
    public Biglietteria(Time oraApertura, Time oraChiusura, ModalitaPagamento modPag, Integer codEM) {
        this.oraApertura = oraApertura;
        this.oraChiusura = oraChiusura;
        this.modPag = modPag;
        this.codEM = codEM;
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
