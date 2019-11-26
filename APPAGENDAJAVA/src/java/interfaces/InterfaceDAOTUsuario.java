/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import org.hibernate.Session;
import pojos.Tusuario;

/**
 *
 * @author FABAME
 */
public interface InterfaceDAOTUsuario {

    public boolean register(Session session, Tusuario tusuario) throws Exception;

    public Tusuario getByCodigoUsuario(Session session, String codigoUsuario) throws Exception;

    public Tusuario getByCorreoElectronico(Session session, String correoElectronico) throws Exception;

    public Tusuario getByCorreoElectronicoDiferente(Session session, String codigoUsuario, String correoElectronico) throws Exception;

    public List<Tusuario> getAll(Session session) throws Exception;

    public boolean update(Session session, Tusuario tusuario) throws Exception;

    public boolean delete(Session session, Tusuario tusuario) throws Exception;

}
