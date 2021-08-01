package de.medieninformatik.prog3;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    /**
     * setzt den Server mit entsprechender Adresse auf
     * @param args wird übergeben
     * @throws URISyntaxException, wenn Programmfehler auftreten
     * @throws IOException, wenn Programmfehler auftreten
     */

    public static void main(String[] args) throws URISyntaxException, IOException {

        Logger.getLogger("org.glassfish").setLevel(Level.ALL);

        URI baseUri = new URI("http://localhost:8000/music");

        ResourceConfig config = ResourceConfig.forApplicationClass(ServerApplication.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);

        StaticHttpHandler handler = new StaticHttpHandler("web");
        handler.setFileCacheEnabled(false);
        ServerConfiguration serverConfiguration = server.getServerConfiguration();
        serverConfiguration.addHttpHandler(handler, "/");

        /**
         * Überprüfung des Serverstatus
         * wenn Server noch nicht gestartet -> starte Server
         */
        if (!server.isStarted()) server.start();
        System.out.println("ENTER stoppt den Server.");
        System.in.read();
        server.shutdown();
    }
}

