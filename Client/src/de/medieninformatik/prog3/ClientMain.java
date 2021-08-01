package de.medieninformatik.prog3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @autor Lucas Kahl m27606
 * Prüfungshausarbeit Programmierung3
 * WiSe 2020/2021
 * Prüfer: Prof. Singer
 *
 * Abgabedatum: 28.02.2021
 */


/**
 * ClientMain
 * Bestandteil des Modules Client
 * extends Application -> für die Ausführung und Erzeugung einer JavaFX Oberfläche
 *
 * main() führt lediglich die launch Methode aus
 */



public class ClientMain extends Application {
    public static void main (String [] args){
        launch(args);
    }


    /**
     * start () befüllt die Stage
     * Layout und Styling der GUI wird mittels SceneBuilder realisiert
     * SceneBuilder schreibt basierend auf dem optischen Layout innerhalb der SceneBuilder-Oberfläche eine
     * fxml-Datei, welche in der start() über einen FXMLLoader geladen werden kann.
     * Dieser Loader wird mit der load()-Methode ausgeführt und der Inhalt an eine AnchorPane übergeben
     * die fxml-Datei musste zunächst im Modul Client, im Package de.medieninformatik.prog3 erzeugt werden.
     * Über Rechtsklick auf die fxml im Projektmenü, wurde dann "open in SceneBuilder" ausgewählt
     *
     * SceneBuilder ist eine kostenlos erhältliche Software die vor dessen Einsatz heruntergeladen und installiert werden muss
     *
     * @param stage
     * stage wird auf setResizeable(false) gesetzt, um eine Änderung der Fenstergröße der GUI zu unterbinden
     *
     * Erzeugung einer neuen Scene, welcher eine AnchorPane übergeben wird
     *
     * Scene wird der Stage hinzugefügt
     *
     * stage.show() zeigt die Oberfläche an
     *
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("Interface.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        stage.setResizable(false);
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("Musiker-Infos");
        stage.show();
    }
}
