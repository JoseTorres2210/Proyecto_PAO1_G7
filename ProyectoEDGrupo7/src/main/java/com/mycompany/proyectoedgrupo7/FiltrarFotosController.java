package com.mycompany.proyectoedgrupo7;

import TDAs.ArrayList;
import TDAs.CircularDoublyLinkedList;
import TDAs.LinkedList;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.Album;
import modelo.Foto;
import modelo.Persona;

public class FiltrarFotosController implements Initializable{

    @FXML
    private Button buttonFiltrar;
    @FXML
    private Button buttonRegresar;
    @FXML
    private TextField txtLugar;
    @FXML
    private TextField txtPersona;
    @FXML
    private DatePicker fechaDesde;
    @FXML
    private DatePicker fechaHasta;
    @FXML
    private CheckBox filtrarPersona;
    @FXML
    private CheckBox filtrarLugar;
    @FXML
    private CheckBox filtrarFecha;
    private CircularDoublyLinkedList<Foto> listaFotosFiltradas;
    public void initialize(URL url, ResourceBundle rb) {

        fechaDesde.setVisible(false);
        fechaHasta.setVisible(false);
        txtPersona.setVisible(false);
        txtLugar.setVisible(false);
        listaFotosFiltradas = new CircularDoublyLinkedList<>();
//        CheckComboBox cb = new CheckComboBox();
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        
        info.setTitle("Importante");
        info.setHeaderText("Filtrado: ");
        info.setContentText("Si desea filtrar sus fotos por varias personas\nasegúrese de separar"
                + " el nombre completo de cada persona \nusando comas");
        info.show();

    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("visualizarAlbumes");
    }

    
    private void buscar(){
       LinkedList<Album> lista = Album.leerArchivoAlbumes(App.pathAlbumes);
        if(filtrarPersona.isSelected()){
            String[] array=txtPersona.getText().split(",");
            String primero=array[0];
            String[] elementos=primero.split(" ");
            if(elementos.length==1){
                listaFotosFiltradas= filtrarPersonaAlbum(lista,elementos[0],null);
            }
            else{listaFotosFiltradas= filtrarPersonaAlbum(lista,elementos[0],elementos[1]);}
            int counter= 1;
            while(counter<array.length){
                String tmp=array[counter];
                elementos=tmp.split(" ");
                listaFotosFiltradas= filtrarPersonaFoto(listaFotosFiltradas,elementos[0],elementos[1]);
                counter++;
            }
        }
        if(filtrarFecha.isSelected()){
            if(listaFotosFiltradas.isEmpty()){
                listaFotosFiltradas= filtrarFechaAlbum(lista,fechaDesde,fechaHasta);
            }
            else{
                listaFotosFiltradas= filtrarFechaFoto(listaFotosFiltradas,fechaDesde,fechaHasta);
            }
        }
        if(filtrarLugar.isSelected()){
            if(listaFotosFiltradas.isEmpty()){
                listaFotosFiltradas= filtrarLugarAlbum(lista,txtLugar.getText());
            }
            else{
                listaFotosFiltradas= filtrarLugarFoto(listaFotosFiltradas,txtLugar.getText());
            }
        }
    }

    @FXML
    private void unlockP() {
        /*
        Metodo para desbloquear y bloquear diferentes parametros de filtrado
        */
        if(filtrarPersona.isSelected()){
            txtPersona.setVisible(true);
        }else{
            txtPersona.setVisible(false);
        }
    }

    @FXML
    private void unlockL(){
        if (filtrarLugar.isSelected()){
            txtLugar.setVisible(true);
        }else{
            txtLugar.setVisible(false);
        }
    }
    
    @FXML
    private void unlockF(){
        if(filtrarFecha.isSelected()){
            fechaDesde.setVisible(true);
            fechaHasta.setVisible(true);
        }else{
            fechaDesde.setVisible(false);
            fechaHasta.setVisible(false);
        }

    }
    
    
    private static CircularDoublyLinkedList<Foto> filtrarPersonaFoto(CircularDoublyLinkedList<Foto> fotos,String nombre,String apellido){
        Comparator<Persona> cmp1 = (Persona p1,Persona p2) -> p1.getNombre().toLowerCase()
                .compareTo(p2.getNombre().toLowerCase());
        Comparator<Persona> cmp2 = (Persona p1,Persona p2) -> p1.getApellido().toLowerCase()
                .compareTo(p2.getApellido().toLowerCase());
        Persona tmp=new Persona(nombre,apellido);
        CircularDoublyLinkedList<Foto> resultado=new CircularDoublyLinkedList<Foto>();
        for(int i=0;i<fotos.size();i++){
            if(fotos.get(i).getPersonas()!=null){
                for(Persona p : fotos.get(i).getPersonas()){
                    if(apellido==null){
                        if(cmp1.compare(p, tmp)==0){
                        resultado.addFirst(fotos.get(i));
                        }                        
                    }
                    else if(cmp1.compare(p, tmp)==0 && cmp2.compare(p, tmp)==0){
                        resultado.addFirst(fotos.get(i));
                    }
                }
            }        
        }   
        return resultado;
    }
    
