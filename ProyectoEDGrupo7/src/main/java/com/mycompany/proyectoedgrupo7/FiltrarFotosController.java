package com.mycompany.proyectoedgrupo7;

import TDAs.ArrayList;
import TDAs.CircularDoublyLinkedList;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("visualizarAlbumes");
    }

    
    private void buscar(ActionEvent event){
        if(filtrarPersona.isSelected()){
            
        }
        if(filtrarFecha.isSelected()){
            
        }
        if(filtrarLugar.isSelected()){
            
        }
    }
    
    
    private static ArrayList<Foto> filtrarPersonaFoto(CircularDoublyLinkedList<Foto> fotos,String nombre){
        Comparator<Persona> cmp = (Persona p1,Persona p2) -> p1.getNombre()
                .compareTo(p2.getNombre());
        Persona tmp=new Persona(nombre,null);
        ArrayList<Foto> resultado=new ArrayList();
        for(int i=0;i<fotos.size();i++){
            if(fotos.get(i).getPersonas()!=null){
//                if((fotos.get(i).getPersonas().findSame(cmp,tmp)).size()!=0){
//                    resultado.addFirst(fotos.get(i));
                for(Persona p : fotos.get(i).getPersonas()){
                    if(cmp.compare(p, tmp)==0){
                        resultado.addFirst(fotos.get(i));
                    }
                    
                }
            }        
        }   
        return resultado;
    }
    
    public static CircularDoublyLinkedList<Foto> filtrarPersonaAlbum(ArrayList<Album> albumes,String nombre){
        CircularDoublyLinkedList<Foto> result=new CircularDoublyLinkedList<Foto>();
        for(Album album:albumes){
            ArrayList<Foto> fotos=filtrarPersonaFoto(album.getFotos(), nombre);
            for(Foto foto:fotos){
                result.addLast(foto);
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
    
    public static CircularDoublyLinkedList<Foto> filtrarLugarAlbum(ArrayList<Album> albumes,String lugar){
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
        if(fechaInicio==null && fechaFin==null){                   
            return null;
        }
        else if(fechaInicio==null){     
            LocalDate d2= fechaFin.getValue();
            Foto f2=new Foto(d2,null,null);
            CircularDoublyLinkedList<Foto> resultado=fotos.findSmall(cmp,f2);
            return resultado;
    
        }
        else if(fechaFin==null){
            LocalDate d1=fechaInicio.getValue();
            Foto f1=new Foto(d1,null,null);
            CircularDoublyLinkedList<Foto> resultado=fotos.findBig(cmp,f1);
            return resultado;
        }
          LocalDate d1=fechaInicio.getValue();
          LocalDate d2= fechaFin.getValue();
          Foto f1=new Foto(d1,null,null);
          Foto f2=new Foto(d2,null,null);
          CircularDoublyLinkedList<Foto> resultado=fotos.findBig(cmp,f1);
          resultado=fotos.findSmall(cmp,f2);  
           return resultado;
    }
    
    public static CircularDoublyLinkedList<Foto> filtrarFechaAlbum(ArrayList<Album> albumes,DatePicker fechaInicio,DatePicker fechaFin){
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
    private void switchToMostrarResultadoFiltro(ActionEvent event) {
    }
}
