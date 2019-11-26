/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeanview;

import clases.Encrypt;
import clases.FacesUtil;
import dao.DAOTUsuario;
import dao.DAOTUsuarioAmigo;
import hibernateutil.HibernateUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;
import pojos.Tusuario;
import pojos.Tusuarioamigo;

/**
 *
 * @author FABAME
 */
@Named(value = "managedBeanViewUsuarioAmigo")
@ViewScoped
public class ManagedBeanViewUsuarioAmigo implements Serializable {

    private Session session;
    private Transaction transaction;

    private Tusuarioamigo usuarioamigo;
    private List<Tusuarioamigo> listadoUsuariosAmigos;
    private Tusuario usuario;

    private String textRepitaContrasenia;

    private MindmapNode nodoUsuario;

    private Calendar fechaRegistro;
    private Calendar fechaModificacion;

    /**
     * Creates a new instance of ManagedBeanViewUsuarioAmigo
     */
    public ManagedBeanViewUsuarioAmigo() {
        this.usuarioamigo = new Tusuarioamigo();
        this.fechaRegistro = new GregorianCalendar();
        this.fechaModificacion = new GregorianCalendar();
        this.usuarioamigo.setCodigoUsuarioAmigo("");
        this.usuarioamigo.setSexo(true);
        this.usuarioamigo.setFechaRegistro(this.fechaRegistro.getTime());
        this.usuarioamigo.setFechaModificacion(this.fechaModificacion.getTime());
    }

    public void register() {
        this.session = null;
        this.transaction = null;

        try {
            if (!this.usuarioamigo.getContrasenia().equals(this.textRepitaContrasenia)) {
                FacesUtil.addErrorMessage("Error:", "Las contraseñas no coinciden.");

                return;
            }

            DAOTUsuarioAmigo daoTUsuarioAmigo = new DAOTUsuarioAmigo();
            DAOTUsuario daoTUsuario = new DAOTUsuario();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            if (daoTUsuarioAmigo.getByCorreoElectronico(this.session, this.usuarioamigo.getCorreoElectronico()) != null) {
                FacesUtil.addErrorMessage("Error:", "El correo electrónico ya se encuentra registrado en el sistema.");

                return;
            }

            this.usuarioamigo.setContrasenia(Encrypt.sha512(this.usuarioamigo.getContrasenia()));

            HttpSession sessionUsuario = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

            this.usuarioamigo.setTusuario(daoTUsuario.getByCorreoElectronico(this.session, sessionUsuario.getAttribute("correoElectronico").toString()));

            daoTUsuarioAmigo.register(this.session, this.usuarioamigo);

            this.transaction.commit();

            FacesUtil.addInfoMessage("Correcto:", "El registro fue realizado correctamente.");

            //RequestContext.getCurrentInstance().execute("limpiarFormulario('formRegistrarUsuarioAmigo')");
            this.usuarioamigo = new Tusuarioamigo();
            this.fechaRegistro = new GregorianCalendar();
            this.fechaModificacion = new GregorianCalendar();
            this.usuarioamigo.setCodigoUsuarioAmigo("");
            this.usuarioamigo.setSexo(true);
            this.usuarioamigo.setFechaRegistro(this.fechaRegistro.getTime());
            this.usuarioamigo.setFechaModificacion(this.fechaModificacion.getTime());

            /*}catch (ConstraintViolationException ex) {
             if (this.transaction != null) {
             this.transaction.rollback();
             }

             for (ConstraintViolation constraint : ex.getConstraintViolations()) {
             FacesUtil.addErrorMessage("Error:", constraint.getPropertyPath() + ": " + constraint.getMessage()));
             }*/
        } catch (Exception ex) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }

