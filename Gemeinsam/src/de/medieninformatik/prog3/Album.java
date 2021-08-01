package de.medieninformatik.prog3;

import java.util.ArrayList;
import java.util.List;

/**
 * Album Objekt
 * dient zur Speicherung aller relevanten Infos eines Albums
 *
 */
public class Album {
    /**
     * Initialisierung relevanter Attribute
     */
    private String vorname;
    private String nachname;
    private int interpretid;
    private List<Song> album = new ArrayList<>();
    private String info;

    /**
     * Konstruktor
     * @param vorname
     * @param nachname
     * @param interpretid
     * @param info
     */
    public Album(String vorname, String nachname, int interpretid, String info) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.interpretid = interpretid;
        this.info = info;
    }

    /**
     * default Konstruktor mit speziefischem Inhalt, zur Vermeidung von Fehlern
     * dieser ist notwendig für den ObjektMapper in der DataReceiver Klasse
     *
     * Dieser erstelt dort bereits ein Album, um es dann mit Werten zu füllen die in der Json Datei übergeben werden.
     * Da an der Stelle und zu dem Zeitpunkt, wo der Mapper das Album erstellt, noch keine Attribute übergeben werden,
     * muss ein Konstruktor ohne Attribute zur Erzeugung des Album-Objektes aufgerufen werden.
     */
    public Album(){
        this.vorname= "";
        this.nachname= "";
        this.interpretid=-1;
        this.album=null;
        this.info ="";
    }
    /**
     * getter-Methoden zum gezielten Zugriff auf die Attribute eines Album-Objektes
     * @return
     */
    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public int getInterpretid() {
        return interpretid;
    }

    public List<Song> getAlbum() {
        return album;
    }

    public String getInfo() {
        return info;
    }
    /**
     * Setter-Methode zum Hinzufügen der befüllten Liste von Songs zum Album
     * @param album
     */
    public void setAlbum(List<Song> album) {
        this.album = album;
    }
}
