
package modelo;

import TDAs.CircularDoublyLinkedList;

public class Album {
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
    
    
    
    

    @Override
    public String toString() {
        return "Album{" + "nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    
    
    /*
    Metodo para escribir o agregar un album a un archivo serializado
    */
    
    public void agregarAlbum(){
        
    }
    
    
    
}
