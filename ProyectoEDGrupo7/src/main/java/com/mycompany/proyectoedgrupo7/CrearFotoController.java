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
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import modelo.Album;
import modelo.Foto;
import modelo.Persona;
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
    private RadioButton rbAparecenPersonas;
    private RadioButton rbNoAparecenPersonas;
    @FXML
    private ImageView imgviewFotoSeleccionada;
    private Button buttonSeleccionarPersonas;
    @FXML
    private ComboBox<Album> cbAlbumes;
    private String direccionImagen;
    @FXML
    private BorderPane bpFoto;
    @FXML
    private GridPane gridPaneOpciones;
  

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
            
            //Se crea el objeto sin un ArrayList de personas inicialmente
            if(lugar==null||descripcion==null){
                throw new EmptyFieldsException();
            }

            foto = new Foto(fecha, lugar, descripcion);
            foto.setAlbum(cbAlbumes.getSelectionModel().getSelectedItem());     
            System.out.println(direccionImagen);
            //Se settea la direccion de la foto
            System.out.println(foto);
            foto.setImagen(direccionImagen);
            
            CrearFoto2Controller.fotoOG = foto;
            App.setRoot("crearFoto2");
  

            
            
            
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


        
    
    
    private void llenarCb(){
        for(Album a:Album.leerArchivoAlbumes(App.pathAlbumes)){
        cbAlbumes.getItems().add(a);
        }  
    }


    
    
  


}
