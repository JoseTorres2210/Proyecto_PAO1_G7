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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.Album;
import modelo.Foto;
/**
 * FXML Controller class
 *
 * @author Det-Pc
 */
public class VisualizarFotosEnAlbumController implements Initializable {


    @FXML
    private Label lblNombreAlbumFotos;
    @FXML
    private Button buttonEditarPersonasFoto;
    @FXML
    private ImageView imgvFotos;
    
    public static Album albumOG;
    
    int numFoto = 0;
    private CircularDoublyLinkedList<Foto> fotos = albumOG.getFotos();
    private  CircularNode<Foto> nodo = fotos.first;
    private Foto fotoActual = nodo.getContent();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(albumOG!=null)
            lblNombreAlbumFotos.setText(albumOG.getNombre());
        llenarImageView(fotoActual);
        CircularNode<Foto> nodo = albumOG.getFotos().getCurrentNode();
        //fotoActual = nodo.getContent();
        System.out.println("----->"+fotoActual);
    }    
    
    @FXML
    private void mostrarFotoAnterior(ActionEvent event) {
        nodo = nodo.getPrevious();
        fotoActual = nodo.getContent();
        llenarImageView(fotoActual);
        System.out.println(fotoActual);
        
    }

    @FXML
    private void switchToVisualizarAlbumes(ActionEvent event) throws IOException {
        //Volvemos a la ventana anterior
        App.setRoot("visualizarAlbumes");
    }

    @FXML
    private void eliminarFotoActual(ActionEvent event) {
        eliminarFoto(fotoActual);
    }

    @FXML
    private void editarPersonasEnFoto(ActionEvent event) {
        System.out.println("Se cambia a una ventana de edicion de personas en la foto");
    }

    @FXML
    private void mostrarFotoSiguiente(ActionEvent event) {
        System.out.println("Se muestra la foto siguiente");
        System.out.println("******" +fotos);
        nodo = nodo.getNext();

        fotoActual = nodo.getContent();
        System.out.println(fotoActual);
        System.out.println("******" +fotos);
        llenarImageView(fotoActual);
    }
    
    
    
    //Metodo para llenar el imagview
    
    /*
    ##################
    Enviarle un iterador al metodo imageView???
    ##################
    */
    private void llenarImageView(Foto foto){
        

        
        try{            
            //String filename = "archivos/fotos/"+fotos.get(numFoto).getImagen(); 
            String filename = "archivos/fotos/"+foto.getImagen();
            Image image = new Image(new FileInputStream(filename));
            imgvFotos.setImage(image);
        }catch (FileNotFoundException ex) { 
            ex.printStackTrace();
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }
        
        
        
    }
    
    private void eliminarFoto(Foto foto){
        System.out.println("Se pide confirmacion para eliminar la foto actual");
        try{
        Alert alerta = new Alert(Alert.AlertType.WARNING,"Recuerde que esta acción es irreversible. \n¿Seguro que desea continuar?");
        
        alerta.setHeaderText("Eliminacion de Foto");
        alerta.showAndWait();
        
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setHeaderText("Eliminacion");
        confirmacion.setContentText("Se procederá a eliminar esta foto de su album.\n ¿Continuar?");

        
        

        Optional<ButtonType> result = confirmacion.showAndWait();
        if(result.get()==ButtonType.OK){
            //Se elimina el objeto de la lista
            albumOG.eliminarFoto(foto);

            //Se actualiza el archivo
            Album.actualizarAlbum(albumOG, App.pathAlbumes);

        }else{
            System.out.println("No problem");
        }
        }catch(Exception e){
            System.out.println("Error al eliminar el album: "+e.getLocalizedMessage());
            e.printStackTrace();
            e.getCause();
           
        }
    }

}
