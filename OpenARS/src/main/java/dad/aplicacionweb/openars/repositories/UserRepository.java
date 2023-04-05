package dad.aplicacionweb.openars.repositories;

import dad.aplicacionweb.openars.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findById(Long l);
    Optional<User> findByUsername(String username); //El username no se puede repetir
    //----------------ATENCION: cambio a optional no user por error en RepositoryUserDetailsService
    void deleteById(Long id);
    User getByUsername(String username);
}
