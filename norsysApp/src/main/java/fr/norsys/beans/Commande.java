package fr.norsys.beans;


public class Commande {

    private Long   id;
    private Client client;
    private String date;
    private Double montant;
    private String modeDePaiement;
    private String statutDePaiement;
    private String modeDeLivraison;
    private String statutDeLivraison;

    public Client getClient() {
        return client;
    }

    public void setClient( Client client ) {
        this.client = client;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant( double montant ) {
        this.montant = montant;
    }

    public String getModeDePaiement() {
        return modeDePaiement;
    }

    public void setModeDePaiement( String modeDePaiement ) {
        this.modeDePaiement = modeDePaiement;
    }

    public String getStatutDePaiement() {
        return statutDePaiement;
    }

    public void setStatutDePaiement( String statutDePaiement ) {
        this.statutDePaiement = statutDePaiement;
    }

    public String getModeDeLivraison() {
        return modeDeLivraison;
    }

    public void setModeDeLivraison( String modeDeLivraison ) {
        this.modeDeLivraison = modeDeLivraison;
    }

    public String getStatutDeLivraison() {
        return statutDeLivraison;
    }

    public void setStatutDeLivraison( String statutDeLivraison ) {
        this.statutDeLivraison = statutDeLivraison;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public void setMontant( Double montant ) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate( String date ) {
        this.date = date;
    }

}
