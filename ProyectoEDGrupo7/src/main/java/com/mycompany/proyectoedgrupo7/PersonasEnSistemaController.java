package com.mycompany.proyectoedgrupo7;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import TDAs.CircularDoublyLinkedList;
import TDAs.LinkedList;
import com.mycompany.proyectoedgrupo7.App;
import static com.mycompany.proyectoedgrupo7.CrearFoto2Controller.fotoOG;
import static com.mycompany.proyectoedgrupo7.VisualizarFotosEnAlbumController.albumOG;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author Det-Pc
 */
public class PersonasEnSistemaController implements Initializable {

    @FXML
    private Button buttonAgregarPersona;
    @FXML
    private TableView<Persona> tvPersonasRegistradas;
    @FXML
    private TableColumn<Persona, String> tcNombre;
    @FXML
    private TableColumn<Persona, Void> tcOpciones;
    
    public static Persona personaNueva;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
       
        tvPersonasRegistradas.getItems().setAll(Persona.leerArchivoPersonas(App.pathPersonas));

        agregarOpciones();

    }    

    @FXML
    private void switchToCrearPersona(ActionEvent event) throws IOException {
        CrearPersonaController.creacionPersonaEnArchivoNuevo = true;
        App.setRoot("crearPersona");
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("menuPrincipal");
    }
    
    
    private void agregarOpciones(){
        Callback<TableColumn<Persona, Void>, TableCell<Persona, Void>> cellFactory = new Callback<TableColumn<Persona, Void>, TableCell<Persona, Void>>() {
            @Override
            public TableCell<Persona, Void> call(final TableColumn<Persona, Void> param) {
                TableCell<Persona, Void> cell = new TableCell<Persona, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el empleado de la fila
                            //Album album = getTableView().getItems().get(getIndex());
                            Persona persona = getTableView().getItems().get(getIndex());
                  
                            //boton para ver fotos
                            Button btnEliminar = new Button("Eliminar de los registros");
                            Button btnEditar = new Button("Editar");

                            
                            btnEliminar.setOnAction(e ->{
                                System.out.println("Se elimina a la persona "+ persona +" de los registros");
                                eliminarPersona(persona);
                            });
                            
                            btnEditar.setOnAction(e -> {
                                //Pasamos a la ventana de creacion de persona con los campos cargados
                                System.out.println("SE EDITA A ESA PERSONA");
                                editarPersona(persona);
 //                               editar(persona,personaNueva,Album.leerArchivoAlbumes(App.pathAlbumes));
                            });
           
                            
                            //se agregan botones al hbox
                            hbOpciones.getChildren().addAll(btnEditar,btnEliminar);
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
    
    
    
    //Metodo para editar a las personas en el sistema
    private void editarPersona(Persona p){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("crearPersona.fxml"));//no tiene el controlador especificado
            Parent root = (Parent) fxmlLoader.load();
            CrearPersonaController ct = fxmlLoader.getController();
            fxmlLoader.setController(ct);//se asigna el controlador
            ct.llenarCampos(p); 
            App.changeRoot(root);
            tvPersonasRegistradas.refresh();
            
        }catch (IOException ex) {
            System.out.println("Error fatal: "+ex);
            
            
        }catch(Exception e){
            System.out.println("Excepcion general: "+e);
        }   
    }
    
    
    private void eliminarPersona(Persona p){
        /*
        #################################################
        Logica para la eliminacion de la persona de los registros
        #################################################
        */
        try{
        Alert alerta = new Alert(Alert.AlertType.WARNING,"Recuerde que esta acción es irreversible. \n¿Seguro que desea continuar?");
        
        alerta.setHeaderText("Eliminacion de persona");
        alerta.showAndWait();
        
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setHeaderText("Eliminacion");
        confirmacion.setContentText("Se procederá a eliminar la persona de los registros.\n ¿Continuar?");
        Optional<ButtonType> result = confirmacion.showAndWait();
        if(result.get()==ButtonType.OK){
            //Se elimina el objeto del conjunto
            Set<Persona> personas = Persona.leerArchivoPersonas(App.pathPersonas);
            personas.remove(p); //Se elimina la ocurrencia del conjunto
            eliminar(p,Album.leerArchivoAlbumes(App.pathAlbumes));
            
            //Se actualiza el archivo
            Persona.actualizarListaPersonas(personas, App.pathPersonas);
            
            App.setRoot("personasEnSistema");

        }else{
            System.out.println("No problem");
        }
        }catch(Exception e){
            System.out.println("Error al eliminar la persona: "+e.getLocalizedMessage());
            e.printStackTrace();
            e.getCause();
           
        }
    }
    
//        public void editar(Persona vieja,Persona nueva, LinkedList<Album> albumes){
//               Comparator<Persona> cmp1 = (Persona p1,Persona p2) -> p1.getNombre().toLowerCase()
//                      .compareTo(p2.getNombre().toLowerCase());
//               Comparator<Persona> cmp2 = (Persona p1,Persona p2) -> p1.getApellido().toLowerCase()
//                       .compareTo(p2.getApellido().toLowerCase());
//                   for(Album album:albumes ){
//                       CircularDoublyLinkedList<Foto> fotos=album.getFotos();
//                           for(int i=0;i<fotos.size();i++){
//                               Set<Persona> personas=fotos.get(i).getPersonas();
//                               for(Iterator<Persona> j = personas.iterator(); j.hasNext();){
//                                   Persona persona=j.next();
//                                   if(cmp1.compare(vieja, persona)==0 && cmp2.compare(vieja, persona)==0){
//                                       vieja.setNombre(nueva.getNombre());
//                                       vieja.setApellido(nueva.getApellido());
//                                       vieja.setNombreCompleto(nueva.getNombreCompleto());
//                                   }
//                                   persona.actualizarListaPersonas(personas, App.pathPersonas);
//                               }
//                           }
//                           Album.actualizarAlbum(album, App.pathAlbumes);
//
//                   }
//        }
    
        public void eliminar(Persona p, LinkedList<Album> albumes){
        Comparator<Persona> cmp1 = (Persona p1,Persona p2) -> p1.getNombre().toLowerCase()
               .compareTo(p2.getNombre().toLowerCase());
        Comparator<Persona> cmp2 = (Persona p1,Persona p2) -> p1.getApellido().toLowerCase()
                .compareTo(p2.getApellido().toLowerCase());
            for(Album album:albumes ){
                CircularDoublyLinkedList<Foto> fotos=album.getFotos();
                    for(int i=0;i<fotos.size();i++){
                        Set<Persona> personas=fotos.get(i).getPersonas();
                        for(Iterator<Persona> j = personas.iterator(); j.hasNext();){
                            Persona persona=j.next();
                            if(cmp1.compare(p, persona)==0 && cmp2.compare(p, persona)==0){
                               persona.eliminarFotoAparicion(fotos.get(i));
                               j.remove();
                            }
                            persona.actualizarListaPersonas(personas, App.pathPersonas);
                        }
                    }
                    Album.actualizarAlbum(album, App.pathAlbumes);
            }    
    }
        
}
