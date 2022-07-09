/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoedgrupo7;

import TDAs.LinkedList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ListView;
import modelo.Foto;
import modelo.Persona;
/**
 * FXML Controller class
 *
 * @author Det-Pc
 */
public class SeleccionarPersonasController implements Initializable {


    @FXML
    private ListView<Persona> lvPersonasEnFoto;
    @FXML
    private ListView<Persona> lvPersonasEnSistema;
    private LinkedList<Persona> listaPersonas = Persona.leerArchivoPersonas(App.pathPersonas);
    private LinkedList<Persona> listaPersonasCopia = Persona.leerArchivoPersonas(App.pathPersonas);
    ObservableList<Persona> l = FXCollections.observableArrayList();
    public static Foto fotoOG;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(Persona p: listaPersonas){
            System.out.println(p);
            //Se los agg al ObservableList
            l.add(p);
        }
        lvPersonasEnSistema.getItems().setAll(l);
    }    
    
    @FXML
    private void switchToCrearFoto(ActionEvent event) throws IOException {
        App.setRoot("crearFoto");
    }

    @FXML
    private void switchToRegistrarNuevaPersona(ActionEvent event) {
        
    }

    @FXML
    private void agregarPersonaAFoto(ActionEvent event) {
        System.out.println("Se agg una nueva persona a la foto");
        Persona p = (Persona) lvPersonasEnSistema.getSelectionModel().getSelectedItem();
        System.out.println("Agregando a la lista de personas en foto a la persona: "+p);
        fotoOG.agregarPersonaEnFoto(p);
        
        //Se recupera la persona 
    }

}
