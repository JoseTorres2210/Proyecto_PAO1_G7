/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoedgrupo7;

import TDAs.LinkedList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
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
public class CrearFoto2Controller implements Initializable {


    @FXML
    private Label lblCrearFoto;
    @FXML
    private Button buttonCrearFoto;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonAgregarNuevaPersona;
    @FXML
    private Label lblPersonasEnFoto;
    @FXML
    private RadioButton rbSiHayPersonas;
    @FXML
    private ToggleGroup personas;
    @FXML
    private RadioButton rbNoHayPersonas;
    @FXML
    private TableView<Persona> tvPersonasEnSistema;
    @FXML
    private TableColumn<Persona, String> tcPersonas;
    @FXML
    private TableColumn<Persona, Void> tcOpciones;
    public static Foto fotoOG;
    public static Foto fotoOG2;
    private boolean edicion = false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        edicion = false;
        tvPersonasEnSistema.setVisible(false);
        
        tcPersonas.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
         ObservableList<Persona> l = FXCollections.observableArrayList();
         for(Persona p :Persona.leerArchivoPersonas(App.pathPersonas)){
             System.out.println(p.getNombreCompleto());
             l.add(p);
             System.out.println(p);
         }
         System.out.println(l);
         tvPersonasEnSistema.getItems().addAll(l);
         agregarOpciones();
        
    }    
    
    @FXML
    private void crearFoto(ActionEvent event) throws IOException {
        //Se escribe el archivo
        //Se actualiza el archivo de albumes para que ahora contenga la nueva foto
        System.out.println("AGREGANDO EL ALBUM ACTUALIZADO A LA LISTA DE ALBUMES CONTENIDOS EN EL ARCHIVO");
        LinkedList<Album> l = Album.leerArchivoAlbumes(App.pathAlbumes);
        for(Album a:l){
            if(a.compareTo(fotoOG.getAlbum())==0){
                //Son el mismo album, Se agrega la foto
                a.agregarFoto(fotoOG);
            }
        }
        //Se escribe la lista actualizada en el archivo
        Album.actualizarListaAlbumes(l, App.pathAlbumes);


        Alert exito = new Alert(Alert.AlertType.INFORMATION, "La foto ha sido creada de manera exitosa");
        exito.setTitle("Exito");
        exito.setHeaderText("Operacion exitosa");
        exito.show();
        App.setRoot("menuPrincipal");
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("crearFoto");
    }

    @FXML
    private void switchToAgregarPersona(ActionEvent event) throws IOException {
        /*
        #########################
        DARLE IMPLEMENTACION A ESTE METODO
        #########################
        */
        App.setRoot("crearPersona");
    }

    @FXML
    private void unlockButton(ActionEvent event) {
        /*
        Metodo para bloquear el boton de agregar personas hasta que se seleccione el 
        radio button con el texto "Si"
        */
        if(rbSiHayPersonas.isSelected()){
            tvPersonasEnSistema.setVisible(true);
        }else if(rbNoHayPersonas.isSelected()){
            tvPersonasEnSistema.setVisible(false);
        }
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
                            Button btnEl = new Button("Eliminar de la foto");
                            Button btnEditar = new Button("Modificar Persona");
                            btnEl.setDisable(true);
                            Button btnEd = new Button("Agregar a foto");
                            btnEd.setOnAction(e ->{
                                
                                System.out.println("Se agrega la persona a la foto: "+persona);
                                
                                System.out.println(fotoOG.agregarPersonaEnFoto(persona));
                                //Se permite que el usuario elimine de la foto a esa persona
                                btnEl.setDisable(false);
                                btnEditar.setDisable(true);
                                btnEd.setDisable(true);
                                System.out.println(fotoOG.getPersonas());
                                
                            });
                            
                            btnEl.setOnAction(e ->{
                                System.out.println("Se elimina a la persona "+ persona +" de la foto");
                                btnEd.setDisable(false);
                                btnEditar.setDisable(false);
                                btnEl.setDisable(true);
                                fotoOG.eliminarPersonaDeFoto(persona);
                                System.out.println(fotoOG.getPersonas());
                                
                            });
           
                            
                            //se agregan botones al hbox
                            hbOpciones.getChildren().addAll(btnEd,btnEl);
                            //se ubica hbox en la celda
                            setGraphic(hbOpciones);
                            if(edicion){
                                btnEl.setVisible(false);
                                btnEd.setVisible(false);
                            }
                            
                        }
                    }
                };
                return cell;
            }
        };

        tcOpciones.setCellFactory(cellFactory);
    }
    
    
    
    public void llenarCampos(Foto f){
        
        edicion = true;
        buttonCancelar.setOnAction(e ->{
            try {
                App.setRoot("visualizarFotosEnAlbum");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        tvPersonasEnSistema.getItems().clear();
        lblPersonasEnFoto.setVisible(false);
        lblCrearFoto.setText("Personas en esta foto: ");
        rbNoHayPersonas.setVisible(false);
        rbSiHayPersonas.setVisible(false);
        tvPersonasEnSistema.setVisible(true);
        tcPersonas.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        ObservableList<Persona> l = FXCollections.observableArrayList();
         for(Persona p :f.getPersonas()){
             System.out.println(p.getNombreCompleto());
             l.add(p);
             System.out.println(p);
         }
         System.out.println(l);
         tvPersonasEnSistema.getItems().addAll(l);
         //agregarOpciones();
         
    }

}
