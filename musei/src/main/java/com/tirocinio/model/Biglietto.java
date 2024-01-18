package com.tirocinio.model;

import java.sql.Date;

public class Biglietto {

    public enum TipoBiglietto {
        GRATIS, RIDOTTO, PIENO
    }

    private Integer codBi;
    private float prezzo;
    private TipoBiglietto tipo;
    private Date data;
    private Integer codECli; // Codice del Cliente associato al Biglietto
    private Integer codEB; // Codice della Biglietteria associata al Biglietto
    private Cliente cliente; // Riferimento all'oggetto Cliente
    private Biglietteria biglietteria; // Riferimento all'oggetto Biglietteria

    // Costruttore vuoto 
    public Biglietto() {}

    // Costruttore con parametri
    public Biglietto(float prezzo, TipoBiglietto tipo, Date data, Integer codECli, Integer codEB) {
        this.prezzo = prezzo;
        this.tipo = tipo;
        this.data = data;
        this.codECli = codECli;
        this.codEB = codEB;
    }

    // Metodi getter e setter per ogni attributo
    public Integer getCodBi() {
        return codBi;
    }

    public void setCodBi(Integer codBi) {
        this.codBi = codBi;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public TipoBiglietto getTipo() {
        return tipo;
    }

    public void setTipo(TipoBiglietto tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getCodECli() {
        return codECli;
    }

    public void setCodECli(Integer codECli) {
        this.codECli = codECli;
    }

    public Integer getCodEB() {
        return codEB;
    }

    public void setCodEB(Integer codEB) {
        this.codEB = codEB;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Biglietteria getBiglietteria() {
        return biglietteria;
    }

    public void setBiglietteria(Biglietteria biglietteria) {
        this.biglietteria = biglietteria;
    }


}