            FacesUtil.addFatalMessage("Error fatal:", "Por favor contacte con su administrador. " + ex.getMessage());

        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void update() {
        this.session = null;
        this.transaction = null;

        try {
            DAOTUsuarioAmigo dAOTUsuarioAmigo = new DAOTUsuarioAmigo();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            if (dAOTUsuarioAmigo.getByCorreoElectronicoDiferente(this.session, this.usuarioamigo.getCodigoUsuarioAmigo(), this.usuarioamigo.getCorreoElectronico()) != null) {
                FacesUtil.addErrorMessage("Error:", "Correo electrónico ocupado.");

                return;
            }

            dAOTUsuarioAmigo.update(this.session, this.usuarioamigo);

            this.transaction.commit();

            FacesUtil.addInfoMessage("Correcto:", "Los cambios fueron guardados correctamente.");

        } catch (Exception ex) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }

            FacesUtil.addFatalMessage("Error fatal:", "Por favor contacte con su administrador. " + ex.getMessage());

        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void delete() {

    }

    public MindmapNode cargarNodosUsuariosAmigosPorCodigoUsuario() {
        this.session = null;
        this.transaction = null;

        try {
            DAOTUsuarioAmigo daoTUsuarioAmigo = new DAOTUsuarioAmigo();
            DAOTUsuario daoTUsuario = new DAOTUsuario();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            HttpSession sessionUsuario = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

            this.usuario = daoTUsuario.getByCorreoElectronico(this.session, sessionUsuario.getAttribute("correoElectronico").toString());
            this.listadoUsuariosAmigos = daoTUsuarioAmigo.getByCodigoUsuario(this.session, this.usuario.getCodigoUsuario());

            this.nodoUsuario = new DefaultMindmapNode(this.usuario.getNombre() + " " + this.usuario.getApellidoPaterno() + " " + this.usuario.getApellidoMaterno(), this.usuario.getCodigoUsuario(), "EBF8A4", false);

            for (Tusuarioamigo item : this.listadoUsuariosAmigos) {
                this.nodoUsuario.addNode(new DefaultMindmapNode(item.getNombre() + " " + item.getApellidoPaterno() + " " + item.getApellidoMaterno() + ':' + item.getCodigoUsuarioAmigo(), item.getCodigoUsuarioAmigo(), "E5E5E5", true));
            }

            this.transaction.commit();

            return this.nodoUsuario;

        } catch (Exception ex) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }

            FacesUtil.addFatalMessage("Error fatal:", "Por favor contacte con su administrador. " + ex.getMessage());

            return null;

        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void mostrarDialogoEditarUsuarioAmigo(SelectEvent event) {
        MindmapNode node = (MindmapNode) event.getObject();

        String codigoUsuarioAmigo = node.getLabel().split(":")[1];

        this.session = null;
        this.transaction = null;

        try {
            DAOTUsuarioAmigo daoTUsuarioAmigo = new DAOTUsuarioAmigo();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.usuarioamigo = daoTUsuarioAmigo.getByCodigoUsuarioAmigo(this.session, codigoUsuarioAmigo);

            RequestContext.getCurrentInstance().update("formEditarUsuarioAmigo:panelEditarUsuarioAmigo");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarUsuarioAmigo').show()");

            this.transaction.commit();

        } catch (Exception ex) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }

            FacesUtil.addFatalMessage("Error fatal:", "Por favor contacte con su administrador. " + ex.getMessage());

        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }

    }

    public Tusuarioamigo getUsuarioamigo() {
        return usuarioamigo;
    }

    public void setUsuarioamigo(Tusuarioamigo usuarioamigo) {
        this.usuarioamigo = usuarioamigo;
    }

    public Tusuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Tusuario usuario) {
        this.usuario = usuario;
    }

    public List<Tusuarioamigo> getListadoUsuariosAmigos() {
        return listadoUsuariosAmigos;
    }

    public void setListadoUsuariosAmigos(List<Tusuarioamigo> listadoUsuariosAmigos) {
        this.listadoUsuariosAmigos = listadoUsuariosAmigos;
    }

    public String getTextRepitaContrasenia() {
        return textRepitaContrasenia;
    }

    public void setTextRepitaContrasenia(String textRepitaContrasenia) {
        this.textRepitaContrasenia = textRepitaContrasenia;
    }

    public MindmapNode getNodoUsuario() {
        return nodoUsuario;
    }

    public void setNodoUsuario(MindmapNode nodoUsuario) {
        this.nodoUsuario = nodoUsuario;
    }

}
