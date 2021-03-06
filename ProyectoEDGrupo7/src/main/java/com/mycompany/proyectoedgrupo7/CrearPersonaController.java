/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoedgrupo7;

import TDAs.CircularDoublyLinkedList;
import TDAs.LinkedList;
import exceptions.EmptyFieldsException;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo.Album;
import modelo.Foto;
import modelo.Persona;
/**
 * FXML Controller class
 *
 * @author Det-Pc
 */
public class CrearPersonaController implements Initializable {


    @FXML
    private Label lblCrearNuevaPersona;
    @FXML
    private Button buttonFinalizarCreacionPersona;
    @FXML
    private Button buttonCancelar;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    public static boolean creacionPersonaEnArchivoNuevo = false;
    @FXML
    private Button buttonActualizar;
    private Persona personaActualizar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        buttonActualizar.setVisible(false);
    }    
    
    public Persona getPersonaActualizar(){
        return personaActualizar;
    }
    
    @FXML
    private void crearPersona(ActionEvent event) {
        //Se recuperan los valores de los campos especificados
        try{
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();

            if(nombre.equals("")||apellido.equals("")){
                throw  new EmptyFieldsException();
            }
            //Se crea a la persona
            Persona personaNueva = new Persona(nombre, apellido);
            
            //Se la agrega al archivo de personas
            Persona.agregarNuevaPersonaArchivos(personaNueva, App.pathPersonas);
            
            Alert exito = new Alert(Alert.AlertType.INFORMATION, "La persona ha sido agregada al sistema de manera exitosa");
            exito.setTitle("Exito");
            exito.setHeaderText("Operacion exitosa");
            exito.show();
            
            if(creacionPersonaEnArchivoNuevo){
                creacionPersonaEnArchivoNuevo = false;
                App.setRoot("personasEnSistema");
            }else{
                creacionPersonaEnArchivoNuevo = false;
                App.setRoot("crearFoto2");
                
            }
            
        }catch(EmptyFieldsException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR,"Aseg??rese de llenar todos los campos"); //FIXME
            alerta.setTitle("Error");
            alerta.setHeaderText("Ha ocurrido un error:");
            alerta.show(); 
        }catch(NullPointerException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR,"Aseg??rese de llenar todos los campos"); //FIXME
            alerta.setTitle("Error");
            alerta.setHeaderText("Ha ocurrido un error:");
            System.out.println(e.getClass());
            alerta.show();  
        }catch(Exception e){
            System.out.println("EXCEPCION GENERAL INESPERADA");
        }

        
        
    }

    @FXML
    private void switchToCrearFoto2(ActionEvent event) throws IOException {
        //Se retorna a la parte anterior
        
        if(creacionPersonaEnArchivoNuevo){
            creacionPersonaEnArchivoNuevo = false;
            App.setRoot("personasEnSistema");
        }else{
            creacionPersonaEnArchivoNuevo = false;
            App.setRoot("crearFoto2");
        }
        
      
    }

    @FXML
    private void actualizarPersona(ActionEvent event) {
        Persona vieja=null;
        Persona nueva=null;
        creacionPersonaEnArchivoNuevo = true;
        //Obtenemos el conjunto de personas
        try{
            Set<Persona> listaPersonas = Persona.leerArchivoPersonas(App.pathPersonas);

            for(Persona p : listaPersonas){
                //#####################OJO CON ESTA LINEA
                if(p.getId().equals(personaActualizar.getId())){
                    vieja=new Persona(p.getNombre(),p.getApellido());
                    vieja.setId(p.getId());
//                    vieja=p;
                    //Removemos a la persona
                    p.setNombre(txtNombre.getText());
                    
                    p.setApellido(txtApellido.getText());
                    p.setNombreCompleto(txtNombre.getText()+" "+txtApellido.getText());
                    nueva=p;
                    System.out.println(PersonasEnSistemaController.personaNueva);
                    
                    if(p.getNombre().equals("")||p.getApellido().equals("")){
                        throw  new EmptyFieldsException();
                    }
                }
            
                
            }
            System.out.println(personaActualizar);
            editar(personaActualizar,nueva,Album.leerArchivoAlbumes(App.pathAlbumes));
            System.out.println("Lista actualizada>>>"+listaPersonas);
            //Se actualiza el archivo
            Persona.actualizarListaPersonas(listaPersonas, App.pathPersonas); 
            //Se muestra aviso de exito
            Alert exito = new Alert(Alert.AlertType.INFORMATION, "La persona ha sido actualizada en el sistema de manera exitosa");
            exito.setTitle("Exito");
            exito.setHeaderText("Operacion exitosa");
            exito.show();
            System.out.println("LISTA EN LOS ARCHIVOS>>>>"+Persona.leerArchivoPersonas(App.pathPersonas));
            App.setRoot("personasEnSistema");
            
            
        }catch(EmptyFieldsException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR,"Aseg??rese de llenar todos los campos"); //FIXME
            alerta.setTitle("Error");
            alerta.setHeaderText("Ha ocurrido un error:");
            alerta.show(); 
        }catch(NullPointerException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR,"Aseg??rese de llenar todos los campos"); //FIXME
            alerta.setTitle("Error");
            alerta.setHeaderText("Ha ocurrido un error:");
            System.out.println(e.getClass());
            alerta.show();  
        }catch(Exception e){
            System.out.println("EXCEPCION GENERAL INESPERADA");
        }

        
        
    }
    
    public void llenarCampos(Persona p){
        lblCrearNuevaPersona.setText("Editar datos de persona");
        creacionPersonaEnArchivoNuevo = true;
        //Se vuelve el boton para crear persona invisible
        buttonFinalizarCreacionPersona.setVisible(false);
        
        //Se vuelve visible el boton de actualizacion
        buttonActualizar.setVisible(true);
        
        //Se llenan los campos con respecto a la persona
        txtNombre.setText(p.getNombre());
        txtApellido.setText(p.getApellido());
        personaActualizar = p;
    }
    
            public void editar(Persona vieja,Persona nueva, LinkedList<Album> albumes){
                System.out.println(nueva);
                System.out.println(vieja);
               Comparator<Persona> cmp1 = (Persona p1,Persona p2) -> p1.getNombre().toLowerCase()
                      .compareTo(p2.getNombre().toLowerCase());
               Comparator<Persona> cmp2 = (Persona p1,Persona p2) -> p1.getApellido().toLowerCase()
                       .compareTo(p2.getApellido().toLowerCase());
               Comparator<Persona> cmp3 = (Persona p1,Persona p2) -> p1.getId().toLowerCase()
                       .compareTo(p2.getId().toLowerCase());               
                   for(Album album:albumes ){
                       CircularDoublyLinkedList<Foto> fotos=album.getFotos();
                           for(int i=0;i<fotos.size();i++){
                               Set<Persona> personas=fotos.get(i).getPersonas();
                               for(Iterator<Persona> j = personas.iterator(); j.hasNext();){
                                   Persona persona=j.next();
                                   if(cmp3.compare(vieja, persona)==0){
                                       vieja.setNombre(nueva.getNombre());
                                       vieja.setApellido(nueva.getApellido());
                                       vieja.setNombreCompleto(nueva.getNombreCompleto());
                                   }
                                   persona.actualizarListaPersonas(personas, App.pathPersonas);
                               }
                           }
                           Album.actualizarAlbum(album, App.pathAlbumes);

                   }
        }

}
