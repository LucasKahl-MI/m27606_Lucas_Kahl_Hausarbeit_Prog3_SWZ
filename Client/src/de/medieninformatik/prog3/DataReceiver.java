package de.medieninformatik.prog3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 */

public class DataReceiver {

    /**
     * Deklaration notwendiger Attribute
     */
    private final String URI;
    private ObjectMapper mapper;
    private final Client CLIENT;

    /**
     * Konstruktor
     * @param URI
     */
    public DataReceiver(String URI) {
        this.mapper = new ObjectMapper();
        this.CLIENT = ClientBuilder.newClient();
        this.URI = URI;
    }

    /**
     * Die entpackenDaten() dient zum Entpacken des vom Server erhaltenen Json Strings
     *
     * Dies passiert explizit nach Prüfung des Status (Zeile 64) in Zeile 66
     * ->  resultMap =mapper.readValue(json, typeReference);
     *
     *
     * @param URI
     * @return resultMap -> beinhaltet die entpackten Daten zur Lokalen Weiterverarbeitung
     * @throws IOException
     *
     * erzeugt ein neues WebTarget
     */

    public HashMap<Integer, Album> entpackenDaten(String URI) throws IOException {

        WebTarget target = getTarget("GET", URI);
        Response response = target.request().accept(MediaType.TEXT_PLAIN).get();
        /**
         * Anlage der HashMap in welche die entpackten, erhaltenen Daten gespeichert werden sollen
         */
        HashMap<Integer, Album> resultMap = null;
        /**
         * Referenzobjekt für die HashMap
         */
        TypeReference <HashMap<Integer,Album>> typeReference = new TypeReference<HashMap<Integer, Album>>() {};

        if (status(response)== Response.Status.OK.getStatusCode()){
            String json= response.readEntity(String.class);
            resultMap =mapper.readValue(json, typeReference);
        }
        return resultMap;
    }


    /**
     * gibt das Target für eine zusammengeführte URI zurück
     * @param crud
     * @param URI
     * @return
     */
    public WebTarget getTarget(String crud, String URI){
        return CLIENT.target(this.URI + URI);
    }

    /**
     * Prüfmethode für das Response
     * @param response
     * @return Integer Wert als Prüfcode
     */
    public int status(Response response){
        int code = response.getStatus();
        String reason = response.getStatusInfo().getReasonPhrase();
        return code;
    }
}
