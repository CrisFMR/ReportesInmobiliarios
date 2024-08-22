package cl.praxis.ReposrtesInmobiliaria.model.service;

import cl.praxis.ReposrtesInmobiliaria.model.entities.User;
import cl.praxis.ReposrtesInmobiliaria.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findOne(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User findOneP(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public boolean update(User u) {
        User result = repository.save(u);
        return true;
    }

    @Override
    public boolean create(User u) {
        repository.save(u);
        return true;
    }

    @Override
    public boolean delete(int id) {
        boolean exist = repository.existsById(id);
        if(exist){
            repository.deleteById(id);
        }
        return exist;
    }
}
