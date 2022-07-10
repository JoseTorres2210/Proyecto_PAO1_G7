/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoedgrupo7;

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
import modelo.Persona;

public class MenuPrincipalController implements Initializable {


    @FXML
    private ImageView imgviewFotoMenuPrincipal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(Album.leerArchivoAlbumes(App.pathAlbumes));
        LinkedList<Integer> l1 = new LinkedList<>();
        for(int i =0;i<=10;i++){
            l1.addLast(i);
        }
        System.out.println("LISTA---->: "+l1);
        l1.remove(l1.size()-1);
        System.out.println("REMOVIENDO EL ULTIMO ELEMENTO---->: "+l1);
        System.out.println("AGREGANDO DESPUES DE REMOVER");
        l1.add(l1.size(), 15555);
        System.out.println(l1);
        
        
        try{

            //Tener una CircularLinkedList para simular la repeticion de las imagenes
            
            String filename = "archivos/imagenesMainMenu/dogo.jpg"; //<---- PROVISIONAL, se debe cambiar por hilos
            Image image = new Image(new FileInputStream(filename));
            imgviewFotoMenuPrincipal.setImage(image);
        }catch (FileNotFoundException ex) {
            /*
            try{
                System.out.println("no se encuentra archivo de imagen: ");
                //Hacemos un split por el punto para transformar de jpg a png o visceversa
                
                String[] datos = m.getFoto().split(".");
                System.out.println(m.getFoto());
                if(datos[1].equals("jpg")){
                    System.out.println("SE CAMBIA A .JPG");
                    String fileName = "archivos/ImagenesMascotas"+datos[0]+".jpg";
                    System.out.println(fileName);
                    Image image = new Image(new FileInputStream(fileName));
                    ImagenMascota.setImage(image);
                }else if(datos[1].equals("png")){
                    System.out.println("SE CAMBIA A .PNG");
                    String fileName = "archivos/ImagenesMascotas"+datos[0]+".png";
                    System.out.println(fileName);
                    Image image = new Image(new FileInputStream(fileName));
                    ImagenMascota.setImage(image);
                }
            }catch(IOException e){
                System.out.println("ERROR: ");
            }
            */
            
            
            
            ex.printStackTrace();
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

}
