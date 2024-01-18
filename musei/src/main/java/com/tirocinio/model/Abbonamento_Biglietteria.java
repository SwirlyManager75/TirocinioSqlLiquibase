package com.tirocinio.model;

public class Abbonamento_Biglietteria {

    private Integer codAB;
    private Integer codEA; // Codice dell'Abbonamento associato alla relazione
    private Integer codEB; // Codice della Biglietteria associata alla relazione
    private Abbonamento abbonamento; // Riferimento all'oggetto Abbonamento
    private Biglietteria biglietteria; // Riferimento all'oggetto Biglietteria

    // Costruttore vuoto 
    public Abbonamento_Biglietteria() {}

    // Costruttore con parametri
    public Abbonamento_Biglietteria(Integer codEA, Integer codEB) {
        this.codEA = codEA;
        this.codEB = codEB;
    }

    // Metodi getter e setter per ogni attributo
    public Integer getCodAB() {
        return codAB;
    }

    public void setCodAB(Integer codAB) {
        this.codAB = codAB;
    }

    public Integer getCodEA() {
        return codEA;
    }

    public void setCodEA(Integer codEA) {
        this.codEA = codEA;
    }

    public Integer getCodEB() {
        return codEB;
    }

    public void setCodEB(Integer codEB) {
        this.codEB = codEB;
    }

    public Abbonamento getAbbonamento() {
        return abbonamento;
    }

    public void setAbbonamento(Abbonamento abbonamento) {
        this.abbonamento = abbonamento;
    }

    public Biglietteria getBiglietteria() {
        return biglietteria;
    }

    public void setBiglietteria(Biglietteria biglietteria) {
        this.biglietteria = biglietteria;
    }

}
