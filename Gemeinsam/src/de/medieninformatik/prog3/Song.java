package de.medieninformatik.prog3;


/**
 * Song Objekt
 * dient zur Speicherung aller relevanten Infos eines Songs
 *
 */

public class Song {
    /**
     * Initialisierung relevanter Attribute
     */
    private int titel_id;
    private int interpret_id;
    private String song_titel;
    private String song_laenge;

    /**
     * Konstruktor
     * @param titel_id
     * @param interpret_id
     * @param song_titel
     * @param song_laenge
     */
    public Song(int titel_id, int interpret_id, String song_titel, String song_laenge) {
        this.titel_id = titel_id;
        this.interpret_id = interpret_id;
        this.song_titel = song_titel;
        this.song_laenge = song_laenge;
    }

    /**
     * default Konstruktor zur Fehlervermeidung mit definiertem Inhalt
     *
     * für den Fall es wird ein Song erstellt, ohne das er direkt im gleichen Schritt mit entsprechenden Attributwerten
     * befüllt wird. Somit werten Default songs erstellt
     */
    public Song(){
        this.titel_id=-1;
        this.interpret_id=-1;
        this.song_titel="";
        this.song_laenge="";
    }

    /**
     * getter-Methoden zum gezielten Zugriff auf die Attribute eines Song-Objektes
     * @return
     */
    public int getTitel_id() {
        return titel_id;
    }

    public int getInterpret_id() {
        return interpret_id;
    }

    public String getSong_titel() {
        return song_titel;
    }

    public String getSong_laenge() {
        return song_laenge;
    }

    /**
     * Überschrebene toString Methode zur einfachen, geordneten Ausgabe auf der GUI
     * @return
     */
    @Override
    public String toString() {
        return this.titel_id +" "+ song_titel +" "+ song_laenge ;
    }
}
