package fr.norsys.controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.norsys.beans.Client;
import fr.norsys.beans.Fichier;
import fr.norsys.dao.ClientDao;
import fr.norsys.forms.ConnexionClientForm;

@Controller
public class IndexControleur {

    private static final String VUE_INSCRIPTION    = "form_auth/inscription";
    private static final String ATT_SESSION_USER   = "sessionUtilisateur";
    private static final String VUE_INDEX          = "index";
    private static final String ATT_FORM_CONNEXION = "formConnexion";

    @Autowired
    private ClientDao           clientDao;

    /**
     * @return L'adresse de la page index
     */
    @RequestMapping( { "/index", "/" } )
    public String affichage( final Model model ) {
        final Fichier fileModel = new Fichier();
        model.addAttribute( "file", fileModel );
        return VUE_INDEX;
    }

    /**
     * @param request
     * @return une vue
     */
    @RequestMapping( path = { "/index", "/" }, method = RequestMethod.POST )
    public String inscriptionControleur( final HttpServletRequest request ) {
        final ConnexionClientForm connexionForm = new ConnexionClientForm( request );
        request.setAttribute( ATT_FORM_CONNEXION, connexionForm );
        if ( connexionForm.getErreurs().isEmpty() ) {
            final Client client = clientDao.connexionClient( connexionForm.getEmailClient(),
                    connexionForm.getMdpClient() );
            if ( client != null ) {
                final HttpSession session = request.getSession();
                session.setAttribute( ATT_SESSION_USER, client );
                return VUE_INDEX;
            }
        }
        return VUE_INSCRIPTION;
    }
}