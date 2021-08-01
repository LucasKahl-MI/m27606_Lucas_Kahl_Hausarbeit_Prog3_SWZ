package de.medieninformatik.prog3;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Steuert die Datendarstellung auf der GUI
 */

public class InterfaceController {

    private DataReceiver receiver;

    /**
     * Verweise auf die entsprechenden fx:id in der Interface.fxml
     */
    @FXML
    public GridPane kuenstlerpane;
    @FXML
    public TextArea info;
    @FXML
    public GridPane songs;
    @FXML
    public Label name;
    @FXML
    public Label topsongs;


    private final String URI= "http://localhost:8000/music";

    /**
     * Deklaration der Hashmap welche alle Daten bekommt
     */
    private HashMap<Integer, Album> map;


    /**
     * InterfaceController Konstruktor
     * übergibt einer neuen Instanz von DataReceiver die URI
     *
     * map bekommt die entpackten,uber die URI erhaltenen Daten mit der URI-Endung /song
     * @throws IOException
     */
    public InterfaceController() throws IOException {

        receiver = new DataReceiver(URI);
        map = receiver.entpackenDaten("/song");
    }

    /**
     * initialisiert FXML-bezogen die zeigeKuenstername()-Methode
     */
    @FXML
    public void initialize(){
        zeigeKuenstlername();
    }

    public void zeigeKuenstlername(){

        /**
         * Deklaration und Initialisierung von Counts für die Zeilen und Spalten
         * X -> Spalten
         * Y -> Zeilen
         */
        int countX = 0;
        int countY = 0;


        ArrayList<String> liste = new ArrayList();

        map.entrySet().forEach(entity->{
            System.out.println(entity.getKey() + "__" + entity.getValue());
            liste.add(entity.getValue().getNachname());
        });
        for ( String l : liste) {
            System.out.println("Nachname ist: "+l);
        }

        /**
         * Hier ist zunächst über hard code festgelegt, dass es 6 Schleifendurchläufe gibt. genau so viele, wie es
         * Interpreten in der GUI geben wird. Das ist perspektivisch noch dynamisch zu gestalten.
         *
         * für jeden Interpreten, angesprochen über i:
         *
         * wird eine neue StackPane erzeugt
         *
         * wird ein neuer Text erzeugt, welchem über die getVorname() und getNachname(), diese Werte
         * als zusammengesetzter String übergeben wird. Die Stellen in der map müssen mit i+1 angesprochen werden,
         * da bei Ergebnistabellen die erste Spalte mit 1 beginnt,nicht wie bei Arrays mit 0.
         */

        for(int i = 0; i<=5;i++){
            StackPane pane = new StackPane();
            Text text = new Text(map.get(i+1).getVorname() + "\n" + map.get(i+1).getNachname());

            /**
             * Textstyling:
             * Schriftart, Schriftschnitt und Schriftgröße für die Darstellung
             * Textfarbe
             */
            text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
            text.setFill(Color.WHITE);
            /**
             * hinzufügen des Textes zur entsprechenden pane des Schleifendruchlaufes
             */
            pane.getChildren().add(text);
            /**
             * Textausrichtung Mittig
             */
            pane.setAlignment(text, Pos.CENTER);
            /**
             * Vergabe einer paneID
             */
            pane.setId(String.valueOf(i+1));
            /**
             * Lamdaausdruck:
             * bei MouseClicked -> rufe interpretInfo() auf */
            pane.setOnMouseClicked(e -> {
               interpretInfo(pane.getId());
            });

            /**
             * um das richtige Layout mit korrektem Umbruch zu gewährleisten
             * Solange der Spalten-CountX kleiner als der Spaltencount der gesamten, angelegten Pane ist
             * -> füge die in der Schleife erzeugte Pane an den Stellen countX und countY hinzu
             * zähle countX um 1 hoch
             *
             * jede Zeile wird von links nach rechts vollständig befüllt.
             */
            if (countX<kuenstlerpane.getColumnCount()){
                kuenstlerpane.add(pane, countX, countY);
                countX++;
                /**
                 * Anderenfalls : Spaltencount = ColumnCount-> genau dann ist eine Zeile voll
                 * zähle also countY um 1 hoch weil eine neue Zeile beginnt, setzt countX auf 0, da wir wieder in der
                 * vordersten Spalte beginnen wollen,
                 *
                 * füge die Pane an der abgezielten Stelle hinzu und Springe eine Spalte nach rechts, in dem der countX
                 * um 1 erhöht wird
                 */
            }else{
                countY++;
                countX=0;
                kuenstlerpane.add(pane, countX, countY);
                countX++;
            }
        }
    }

    /**
     *
     * @param id
     */
    private void interpretInfo(String id){
        int paneId = Integer.valueOf(id);

        /**
         * GridPane muss bei Neuaufruf und vor der neuen Befüllung leer gemacht werden -> daher clear aufrufen
         */
        songs.getChildren().clear();

        /**
         * TextArea mit den Infos soll  nicht bearbeitbar sein -> daher setEditable(false)
         */
        info.setEditable(false);
        /**
         * Befüllt die Area mit dem entsprechenden Text
         */
        info.setText("Unnützes Wissen ToGo: " + "\n" + map.get(paneId).getInfo() + "\n");


        /**
         * für die Anzahl der Inhalte eines Album
         * erzeuge ein Label text2 , füge den Inhalt aus der map ein und schreibe es in die GridPane songs in die
         * nullte Spalte und i- te Zeile
         */
        for (int i =0; i < map.get(paneId).getAlbum().size(); i++){
            Label text2 = new Label(map.get(paneId).getAlbum().get(i).toString());
            songs.add(text2,0, i);
        }

        /**
         * fülle Text in Textarea name mit Vor- und Nachnamen -> geladen aus der Map an der Stelle der
         * PaneId
         *
         * Setze Schriftart für TextArea name
         */
        name.setText((map.get(paneId).getVorname()) +" "+ map.get(paneId).getNachname());
        name.setFont(Font.font ("Verdana", 20));

        /**
         * Setze Textarea topsongs
         */
        topsongs.setText("Die top 3 Songs:");
    }
}
