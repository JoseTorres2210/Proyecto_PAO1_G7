package com.mycompany.proyectoedgrupo7;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AgregarPersonaController implements Initializable{


    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblTitulo;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtApellido;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }


    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        //Volvemos a la ventana de inicio
        App.setRoot("visualizarFotosEnAlbum");
    }


    
}
