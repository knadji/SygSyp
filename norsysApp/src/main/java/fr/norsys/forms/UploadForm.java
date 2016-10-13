package fr.norsys.forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.norsys.beans.Fichier;

public class UploadForm {

    private static final String       CHAMP_FICHIER     = "fichier";
    private static final String       EXTENSION_FICHIER = "jar";
    private static final int          TAILLE_TAMPON     = 10240;

    private static final Logger       LOGGER            = LoggerFactory.getLogger( UploadForm.class );
    private final Map<String, String> erreurs           = new HashMap<String, String>();
    private InputStream               contenuFichier;
    private String                    nomFichier;
    private String                    resultat;

    /**
     *
     * @param request
     * @param chemin
     *            de sauvegarde du fichier
     * @return un fichier
     */
    public Fichier enregistrerFichier( final HttpServletRequest request, final String chemin ) {
        final Fichier fichier = new Fichier();
        recupParam( request );
        creationDuFichier( fichier, chemin );
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'envoi du fichier.";
        } else {
            resultat = "Echec de l'envoi du fichier.";
        }
        return fichier;
    }

    /**
     *
     * @param request
     * @param nomFichier
     * @param contenuFichier
     */
    private void recupParam( final HttpServletRequest request ) {
        try {
            final Part part = request.getPart( CHAMP_FICHIER );
            nomFichier = getNomFichier( part );
            if ( getExtensionFichier( nomFichier ).equals( EXTENSION_FICHIER ) ) {
                if ( nomFichier != null && !nomFichier.isEmpty() ) {
                    contenuFichier = part.getInputStream();
                }
            } else {
                setErreur( CHAMP_FICHIER, "vous devriez import un fichier de type \"war\"." );
            }
        } catch ( final IllegalStateException e ) {
            LOGGER.debug( "Erreur IllegaleStateException {}", e.getMessage() );
            setErreur( CHAMP_FICHIER, "Les données envoyées sont trop volumineuses." );
        } catch ( final IOException e ) {
            LOGGER.debug( "Erreur IOException {}", e.getMessage() );
            setErreur( CHAMP_FICHIER, "Erreur de configuration du serveur" );
        } catch ( final ServletException e ) {
            LOGGER.debug( "Erreur ServletException {}", e.getMessage() );
            setErreur( CHAMP_FICHIER,
                    "Ce type de requête n'est pas supporté, merci d'utiliser le formulaire prévu pour envoyer votre fichier." );
        }
    }

    /**
     *
     * @param nomFichier
     * @param contenuFichier
     * @param fichier
     * @param chemin
     */
    private void creationDuFichier( final Fichier fichier, final String chemin ) {

        if ( erreurs.isEmpty() ) {
            if ( nomFichier != null && contenuFichier != null ) {
                fichier.setNom( nomFichier );
            } else {
                LOGGER.debug( "Merci de sélectionner un fichier à envoyer." );
            }
        }
        if ( erreurs.isEmpty() ) {
            try {
                ecrireFichier( contenuFichier, nomFichier, chemin );
            } catch ( final Exception e ) {
                setErreur( CHAMP_FICHIER, "Erreur l'ors de l'écriture du fichier sur le disque." );
            }
        }
    }

    /**
     *
     * @param contenuFichier
     * @param nomFichier
     * @param chemin
     * @throws Exception
     */
    private void ecrireFichier( final InputStream contenuFichier, final String nomFichier, final String chemin )
            throws Exception {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream( contenuFichier, TAILLE_TAMPON );
            sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier ) ), TAILLE_TAMPON );
            final byte[] tampon = new byte[TAILLE_TAMPON];
            int longeur = 0;
            while ( ( longeur = entree.read( tampon ) ) > 0 ) {
                sortie.write( tampon, 0, longeur );
            }
        } finally {
            try {
                sortie.close();
            } catch ( final IOException e ) {
            }
            try {
                entree.close();
            } catch ( final IOException e ) {
            }
        }
    }

    /**
     *
     * @param part
     * @return
     */
    private String getNomFichier( final Part part ) {
        for ( final String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }

    /**
     *
     * @param nomFichier
     * @return l'extension du fichier
     */
    private String getExtensionFichier( final String nomFichier ) {
        String extention = "";
        final int i = nomFichier.lastIndexOf( "." );
        if ( i > 0 ) {
            extention = nomFichier.substring( i + 1 );
        }
        return extention;
    }

    /**
     *
     * @return un resultat
     */
    public String getResultat() {
        return resultat;
    }

    /**
     *
     * @return une map d'erreur
     */
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    /**
     *
     * @param champ
     *            ou écrire le message
     * @param message
     *            d'erreur à écrire
     */
    private void setErreur( final String champ, final String message ) {
        erreurs.put( champ, message );
    }
}
