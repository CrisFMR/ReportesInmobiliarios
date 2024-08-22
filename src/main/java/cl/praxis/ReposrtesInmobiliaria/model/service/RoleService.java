package cl.praxis.ReposrtesInmobiliaria.model.service;

import cl.praxis.ReposrtesInmobiliaria.model.entities.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findOne(int id);
    Role findOneP(int id);
    boolean update(Role r);
    boolean create(Role r);
    boolean delete(int id);
}
