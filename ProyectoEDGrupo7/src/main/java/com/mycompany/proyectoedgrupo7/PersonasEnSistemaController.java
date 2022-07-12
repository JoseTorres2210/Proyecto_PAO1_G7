package com.mycompany.proyectoedgrupo7;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mycompany.proyectoedgrupo7.App;
import static com.mycompany.proyectoedgrupo7.CrearFoto2Controller.fotoOG;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        ObservableList<Persona> l = FXCollections.observableArrayList();
        System.out.println("%%%%%"+Persona.leerArchivoPersonas(App.pathPersonas));
        System.out.println("-----------------");
        for(Persona p :Persona.leerArchivoPersonas(App.pathPersonas)){
            System.out.println(p);
            l.add(p);
            
        }
        System.out.println("Observable list: "+l);
        tvPersonasRegistradas.getItems().addAll(l);
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
            //CrearCiudadController ct = new CrearCiudadController(); //Recien aqui se esta creando el controlador
            Parent root = (Parent) fxmlLoader.load();
            CrearPersonaController ct = fxmlLoader.getController();
            fxmlLoader.setController(ct);//se asigna el controlador
            ct.llenarCampos(p); 
            App.changeRoot(root);
            
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
    }
    
}