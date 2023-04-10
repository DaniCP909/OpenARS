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

    public List<Resource> findAll(){
        return resourceRepo.findAll();
    }
    public Optional<Resource> findById(Long id){
        return resourceRepo.findById(id);
    }
    public Optional<Resource> findByName(String name){
        return resourceRepo.findByName(name);
    }
    public void deleteById(Long id){
        resourceRepo.deleteById(id);
    }
    public Resource getByName(String name){
        return resourceRepo.getByName(name);
    }

}
