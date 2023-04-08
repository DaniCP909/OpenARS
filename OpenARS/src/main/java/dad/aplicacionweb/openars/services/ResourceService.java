package dad.aplicacionweb.openars.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dad.aplicacionweb.openars.models.Resource;
import dad.aplicacionweb.openars.repositories.ResourceRepository;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepo;

    List<Resource> findAll(){
        return resourceRepo.findAll();
    }
    Optional<Resource> findById(Long id){
        return resourceRepo.findById(id);
    }
    Optional<Resource> findByName(String name){
        return resourceRepo.findByName(name);
    }
    void deleteById(Long id){
        resourceRepo.deleteById(id);
    }
    Resource getByName(String name){
        return resourceRepo.getByName(name);
    }

}
