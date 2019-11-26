/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.InterfaceDAOTUsuarioAmigo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pojos.Tusuarioamigo;

/**
 *
 * @author FABAME
 */
public class DAOTUsuarioAmigo implements InterfaceDAOTUsuarioAmigo {

    public DAOTUsuarioAmigo() {

    }

    @Override
    public boolean register(Session session, Tusuarioamigo tusuarioamigo) throws Exception {
        session.save(tusuarioamigo);

        return true;
    }

    @Override
    public Tusuarioamigo getByCodigoUsuarioAmigo(Session session, String codigoUsuarioAmigo) throws Exception {
        return (Tusuarioamigo) session.get(Tusuarioamigo.class, codigoUsuarioAmigo);
    }

    @Override
    public Tusuarioamigo getByCorreoElectronico(Session session, String correoElectronico) throws Exception {
        String hql = "from Tusuarioamigo where correoElectronico=:correoElectronico";
        Query query = session.createQuery(hql);
        query.setParameter("correoElectronico", correoElectronico);

        return (Tusuarioamigo) query.uniqueResult();
    }

    @Override
    public Tusuarioamigo getByCorreoElectronicoDiferente(Session session, String codigoUsuarioAmigo, String correoElectronico) throws Exception {
        String hql = "from Tusuarioamigo where codigoUsuarioAmigo!=:codigoUsuarioAmigo and correoElectronico=:correoElectronico";
        Query query = session.createQuery(hql);
        query.setParameter("codigoUsuarioAmigo", codigoUsuarioAmigo);
        query.setParameter("correoElectronico", correoElectronico);

        return (Tusuarioamigo) query.uniqueResult();
    }

    @Override
    public List<Tusuarioamigo> getByCodigoUsuario(Session session, String codigoUsuario) throws Exception {
        String hql = "from Tusuarioamigo where codigoUsuario=:codigoUsuario";
        Query query = session.createQuery(hql);
        query.setParameter("codigoUsuario", codigoUsuario);

        return (List<Tusuarioamigo>) query.list();
    }

    @Override
    public boolean update(Session session, Tusuarioamigo tusuarioamigo) throws Exception {
        session.update(tusuarioamigo);

        return true;
    }

    @Override
    public boolean delete(Session session, Tusuarioamigo tusuarioamigo) throws Exception {
        session.delete(tusuarioamigo);

        return true;
    }

}
