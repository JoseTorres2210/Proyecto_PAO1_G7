package modelo;
import TDAs.ArrayList;
import TDAs.CircularDoublyLinkedList;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Foto implements Serializable{
    private static final long serialVersionUID = 4884631801407000316L;
    private Set<Persona> personas;
    private LocalDate fecha;
    private String lugar;
    private String descripcion;
    private String nomAlbum;
    private String imagen;
    //private Persona persona;
    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Foto(Set<Persona> personas, LocalDate fecha, String lugar,String descripcion){
        this.personas= personas;
        this.fecha=fecha;
        this.lugar=lugar;
        this.descripcion=descripcion;
    }

    public Foto(LocalDate fecha, String lugar,String descripcion){
        this.fecha=fecha;
        this.lugar=lugar;
        this.descripcion=descripcion;
        this.personas = new HashSet<>();   //Xq no hay personas en la foto
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

    public Set<Persona> getPersonas() {
        return personas;
    }

    public void setPersona(Set<Persona> personas) {
        this.personas = personas;
    }
    
    
    public boolean agregarPersonaEnFoto(Persona persona){
        boolean retorno = true;
        //Debemos agregar una persona a una foto SOLO SI ESTA NO SE ENCUENTRA EN LA LISTA PREVIAMENTE
//        if(personas.isEmpty()){
//            personas.add(persona);
//            return true;
//        }
//        for(Persona p : personas){
//            if(!p.equals(persona)){
//                System.out.println("NO HAY COINCIDENCIA");
//                //La agregamos a la lista
//                personas.add(persona);
//                retorno =  true;
//            }else{
//                System.out.println("LA PERSONA YA ESTA EN LA LISTAAAAA");
//            }
//        }
        personas.add(persona);
        return retorno;
    }
    
    
    public boolean eliminarPersonaDeFoto(Persona persona){

        
//        //Debemos eliminar a esa persona a una foto SOLO SI ESTA NO SE ENCUENTRA EN LA LISTA PREVIAMENTE
//        for(Persona p : personas){
//            if(p.equals(persona)){
//                //La eliminamos
//                int indice = personas.indexOf(p);
//                personas.remove(indice);
//                retorno =  true;
//            }else{
//                System.out.println("LA PERSONA NO ESTABA EN LA LISTA");
//            }
//        }
//        return retorno;
          
          return personas.remove(persona);
    }
    
    

    @Override
    public String toString() {
        return "Foto{" + "fecha=" + fecha + ", lugar=" + lugar + ", descripcion=" + descripcion + ", nomAlbum=" + nomAlbum + ", imagen=" + imagen + ", persona=" + personas + '}';
    }
    
    
    public static CircularDoublyLinkedList<String> generarCarreteMuestra(){
        CircularDoublyLinkedList<String> carreteMuestra = new CircularDoublyLinkedList<>();
        carreteMuestra.addLast("paisaje1.jpg");
        carreteMuestra.addLast("paisaje2.jpg");
        carreteMuestra.addLast("paisaje3.jpg");
        carreteMuestra.addLast("paisaje4.jfif");
        carreteMuestra.addLast("paisaje5.jpg");
        carreteMuestra.addLast("paisaje6.jpg");
        carreteMuestra.addLast("paisaje7.jpg");
        carreteMuestra.addLast("paisaje8.jpg");
        carreteMuestra.addLast("paisaje9.jpg");
        carreteMuestra.addLast("paisaje10.jpg");
        carreteMuestra.addLast("paisaje11.jpg");
        carreteMuestra.addLast("paisaje12.jpg");
        carreteMuestra.addLast("paisaje13.jpg");
        carreteMuestra.addLast("paisaje14.jpg");
        carreteMuestra.addLast("paisaje15.jpg");
        carreteMuestra.addLast("paisaje16.jpg");
        carreteMuestra.addLast("paisaje17.jpg");
        carreteMuestra.addLast("paisaje18.jfif");
        carreteMuestra.addLast("paisaje19.jpg");
        carreteMuestra.addLast("paisaje20.jpg");
        return carreteMuestra;
    }
    
    




}
