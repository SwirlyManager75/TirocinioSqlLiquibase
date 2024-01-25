package com.tirocinio.model;

import java.util.ArrayList;
import java.util.List;

public class Citta {

    private Integer codCi;
    private String nome;
    private boolean provincia;
    
    private List<Museo> musei = new ArrayList<>();
    private List<Cliente> clienti= new ArrayList<>();
    private List<Dipendente> dipendenti= new ArrayList<>();
    private List<Artista> artisti= new ArrayList<>();

   
    // Costruttore vuoto 
    public Citta() {}

    // Costruttore con parametri
    public Citta(String nome, boolean provincia) {
        this.nome = nome;
        this.provincia = provincia;
    }

    // Metodi getter e setter per ogni attributo
    public Integer getCodCi() {
        return codCi;
    }

    public void setCodCi(Integer codCi) {
        this.codCi = codCi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isProvincia() {
        return provincia;
    }

    public void setProvincia(boolean provincia) {
        this.provincia = provincia;
    }

    public List<Museo> getMusei() {
        return musei;
    }

    public void setMusei(List<Museo> musei) {
        this.musei = musei;
    }

    public void addMuseo(Museo museo)
    {
        musei.add(museo);
        museo.setCitta(this);
    }

    public void removeMuseo(Museo museo)
    {
        musei.remove(museo);
        museo.setCitta(null);
    }

    public List<Cliente> getClienti() {
        return clienti;
    }

    public void setClienti(List<Cliente> clienti) {
        this.clienti = clienti;
    }

    public void addCliente(Cliente cliente)
    {
        clienti.add(cliente);
        cliente.setCitta(this);
    }

    public void removeCliente(Cliente cliente)
    {
        clienti.remove(cliente);
        cliente.setCitta(null);
    }

    public List<Dipendente> getDipendenti() {
        return dipendenti;
    }

    public void setDipendenti(List<Dipendente> dipendenti) {
        this.dipendenti = dipendenti;
    }

    public void addDipendente(Dipendente dipendente)
    {
        dipendenti.add(dipendente);
        dipendente.setCitta(this);
    }

    public void removeDipedente(Dipendente dipendente)
    {
        dipendenti.remove(dipendente);
        dipendente.setCitta(null);
    }
    
    public List<Artista> getArtisti() {
        return artisti;
    }

    public void setArtisti(List<Artista> artisti) {
        this.artisti = artisti;
    }

    public void addArtista(Artista artista)
    {
        artisti.add(artista);
        artista.setCitta(this);
    }

    public void removeArtista(Artista artista)
    {
        artisti.remove(artista);
        artista.setCitta(null);
    }

}
