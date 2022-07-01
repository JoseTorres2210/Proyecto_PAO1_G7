/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoedgrupo7;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelo.Album;

public class PantallaInicioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Album.crearArchivoAlbumes(App.pathAlbumes);
        
    }    
    
    @FXML
    private void switchToMenuPrincipal(ActionEvent event) throws IOException{
        System.out.println("Cambio al menu principal");
        App.setRoot("menuPrincipal");
    }

}
