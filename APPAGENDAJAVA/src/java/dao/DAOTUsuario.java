/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.InterfaceDAOTUsuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pojos.Tusuario;

/**
 *
 * @author FABAME
 */
public class DAOTUsuario implements InterfaceDAOTUsuario {

    public DAOTUsuario() {

    }

    @Override
    public boolean register(Session session, Tusuario tusuario) throws Exception {
        session.save(tusuario);

        return true;
    }

    @Override
    public Tusuario getByCodigoUsuario(Session session, String codigoUsuario) throws Exception {
        return (Tusuario) session.get(Tusuario.class, codigoUsuario);
    }

    @Override
    public Tusuario getByCorreoElectronico(Session session, String correoElectronico) throws Exception {
        String hql = "from Tusuario where correoElectronico=:correoElectronico";
        Query query = session.createQuery(hql);
        query.setParameter("correoElectronico", correoElectronico);

        return (Tusuario) query.uniqueResult();
    }

    @Override
    public Tusuario getByCorreoElectronicoDiferente(Session session, String codigoUsuario, String correoElectronico) throws Exception {
        String hql = "from Tusuario where codigoUsuario!=:codigoUsuario and correoElectronico=:correoElectronico";
        Query query = session.createQuery(hql);
        query.setParameter("codigoUsuario", codigoUsuario);
        query.setParameter("correoElectronico", correoElectronico);

        return (Tusuario) query.uniqueResult();
    }

    @Override
    public List<Tusuario> getAll(Session session) throws Exception {
        String hql = "from Tusuario";
        Query query = session.createQuery(hql);

        return (List<Tusuario>) query.list();
    }

    @Override
    public boolean update(Session session, Tusuario tusuario) throws Exception {
        session.update(tusuario);

        return true;
    }

    @Override
    public boolean delete(Session session, Tusuario tusuario) throws Exception {
        session.delete(tusuario);

        return true;
    }

}
