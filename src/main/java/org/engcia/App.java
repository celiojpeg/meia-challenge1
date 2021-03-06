package org.engcia;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;
import org.engcia.meiaChallenge1Team4.routeHandlers.CarInfoHandler;
import org.engcia.meiaChallenge1Team4.routeHandlers.FireRulesHandler;

import java.io.IOException;

public class App extends RouterNanoHTTPD {
    public App() throws IOException {
        super(8081);
        addMappings();
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning! http://localhost:8081/ \n");
    }

    public static void main(String[] args ) throws IOException {
        try {
            new App();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }

    @Override
    public void addMappings() {
        addRoute("/fire-rules", FireRulesHandler.class);
        addRoute("/car-info", CarInfoHandler.class);
    }
}