package fr.norsys.controleur;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class CollectAppsServerControleur {

    private static final String DOMAIN = "Catalina";
    private static final Logger LOGGER = LoggerFactory.getLogger( CollectAppsServerControleur.class );

    /**
     * Récupere les noms des applications dans le serveur
     * 
     * @return La liste des noms des applications
     */
    public Iterable<String> collectAllDeployedApps() {
        final Set<String> result = new HashSet<>();
        try {
            final Set<ObjectName> instances = findServer()
                    .queryNames( new ObjectName( "Catalina:j2eeType=WebModule,*" ), null );
            for ( final ObjectName each : instances ) {
                result.add( StringUtils.substringAfterLast( each.getKeyProperty( "name" ), "/" ) );
            }
        } catch ( final MalformedObjectNameException e ) {
            LOGGER.debug( " MalformeObjectNameException : {}", e.getMessage() );
        }
        return result;
    }

    /**
     * Récupérer le MBeanServeur Catalina
     * 
     * @return Le MBeanServer
     */
    private MBeanServer findServer() {
        final ArrayList<MBeanServer> servers = MBeanServerFactory.findMBeanServer( null );
        for ( final MBeanServer eachServer : servers ) {
            for ( final String domain : eachServer.getDomains() ) {
                if ( domain.equals( DOMAIN ) ) {
                    return eachServer;
                }
            }
        }
        return null;
    }
}