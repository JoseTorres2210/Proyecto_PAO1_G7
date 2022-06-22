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

import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author Det-Pc
 */
public class MenuPrincipalController implements Initializable {


    @FXML
    private ImageView imgviewFotoMenuPrincipal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void switchToAgregarFoto(ActionEvent event) {
    }

    @FXML
    private void switchToAlbumes(ActionEvent event) {
    }

    @FXML
    private void switchToCrearNuevoAlbum(ActionEvent event) {
    }

}
