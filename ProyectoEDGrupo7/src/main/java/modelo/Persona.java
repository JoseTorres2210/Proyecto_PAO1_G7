
package modelo;
import java.util.UUID;

public class Persona {
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
        this.asignarid();
    }
    
    public Persona(String nombre){
        this.nombre = nombre;
        this.apellido = ""; //Volvemos el apellido un String vacio
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
