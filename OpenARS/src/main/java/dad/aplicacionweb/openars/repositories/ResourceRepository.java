package dad.aplicacionweb.openars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dad.aplicacionweb.openars.models.Resource;

import java.util.List;
import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource, Long>{

    List<Resource> findAll();
    Optional<Resource> findById(Long l);
    Optional<Resource> findByName(String name); //El username no se puede repetir
    //----------------ATENCION: cambio a optional no user por error en RepositoryUserDetailsService
    void deleteById(Long id);
    Resource getByName(String name);

}
