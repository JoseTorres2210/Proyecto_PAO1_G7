/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoedgrupo7;

import TDAs.CircularDoublyLinkedList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import modelo.Foto;

/**
 * FXML Controller class
 *
 * @author Det-Pc
 */
public class MostrarResultadosFiltroController implements Initializable {
    public static CircularDoublyLinkedList<Foto> listaFotosFiltradas;
    @FXML
    private FlowPane paneFotosFiltradas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(listaFotosFiltradas);
        paneFotosFiltradas.getChildren().clear();
        /*
        #########################################
        Se recorre la lista de fotos filtradas y se crea un vbox por cada una
        de ellas, posteriormente se agrega al vbox la foto, un lbl con el nombre de la foto
        y el album al que pertenece
        #########################################
        */
        if(!listaFotosFiltradas.isEmpty()){
            System.out.println(">>>"+listaFotosFiltradas);
            for(int i = 0;i<listaFotosFiltradas.size();i++){
                System.out.println(listaFotosFiltradas.get(i));
                Foto foto = listaFotosFiltradas.get(i);
                //Por cada elemento creamos un vBox
                VBox cuadro = new VBox();
//                cuadro.setMaxHeight(40);
                cuadro.setMaxSize(40,40);
                Label tituloFoto =new Label(foto.getDescripcion());
                Label lugar = new Label("Lugar: "+foto.getLugar());
                Label album = new Label("Album: "+foto.getNomAlbum());
                
                //Se crea un ImageView por cada elemento en la lista
                ImageView imgview = new ImageView();
                
                imgview.maxHeight(20);
                imgview.minHeight(20);
                try{            
                    //String filename = "archivos/fotos/"+fotos.get(numFoto).getImagen(); 
                    String filename = "archivos/fotos/"+foto.getImagen();
                    Image image = new Image(new FileInputStream(filename),250,250,false,false);
                    
                    imgview.setImage(image);
                    imgview.resize(20, 20);
                    imgview.maxHeight(20);
                    imgview.minHeight(20);
                }catch (FileNotFoundException ex) { 
                    ex.printStackTrace();
                }catch(IllegalArgumentException e){
                    e.printStackTrace();
                }
                
                //Agregamos cada componente al vbox
                cuadro.getChildren().addAll(imgview,tituloFoto,lugar,album);
                
                //Agregamos cada vbox al pane
                paneFotosFiltradas.getChildren().add(cuadro);
                
                
                
            }
        }else{
            System.out.println("LA LISTA ESTA VACIA, NO HAY RESULTADOS DEL FILTRADO");
        }
        
        
        
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
