package fr.norsys.forms;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.norsys.util.Sha;

public class ConnexionClientForm {

    private static final Logger       LOGGER      = LoggerFactory.getLogger( ConnexionClientForm.class );

    private static final String       CHAMP_EMAIL = "emailClient";
    private static final String       CHAMP_MDP   = "motDePasse";

    private String                    resultat;
    private final Map<String, String> erreurs     = new HashMap<String, String>();

    private String                    emailClient;
    private String                    mdpClient;

    public ConnexionClientForm( final HttpServletRequest request ) {
        recupParamConnexion( request );
        virifierInfoConnexion();
        if ( erreurs.isEmpty() ) {
            resultat = "Succés de la connexion du client.";
        } else {
            resultat = "Echec de la connexion du client.";
        }
    }

    /**
     *
     * @param request
     */
    private void recupParamConnexion( final HttpServletRequest request ) {
        emailClient = request.getParameter( CHAMP_EMAIL );
        try {
            mdpClient = Sha.hash256( request.getParameter( CHAMP_MDP ) );
        } catch ( final NoSuchAlgorithmException e ) {
            LOGGER.debug( "Problème de hashage du mot de passe {}", e.getMessage() );
        }
    }

    /**
     *
     */
    private void virifierInfoConnexion() {

        if ( !( emailClient != null && emailClient.trim().length() != 0 && emailClient
                .matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) ) {
            setErreur( CHAMP_EMAIL, "Merci de saisir une adresse mail valide." );
        }
        if ( mdpClient != null && mdpClient.trim().length() != 0 ) {
            if ( mdpClient.trim().length() < 6 ) {
                setErreur( CHAMP_MDP, "Les mots de passe doivent contenir au moins 6 caractères. " );
            }
        } else {
            setErreur( CHAMP_MDP, "Merci de saisir votre mot de passe." );
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

    public String getEmailClient() {
        return emailClient;
    }

    public String getMdpClient() {
        return mdpClient;
    }
}
