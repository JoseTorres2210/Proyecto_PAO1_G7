/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoedgrupo7;

import TDAs.CircularDoublyLinkedList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelo.Foto;

/**
 * FXML Controller class
 *
 * @author Det-Pc
 */
public class MostrarResultadosFiltroController implements Initializable {
    public static CircularDoublyLinkedList<Foto> listaFotosFiltradas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        /*
        #########################################
        Se recorre la lista de fotos filtradas y se crea un vbox por cada una
        de ellas, posteriormente se agrega al vbox la foto, un lbl con el nombre de la foto
        y el album al que pertenece
        #########################################
        */
        
        
        
    }    
    
    @FXML
    private void switchToFiltrarFotos(ActionEvent event) throws IOException {
        App.setRoot("FiltrarFotos");
    }

    @FXML
    private void switchToMenuPrincipal(ActionEvent event) throws IOException {
        App.setRoot("menuPrincipal");
    }

}
