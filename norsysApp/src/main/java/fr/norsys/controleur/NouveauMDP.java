package fr.norsys.controleur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NouveauMDP {

    private static final Logger LOGGER    = LoggerFactory.getLogger( NouveauMDP.class );
    private static final String VUE_INDEX = "form_auth/nouveauMdp";

    /**
     * @return L'adresse de la page inscription
     */
    @RequestMapping( "/nouveauMdp" )
    public String affichage() {
        LOGGER.info( "----- Test Slf4j -----" );
        return VUE_INDEX;
    }
}