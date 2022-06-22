
package modelo;

import TDAs.CircularDoublyLinkedList;

public class Album {
    private String nombre;
    private String descripcion;
    private CircularDoublyLinkedList<Foto> fotos;
    
    
    //Constructor
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
    
    
    
    

    @Override
    public String toString() {
        return "Album{" + "nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
