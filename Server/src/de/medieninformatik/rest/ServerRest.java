package de.medieninformatik.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.medieninformatik.prog3.Album;
import de.medieninformatik.prog3.Datenbank;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * übermittelt die Daten an die Clienten als Json String
 */

@Path("song")
public class ServerRest {

    /**
     * die Datenbank beinhaltet alle Daten zu den Interpreten
     */

    private Datenbank datenbank;

    private ObjectMapper mapper;

    public ServerRest() {
        this.datenbank = Datenbank.getInstance();
        this.mapper = new ObjectMapper();
    }

    /**
     * getAllData() holt die Daten der Datenbank
     * speichert diese in einer Map zwischen und schreibt diese Daten über einen ObjectMapper in einen
     * Json String
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllData() {

        HashMap<Integer, Album> map;
        String json = null;
        try {
            map = datenbank.getData();
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        /**
         * Gibt den gebauten Json String zurück
         */
        return Response.ok(json).build();
    }
}

