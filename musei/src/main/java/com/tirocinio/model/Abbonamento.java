package com.tirocinio.model;

import java.util.ArrayList;
import java.util.List;

public class Abbonamento {

    public enum TipoAbbonamento {
        BASE, AVANZATO, PREMIUM
    }

    private Integer codAb;
    private TipoAbbonamento tipo;
    private String descrizione;
    private float prezzo;

    private List<Biglietteria> biglietterie = new ArrayList<>();
    private List<Cliente> clienti = new ArrayList<>();
    
    

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

    public void aggiungiBiglietteria(Biglietteria biglietteria) {
        biglietterie.add(biglietteria);
        biglietteria.getAbbonamenti().add(this);
    }

    public void rimuoviBiglietteria(Biglietteria biglietteria) {
        biglietterie.remove(biglietteria);
        biglietteria.getAbbonamenti().remove(this);
    }

    public List<Biglietteria> getBiglietterie() {
        return biglietterie;
    }
    
    public List<Cliente> getClienti() {
        return clienti;
    }

    public void setClienti(List<Cliente> clienti) {
        this.clienti = clienti;
    }

    public void aggiungiCliente(Cliente cliente) {
        clienti.add(cliente);
        cliente.getAbbonamenti().add(this);
    }

    public void rimuoviCliente(Cliente cliente) {
        clienti.remove(cliente);
        cliente.getAbbonamenti().remove(this);
    }


}
