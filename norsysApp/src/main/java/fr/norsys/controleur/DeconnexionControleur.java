package fr.norsys.controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeconnexionControleur {

    private static final String VUE_INDEX = "redirect:index";

    @RequestMapping( "/deconnexion" )
    public String deconnexionClient( final HttpServletRequest request ) {
        final HttpSession session = request.getSession();
        session.invalidate();
        return VUE_INDEX;
    }
}