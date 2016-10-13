package fr.norsys.beans;

import java.io.Serializable;

public class Client implements Serializable {

    private Long   id;
    private String test;
    private String nom;
    private String prenom;
    private String adresseDeLivraison;
    private String numTel;
    private String adresseMail;
    private String motDePasse;

    public String getNom() {
        return nom;
    }

    public void setNom( final String nom ) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom( final String prenom ) {
        this.prenom = prenom;
    }

    public String getAdresseDeLivraison() {
        return adresseDeLivraison;
    }

    public void setAdresseDeLivraison( final String adresseDeLivraison ) {
        this.adresseDeLivraison = adresseDeLivraison;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel( final String numTel ) {
        this.numTel = numTel;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail( final String adresseMail ) {
        this.adresseMail = adresseMail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse( final String motDePasse ) {
        this.motDePasse = motDePasse;
    }

    public Long getId() {
        return id;
    }

    public void setId( final Long id ) {
        this.id = id;
    }

}
