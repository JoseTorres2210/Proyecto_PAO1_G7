/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoedgrupo7;

import exceptions.EmptyFieldsException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo.Persona;
/**
 * FXML Controller class
 *
 * @author Det-Pc
 */
public class CrearPersonaController implements Initializable {


    @FXML
    private Label lblCrearNuevaPersona;
    @FXML
    private Button buttonFinalizarCreacionPersona;
    @FXML
    private Button buttonCancelar;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void crearPersona(ActionEvent event) {
        //Se recuperan los valores de los campos especificados
        try{
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();

            if(nombre.equals("")||apellido.equals("")){
                throw  new EmptyFieldsException();
            }
            //Se crea a la persona
            Persona personaNueva = new Persona(nombre, apellido);
            
            //Se la agrega al archivo de personas
            Persona.agregarNuevaPersonaArchivos(personaNueva, App.pathPersonas);
            
            Alert exito = new Alert(Alert.AlertType.INFORMATION, "La persona ha sido agregada al sistema de manera exitosa");
            exito.setTitle("Exito");
            exito.setHeaderText("Operacion exitosa");
            exito.show();
            App.setRoot("crearFoto2");
            
        }catch(EmptyFieldsException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR,"Asegúrese de llenar todos los campos"); //FIXME
            alerta.setTitle("Error");
            alerta.setHeaderText("Ha ocurrido un error:");
            alerta.show(); 
        }catch(NullPointerException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR,"Asegúrese de llenar todos los campos"); //FIXME
            alerta.setTitle("Error");
            alerta.setHeaderText("Ha ocurrido un error:");
            System.out.println(e.getClass());
            alerta.show();  
        }catch(Exception e){
            System.out.println("EXCEPCION GENERAL INESPERADA");
        }

        
        
    }

    @FXML
    private void switchToCrearFoto2(ActionEvent event) throws IOException {
        //Se retorna a la parte anterior
        App.setRoot("crearFoto2");
    }

}