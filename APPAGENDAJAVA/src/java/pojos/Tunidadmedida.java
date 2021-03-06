package pojos;
// Generated 6/07/2016 04:33:48 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tunidadmedida generated by hbm2java
 */
public class Tunidadmedida  implements java.io.Serializable {


     private String codigoUnidadMedida;
     private String nombre;
     private String descripcion;
     private Date fechaRegistro;
     private Date fechaModificacion;
     private Set tactividadpresupuestos = new HashSet(0);

    public Tunidadmedida() {
    }

	
    public Tunidadmedida(String codigoUnidadMedida, String nombre, String descripcion, Date fechaRegistro, Date fechaModificacion) {
        this.codigoUnidadMedida = codigoUnidadMedida;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
    }
    public Tunidadmedida(String codigoUnidadMedida, String nombre, String descripcion, Date fechaRegistro, Date fechaModificacion, Set tactividadpresupuestos) {
       this.codigoUnidadMedida = codigoUnidadMedida;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.fechaRegistro = fechaRegistro;
       this.fechaModificacion = fechaModificacion;
       this.tactividadpresupuestos = tactividadpresupuestos;
    }
   
    public String getCodigoUnidadMedida() {
        return this.codigoUnidadMedida;
    }
    
    public void setCodigoUnidadMedida(String codigoUnidadMedida) {
        this.codigoUnidadMedida = codigoUnidadMedida;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    public Set getTactividadpresupuestos() {
        return this.tactividadpresupuestos;
    }
    
    public void setTactividadpresupuestos(Set tactividadpresupuestos) {
        this.tactividadpresupuestos = tactividadpresupuestos;
    }




}


