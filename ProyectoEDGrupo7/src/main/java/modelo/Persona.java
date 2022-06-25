
package modelo;
import TDAs.ArrayList;
import TDAs.LinkedList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Persona implements Serializable{
    public ArrayList<Foto> listaFotosAparicion;
    
    private String nombre;
    private String apellido;
    /*
    private static int idCount;
    private int id;
    */
    private String id;
    
    //Constructor
    public Persona(String nombre, String apellido){
        this.nombre = nombre;
        this.apellido = apellido;
        this.listaFotosAparicion = new ArrayList<>();
        this.asignarid();
    }
    
    //Constructor que recibe la lista de fotos donde aparece esa persona
    public Persona(String nombre, String apellido, ArrayList<Foto> listaFotosAparicion){
        this.nombre = nombre;
        this.apellido = apellido;
        this.listaFotosAparicion = listaFotosAparicion;
        this.asignarid();
    }
    
    
    
    //Metodo para generar id aleatoria
    
    public void asignarid(){
        String uniqueID = UUID.randomUUID().toString();
        this.id = uniqueID;
        /*
        Esta forma de asignar un id unico es con una variable estatica que aumente en 1
        cada vez que se asigna un nuevo id
        idCount++;
        this.id = idCount; //Esto garantiza que el id es unico
        */
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        return true;
    }

    
    
    
    
    //Getters and setters

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", apellido=" + apellido + '}';
    }
    
    
    
    public static void crearArchivoPersonas(String path){
        /*
        Metodo para generar los archivos que contengan a las personas que se vayan agregando
        */
        LinkedList<Persona> listaPersonas = new LinkedList<>();
        Persona p1 = new Persona("Roberto", "Patino");
        Persona p2 = new Persona("Jose", "Torres");
        Persona p3 = new Persona("Allan", "Villavicencio");
        listaPersonas.addLast(p1);
        listaPersonas.addLast(p2);
        listaPersonas.addLast(p3);
        
        
        
        //Usando archivos serializados para escribir la lista en el
        try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(path))){            
            escritor.writeObject(listaPersonas);
            
        }catch(FileNotFoundException e){
            System.out.println("No se pudo encontrar el archivo");
        }catch(IOException e){
            System.out.println("No se pudo escribir");
        }catch(Exception e){
            System.out.println("Ha ocurrido un error inesperado");
        }
        
        
    }
    
    public static LinkedList<Persona> leerArchivoPersonas(String path){
        LinkedList<Persona> listaPersonas = new LinkedList<>();
        try(ObjectInputStream lector = new ObjectInputStream(new FileInputStream(path))){
            listaPersonas = (LinkedList<Persona>) lector.readObject();
        }catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado: "+e);
        }catch(IOException e){
            System.out.println("Error al cargar la lista de concursos: "+e);
        }catch(Exception e){
            System.out.println("Error inesperado: "+e);
        }
        return listaPersonas;
    }
    
    
        
    
}
