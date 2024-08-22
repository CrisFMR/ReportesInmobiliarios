package cl.praxis.ReposrtesInmobiliaria.model.repository;

import cl.praxis.ReposrtesInmobiliaria.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
