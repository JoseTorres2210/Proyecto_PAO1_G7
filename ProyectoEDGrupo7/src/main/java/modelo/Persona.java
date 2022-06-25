
package modelo;
import TDAs.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Persona {
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
    
    
    
        
    
}
