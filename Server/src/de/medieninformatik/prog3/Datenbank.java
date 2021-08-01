package de.medieninformatik.prog3;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Java Class Datenbank
 * stellt die Verbindung zur lokalen Datenbank her.
 * Hier werden Datenabfragen getätigt und weiterverarbeitet
 */

public class Datenbank  {

    /**
     * klassenweit bekannte, einzigartige ,finale Datenbank, nach Singleton-Entwurfsmuster
     */
    private static final Datenbank DATENBANK = new Datenbank();

    /**
     *Anlage eines Statement für Datenbankbefehle
     */
    Statement statement01;

    /**
     * ################################################
     *
     * Anlage einer Verbindung zur Datenbank mittels Connection und dem DriverManager
     * der getConnection() wird die URL der Datenbank übergeben, sowie ein User und ein Passwort
     *
     * Im Falle eines datenbankbedingten Fehlers wird eine Fehlermeldung geworfen
     *
     * Datenbank URI ist abhängig vom Ort an dem die Datenbank liegt.
     * Die Datenbank kann online verfügbar sein, oder local installiert sein.
     *
     * Bei der Benutzung von xampp ist auf den entsprechenden Port (hier in der URl: 3306) zu achten!
     */

    /**
     * Konstruktor nach dem Singleton Entwurfsmuster
     * Konstruktor ist private, sodass sich die Singleton Klasse nur selbst instanziieren kann
     *
     * es darf nur eine connection zur Datenbank geben!
     */
    private Datenbank() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/musikdatenbank", "root", "");
            statement01 = connection.createStatement();
        }catch (SQLException e){
            System.err.println("Es gibt wohl einen Fehler bei der Datenbankverbindung.");
        }
    }

    /**
     * Gibt die Datenbank zurück
     * @return
     */
    public static Datenbank getInstance(){
        return DATENBANK;
    }


    /**
     * die getData Methode führt alle Abfragen durch und speichert die abgefragten Daten in einer HashMap
     * @return datenMap , welche die Sammlung aller abgefragter Daten darstellt
     * @throws SQLException
     */
    public HashMap <Integer, Album> getData () throws SQLException{
        HashMap datenMap = new HashMap();


        /**
         * Abfrage der Anzahl der Interpreten und Abspeichern in int
         *
         * Dies dient zur dynamischen Erweiterung aller nachfolgenden Schritte, sollte sich die Anzahl der
         * Datenbankeinträge ändern
         *
         * Das ResultSet sollte eine einsplatige Tabelle zurück geben, in der die datenbankseitig gezählte Anzahl
         * der Interpreten gespeichert ist.
         */
        ResultSet rsInterpretCount = statement01.executeQuery("SELECT  COUNT(*) FROM interpret ");
        int idCount=0;
        while (rsInterpretCount.next()){
            idCount = Integer.valueOf(rsInterpretCount.getString(1));
        }

        /**
         * Abfrage der einzelnen Songs der entsprechenden Interpret_ID
         *
         * Über die Anzahl aller Interpreten wird eine Ergebnistabelle im ResultSet rsInterpret gespeichert.
         * Das auszuführende SQL-Statement wird der Methode executeQuery als String übergeben
         * Laufzeitvariable i ist in jedem Schleifendurchlauf die entsprechende Interpreten ID,
         * wie sie in der Datenbank definiert ist
         */
        for(int i =1 ;i <= idCount; i++){
            ResultSet rsInterpret = statement01.executeQuery("SELECT * FROM Interpret WHERE ID = " + i);
            /**
             * Erzeuge eine neue Instanz von Album
             * Lege eine Liste vom Typ Song als ArrayList an
             */
            Album album= null;
            List<Song> songlist = new ArrayList();
            /**
             * Solange die Ergebnistabelle Inhalt hat:
             *
             * Initialisierung eines neuen Albums für den Interpreten der gerade über die For-Schleife und dessen ID
             * abgefragt wurde
             * diesem Album werden, wie im Album.Konstruktor verlangt, entsprechende Werte ais der Ergebnistabelle übergeben
             *
             * Ein Album speichert den Vor und Nachnamen des Interpreten, die ID und Infos über den Interpreten
             */
            while (rsInterpret.next()){
                album = new Album(rsInterpret.getString(2), rsInterpret.getString(3),
                        rsInterpret.getInt(1), rsInterpret.getString(4));
            }


            /**
             * Anlage einer neuen Ergebnistabelle als ResultSet
             *
             * "hole alles aus der Tabelle Song, wo die Interpreten ID jene ist, in dessen Schleifendurchlauf wie uns gerade befinden
             * es wird eine neue Instanz von Song erzeugt, in der die geforderten Song-Informationen aus dem ResultSet übergeben werden
             *
             * Anschließend wir jeder song der interpreteneigenen Songlist hinzugefügt
             */
            ResultSet rsSong = statement01.executeQuery("SELECT * FROM Song WHERE Interpret_ID = " + i);

            //Solange es weitere Songs des Interpreten gibt
            while (rsSong.next()){
               Song song = new Song(rsSong.getInt(1), rsSong.getInt(2),
                       rsSong.getString(3), rsSong.getString(4));
               // jeder song wird in seinem Schleifendurchlauf der Songlist hinzugefügt
                songlist.add(song);
            }

            /**
             * Da ein Album neben den oben beschriebenen Attributen auch eine Liste von Songs beinhalten kann, wird die gerade befüllte
             * songlist jetzt dem Album hinzugefügt
             */
            album.setAlbum(songlist);

            /**
             * der DatenMap werden die gefüllten Alben übergeben,
             * als Key fungiert die InterpretenID welche in jedem Schleifendurchlauf i ist.
             */
            datenMap.put(i, album);
        }
        return datenMap;
    }
}
