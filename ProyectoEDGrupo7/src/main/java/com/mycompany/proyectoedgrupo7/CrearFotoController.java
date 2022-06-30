/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoedgrupo7;

import com.mycompany.proyectoedgrupo7.App;
import com.mycompany.proyectoedgrupo7.App;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author Det-Pc
 */
public class CrearFotoController implements Initializable {


    @FXML
    private Button buttonCrearFoto;
    @FXML
    private TextArea txtDescripcionAlbum;
    @FXML
    private TextField txtLugarFoto;
    @FXML
    private DatePicker fechaFoto;
    @FXML
    private RadioButton rbAparecenPersonas;
    @FXML
    private RadioButton rbNoAparecenPersonas;
    @FXML
    private ImageView imgviewFotoSeleccionada;
    @FXML
    private Button buttonSeleccionarPersonas;
    @FXML
    private ComboBox<?> cbAlbumes;
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        buttonSeleccionarPersonas.setVisible(false);
        
    }    
    
    @FXML
    private void crearFoto(ActionEvent event) {
        //Metodo para llevar a cabo la creacion de una foto
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("menuPrincipal");
    }

    @FXML
    private void browseFoto(ActionEvent event) {
        //Busca la foto que se va a guardar
    }

    @FXML
    private void switchToSeleccionarPersonas(ActionEvent event) {
        /*
        AQUI LA IDEA ES PASAR A UN LV DE PERSONAS DONDE EL USUARIO
        PUEDA SELECCIONAR A TODAS LAS PERSONAS QUE SE DESEA AGREGAR A LA FOTO
        */
    }

    @FXML
    private void unlockButton() {
        /*
        Metodo para bloquear el boton de agregar personas hasta que se seleccione el 
        radio button con el texto "Si"
        */
        if(rbAparecenPersonas.isSelected()){
            buttonSeleccionarPersonas.setVisible(true);
        }else {
            buttonSeleccionarPersonas.setVisible(false);
        }
    }


}
