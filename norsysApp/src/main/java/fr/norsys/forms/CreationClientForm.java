package fr.norsys.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class CreationClientForm {

    private static final String       CHAMP_NOM      = "nomClient";
    private static final String       CHAMP_PRENOM   = "prenomClient";
    private static final String       CHAMP_ADRESSE  = "adresseClient";
    private static final String       CHAMP_TEL      = "telephoneClient";
    private static final String       NOUVEAU_CLINET = "choixNouveauClient";
    private static final String       ANCIEN_CLIENT  = "ancienClient";

    private String                    resultat;
    private final Map<String, String> erreurs        = new HashMap<String, String>();

    private String                    nomClient;
    private String                    prenomClient;
    private String                    adresseDeLivraison;
    private String                    telephoneClient;
    private String                    ancienClient;

    public CreationClientForm( final HttpServletRequest request ) {

        recupParam( request );

        if ( !ANCIEN_CLIENT.equals( ancienClient ) || ancienClient == null ) {
            virifierInfoClient();
        }

        if ( erreurs.isEmpty() ) {
            resultat = "Succés de la création du client.";
        } else {
            resultat = "Echec de la création du client.";
        }
    }

    private void recupParam( final HttpServletRequest request ) {

        nomClient = request.getParameter( CHAMP_NOM );
        prenomClient = request.getParameter( CHAMP_PRENOM );
        adresseDeLivraison = request.getParameter( CHAMP_ADRESSE );
        telephoneClient = request.getParameter( CHAMP_TEL );
        ancienClient = request.getParameter( NOUVEAU_CLINET );
    }

    /**
     * Vérification des informations saisi par le client
     */
    private void virifierInfoClient() {

        if ( !( nomClient != null && nomClient.length() >= 3 ) ) {
            setErreur( CHAMP_NOM, "Le nom doit contenir au minimum 3 caractères." );
        }

        if ( !( prenomClient != null && prenomClient.length() >= 3 ) ) {
            setErreur( CHAMP_PRENOM, "Le prénom doit contenir au minimum 3 caractères." );
        }

        if ( !( adresseDeLivraison != null && adresseDeLivraison.length() >= 10 ) ) {
            setErreur( CHAMP_ADRESSE, "l'adresse de livraison doit contenir au moins 10 caractères." );
        }

        if ( !( telephoneClient != null && telephoneClient.matches( "^\\d+$" ) && telephoneClient.length() >= 4 ) ) {
            setErreur( CHAMP_TEL, "Le numéro de téléphone doit contenir au moins 4 chiffres." );
        }
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    private void setErreur( final String champ, final String message ) {
        erreurs.put( champ, message );
    }

    public String getNomClient() {
        return nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public String getAdresseDeLivraison() {
        return adresseDeLivraison;
    }

    public String getTelephoneClient() {
        return telephoneClient;
    }

    public String getAncienClient() {
        return ancienClient;
    }

}
