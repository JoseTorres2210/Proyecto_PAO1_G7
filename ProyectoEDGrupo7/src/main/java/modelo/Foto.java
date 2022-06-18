package modelo;
import java.util.Date;

public class Foto {
    //private ArrayList<Persona> personas;
    private Date fecha;
    private String lugar;
    private String descripcion;
    private String nomAlbum;
    private String imagen;
    private Persona persona;

    public Foto(Persona persona, Date fecha, String lugar,String descripcion){
        this.persona= persona;
        this.fecha=fecha;
        this.lugar=lugar;
        this.descripcion=descripcion;
    }

    public Foto(Date fecha, String lugar,String descripcion){
        this.fecha=fecha;
        this.lugar=lugar;
        this.descripcion=descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }




}
