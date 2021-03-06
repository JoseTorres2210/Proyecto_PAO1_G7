
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Persona implements Serializable{
    private static final long serialVersionUID = -6341945772409309375L;
    public ArrayList<Foto> listaFotosAparicion;
    
    private String nombre;
    private String apellido;
    /*
    private static int idCount;
    private int id;
    */
    private String id;
    private String nombreCompleto;

    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    public void eliminarFotoAparicion(Foto f){
        for (Iterator<Foto> iterator = listaFotosAparicion.iterator(); iterator.hasNext();) {
        Foto foto = iterator.next();
            if(foto.equals(f)) {
                iterator.remove();
            }
        }
    }
    
    //Constructor
    public Persona(String nombre, String apellido){
        this.nombre = nombre;
        this.apellido = apellido;
        this.listaFotosAparicion = new ArrayList<>();
        this.asignarid();
        this.nombreCompleto = nombre+" "+apellido;
    }
    
    //Constructor que recibe la lista de fotos donde aparece esa persona
    public Persona(String nombre, String apellido, ArrayList<Foto> listaFotosAparicion){
        this.nombre = nombre;
        this.apellido = apellido;
        this.listaFotosAparicion = listaFotosAparicion;
        this.asignarid();
        this.nombreCompleto = nombre+" "+apellido;
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
    
    public void setNombreCompleto(String nombreCompleto){
        this.nombreCompleto = nombreCompleto;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido ;
    }
    
    
    
    public static void crearArchivoPersonas(String path){
        /*
        Metodo para generar los archivos que contengan a las personas que se vayan agregando
        */
        Set<Persona> listaPersonas = new HashSet<>();
        Persona p1 = new Persona("Roberto", "Patino");
        Persona p2 = new Persona("Jose", "Torres");
        Persona p3 = new Persona("Allan", "Villavicencio");
        listaPersonas.add(p1);
        listaPersonas.add(p2);
        listaPersonas.add(p3);
        
        
        
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
    
    public static Set<Persona> leerArchivoPersonas(String path){
        Set<Persona> listaPersonas = new HashSet<>();
        try(ObjectInputStream lector = new ObjectInputStream(new FileInputStream(path))){
            listaPersonas = (Set<Persona>) lector.readObject();
        }catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado: "+e);
        }catch(IOException e){
            System.out.println("Error al cargar la lista de concursos: "+e);
        }catch(Exception e){
            System.out.println("Error inesperado: "+e);
        }
        return listaPersonas;
    }
    
    
    public static void agregarNuevaPersonaArchivos(Persona persona,String path){
        Set<Persona> listaPersonas = leerArchivoPersonas(path);
        listaPersonas.add(persona);
        try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(path))){
            //Se escribe la lista con los auspiciantes actualizada
            escritor.writeObject(listaPersonas);
        }catch(FileNotFoundException e){
            System.out.println("No se encontro el archivo");
        }catch(IOException e){
            System.out.println("Error al escribir: "+e);
        }catch(Exception e){
            System.out.println("Error general");
        }
    }
    
    public static void actualizarListaPersonas(Set<Persona> listaPersonas, String path){
        try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(path))){
           //Escribimos la nueva lista actualizada en el archivo serializado
            System.out.println("A ESCRIBIR: >>>"+listaPersonas);
           escritor.writeObject(listaPersonas);
        }catch(IOException e){
            System.out.println("Error al escribir: "+e);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Error con los limites al actualizar el archivo");
        }catch(Exception e){
            System.out.println("Error general: "+e);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + Objects.hashCode(this.apellido);
        return hash;
    }

    public void setId(String id) {
        this.id=id;
    }
  
    
        
        
        
    
    
    
        
    
}
