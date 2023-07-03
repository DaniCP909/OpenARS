package dad.aplicacionweb.openars.repositories;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dad.aplicacionweb.openars.models.Resource;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "resources")
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long>{

    @Cacheable
    List<Resource> findAll();
    Optional<Resource> findById(Long id);
    Optional<Resource> findByName(String name); //El username no se puede repetir
    //----------------ATENCION: cambio a optional no user por error en RepositoryUserDetailsService
    @CacheEvict(allEntries = true)
    void deleteById(Long id);

    @CacheEvict(allEntries = true)
    Resource save(Resource resource);

    Resource getByName(String name);

}
