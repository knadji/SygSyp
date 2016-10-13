package fr.norsys.controleur;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.norsys.beans.Client;
import fr.norsys.dao.ClientDao;
import fr.norsys.forms.InscriptionClientForm;

@Controller
@RequestMapping( "/inscription" )
public class InscriptionControleur {

    private static final String VUE_INSCRIPTION = "form_auth/inscription";
    private static final String ATT_FORM        = "form";
    private static final String VUE_INDEX       = "index";

    private static final Logger LOGGER          = LoggerFactory.getLogger( InscriptionControleur.class );

    @Autowired
    private ClientDao           clientDao;

    /**
     * @return L'adresse de la page inscription
     */
    @RequestMapping( method = RequestMethod.GET )
    public String affichage() {
        LOGGER.info( "----- Test Slf4j -----" );
        return VUE_INSCRIPTION;
    }

    /**
     * @param request
     * @return L'adresse de la page jsp.
     */
    @RequestMapping( method = RequestMethod.POST )
    public String inscriptionClient( final HttpServletRequest request ) {
        final InscriptionClientForm clientForm = new InscriptionClientForm( request );
        final Client client = creationClient( clientForm );
        request.setAttribute( ATT_FORM, clientForm );
        if ( clientForm.getErreurs().isEmpty() ) {
            clientDao.inscriptionClient( client );
            return VUE_INDEX;
        } else {
            return VUE_INSCRIPTION;
        }
    }

    /**
     * @param request
     * @param clientForm
     * @return un client
     */
    public Client creationClient( final InscriptionClientForm clientForm ) {
        final Client client = new Client();
        client.setAdresseMail( clientForm.getEmailClient() );
        client.setMotDePasse( clientForm.getMdpClient() );
        return client;
    }

}