    public static CircularDoublyLinkedList<Foto> filtrarPersonaAlbum(LinkedList<Album> albumes,String nombre,String apellido){
        CircularDoublyLinkedList<Foto> result=new CircularDoublyLinkedList<Foto>();
        for(Album album:albumes){
            CircularDoublyLinkedList<Foto> fotos=filtrarPersonaFoto(album.getFotos(), nombre, apellido);
            int counter=0;
            while(counter<fotos.size()){
                result.addLast(fotos.get(counter));
                counter++;
            } 
        }
        return result;
    }
    
    
    
    public static CircularDoublyLinkedList<Foto> filtrarLugarFoto(CircularDoublyLinkedList<Foto> fotos,String lugar){
        Foto tmp=new Foto(null,lugar,null);
        CircularDoublyLinkedList<Foto> resultado=new CircularDoublyLinkedList<Foto>();
        Comparator<Foto> cmp = (Foto foto1,Foto foto2) -> foto1.getLugar()
                .compareTo(foto2.getLugar());
            resultado=fotos.findSame(cmp,tmp);
        
        return resultado;
    
    }
    
    public static CircularDoublyLinkedList<Foto> filtrarLugarAlbum(LinkedList<Album> albumes,String lugar){
        CircularDoublyLinkedList<Foto> result=new CircularDoublyLinkedList<Foto>();
        for(Album album:albumes){
            CircularDoublyLinkedList<Foto> fotos=filtrarLugarFoto(album.getFotos(), lugar);
            for(int i=0;i<fotos.size();i++){
                result.addLast(fotos.get(i));
            } 
        }
        return result;
    }

    public static CircularDoublyLinkedList<Foto> filtrarFechaFoto(CircularDoublyLinkedList<Foto> fotos,DatePicker fechaInicio,DatePicker fechaFin){
        Comparator<Foto> cmp = (Foto foto1,Foto foto2) -> foto1.getFecha()
                .compareTo(foto2.getFecha());
//        if(fechaInicio==null && fechaFin==null){                   
//            return null;
//        }
//        else if(fechaInicio==null){     
//            LocalDate d2= fechaFin.getValue();
//            Foto f2=new Foto(d2,null,null);
//            CircularDoublyLinkedList<Foto> resultado=fotos.findSmall(cmp,f2);
//            return resultado;
//    
//        }
//        else if(fechaFin==null){
//            LocalDate d1=fechaInicio.getValue();
//            Foto f1=new Foto(d1,null,null);
//            CircularDoublyLinkedList<Foto> resultado=fotos.findBig(cmp,f1);
//            return resultado;
//        }
          LocalDate d1=fechaInicio.getValue();
          LocalDate d2= fechaFin.getValue();
          Foto f1=new Foto(d1,null,null);
          Foto f2=new Foto(d2,null,null);
          CircularDoublyLinkedList<Foto> resultado=fotos.findSmall(cmp,f1);
          resultado=resultado.findBig(cmp,f2);  
           return resultado;
    }
    
    public static CircularDoublyLinkedList<Foto> filtrarFechaAlbum(LinkedList<Album> albumes,DatePicker fechaInicio,DatePicker fechaFin){
        CircularDoublyLinkedList<Foto> result=new CircularDoublyLinkedList<Foto>();
        for(Album album:albumes){
            CircularDoublyLinkedList<Foto> fotos=filtrarFechaFoto(album.getFotos(),fechaInicio,fechaFin);
            for(int i=0;i<fotos.size();i++){
                result.addLast(fotos.get(i));
            } 
        }
        return result;
    }

    @FXML
    private void switchToMostrarResultadoFiltro(ActionEvent event) throws IOException {
        /*
        ###########################################
        Es necesario guardar en una lista circular las fotos resultantes del filtro
        para luego asignar esa lista al atributo estatico que contendra la lista
        de fotos resultantes en la clase MostrarResultadoFiltroController
        ############################################
        */
        
        //OJO CON ESTA LINEA
        buscar();
        MostrarResultadosFiltroController.listaFotosFiltradas = listaFotosFiltradas;
        
        
        
        App.setRoot("mostrarResultadosFiltro");
    }
}
