package dad.aplicacionweb.openars.services;

import dad.aplicacionweb.openars.models.Resource;
import dad.aplicacionweb.openars.models.User;
import dad.aplicacionweb.openars.repositories.ResourceRepository;
import dad.aplicacionweb.openars.repositories.UserRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class DatabaseInitializer {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ResourceRepository resourceRepo;

    @PostConstruct
    public void init() throws IOException, URISyntaxException{

        userRepo.save(new User("admin", "adminpass", "ADMIN"));
        userRepo.save(new User ("User 1", "123123", "USER"));
        userRepo.save(new User ("User 2", "123123", "USER"));
        userRepo.save(new User ("User 3", "123123", "USER"));
        userRepo.save(new User ("User 4", "123123", "USER"));
        userRepo.save(new User ("User 5", "123123", "USER"));

        Resource resource1 = new Resource("Wierd_Sword", "Modelo de espada futurista en multiples formatos");
        setResourcePreview(resource1, "static/images/sword2.png");
        setResourceFile(resource1, "resources3d/21-sword.zip",true);
        resourceRepo.save(resource1);

        /*
        Resource resource2 = new Resource("Airplane_v2", "Modelo de avión privado sin marca de aerolinea");
        setResourcePreview(resource2, "dbinit_images/plane.png");
        setResourceFile(resource2, "resources3d/airplane_v2.zip",true);
        resourceRepo.save(resource2);

         */

        Resource resource3 = new Resource("Mechanical_Arm", "Modelo de un brazo robot futurista en distintos formatos");
        setResourcePreview(resource3, "dbinit_images/brazo-mecanico.png");
        setResourceFile(resource3, "resources3d/brazo-mecanico-ModelRmk3.zip",true);
        resourceRepo.save(resource3);

        Resource resource4 = new Resource("Tree", "Modelo de arbol realista");
        setResourcePreview(resource4, "dbinit_images/tree.png");
        setResourceFile(resource4, "resources3d/tree.zip",true);
        resourceRepo.save(resource4);

        Resource resource5 = new Resource("oldbook", "Modelo de un libro antiguo para decoración");
        setResourcePreview(resource5, "dbinit_images/oldbook.png");
        setResourceFile(resource5, "resources3d/oldbook.zip",true);
        resourceRepo.save(resource5);

        resourceRepo.save(new Resource("Carro decoración", "Modelo de un carro para ambientación en formato .3DS"));
        resourceRepo.save(new Resource("Espada Rara", "Modelo de espada para videojuegos en formato .3DS"));

        //INICIALIZACION COMENTARIOS:


    }

    public void setResourcePreview(Resource resource, String classpathResource) throws IOException {
       resource.setBpreview(true);

       org.springframework.core.io.Resource preview = new ClassPathResource(classpathResource);

       resource.setPreview(BlobProxy.generateProxy(preview.getInputStream(), preview.contentLength()));
    }

    public void setResourceFile(Resource resource, String classpathResource, boolean is3d) throws IOException {
        resource.setBfile(true);
        resource.setIs3d(is3d);

        org.springframework.core.io.Resource file = new ClassPathResource(classpathResource);
        resource.setFile(BlobProxy.generateProxy(file.getInputStream(), file.contentLength()));
    }

}
