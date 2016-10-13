package fr.norsys.controleur;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.norsys.beans.Client;
import fr.norsys.dao.ClientDao;
import fr.norsys.forms.CreationClientForm;

@Controller
public class ClientControleur {

    private static final String VUE_CLIENT                 = "form_client/afficherClient";
    private static final String VUE_FORM_CLIENT            = "form_client/creerClient";
    private static final String VUE_LISTE_CLIENTS          = "form_client/listerClients";
    private static final String VUE_REDIRECT_LISTE_CLIENTS = "redirect:listeClients";
    private static final String LISTE_CLIENT               = "listeClient";
    private static final String ATT_ID                     = "idClient";
    private static final String ATT_CLIENT                 = "client";
    private static final String ATT_FORM                   = "form";

    @Autowired
    private ClientDao           clientDao;

    /**
     * @return L'adresse de la page jsp.
     */
    @RequestMapping( path = "/creationClient", method = RequestMethod.GET )
    public String afficheFormClient() {
        return VUE_FORM_CLIENT;
    }

    /**
     * @param request
     * @return L'adresse de la page jsp.
     */
    @RequestMapping( path = "/creationClient", method = RequestMethod.POST )
    public String creationClient( final HttpServletRequest request ) {
        final CreationClientForm clientForm = new CreationClientForm( request );
        final Client client = creationClient( request, clientForm );
        if ( clientForm.getErreurs().isEmpty() ) {
            clientDao.creer( client );
            return VUE_CLIENT;
        } else {
            return VUE_FORM_CLIENT;
        }
    }

    /**
     * @param model
     * @return L'adresse de la vue liste client
     */
    @RequestMapping( "/listeClients" )
    public String afficheListeClient( final ModelMap model ) {
        model.addAttribute( LISTE_CLIENT, clientDao.lister() );
        return VUE_LISTE_CLIENTS;
    }

    /**
     * @param request
     * @param idClient
     * @return L'adresse d'une page jsp.
     */
    @RequestMapping( "/suppressionClient" )
    public String supprimerClient( final HttpServletRequest request, @RequestParam( ATT_ID ) final String idClient ) {
        final Long id = Long.parseLong( idClient );
        if ( id != null ) {
            clientDao.supprimer( id );
        }
        return VUE_REDIRECT_LISTE_CLIENTS;
    }

    /**
     * @param request
     * @param clientForm
     * @return Un client
     */
    public Client creationClient( final HttpServletRequest request, final CreationClientForm clientForm ) {
        final Client client = new Client();
        client.setNom( clientForm.getNomClient() );
        client.setPrenom( clientForm.getPrenomClient() );
        client.setAdresseDeLivraison( clientForm.getAdresseDeLivraison() );
        client.setNumTel( clientForm.getTelephoneClient() );
        request.setAttribute( ATT_CLIENT, client );
        request.setAttribute( ATT_FORM, clientForm );
        return client;
    }
}
