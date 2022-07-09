package com.mycompany.proyectoedgrupo7;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class FiltrarFotosController implements Initializable{

    @FXML
    private Button buttonFiltrar;
    @FXML
    private Button buttonRegresar;
    @FXML
    private TextField txtLugar;
    @FXML
    private TextField txtPersona;
    @FXML
    private DatePicker fechaDesde;
    @FXML
    private DatePicker fechaHasta;
    @FXML
    private CheckBox filtrarPersona;
    @FXML
    private CheckBox filtrarLugar;
    @FXML
    private CheckBox filtrarFecha;

    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("visualizarAlbumes");
    }

    
    private void buscar(ActionEvent event){
        if(filtrarPersona.isSelected()){
            
        }
        if(filtrarFecha.isSelected()){
            
        }
        if(filtrarLugar.isSelected()){
            
        }
    }

    
}
