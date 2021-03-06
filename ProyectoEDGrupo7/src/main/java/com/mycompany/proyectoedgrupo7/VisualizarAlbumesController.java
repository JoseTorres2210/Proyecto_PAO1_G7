/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoedgrupo7;


import TDAs.LinkedList;
import com.mycompany.proyectoedgrupo7.App;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import modelo.Album;
import modelo.Foto;

public class VisualizarAlbumesController implements Initializable {


    @FXML
    private TableView tvAlbumes;
    @FXML
    private TableColumn<Album, String> tcAlbumes;
    @FXML
    private TableColumn<Album, Void> tcOpciones;
    @FXML
    private TableColumn<Album, String> tcDescripcion;
    public static Foto foto;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        Aqui se debe inicializar el table view
        */
        tcAlbumes.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        ObservableList<Album> l = FXCollections.observableArrayList();
        agregarOpciones();
        for(Album a: Album.leerArchivoAlbumes(App.pathAlbumes)){
            System.out.println(a);
            //Se los agg al ObservableList
            l.add(a);
        }
        System.out.println(Album.leerArchivoAlbumes(App.pathAlbumes));
        tvAlbumes.getItems().addAll(l);
        
        
    }    
    
    @FXML
    private void switchToFiltrarFotos(ActionEvent event) throws IOException{
        /*
        Se pasa a una ventana de filtro para las fotos
        */
        App.setRoot("FiltrarFotos");
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("menuPrincipal");
    }
    
    
    
    private void agregarOpciones(){
        Callback<TableColumn<Album, Void>, TableCell<Album, Void>> cellFactory = new Callback<TableColumn<Album, Void>, TableCell<Album, Void>>() {
            @Override
            public TableCell<Album, Void> call(final TableColumn<Album, Void> param) {
                TableCell<Album, Void> cell = new TableCell<Album, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el empleado de la fila
                            Album album = getTableView().getItems().get(getIndex());
                  
                            //boton para ver fotos
                            Button btnEd = new Button("Ver album");
                            if(album.getFotos().isEmpty()){
                                //Evitamos que el usuario pueda ver las fotos del album
                                btnEd.setDisable(true);
                            }
                            btnEd.setOnAction(e ->{
                                try {
                                    visualizarFotosAlbum(album);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            });
                               
                            //boton eliminar
                            Button btnEl = new Button("Eliminar");
                            btnEl.setOnAction(e -> eliminarAlbum(album));
                            //se agregan botones al hbox
                            hbOpciones.getChildren().addAll(btnEd,btnEl);
                            //se ubica hbox en la celda
                            setGraphic(hbOpciones);
                        }
                    }
                };
                return cell;
            }
        };

        tcOpciones.setCellFactory(cellFactory);
    }
    
    
    
    
    //Metodo para eliminar un album
    private void eliminarAlbum(Album a) {
        try{
        Alert alerta = new Alert(Alert.AlertType.WARNING,"Recuerde que esta acci??n es irreversible. \n??Seguro que desea continuar?");
        
        alerta.setHeaderText("Eliminacion de album");
        alerta.showAndWait();
        
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setHeaderText("Eliminacion");
        confirmacion.setContentText("Se proceder?? a eliminar su album de los registros.\n ??Continuar?");

        
        //confirmacion.show();

        Optional<ButtonType> result = confirmacion.showAndWait();
        if(result.get()==ButtonType.OK){
            //Se elimina el objeto de la lista
            LinkedList<Album> lista = Album.leerArchivoAlbumes(App.pathAlbumes);
            System.out.println(lista.contains(a));
            int indice = lista.indexOf(a);
            System.out.println("Lista antes de la eliminacion: "+lista.size());
            System.out.println("Indice a eliminar----->>>>: "+indice);
            System.out.println("Elemento a eliminar: "+lista.get(indice));
            lista.remove(indice); 
            
            System.out.println("Lista despues de la eliminacion: "+lista.size());
            
            //Se actualiza el archivo
            Album.actualizarListaAlbumes(lista, App.pathAlbumes);
            System.out.println("Se ha eliminado el album: "+a);
            App.setRoot("visualizarAlbumes");


        }else{
            System.out.println("No problem");
        }
        }catch(Exception e){
            System.out.println("Error al eliminar el album: "+e.getLocalizedMessage());
            e.printStackTrace();
            e.getCause();
           
        }
        
        
    }
    
    private void visualizarFotosAlbum(Album a) throws IOException{
        VisualizarFotosEnAlbumController.albumOG = a;
        System.out.println("SE MUESTRAN LAS FOTOS DEL ALBUM");
        App.setRoot("visualizarFotosEnAlbum");
        
    }

}
