package pojos;
// Generated 6/07/2016 04:33:48 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Tactividadcomentario generated by hbm2java
 */
public class Tactividadcomentario  implements java.io.Serializable {


     private String codigoActividadComentario;
     private Tactividad tactividad;
     private Tusuarioamigo tusuarioamigo;
     private String comentario;
     private Date fechaRegistro;
     private Date fechaModificacion;

    public Tactividadcomentario() {
    }

    public Tactividadcomentario(String codigoActividadComentario, Tactividad tactividad, Tusuarioamigo tusuarioamigo, String comentario, Date fechaRegistro, Date fechaModificacion) {
       this.codigoActividadComentario = codigoActividadComentario;
       this.tactividad = tactividad;
       this.tusuarioamigo = tusuarioamigo;
       this.comentario = comentario;
       this.fechaRegistro = fechaRegistro;
       this.fechaModificacion = fechaModificacion;
    }
   
    public String getCodigoActividadComentario() {
        return this.codigoActividadComentario;
    }
    
    public void setCodigoActividadComentario(String codigoActividadComentario) {
        this.codigoActividadComentario = codigoActividadComentario;
    }
    public Tactividad getTactividad() {
        return this.tactividad;
    }
    
    public void setTactividad(Tactividad tactividad) {
        this.tactividad = tactividad;
    }
    public Tusuarioamigo getTusuarioamigo() {
        return this.tusuarioamigo;
    }
    
    public void setTusuarioamigo(Tusuarioamigo tusuarioamigo) {
        this.tusuarioamigo = tusuarioamigo;
    }
    public String getComentario() {
        return this.comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }
    
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }




}


