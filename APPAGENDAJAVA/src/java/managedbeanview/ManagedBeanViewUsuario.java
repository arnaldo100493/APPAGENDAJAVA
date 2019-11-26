/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeanview;

import clases.Encrypt;
import clases.FacesUtil;
import dao.DAOTUsuario;
import hibernateutil.HibernateUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import managedbeansession.ManagedBeanSessionLogin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;
import pojos.Tusuario;

/**
 *
 * @author FABAME
 */
@ManagedBean(name = "managedBeanViewUsuario")
@ViewScoped
public class ManagedBeanViewUsuario implements Serializable {

    private Session session;
    private Transaction transaction;

    private Tusuario usuario;
    private List<Tusuario> listadoUsuarios;
    private List<Tusuario> listadoUsuariosFiltrados;

    private String textRepitaContrasenia;
    private UploadedFile avatar;

    @ManagedProperty(value = "#{managedBeanSessionLogin}")
    private ManagedBeanSessionLogin managedBeanSessionLogin;

    private Calendar fechaRegistro;
    private Calendar fechaModificacion;

    /**
     * Creates a new instance of ManagedBeanViewUsuario
     */
    public ManagedBeanViewUsuario() {
        this.usuario = new Tusuario();
        this.fechaRegistro = new GregorianCalendar();
        this.fechaModificacion = new GregorianCalendar();
        this.usuario.setCodigoUsuario("");
        this.usuario.setSexo(true);
        this.usuario.setFechaRegistro(this.fechaRegistro.getTime());
        this.usuario.setFechaModificacion(this.fechaModificacion.getTime());
    }

    public void register() {
        this.session = null;
        this.transaction = null;

        try {
            if (!this.usuario.getContrasenia().equals(this.textRepitaContrasenia)) {
                FacesUtil.addErrorMessage("Error:", "Las contraseñas no coinciden.");

                return;
            }

            DAOTUsuario daoTUsuario = new DAOTUsuario();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            if (daoTUsuario.getByCorreoElectronico(this.session, this.usuario.getCorreoElectronico()) != null) {
                FacesUtil.addErrorMessage("Error:", "El usuario ya se encuentra registrado en el sistema.");

                return;
            }

            this.usuario.setContrasenia(Encrypt.sha512(this.usuario.getContrasenia()));
            daoTUsuario.register(this.session, this.usuario);

            this.transaction.commit();

            FacesUtil.addInfoMessage("Correcto:", "El registro fue realizado correctamente.");

            //RequestContext.getCurrentInstance().execute("limpiarFormulario('formRegistrarUsuario')");
            this.usuario = new Tusuario();
            this.fechaRegistro = new GregorianCalendar();
            this.fechaModificacion = new GregorianCalendar();
            this.usuario.setCodigoUsuario("");
            this.usuario.setSexo(true);
            this.usuario.setFechaRegistro(this.fechaRegistro.getTime());
            this.usuario.setFechaModificacion(this.fechaModificacion.getTime());

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

    public Tusuario getByCorreoElectronico() {
        this.session = null;
        this.transaction = null;

        try {
            DAOTUsuario daoTUsuario = new DAOTUsuario();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            HttpSession sessionUsuario = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

            this.usuario = daoTUsuario.getByCorreoElectronico(this.session, sessionUsuario.getAttribute("correoElectronico").toString());

            this.transaction.commit();

            return this.usuario;

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

    public List<Tusuario> getAll() {
        this.session = null;
        this.transaction = null;

        try {
            DAOTUsuario daoTUsuario = new DAOTUsuario();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.listadoUsuarios = daoTUsuario.getAll(this.session);

            this.transaction.commit();

            return this.listadoUsuarios;

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

    public void update() {
        this.session = null;
        this.transaction = null;

        try {
            DAOTUsuario daoTUsuario = new DAOTUsuario();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            if (daoTUsuario.getByCorreoElectronicoDiferente(this.session, this.usuario.getCodigoUsuario(), this.usuario.getCorreoElectronico()) != null) {
                FacesUtil.addErrorMessage("Error:", "Correo electrónico ocupado.");

                return;
            }

            daoTUsuario.update(this.session, this.usuario);

            this.transaction.commit();

            this.managedBeanSessionLogin.setCorreoElectronico(this.usuario.getCorreoElectronico());

            HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            httpSession.setAttribute("correoElectronico", this.usuario.getCorreoElectronico());

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
        this.session = null;
        this.transaction = null;

        try {
            DAOTUsuario daoTUsuario = new DAOTUsuario();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            daoTUsuario.delete(this.session, this.usuario);

            this.transaction.commit();

            FacesUtil.addInfoMessage("Correcto:", "La eliminación fue realizada correctamente.");

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

    public void actualizarAvatar() throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            if (this.avatar.getSize() <= 0) {
                FacesUtil.addErrorMessage("Error:", "Usted debe seleccionar un archivo de imagen \".png\"");
                return;
            }

            if (!this.avatar.getFileName().endsWith(".png")) {
                FacesUtil.addErrorMessage("Error:", "El archivo debe ser con extensión \".png\"");
                return;
            }

            if (this.avatar.getSize() > 2097152) {
                FacesUtil.addErrorMessage("Error:", "El archivo no puede ser más de 2MB  \".png\"");
                return;
            }

            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String carpetaAvatar = (String) servletContext.getRealPath("/avatar");
            String rutaAvatar = carpetaAvatar + "/" + this.usuario.getCodigoUsuario() + ".png";

            outputStream = new FileOutputStream(new File(rutaAvatar));
            inputStream = this.avatar.getInputstream();

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            FacesUtil.addInfoMessage("Correcto:", "Avatar actualizado correctamente.");

        } catch (Exception ex) {
            FacesUtil.addFatalMessage("Error fatal:", "Por favor contacte con su administrador. " + ex.getMessage());
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public void cargarUsuarioEditar(String codigoUsuario) {
        this.session = null;
        this.transaction = null;

        try {
            DAOTUsuario daoTUsuario = new DAOTUsuario();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.usuario = daoTUsuario.getByCodigoUsuario(this.session, codigoUsuario);

            RequestContext.getCurrentInstance().update("formEditarUsuario:panelEditarUsuario");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarUsuario').show()");

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

    public Tusuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Tusuario usuario) {
        this.usuario = usuario;
    }

    public List<Tusuario> getListadoUsuarios() {
        return listadoUsuarios;
    }

    public void setListadoUsuarios(List<Tusuario> listadoUsuarios) {
        this.listadoUsuarios = listadoUsuarios;
    }

    public List<Tusuario> getListadoUsuariosFiltrados() {
        return listadoUsuariosFiltrados;
    }

    public void setListadoUsuariosFiltrados(List<Tusuario> listadoUsuariosFiltrados) {
        this.listadoUsuariosFiltrados = listadoUsuariosFiltrados;
    }

    public String getTextRepitaContrasenia() {
        return textRepitaContrasenia;
    }

    public void setTextRepitaContrasenia(String textRepitaContrasenia) {
        this.textRepitaContrasenia = textRepitaContrasenia;
    }

    public UploadedFile getAvatar() {
        return avatar;
    }

    public void setAvatar(UploadedFile avatar) {
        this.avatar = avatar;
    }

    public ManagedBeanSessionLogin getManagedBeanSessionLogin() {
        return managedBeanSessionLogin;
    }

    public void setManagedBeanSessionLogin(ManagedBeanSessionLogin managedBeanSessionLogin) {
        this.managedBeanSessionLogin = managedBeanSessionLogin;
    }

}
