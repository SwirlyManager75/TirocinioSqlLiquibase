package com.tirocinio.model;

import java.util.ArrayList;
import java.util.List;

public class Museo {

    private Integer codM;
    private String nome;
    private String via;
    
    private Citta citta; // Riferimento all'oggetto Citta
    private List<Poi> pois = new ArrayList<>();
    private List<Dipendente> dipendenti = new ArrayList<>();
    private List<Biglietteria> biglietterie = new ArrayList<>();
    private List<Opera> opere = new ArrayList<>();
    
    // Costruttore vuoto
    public Museo() {}

    // Costruttore con parametri
    public Museo(String nome, String via) {
        this.nome = nome;
        this.via = via;
    }

    // Metodi getter e setter per ogni attributo
    public Integer getCodM() {
        return codM;
    }

    public void setCodM(Integer codM) {
        this.codM = codM;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public Citta getCitta() {
        return citta;
    }

    public void setCitta(Citta citta) {
        this.citta = citta;
    }

    public List<Poi> getPois() {
        return pois;
    }

    public void setPois(List<Poi> pois) {
        this.pois = pois;
    }

    public List<Dipendente> getDipendenti() {
        return dipendenti;
    }

    public void setDipendenti(List<Dipendente> dipendenti) {
        this.dipendenti = dipendenti;
    }

    public void addDipendente(Dipendente dipendente)
    {
        this.dipendenti.add(dipendente);
        dipendente.setMuseo(this);
    }

    public void removeDipedente(Dipendente dipendente)
    {
        this.dipendenti.remove(dipendente);
        dipendente.setMuseo(null);
    }

    public List<Biglietteria> getBiglietterie() {
        return biglietterie;
    }

    public void setBiglietterie(List<Biglietteria> biglietterie) {
        this.biglietterie = biglietterie;
    }

    public void addBiglietteria(Biglietteria biglietteria)
    {
        biglietterie.add(biglietteria);
        biglietteria.setMuseo(this);
    }

    public void removeBiglietteria(Biglietteria biglietteria)
    {
        biglietterie.remove(biglietteria);
        biglietteria.setMuseo(null);
    }

    public List<Opera> getOpere() {
        return opere;
    }

    public void setOpere(List<Opera> opere) {
        this.opere = opere;
    }

    public void addOpera(Opera opera)
    {
        opere.add(opera);
        opera.setMuseo(this);
    }

    public void removeOpera(Opera opera)
    {
        opere.remove(opera);
        opera.setMuseo(null);
    }
}
