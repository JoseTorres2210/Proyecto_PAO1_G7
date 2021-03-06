package com.mycompany.proyectoedgrupo7;

import TDAs.CircularDoublyLinkedList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import modelo.Foto;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static String pathPersonas = "archivos/personas.ser";
    public static String pathAlbumes = "archivos/albumes.ser";
    

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("pantallaInicio"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Data Structures Photo Booth");
        stage.show();
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    //metodo para cambiar el contenido de la escena
    static void changeRoot(Parent rootNode) {
        scene.setRoot(rootNode);
    }

}