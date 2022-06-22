/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoedgrupo7;

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

public class MenuPrincipalController implements Initializable {


    @FXML
    private ImageView imgviewFotoMenuPrincipal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            String filename = "archivos/imagenesMainMenu/dogo.jpg";
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
    private void switchToAgregarFoto(ActionEvent event) {
    }

    @FXML
    private void switchToAlbumes(ActionEvent event) {
    }

    @FXML
    private void switchToCrearNuevoAlbum(ActionEvent event) {
    }

}
