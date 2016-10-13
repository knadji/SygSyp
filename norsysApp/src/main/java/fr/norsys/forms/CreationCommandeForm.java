package fr.norsys.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class CreationCommandeForm {

    private static final String CHAMP_MONTANT          = "montantCommande";
    private static final String CHAMP_MODE_PAIEMENT    = "modePaiementCommande";
    private static final String CHAMP_STATUT_PAIEMENT  = "statutPaiementCommande";
    private static final String CHAMP_MODE_LIVRAISON   = "modeLivraisonCommande";
    private static final String CHAMP_STATUT_LIVRAISON = "statutLivraisonCommande";

    private Map<String, String> erreurs                = new HashMap<String, String>();
    private String              resultat;

    private String              montantCommande;
    private String              modePaiementCommande;
    private String              statutPaiementCommande;
    private String              modeLivraisonCommande;
    private String              statutLivraisonCommande;

    public CreationCommandeForm( HttpServletRequest request ) {

        // R�cup�ration des informations formulaire
        montantCommande = request.getParameter( CHAMP_MONTANT );
        modePaiementCommande = request.getParameter( CHAMP_MODE_PAIEMENT );
        statutPaiementCommande = request.getParameter( CHAMP_STATUT_PAIEMENT );
        modeLivraisonCommande = request.getParameter( CHAMP_MODE_LIVRAISON );
        statutLivraisonCommande = request.getParameter( CHAMP_STATUT_LIVRAISON );

        // V�rification des donn�es du formulaire
        virifierInfoCommande();

        if ( erreurs.isEmpty() ) {
            resultat = "Succ�s de la cr�ation du client.";
        } else {
            resultat = "Echec de la cr�ation du client.";
        }
    }

    private void virifierInfoCommande() {

        // V�rification du Montant
        try {
            if ( !( montantCommande != null && Double.parseDouble( montantCommande ) > 0 ) ) {
                setErreur( CHAMP_MONTANT, "Le montant doit �tre un nombre entier positif." );
            }
        } catch ( NumberFormatException e ) {
            montantCommande = String.valueOf( -1 );
            setErreur( CHAMP_MONTANT, "Le montant doit �tre un nombre." );
        }

        // V�rification du mode de paiement
        if ( !( modePaiementCommande != null && modePaiementCommande.length() > 2 ) ) {
            setErreur( CHAMP_MODE_PAIEMENT, "Le mode de paiement doit contenir au moins 3 caract�res" );
        }

        // V�rification du statut de paiement
        if ( !( statutPaiementCommande != null && statutPaiementCommande.length() > 2 ) ) {
            setErreur( CHAMP_STATUT_PAIEMENT, "Le statut de paiement doit contenir au moins 3 caract�res" );
        }

        // V�rification du mode de livraison commande
        if ( !( modeLivraisonCommande != null && modeLivraisonCommande.length() > 2 ) ) {
            setErreur( CHAMP_MODE_LIVRAISON, "Le mode de livraison doit contenir au moins 3 caract�res" );
        }

        // V�rification du statut de livraison commande
        if ( !( statutLivraisonCommande != null && statutLivraisonCommande.length() > 2 ) ) {
            setErreur( CHAMP_STATUT_LIVRAISON, "Le statut de livraison doit contenir au moins 3 caract�res" );
        }
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    public String getMontantCommande() {
        return montantCommande;
    }

    public String getModePaiementCommande() {
        return modePaiementCommande;
    }

    public String getStatutPaiementCommande() {
        return statutPaiementCommande;
    }

    public String getModeLivraisonCommande() {
        return modeLivraisonCommande;
    }

    public String getStatutLivraisonCommande() {
        return statutLivraisonCommande;
    }
}
