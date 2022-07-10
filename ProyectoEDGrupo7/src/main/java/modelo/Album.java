
package modelo;

import TDAs.CircularDoublyLinkedList;
import TDAs.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;



public class Album implements Serializable,Comparable<Album>{
    
    private static final long serialVersionUID = 8645648745972565030L;
    private String nombre;
    private String descripcion;
    private CircularDoublyLinkedList<Foto> fotos;
    private String fotoPortada;
    
    //Constructor
    public Album(String nombre, String descripcion, CircularDoublyLinkedList<Foto> fotos, String fotoPortada){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fotos = fotos;
        this.fotoPortada = fotoPortada;
    }
    
    public Album(String nombre, String descripcion, CircularDoublyLinkedList<Foto> fotos){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fotos = fotos; 
    }
    
    //Constructor que no recibe la lista de fotos, pero inicializa una lista vacia
    public Album(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fotos = new CircularDoublyLinkedList<>();  //Solo se inicializa la lista de fotos
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CircularDoublyLinkedList<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(CircularDoublyLinkedList<Foto> fotos) {
        this.fotos = fotos;
    }

    public String getFotoPortada() {
        return fotoPortada;
    }

    public void setFotoPortada(String fotoPortada) {
        this.fotoPortada = fotoPortada;
    }


    
    //Metodo para agregar una foto al album
    public boolean agregarFoto(Foto foto){
        return this.fotos.addLast(foto);
    }
    
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if(o!=null&&o.getClass() == getClass()){
            Album a = (Album) o;
            return a.nombre.equalsIgnoreCase(this.nombre)&&a.descripcion.equals(this.descripcion);
        }
        return false;
    }
    
    

    @Override
    public String toString() {
        return nombre;
    }
    
    
    
    
    /*
    Metodo para escribir o agregar un album a un archivo serializado
    */
    

    
    
    /*
    Metodo para GENERAR UN ARCHIVO CON VARIOS ALBUMES
    NOTA: ESTOS ALBUMES SE ENCUENTRAN VACIOS
    */
    
    public static void crearArchivoAlbumes(String path){
        
        LinkedList<Album> listaAlbumes = new LinkedList<>();
        //Se crean albumes vacios
        Album a1 = new Album("Album perritos 1", "Fotos de perritos");
        Album a2 = new Album("Album perritos 2", "Fotos de perritos");
        Album a3 = new Album("Album mascotas", "Fotos de mis mascotas");
        Album a4 = new Album("Fotos random", "Fotos locas");
        Album a5 = new Album("Viajes", "Viaje familiar");
        listaAlbumes.addLast(a1);
        listaAlbumes.addLast(a2);
        listaAlbumes.addLast(a3);
        listaAlbumes.addLast(a4);
        listaAlbumes.addLast(a5);
        
        //Se escriben los albumes
        try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(path))){            
            escritor.writeObject(listaAlbumes);
            
        }catch(FileNotFoundException e){
            System.out.println("No se pudo encontrar el archivo");
        }catch(IOException e){
            System.out.println("No se pudo escribir");
        }catch(Exception e){
            System.out.println("Ha ocurrido un error inesperado");
        }

    }
    
    public static LinkedList<Album> leerArchivoAlbumes(String path){
        LinkedList<Album> listaAlbums = new LinkedList<>();
        try(ObjectInputStream lector = new ObjectInputStream(new FileInputStream(path))){
            listaAlbums = (LinkedList<Album>) lector.readObject();
        }catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado: "+e);
        }catch(IOException e){
            System.out.println("Error al cargar la lista de concursos: "+e);
        }catch(Exception e){
            System.out.println("Error inesperado: "+e);
        }
        return listaAlbums;
    }
    
    
    public static void agregarNuevoAlbumArchivo(Album album,String path){
        LinkedList<Album> listaAlbums = leerArchivoAlbumes(path);
        System.out.println(">>>>>>>>ALBUM ANTES DE AGG:"+ listaAlbums);
        listaAlbums.addLast(album);
        System.out.println(">>>>>>>>>ALBUM DESPUES DE AGG:"+ listaAlbums);
        try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(path))){
            //Se escribe la lista con los auspiciantes actualizada
            escritor.writeObject(listaAlbums);
        }catch(FileNotFoundException e){
            System.out.println("No se encontro el archivo");
        }catch(IOException e){
            System.out.println("Error al escribir: "+e);
        }catch(Exception e){
            System.out.println("Error general");
        }
    }
    
    
    public static void actualizarAlbum(Album album,String path){
        LinkedList<Album> listaAlbums = leerArchivoAlbumes(path);
        int indice = listaAlbums.indexOf(album); //Se recupera el indice de ese album
        listaAlbums.remove(indice);
        listaAlbums.add(indice, album);
        try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(path))){
           //Escribimos la nueva lista actualizada en el archivo serializado
           escritor.writeObject(listaAlbums);
        }catch(IOException e){
            System.out.println("Error al escribir: "+e);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Error con los limites al actualizar el archivo");
        }catch(Exception e){
            System.out.println("Error general: "+e);
        }     
        
    }
    
    
    public static void actualizarListaAlbumes(LinkedList<Album> listaAlbumes, String path){
        try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(path))){
           //Escribimos la nueva lista actualizada en el archivo serializado
           escritor.writeObject(listaAlbumes);
        }catch(IOException e){
            System.out.println("Error al escribir: "+e);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Error con los limites al actualizar el archivo");
        }catch(Exception e){
            System.out.println("Error general: "+e);
        }
    }

    @Override
    public int compareTo(Album o) {
        int retorno = this.nombre.compareTo(o.nombre);
        if(retorno ==0){
            return this.descripcion.compareTo(o.descripcion);
        }
        return retorno;
    }
    
    
    
    //Metodo para eliminar una foto de un album
    public void eliminarFoto(Foto foto){
        //Recorremos la lista de fotos asociada a ese album
        for(int i = 0;i<fotos.size();i++){
            if(fotos.get(i).equals(foto)){
                //Se la elimina
                fotos.remove(i);
                break;
            }
            
        }
    }
    
    
    
}
