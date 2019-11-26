/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeansession;

import clases.Encrypt;
import clases.FacesUtil;
import dao.DAOTUsuario;
import hibernateutil.HibernateUtil;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Tusuario;

/**
 *
 * @author FABAME
 */
@Named(value = "managedBeanSessionLogin")
@SessionScoped
public class ManagedBeanSessionLogin implements Serializable {

    private Session session;
    private Transaction transaction;

    private String correoElectronico;
    private String contrasenia;

    /**
     * Creates a new instance of ManagedBeanSessionLogin
     */
    public ManagedBeanSessionLogin() {
        HttpSession miSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        miSession.setMaxInactiveInterval(5000);
    }

    public String login() {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            DAOTUsuario daoTUsuario = new DAOTUsuario();

            Tusuario tusuario = daoTUsuario.getByCorreoElectronico(this.session, this.correoElectronico);

            if (tusuario != null) {
                if (tusuario.getContrasenia().equals(Encrypt.sha512(this.contrasenia))) {
                    HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    httpSession.setAttribute("correoElectronico", this.correoElectronico);

                    return "/usuario/verTodo";
                }
            }

            this.transaction.commit();

            this.correoElectronico = null;
            this.contrasenia = null;

            FacesUtil.addErrorMessage("Error de acceso:", "Usuario o contraseña son incorrectos.");

            return "/index";

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

    public String logout() {
        this.correoElectronico = null;
        this.contrasenia = null;

        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        httpSession.invalidate();

        return "/index";
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

}
