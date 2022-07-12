/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoedgrupo7;

import TDAs.LinkedList;
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
    public static boolean creacionPersonaEnArchivoNuevo = false;
    @FXML
    private Button buttonActualizar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        buttonActualizar.setVisible(false);
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
            
            if(creacionPersonaEnArchivoNuevo){
                creacionPersonaEnArchivoNuevo = false;
                App.setRoot("personasEnSistema");
            }else{
                creacionPersonaEnArchivoNuevo = false;
                App.setRoot("crearFoto2");
                
            }
            
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
        
        if(creacionPersonaEnArchivoNuevo){
            creacionPersonaEnArchivoNuevo = false;
            App.setRoot("personasEnSistema");
        }else{
            creacionPersonaEnArchivoNuevo = false;
            App.setRoot("crearFoto2");
        }
        
      
    }

    @FXML
    private void actualizarPersona(ActionEvent event) {
//        LinkedList<Persona> listaPersonas = Persona.
    }
    
    public void llenarCampos(Persona p){
        //Se vuelve el boton para crear persona invisible
        buttonFinalizarCreacionPersona.setVisible(false);
        
        //Se vuelve visible el boton de actualizacion
        buttonActualizar.setVisible(true);
        
        //Se llenan los campos con respecto a la persona
        txtNombre.setText(p.getNombre());
        txtApellido.setText(p.getApellido());
    }

}
