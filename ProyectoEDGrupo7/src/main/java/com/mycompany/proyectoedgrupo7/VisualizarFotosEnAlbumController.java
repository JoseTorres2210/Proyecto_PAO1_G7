
package com.mycompany.proyectoedgrupo7;

import TDAs.CircularDoublyLinkedList;
import TDAs.CircularNode;
import TDAs.LinkedList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    @FXML
    private Label lblLugar;
    @FXML
    private Label lblFecha;
    
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
        lblLugar.setText(fotoActual.getLugar());
        lblFecha.setText(fotoActual.getFecha().toString());
        CircularNode<Foto> nodo = albumOG.getFotos().getCurrentNode();
        //fotoActual = nodo.getContent();
        System.out.println("----->"+fotoActual);
        if(fotoActual.getPersonas().isEmpty()){
            buttonEditarPersonasFoto.setDisable(true);
        }else{
            buttonEditarPersonasFoto.setDisable(false);
        }
    }    
    
    @FXML
    private void mostrarFotoAnterior(ActionEvent event) {
        nodo = nodo.getPrevious();
        fotoActual = nodo.getContent();
        llenarImageView(fotoActual);
        lblLugar.setText(fotoActual.getLugar());
        lblFecha.setText(fotoActual.getFecha().toString());
        System.out.println(fotoActual);
        if(fotoActual.getPersonas().isEmpty()){
            buttonEditarPersonasFoto.setDisable(true);
        }else{
            buttonEditarPersonasFoto.setDisable(false);
        }
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
        CrearFoto2Controller.fotoOG2 = fotoActual;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("crearFoto2.fxml"));//no tiene el controlador especificado
            //CrearCiudadController ct = new CrearCiudadController(); //Recien aqui se esta creando el controlador
            Parent root = (Parent) fxmlLoader.load();
            CrearFoto2Controller ct = fxmlLoader.getController();
            fxmlLoader.setController(ct);//se asigna el controlador

            ct.llenarCampos(fotoActual); 
            App.changeRoot(root);
            
        }catch (IOException ex) {
            System.out.println("Error fatal: "+ex);
            
            
        }catch(Exception e){
            System.out.println("Excepcion general: "+e);
        } 
        
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
        lblLugar.setText(fotoActual.getLugar());
        lblFecha.setText(fotoActual.getFecha().toString());
        if(fotoActual.getPersonas().isEmpty()){
            buttonEditarPersonasFoto.setDisable(true);
        }else{
            buttonEditarPersonasFoto.setDisable(false);
        }
    }
    
    
    
    //Metodo para llenar el imagview
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
        VisualizarAlbumesController.foto = foto;
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
            System.out.println(fotos);
            //Se elimina la foto de la carpeta
            //eliminarFotoDeCarpeta(foto);
            
            if(fotos.isEmpty()){
                //Se avisa que el album esta vacio y se lo manda a la ventana de atras
                Alert alerta2 = new Alert(Alert.AlertType.INFORMATION,"El album quedó vacío. \nRegresando al menu de visualizacion.");
        
                alerta2.setHeaderText("Album vacío");
                App.setRoot("visualizarAlbumes");
                alerta2.show();

            }else{
                
                nodo = nodo.getPrevious();
                fotoActual = nodo.getContent();
                llenarImageView(fotoActual);

            }
            eliminarFotoDeCarpeta(foto);

        }else{
            System.out.println("No problem");
        }
        }catch(Exception e){
            System.out.println("Error al eliminar el album: "+e.getLocalizedMessage());
            e.printStackTrace();
            e.getCause();
           
        }
    }
    
    
    
    
    public  void eliminarFotoDeCarpeta(Foto foto) throws IOException{
        /*
        ###############################
        Metodo para remover la foto de la carpeta de fotos
        ###############################
        */
        File tmp = new File("archivos/fotos/"+foto.getImagen());

        System.out.println(tmp.toPath());
        //tmp.deleteOnExit();
        //Files.deleteIfExists(tmp.toPath());
        try {

            
            System.out.println(tmp.delete());
            //Files.move(tmp.toPath(), tmp2.toPath());
            
        
            
        } catch (Exception ex) {
            System.out.println("NO SE PUDO BORRAR LA FOTO");
            ex.printStackTrace();
        }
        Files.delete(tmp.toPath());
}

}
