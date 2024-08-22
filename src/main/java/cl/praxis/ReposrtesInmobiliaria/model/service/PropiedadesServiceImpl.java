package cl.praxis.ReposrtesInmobiliaria.model.service;

import cl.praxis.ReposrtesInmobiliaria.model.entities.Propiedades;
import cl.praxis.ReposrtesInmobiliaria.model.repository.PropiedadesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropiedadesServiceImpl implements PropiedadesService{
    private final PropiedadesRepository propiedadesRepository;

    public PropiedadesServiceImpl(PropiedadesRepository propiedadesRepository) {
        this.propiedadesRepository = propiedadesRepository;
    }

    @Override
    public List<Propiedades> findAll() {
        return propiedadesRepository.findAll();
    }

    @Override
    public Propiedades findOne(int id) {
        return propiedadesRepository.findById(id).orElse(null);
    }

    @Override
    public Propiedades update(Propiedades p) {
        return propiedadesRepository.save(p);
    }

    @Override
    public Propiedades create(Propiedades p) {
        return propiedadesRepository.save(p);
    }

    @Override
    public boolean delete(int id) {
        boolean exist = propiedadesRepository.existsById(id);
        if(exist){
            propiedadesRepository.deleteById(id);
        }
        return exist;
    }
}
