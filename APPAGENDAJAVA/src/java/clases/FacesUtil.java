/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_FATAL;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static javax.faces.application.FacesMessage.SEVERITY_WARN;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.context.FacesContext;

/**
 *
 * @author FABAME
 */
/**
 *
 * Clase para mensajes de dialogo en paginas web JSF.
 *
 */
public class FacesUtil {

    public FacesUtil() {

    }

    /**
     *
     * Muestra un mensaje con el tipo de alerta de informacion por defecto en la pagina sin
     * detalles.
     *
     * @param message: Representa el mensaje a mostrar.
     *
     */
    private static void addMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
    }

    /**
     *
     * Muestra un mensaje con el tipo de alerta de informacion en la pagina por defecto con detalles.
     *
     * @param message: Representa el mensaje a mostrar.}
     * @param details: Representa los posibles detalles del mensaje a mostrar
     * (opcional).
     *
     */
    private static void addMessage(String message, String details) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message, details));
    }

    /**
     *
     * Muestra un mensaje de cualquier tipo de alerta en la pagina sin detalles.
     *
     * @param severity: Repesenta el tipo de alerta a mostrar.
     * @param message: Representa el mensaje a mostrar.
     *
     */
    private static void addMessage(Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, null));
    }

    /**
     *
     * Muestra un mensaje de cualquier tipo de alerta en la pagina con detalles.
     *
     * @param severity: Repesenta el tipo de alerta a mostrar.
     * @param message: Representa el mensaje a mostrar.
     * @param details: Representa los posibles detalles del mensaje a mostrar
     * (opcional).
     *
     */
    private static void addMessage(Severity severity, String message, String details) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, details));
    }

    /**
     *
     * Muestra un mensaje de error en la pagina sin detalles.
     *
     * @param message: Representa el mensaje a mostrar.
     *
     */
    public static void addErrorMessage(String message) {
        addMessage(SEVERITY_ERROR, message);
    }

    /**
     *
     * Muestra un mensaje de error en la pagina con detalles.
     *
     * @param message: Representa el mensaje a mostrar.
     * @param details: Representa los posibles detalles del mensaje a mostrar
     * (opcional).
     *
     */
    public static void addErrorMessage(String message, String details) {
        addMessage(SEVERITY_ERROR, message, details);
    }

    /**
     *
     * Muestra un mensaje de error fatal en la pagina sin detalles.
     *
     * @param message: Representa el mensaje a mostrar.
     *
     */
    public static void addFatalMessage(String message) {
        addMessage(SEVERITY_FATAL, message);
    }

    /**
     *
     * Muestra un mensaje de error fatal en la pagina con detalles.
     *
     * @param message: Representa el mensaje a mostrar.
     * @param details: Representa los posibles detalles del mensaje a mostrar
     * (opcional).
     *
     *
     */
    public static void addFatalMessage(String message, String details) {
        addMessage(SEVERITY_FATAL, message, details);
    }

    /**
     *
     * Muestra un mensaje de informacion en la pagina sin detalles.
     *
     * @param message: Representa el mensaje a mostrar.
     *
     */
    public static void addInfoMessage(String message) {
        addMessage(SEVERITY_INFO, message);
    }

    /**
     *
     * Muestra un mensaje de informacion en la pagina con detalles.
     *
     * @param message: Representa el mensaje a mostrar
     * @param details: Representa los posibles detalles del mensaje (opcional).
     *
     */
    public static void addInfoMessage(String message, String details) {
        addMessage(SEVERITY_INFO, message, details);
    }

    /**
     *
     * Muestra un mensaje de advertencia en la pagina sin detalles.
     *
     * @param message: Representa el mensaje a mostrar.
     *
     */
    public static void addWarnMessage(String message) {
        addMessage(SEVERITY_WARN, message);
    }

    /**
     * Muestra un mensaje de advertencia en la pagina con detalles.
     *
     * @param message: Representa el mensaje a mostrar.
     * @param details: Representa los posibles detalles del mensaje (opcional).
     *
     */
    public static void addWarnMessage(String message, String details) {
        addMessage(SEVERITY_WARN, message, details);
    }

    public static boolean retraso() {
        try {
            Thread.sleep(2000);

        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }
        return false;
    }

    public static boolean isDummySelectItem(UIComponent component, String value) {
        for (UIComponent children : component.getChildren()) {
            if (children instanceof UISelectItem) {
                UISelectItem item = (UISelectItem) children;
                if (item.getItemValue() == null && item.getItemLabel().equals(value)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
}
