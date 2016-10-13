package fr.norsys.controleur;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.norsys.beans.Fichier;
import fr.norsys.forms.UploadForm;
import fr.norsys.util.CreationXML;

@Controller
public class UploadControleur {

    private static final String CHEMIN      = "C:/apache-tomcat-7.0.69/lib/";

    private static final String ATT_FICHIER = "fichier";
    private static final String ATT_FORM    = "form";
    private static final String VUE         = "/index";

    @RequestMapping( path = "/UploadFile", method = RequestMethod.POST )
    public String chargerFichier( final HttpServletRequest request ) {

        // Enregistrement du jmxTrans-agent.jar
        final UploadForm form = new UploadForm();
        final Fichier fichier = form.enregistrerFichier( request, CHEMIN );

        // Création et configuration du jmxTrans-agent.xml
        new CreationXML();

        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_FICHIER, fichier );
        return VUE;
    }
}