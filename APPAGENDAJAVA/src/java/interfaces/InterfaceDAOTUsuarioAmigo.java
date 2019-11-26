/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import org.hibernate.Session;
import pojos.Tusuarioamigo;

/**
 *
 * @author FABAME
 */
public interface InterfaceDAOTUsuarioAmigo {

    public boolean register(Session session, Tusuarioamigo tusuarioamigo) throws Exception;

    public Tusuarioamigo getByCodigoUsuarioAmigo(Session session, String codigoUsuarioAmigo) throws Exception;

    public Tusuarioamigo getByCorreoElectronico(Session session, String correoElectronico) throws Exception;

    public Tusuarioamigo getByCorreoElectronicoDiferente(Session session, String codigoUsuarioAmigo, String correoElectronico) throws Exception;

    public List<Tusuarioamigo> getByCodigoUsuario(Session session, String codigoUsuario) throws Exception;

    public boolean update(Session session, Tusuarioamigo tusuarioamigo) throws Exception;

    public boolean delete(Session session, Tusuarioamigo tusuarioamigo) throws Exception;

}
