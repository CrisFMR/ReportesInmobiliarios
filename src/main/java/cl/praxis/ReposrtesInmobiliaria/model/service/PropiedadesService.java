package cl.praxis.ReposrtesInmobiliaria.model.service;

import cl.praxis.ReposrtesInmobiliaria.model.entities.Propiedades;

import java.util.List;

public interface PropiedadesService {
    List<Propiedades> findAll();
    Propiedades findOne(int id);
    Propiedades update(Propiedades p);
    Propiedades create(Propiedades p);
    boolean delete(int id);
}
