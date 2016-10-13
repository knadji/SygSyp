package fr.norsys.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import fr.norsys.controleur.CollectAppsServerControleur;

public class CreationXML {

    private static final String[] paramSansKey    = { "objectName", "attribute", "resultAlias" };
    private static final String[] paramWhithKey   = { "objectName", "attribute", "key", "resultAlias" };
    private static final String   CHEMIN_FILE_XML = "C:/apache-tomcat-7.0.69/conf/jmxtrans-agent.xml";

    /**
     *
     */
    public CreationXML() {
        final CollectAppsServerControleur collectAppsInServer = new CollectAppsServerControleur();
        final DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            final Document doc = icBuilder.newDocument();
            final Element racine = doc.createElement( "jmxtrans-agent" );
            creationElementDocXML( doc, racine, collectAppsInServer.collectAllDeployedApps() );
            transformationEnFichierXML( doc );
        } catch ( final Exception e ) {
        }
    }

    /**
     *
     * @param doc
     * @param racine
     * @param appsInServer
     */
    private void creationElementDocXML( final Document doc, final Element racine, final Iterable<String> appsInServer ) {
        final Element queries = doc.createElement( "queries" );
        doc.appendChild( racine );
        racine.appendChild( queries );

        // Métrique OS
        queries.appendChild( getQuerie( doc, "java.lang:type=OperatingSystem", "ProcessCpuLoad",
                "os.cpu.processCpuLoad" ) );
        queries.appendChild( getQuerie( doc, "java.lang:type=OperatingSystem", "ProcessCpuTime",
                "os.cpu.processCpuTime" ) );
        queries.appendChild( getQuerie( doc, "java.lang:type=OperatingSystem", "SystemCpuLoad", "os.cpu.systemCpuLoad" ) );

        // JVM HeapMemoryUsage
        queries.appendChild( getQuerie( doc, "java.lang:type=Memory", "HeapMemoryUsage", "init",
                "jvm.heapMemoryUsage.init" ) );
        queries.appendChild( getQuerie( doc, "java.lang:type=Memory", "HeapMemoryUsage", "committed",
                "jvm.heapMemoryUsage.committed" ) );
        queries.appendChild( getQuerie( doc, "java.lang:type=Memory", "HeapMemoryUsage", "max",
                "jvm.heapMemoryUsage.max" ) );
        queries.appendChild( getQuerie( doc, "java.lang:type=Memory", "HeapMemoryUsage", "used",
                "jvm.heapMemoryUsage.used" ) );

        // JVM NonHeapMemoryUsage
        queries.appendChild( getQuerie( doc, "java.lang:type=Memory", "NonHeapMemoryUsage", "init",
                "jvm.nonHeapMemoryUsage.init" ) );
        queries.appendChild( getQuerie( doc, "java.lang:type=Memory", "NonHeapMemoryUsage", "committed",
                "jvm.nonHeapMemoryUsage.committed" ) );
        queries.appendChild( getQuerie( doc, "java.lang:type=Memory", "NonHeapMemoryUsage", "max",
                "jvm.nonHeapMemoryUsage.max" ) );
        queries.appendChild( getQuerie( doc, "java.lang:type=Memory", "NonHeapMemoryUsage", "used",
                "jvm.nonHeapMemoryUsage.used" ) );

        // JVM ClassLoading
        queries.appendChild( getQuerie( doc, "java.lang:type=ClassLoading", "LoadedClassCount",
                "jvm.classLoading.loadedClassCount" ) );
        queries.appendChild( getQuerie( doc, "java.lang:type=ClassLoading", "TotalLoadedClassCount",
                "jvm.classLoading.totalLoadedClassCount" ) );
        queries.appendChild( getQuerie( doc, "java.lang:type=ClassLoading", "UnloadedClassCount",
                "jvm.classLoading.unloadedClassCount" ) );

        // JVM Threading
        queries.appendChild( getQuerie( doc, "java.lang:type=Threading", "ThreadCount", "jvm.threading.threadCount" ) );
        queries.appendChild( getQuerie( doc, "java.lang:type=Threading", "DaemonThreadCount",
                "jvm.threading.daemonThreadCount" ) );
        queries.appendChild( getQuerie( doc, "java.lang:type=Threading", "PeakThreadCount",
                "jvm.threading.peakThreadCount" ) );
        queries.appendChild( getQuerie( doc, "java.lang:type=Threading", "TotalStartedThreadCount",
                "jvm.threading.totalStartedThreadCount" ) );
        queries.appendChild( getQuerie( doc, "java.lang:type=Threading", "CurrentThreadCpuTime",
                "jvm.threading.currentThreadCpuTime" ) );

        // JVM GarbageCollector MarkSweep
        queries.appendChild( getQuerie( doc, "java.lang:type=GarbageCollector,name=PS MarkSweep", "CollectionCount",
                "jvm.gc-mark-sweep.collectionCount" ) );
        queries.appendChild( getQuerie( doc, "java.lang:type=GarbageCollector,name=PS MarkSweep", "CollectionTime",
                "jvm.gc-mark-sweep.collectionTime" ) );

        // JVM GarbageCollector Scavenge
        queries.appendChild( getQuerie( doc, "java.lang:type=GarbageCollector,name=PS Scavenge", "CollectionCount",
                "jvm.gc-mark-scavenge.collectionCount" ) );
        queries.appendChild( getQuerie( doc, "java.lang:type=GarbageCollector,name=PS Scavenge", "CollectionTime",
                "jvm.gc-mark-scavenge.collectionTime" ) );

        // JVM Compilation
        queries.appendChild( getQuerie( doc, "java.lang:type=Compilation", "TotalCompilationTime",
                "jvm.compilation.totalCompilationTime" ) );

        // JVM Runtime
        queries.appendChild( getQuerie( doc, "java.lang:type=Runtime", "Uptime", "jvm.runtime.uptime" ) );

        // Métrique TOMCAT

        // Métrique par application(s)
        for ( final String app : appsInServer ) {
            if ( !app.equals( "" ) && !app.equals( "docs" ) && !app.equals( "examples" )
                    && !app.equals( "manager" ) && !app.equals( "host-manager" ) ) {

                queries.appendChild( getQuerie( doc, "Catalina:type=Manager,context=/" + app + ",host=localhost",
                        "activeSessions", "application." + app + ".activeSessions" ) );
                queries.appendChild( getQuerie( doc, "Catalina:type=Manager,context=/" + app + ",host=localhost",
                        "sessionCounter", "application." + app + ".sessionCounter" ) );
                queries.appendChild( getQuerie( doc, "Catalina:type=Manager,context=/" + app + ",host=localhost",
                        "expiredSessions", "application." + app + ".expiredSessions" ) );
                queries.appendChild( getQuerie( doc, "Catalina:type=Manager,context=/" + app + ",host=localhost",
                        "maxActiveSessions", "application." + app + ".maxActiveSessions" ) );
                queries.appendChild( getQuerie( doc, "Catalina:type=Manager,context=/" + app + ",host=localhost",
                        "rejectedSessions", "application." + app + ".rejectedSessions" ) );
                queries.appendChild( getQuerie( doc, "Catalina:type=Manager,context=/" + app + ",host=localhost",
                        "sessionAverageAliveTime", "application." + app + ".sessionAverageAliveTime" ) );

                queries.appendChild( getQuerie(
                        doc,
                        "Catalina:type=JspMonitor,WebModule=//localhost/" + app
                                + ",J2EEApplication=none,J2EEServer=none,name=jsp",
                        "jspCount", "application." + app + ".jspCount" ) );
                queries.appendChild( getQuerie(
                        doc,
                        "Catalina:type=JspMonitor,WebModule=//localhost/" + app
                                + ",J2EEApplication=none,J2EEServer=none,name=jsp",
                        "jspQueueLength", "application." + app + ".jspQueueLength" ) );
                queries.appendChild( getQuerie(
                        doc,
                        "Catalina:type=JspMonitor,WebModule=//localhost/" + app
                                + ",J2EEApplication=none,J2EEServer=none,name=jsp",
                        "jspReloadCount", "application." + app + ".jspReloadCount" ) );
                queries.appendChild( getQuerie(
                        doc,
                        "Catalina:type=JspMonitor,WebModule=//localhost/" + app
                                + ",J2EEApplication=none,J2EEServer=none,name=jsp",
                        "jspUnloadCount", "application." + app + ".jspUnloadCount" ) );

                // Servlet-dispatcher
                queries.appendChild( getQuerie(
                        doc,
                        "Catalina:j2eeType=Servlet,name=servlet-dispatcher,WebModule=//localhost/" + app
                                + ",J2EEApplication=none,J2EEServer=none",
                        "classLoadTime", "application." + app + ".servlet-dispatcher.classLoadTime" ) );
                queries.appendChild( getQuerie(
                        doc,
                        "Catalina:j2eeType=Servlet,name=servlet-dispatcher,WebModule=//localhost/" + app
                                + ",J2EEApplication=none,J2EEServer=none",
                        "errorCount", "application." + app + ".servlet-dispatcher.errorCount" ) );
                queries.appendChild( getQuerie(
                        doc,
                        "Catalina:j2eeType=Servlet,name=servlet-dispatcher,WebModule=//localhost/" + app
                                + ",J2EEApplication=none,J2EEServer=none",
                        "loadOnStartup", "application." + app + ".servlet-dispatcher.loadOnStartup" ) );
                queries.appendChild( getQuerie(
                        doc,
                        "Catalina:j2eeType=Servlet,name=servlet-dispatcher,WebModule=//localhost/" + app
                                + ",J2EEApplication=none,J2EEServer=none",
                        "loadTime", "application." + app + ".servlet-dispatcher.loadTime" ) );
                queries.appendChild( getQuerie(
                        doc,
                        "Catalina:j2eeType=Servlet,name=servlet-dispatcher,WebModule=//localhost/" + app
                                + ",J2EEApplication=none,J2EEServer=none",
                        "requestCount", "application." + app + ".servlet-dispatcher.requestCount" ) );

                // Servlet
                queries.appendChild( getQuerie(
                        doc,
                        "Catalina:j2eeType=WebModule,name=//localhost/" + app + ",J2EEApplication=none,J2EEServer=none",
                        "errorCount", "application." + app + ".servlet.allErrorCount" ) );
                queries.appendChild( getQuerie(
                        doc,
                        "Catalina:j2eeType=WebModule,name=//localhost/" + app + ",J2EEApplication=none,J2EEServer=none",
                        "requestCount", "application." + app + ".servlet.allRequestCount" ) );
            }
        }

        racine.appendChild( getOutputWriter( doc, "org.jmxtrans.agent.ConsoleOutputWriter" ) );
        racine.appendChild( getOutputWriter( doc, "org.jmxtrans.agent.GraphitePlainTextTcpOutputWriter",
                "192.168.100.68", "2003" ) );
        racine.appendChild( getCollectIntervalInSeconds( doc, "10" ) );
        racine.appendChild( getReloadConf( doc, "5" ) );
    }

    /**
     *
     * @param doc
     * @throws TransformerFactoryConfigurationError
     * @throws TransformerConfigurationException
     * @throws TransformerException
     */
    private void transformationEnFichierXML( final Document doc ) throws TransformerFactoryConfigurationError,
            TransformerConfigurationException, TransformerException {
        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();
        final DOMSource source = new DOMSource( doc );
        final StreamResult result = new StreamResult( new File( CHEMIN_FILE_XML ) );
        transformer.transform( source, result );
    }

    /**
     *
     * @param doc
     * @param objets
     * @return
     */
    private Node getOutputWriter( final Document doc, final Object... objets ) {
        final Element outPutWriter = doc.createElement( "outputWriter" );
        outPutWriter.setAttribute( "class", (String) objets[0] );
        if ( objets.length == 3 ) {
            outPutWriter.appendChild( getOutPutElement( doc, outPutWriter, "host", objets[1] ) );
            outPutWriter.appendChild( getOutPutElement( doc, outPutWriter, "port", objets[2] ) );
        }
        return outPutWriter;
    }

    private Node getOutPutElement( final Document doc, final Element outPutWriter, final String name,
            final Object valeur ) {
        final Element node = doc.createElement( name );
        node.appendChild( doc.createTextNode( (String) valeur ) );
        return node;
    }

    /**
     *
     * @param doc
     * @param intervalSeconds
     * @return
     */
    private Node getCollectIntervalInSeconds( final Document doc, final String intervalSeconds ) {
        final Element intervale = doc.createElement( "collectIntervalInSeconds" );
        intervale.appendChild( doc.createTextNode( intervalSeconds ) );
        return intervale;
    }

    /**
     *
     * @param doc
     * @param reloadConfig
     * @return
     */
    private Node getReloadConf( final Document doc, final String reloadConfig ) {
        final Element reloadConfigCheck = doc.createElement( "reloadConfigurationCheckIntervalInSeconds" );
        reloadConfigCheck.appendChild( doc.createTextNode( reloadConfig ) );
        return reloadConfigCheck;
    }

    /**
     *
     * @param doc
     * @param objets
     * @return
     */
    private Node getQuerie( final Document doc, final Object... objets ) {
        final Element query = doc.createElement( "query" );
        initialisationAttributes( query, objets );
        return query;
    }

    /**
     *
     * @param query
     * @param objets
     */
    private void initialisationAttributes( final Element query, final Object... objets ) {
        final int tailleParam = objets.length;
        final String[] param;
        if ( tailleParam == 3 ) {
            param = paramSansKey;
        } else {
            param = paramWhithKey;
        }
        for ( int i = 0; i < tailleParam; i++ ) {
            query.setAttribute( param[i], (String) objets[i] );
        }
    }
}
