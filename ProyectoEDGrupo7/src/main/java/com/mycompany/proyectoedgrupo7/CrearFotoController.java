/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoedgrupo7;

import TDAs.LinkedList;
import com.mycompany.proyectoedgrupo7.App;
import com.mycompany.proyectoedgrupo7.App;
import exceptions.EmptyFieldsException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import modelo.Album;
import modelo.Foto;
/**
 * FXML Controller class
 *
 * @author Det-Pc
 */
public class CrearFotoController implements Initializable {


    @FXML
    private Button buttonCrearFoto;
    @FXML
    private TextArea txtDescripcionAlbum;
    @FXML
    private TextField txtLugarFoto;
    @FXML
    private DatePicker fechaFoto;
    @FXML
    private RadioButton rbAparecenPersonas;
    @FXML
    private RadioButton rbNoAparecenPersonas;
    @FXML
    private ImageView imgviewFotoSeleccionada;
    @FXML
    private Button buttonSeleccionarPersonas;
    @FXML
    private ComboBox<Album> cbAlbumes;
    private String direccionImagen;
    
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        buttonSeleccionarPersonas.setVisible(false);
        //Se llena el comboBox
        llenarCb();
    }    
    
    @FXML
    private void crearFoto(ActionEvent event) throws IOException {
        try{
            
            //Se recupera la descripcion
            String descripcion = txtDescripcionAlbum.getText();
            String lugar = txtLugarFoto.getText();
            LocalDate fecha = fechaFoto.getValue();
            
            
            
            Album album = (Album)cbAlbumes.getValue();
            System.out.println(fecha);
            Foto foto = null;

            if(album==null){
                throw new NullPointerException();
            }

            if(direccionImagen==null){
                throw new EmptyFieldsException();
            }
            if(rbNoAparecenPersonas.isSelected()){
                //Se crea el objeto sin un ArrayList de personas
                if(lugar==null||descripcion==null){
                    throw new EmptyFieldsException();
                }
                
                foto = new Foto(fecha, lugar, descripcion);
                
            }else if(rbAparecenPersonas.isSelected()){
                //Se crea el objeto con el ArrayList de personas seleccionadas
                //foto = new Foto(LISTApersonas, fecha, lugar, descripcion) FIXMEEEEEE
            }
            
            System.out.println(direccionImagen);
            //Se settea la direccion de la foto
            System.out.println(foto);
            foto.setImagen(direccionImagen);
            
            
            
            //Se actualiza el archivo de albumes para que ahora contenga la nueva foto
            System.out.println("AGREGANDO EL ALBUM ACTUALIZADO A LA LISTA DE ALBUMES CONTENIDOS EN EL ARCHIVO");
            LinkedList<Album> l = Album.leerArchivoAlbumes(App.pathAlbumes);
            for(Album a:l){
                if(a.equals(album)){
                    //Son el mismo album, Se agrega la foto
                    a.agregarFoto(foto);
                }
            }
            //Se escribe la lista actualizada en el archivo
            Album.actualizarListaAlbumes(l, App.pathAlbumes);
            
            
            Alert exito = new Alert(Alert.AlertType.INFORMATION, "La foto ha sido creada de manera exitosa");
            exito.setTitle("Exito");
            exito.setHeaderText("Operacion exitosa");
            exito.show();
            App.setRoot("menuPrincipal");
  

            
            
            
        }catch(EmptyFieldsException e){
            System.out.println(e.getStackTrace());
            e.printStackTrace();
            Alert alerta = new Alert(Alert.AlertType.ERROR,"Asegúrese de llenar todos los campos"); //FIXME
            alerta.setTitle("Error");
            alerta.setHeaderText("Ha ocurrido un error:");
            System.out.println(e.getClass());
            alerta.show();  
        }
        catch(NullPointerException e){
            System.out.println(e.getStackTrace());
            e.printStackTrace();
            Alert alerta = new Alert(Alert.AlertType.ERROR,"Asegúrese de llenar o seleccionar todos los campos"); //FIXME
            alerta.setTitle("Error");
            alerta.setHeaderText("Ha ocurrido un error:");
            System.out.println(e.getClass());
            alerta.show();  
        }catch(Exception e){
            System.out.println(e.getStackTrace());
            e.printStackTrace();
            Alert alerta = new Alert(Alert.AlertType.ERROR,"Asegurese de seleccionar una foto"); //FIXME
            alerta.setTitle("Error");
            alerta.setHeaderText("Ha ocurrido un error:");
            System.out.println(e.getClass());
            alerta.show();  
        }
        
        
        
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("menuPrincipal");
    }
    
    

    @FXML
    private void browseFoto(ActionEvent event) throws IOException {
        //Se crea el objeto de tipo FileChooser
        try{
        FileChooser fileChooser = new FileChooser(); //Este nos permite abrir el explorador de archivo
        fileChooser.setTitle("Buscar foto");
        
        //Se filtran los archivos que se van a mostrar
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")       
        );
        
        //Se obtiene la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(null);
        
        //Se muestra la imagen
        if(imgFile!=null){
            Image image = new Image("file:"+imgFile.getAbsolutePath());
            System.out.println(imgFile.getAbsolutePath());
            imgviewFotoSeleccionada.setImage(image);
            
            
            //Se copia la imagen
            Path from = Paths.get(imgFile.toURI());
            Path to = Paths.get("archivos/fotos/"+imgFile.getName());
            System.out.println(imgFile.getName());
            Files.copy(from, to);
            
        }
        
        direccionImagen = imgFile.getName();
    
       }catch(FileAlreadyExistsException e){
            System.out.println("LA FOTO YA EXISTE EN LA CARPETA");
            System.out.println(e.getStackTrace());
            Alert alerta = new Alert(Alert.AlertType.ERROR,"La imagen que se ha seleccionado ya se encuentra \nen uso, por favor seleccione otra"); //FIXME
            alerta.setTitle("Error");
            alerta.setHeaderText("Ha ocurrido un error:");
            System.out.println(e.getClass());
            alerta.show();  
       }
    }

    @FXML
    private void switchToSeleccionarPersonas(ActionEvent event) {
        /*
        AQUI LA IDEA ES PASAR A UN LV DE PERSONAS DONDE EL USUARIO
        PUEDA SELECCIONAR A TODAS LAS PERSONAS QUE SE DESEA AGREGAR A LA FOTO
        */
    }
    
    private void llenarCb(){
        for(Album a:Album.leerArchivoAlbumes(App.pathAlbumes)){
        cbAlbumes.getItems().add(a);
        }  
    }

    @FXML
    private void unlockButton() {
        /*
        Metodo para bloquear el boton de agregar personas hasta que se seleccione el 
        radio button con el texto "Si"
        */
        if(rbAparecenPersonas.isSelected()){
            buttonSeleccionarPersonas.setVisible(true);
        }else {
            buttonSeleccionarPersonas.setVisible(false);
        }
    }


}
