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

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class CrearAlbumController implements Initializable {


    @FXML
    private TextField txtNombreAlbum;
    @FXML
    private TextArea txtDescripcionAlbum;
    @FXML
    private ImageView imgviewPortada;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void crearAlbum(ActionEvent event) {
        /*
        ##########################################
        LOGICA PARA LA CREACION DE UN NUEVO ALBUM
        ##########################################
        */
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        //Volvemos a la ventana de inicio
        App.setRoot("menuPrincipal");
    }

    @FXML
    private void buscarImagenPortada(ActionEvent event) {
        /*
        Metodo que se ejecuta al presionar el boton browse, este
        permite buscar una imagen para colocar como foto de portada
        en el album creado. Si no se selecciona ninguna imagen, se coloca
        una imagen por defecto
        */
    }

}
