package fr.norsys.controleur;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.norsys.beans.Client;
import fr.norsys.beans.Commande;
import fr.norsys.dao.ClientDao;
import fr.norsys.dao.CommandeDao;
import fr.norsys.forms.CreationClientForm;
import fr.norsys.forms.CreationCommandeForm;

@Controller
public class CommandeControleur {

    private static final String VUE_COMMANDE           = "form_commande/afficherCommande";
    private static final String VUE_LISTE_COMMANDE     = "form_commande/listerCommandes";
    private static final String VUE_FORM_COMMANDE      = "form_commande/creerCommande";
    private static final String VUE_REDIRECT_LISTE_CMD = "redirect:listeCommandes";
    private static final String FORMAT_DATE            = "dd/MM/yyyy HH:mm:ss";
    private static final String LISTE_COMMANDE         = "listeCommande";
    private static final String LISTE_CLIENT           = "listeClient";
    private static final String ATT_COMMANDE           = "commande";
    private static final String ATT_FORM               = "form";
    private static final String ID_COMMANDE            = "idCommande";

    private static final Logger LOGGER                 = LoggerFactory.getLogger( CommandeControleur.class );

    @Autowired
    private ClientDao           clientDao;
    @Autowired
    private CommandeDao         commandeDao;

    /**
     * @param request
     * @return L'adresse d'une page jsp.
     */
    @RequestMapping( path = "/creationCommande", method = RequestMethod.GET )
    public String afficheFormCommande( final HttpServletRequest request ) {
        request.setAttribute( LISTE_CLIENT, clientDao.lister() );
        return VUE_FORM_COMMANDE;
    }

    /**
     * @param request
     * @param radioBox
     *            nouveau ou ancien client
     * @return L'adresse d'une page jsp.
     */
    @RequestMapping( path = "/creationCommande", method = RequestMethod.POST )
    public String creerCommande( final HttpServletRequest request,
            @RequestParam( value = "choixNouveauClient", required = false ) final String radioBox,
            @RequestParam( value = "listeClients", required = false ) final String idClient ) {

        final boolean nouveauClient = ( radioBox == null || !radioBox.equals( "ancienClient" ) );
        final CreationCommandeForm commandeForm = new CreationCommandeForm( request );
        Client client = creationClient( request, commandeForm, nouveauClient, idClient );

        if ( commandeForm.getErreurs().isEmpty() ) {
            if ( nouveauClient ) {
                clientDao.creer( client );
                client = clientDao.trouver( client.getAdresseMail() );
                LOGGER.debug( " Id du Client : {}", client.getId() );
            }
            final Commande commande = creationCommande( request, commandeForm, client );
            commandeDao.creer( commande );
            request.setAttribute( ATT_COMMANDE, commande );
            request.setAttribute( ATT_FORM, commandeForm );
            return VUE_COMMANDE;
        }
        else {
            return VUE_FORM_COMMANDE;
        }
    }

    /**
     * @param request
     * @return L'adresse d'une page jsp.
     */
    @RequestMapping( "/listeCommandes" )
    public String listeCommande( final HttpServletRequest request ) {
        request.setAttribute( LISTE_COMMANDE, commandeDao.lister() );
        return VUE_LISTE_COMMANDE;
    }

    /**
     * @param request
     * @param idCommande
     * @return L'adresse d'une page jsp.
     */
    @RequestMapping( path = "/suppressionCommande", method = RequestMethod.GET )
    public String supprimerCommande( final HttpServletRequest request,
            @RequestParam( ID_COMMANDE ) final String idCommande ) {
        final Long id = Long.parseLong( idCommande );
        if ( id != null ) {
            commandeDao.supprimer( id );
        }
        return VUE_REDIRECT_LISTE_CMD;
    }

    /**
     * @param request
     * @param commandeForm
     * @param nouveauClient
     * @return Un client
     */
    private Client creationClient( final HttpServletRequest request, final CreationCommandeForm commandeForm,
            final boolean nouveauClient, final String idClient ) {
        Client client;
        if ( nouveauClient ) {
            client = remplireClient( request, commandeForm );
        } else {
            final long id = Long.parseLong( idClient );
            client = clientDao.trouver( id );
        }
        return client;
    }

    /**
     * @param request
     * @param commandeForm
     * @param client
     * @return Une commande
     */
    private Commande creationCommande( final HttpServletRequest request, final CreationCommandeForm commandeForm,
            final Client client ) {
        final Commande commande = new Commande();

        final LocalDateTime dt = LocalDateTime.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern( FORMAT_DATE );
        final String date = dt.format( formatter );

        commande.setDate( date );
        commande.setMontant( Double.parseDouble( commandeForm.getMontantCommande() ) );
        commande.setClient( client );
        commande.setModeDeLivraison( commandeForm.getModeLivraisonCommande() );
        commande.setModeDePaiement( commandeForm.getModePaiementCommande() );
        commande.setStatutDeLivraison( commandeForm.getStatutLivraisonCommande() );
        commande.setStatutDePaiement( commandeForm.getStatutPaiementCommande() );

        return commande;
    }

    /**
     * @param request
     * @param commandeForm
     * @return Un client
     */
    private Client remplireClient( final HttpServletRequest request, final CreationCommandeForm commandeForm ) {
        final Client client = new Client();
        final CreationClientForm clientForm = new CreationClientForm( request );
        client.setNom( clientForm.getNomClient() );
        client.setPrenom( clientForm.getPrenomClient() );
        client.setAdresseDeLivraison( clientForm.getAdresseDeLivraison() );
        client.setNumTel( clientForm.getTelephoneClient() );
        commandeForm.getErreurs().putAll( clientForm.getErreurs() );
        return client;
    }
}
