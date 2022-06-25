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

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Album;

public class VisualizarAlbumesController implements Initializable {


    @FXML
    private TableView tvAlbumes;
    @FXML
    private TableColumn<Album, String> tcAlbumes;
    @FXML
    private TableColumn<Album, Void> tcOpciones;
    @FXML
    private TableColumn<Album, String> tcDescripcion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        Aqui se debe inicializar el table view
        */
        tcAlbumes.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        
        
        //##########Metodo para agregar opciones
        //agregarOpciones();
    }    
    
    @FXML
    private void switchToFiltrarFotos(ActionEvent event) {
        /*
        Se pasa a una ventana de filtro para las fotos
        */
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("menuPrincipal");
    }
    
    
    
    private void agregarOpciones(){
        /*
        #################
        TODOOOOOOOOOO:
        AGREGAR LOS BOTONES QUE REPRESENTAN LAS OPCIONES
        #################
        */
    }

}
