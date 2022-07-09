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

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import modelo.Album;
/**
 * FXML Controller class
 *
 * @author Det-Pc
 */
public class VisualizarFotosEnAlbumController implements Initializable {


    @FXML
    private Label lblNombreAlbumFotos;
    @FXML
    private Button buttonEditarPersonasFoto;
    @FXML
    private ImageView imgvFotos;
    
    public static Album albumOG;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(albumOG!=null)
            lblNombreAlbumFotos.setText(albumOG.getNombre());
    }    
    
    @FXML
    private void mostrarFotoAnterior(ActionEvent event) {
        System.out.println("Se muestra la foto anterior en la lista circular de fotos");
    }

    @FXML
    private void switchToVisualizarAlbumes(ActionEvent event) throws IOException {
        //Volvemos a la ventana anterior
        App.setRoot("visualizarAlbumes");
    }

    @FXML
    private void eliminarFotoActual(ActionEvent event) {
        System.out.println("Se pide confirmacion para eliminar la foto actual");
    }

    @FXML
    private void editarPersonasEnFoto(ActionEvent event) {
        System.out.println("Se cambia a una ventana de edicion de personas en la foto");
    }

    @FXML
    private void mostrarFotoSiguiente(ActionEvent event) {
        System.out.println("Se muestra la foto siguiente");
    }

}
