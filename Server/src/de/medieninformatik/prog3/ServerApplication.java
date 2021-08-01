package de.medieninformatik.prog3;


import de.medieninformatik.rest.ServerRest;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * rs.core Application wird erzeugt
 * die singletons und classes werden gespeichert
 * Speichert alle Clienten, welche sich mit dem Server verbinden
 */

public class ServerApplication extends Application {

    private Set<Object> singleton = new HashSet<>();
    private Set<Class<?>> classes = new HashSet<>();

    public ServerApplication() {
        singleton.add(new ServerRest());
        classes.add(ServerRest.class);
    }

    /**
     * Getter-Methode
     * @return Set von classes
     */

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    /**
     * Getter-Methode
     * @return Set von singletons
     */

    @Override
    public Set<Object> getSingletons() {
        return singleton;
    }

}

