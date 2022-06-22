/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoedgrupo7;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import modelo.Album;

public class CrearAlbumController implements Initializable {
    String direccionImagen;

    @FXML
    private TextField txtNombreAlbum;
    @FXML
    private TextArea txtDescripcionAlbum;
    @FXML
    private ImageView imgviewPortada;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void crearAlbum(ActionEvent event) throws IOException{
        creacionDeAlbum();
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        //Volvemos a la ventana de inicio
        App.setRoot("menuPrincipal");
    }

    @FXML
    private void buscarImagenPortada(ActionEvent event) {
        /*
        Metodo que se ejecuta al presionar el boton browse, este
        permite buscar una imagen para colocar como foto de portada
        en el album creado. Si no se selecciona ninguna imagen, se coloca
        una imagen por defecto
        */
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
                imgviewPortada.setImage(image);
            
            
            //Se copia la imagen
            //Aqui se deberia setear esa imagen como portada del album
            Path from = Paths.get(imgFile.toURI());
            Path to = Paths.get("archivos/imagenesPortadaAlbum/"+imgFile.getName());
            
            System.out.println(imgFile.getName());
            Files.copy(from, to);
 
        }
        
        direccionImagen = imgFile.getName();
    
       }catch(FileAlreadyExistsException e){
            System.out.println("El archivo ya existe en la carpetaaaaaaaaaa");
            System.out.println(e.getStackTrace());
            Alert alerta = new Alert(Alert.AlertType.ERROR,"La imagen que se ha seleccionado ya se encuentra en uso, por favor seleccione otra"); //FIXME
            alerta.setTitle("Error");
            alerta.setHeaderText("Ha ocurrido un error:");
            System.out.println(e.getClass());
            alerta.show();  
       } catch (IOException ex) {
           
           ex.printStackTrace();
        }catch(Exception e){
            System.out.println("Error inesperado");
        }
    }
    
    
        //Metodo para cargar la imagen por defecto 
    private void cargarFotoPorDefecto(){
        InputStream  input = null;
        try {           
            input =  App.class.getResource("archivos/imagenesPortadaAlbum/defaultPic.png").openStream();
            Image image = new Image(input, 100, 100, false, false);
            imgviewPortada.setImage(image);
        } catch (IOException ex) {
            System.out.println("No se pudo cargar foto por defecto");
        } finally {
            if (input!=null){
            try {
                input.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el recurso");
            }
            }
        }
    }
    
    
    
    
    
    private void creacionDeAlbum() throws IOException{
        /*
        ##########################################
        LOGICA PARA LA CREACION DE UN NUEVO ALBUM
        ##########################################
        */
        
        //Se recupera la informacion ingresada:
        try{
            String nombreAlbum = txtNombreAlbum.getText();
            String descripcionAlbum = txtDescripcionAlbum.getText();

            if(nombreAlbum.equals("")||descripcionAlbum.equals("")){
                throw new NullPointerException();
            }
            
            if(!(direccionImagen==null)){
                Album album = new Album(nombreAlbum, descripcionAlbum);
                album.setFotoPortada(direccionImagen);
                Alert exito = new Alert(Alert.AlertType.INFORMATION, "Su álbum ha sido creada de manera exitosa");
                exito.setTitle("Exito");
                exito.setHeaderText("Operacion exitosa");
                exito.show();
                App.setRoot("menuPrincipal");  
            }else{
                System.out.println("Se pide confirmacion indicando que se colocara la foto por defecto");
                Alert confirmacionFotoDefault = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacionFotoDefault.setTitle("Confirmación");
                confirmacionFotoDefault.setHeaderText("Se procederá a agregar una foto por defecto para la portada de su álbum");
                confirmacionFotoDefault.setContentText("Está usted de acuerdo?");
                
                Optional<ButtonType> result = confirmacionFotoDefault.showAndWait();
                if(result.get()==ButtonType.OK){
                    //Se crea el objeto con la imagen default
                    Album album = new Album(nombreAlbum, descripcionAlbum);
                    album.setFotoPortada("defaultPic.png");          
                    //Aqui se debe escribir al album en el archivo serializado
                    App.setRoot("menuPrincipal");                    
                }
            }
            
            }catch(NullPointerException e){
                System.out.println("No se llenaron todos los campos: ");
                Alert alerta = new Alert(Alert.AlertType.ERROR,"Asegúrese de llenar todos los campos"); //FIXME
                alerta.setTitle("Error");
                alerta.setHeaderText("Ha ocurrido un error:");
                alerta.show();  
            }
    }

}
