package modelo;
import TDAs.ArrayList;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Foto implements Serializable{
    private ArrayList<Persona> personas;
    private LocalDate fecha;
    private String lugar;
    private String descripcion;
    private String nomAlbum;
    private String imagen;
    //private Persona persona;

    public Foto(ArrayList<Persona> personas, LocalDate fecha, String lugar,String descripcion){
        this.personas= personas;
        this.fecha=fecha;
        this.lugar=lugar;
        this.descripcion=descripcion;
    }

    public Foto(LocalDate fecha, String lugar,String descripcion){
        this.fecha=fecha;
        this.lugar=lugar;
        this.descripcion=descripcion;
        this.personas = null;   //Xq no hay personas en la foto
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNomAlbum() {
        return nomAlbum;
    }

    public void setNomAlbum(String nomAlbum) {
        this.nomAlbum = nomAlbum;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersona(ArrayList<Persona> personas) {
        this.personas = personas;
    }
    
    
    public boolean agregarPersonaEnFoto(Persona persona){
        boolean retorno = false;
        //Debemos agregar una persona a una foto SOLO SI ESTA NO SE ENCUENTRA EN LA LISTA PREVIAMENTE
        for(Persona p : personas){
            if(!p.equals(persona)){
                //La agregamos a la lista
                personas.addLast(persona);
                retorno =  true;
            }else{
                System.out.println("LA PERSONA YA ESTA EN LA LISTAAAAA");
            }
        }
        return retorno;
    }

    @Override
    public String toString() {
        return "Foto{" + "fecha=" + fecha + ", lugar=" + lugar + ", descripcion=" + descripcion + ", nomAlbum=" + nomAlbum + ", imagen=" + imagen + ", persona=" + personas + '}';
    }
    
    




}
