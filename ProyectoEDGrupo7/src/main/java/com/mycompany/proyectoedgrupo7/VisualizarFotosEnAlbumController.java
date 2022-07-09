/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoedgrupo7;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void mostrarFotoAnterior(ActionEvent event) {
    }

    @FXML
    private void switchToVisualizarAlbumes(ActionEvent event) {
    }

    @FXML
    private void eliminarFotoActual(ActionEvent event) {
    }

    @FXML
    private void editarPersonasEnFoto(ActionEvent event) {
    }

    @FXML
    private void mostrarFotoSiguiente(ActionEvent event) {
    }

}
