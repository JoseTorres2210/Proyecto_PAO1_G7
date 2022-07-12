/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoedgrupo7;

import TDAs.CircularDoublyLinkedList;
import TDAs.CircularNode;
import TDAs.LinkedList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import modelo.Album;
import modelo.Foto;
import modelo.Persona;

public class MenuPrincipalController implements Initializable {


    @FXML
    private ImageView imgviewFotoMenuPrincipal;
    private CircularDoublyLinkedList<String> carreteMuestra = Foto.generarCarreteMuestra();
    private  CircularNode<String> nodo = carreteMuestra.first;
    private String fotoActual = nodo.getContent();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        try{
            ImageChanger imc = new ImageChanger();
            imc.setName("pics");
            imc.setDaemon(true);
            imc.start(); 
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }    
    
    @FXML
    private void switchToAgregarFoto(ActionEvent event) throws IOException{
        App.setRoot("crearFoto");
    }

    @FXML
    private void switchToAlbumes(ActionEvent event) throws IOException {
        App.setRoot("visualizarAlbumes");
    }

    @FXML
    private void switchToCrearNuevoAlbum(ActionEvent event) throws IOException {
        App.setRoot("crearAlbum");
    }

    @FXML
    private void switchToPersonasEnSistema(ActionEvent event) throws IOException{
        App.setRoot("personasEnSistema");
    }
    
    
    
    //Clase interna para mostrar imagenes con hilos
    private class ImageChanger extends Thread{    
        @Override
        public void run(){
            //Iteramos la lista circular
            while(true){
                //Se hara una iteracion indefinida
                try{            
                    String filename = "archivos/imagenesMainMenu/"+nodo.getContent();
                    Image image = new Image(new FileInputStream(filename));
                    imgviewFotoMenuPrincipal.setImage(image);
                    try{
                        Thread.sleep(4000);
                    }catch(InterruptedException e){
                        System.out.println(e);
                    }                      
                }catch (FileNotFoundException ex) { 
                    ex.printStackTrace();
                }catch(IllegalArgumentException e){
                    e.printStackTrace();
                }
                
                
                
                
                nodo = nodo.getNext();
            }
            
        }
        
    }

}
