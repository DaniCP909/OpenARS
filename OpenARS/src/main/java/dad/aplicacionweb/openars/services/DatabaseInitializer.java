package dad.aplicacionweb.openars.services;

import dad.aplicacionweb.openars.models.Comment;
import dad.aplicacionweb.openars.models.Resource;
import dad.aplicacionweb.openars.models.User;
import dad.aplicacionweb.openars.repositories.CommentRepository;
import dad.aplicacionweb.openars.repositories.ResourceRepository;
import dad.aplicacionweb.openars.repositories.UserRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Service
public class DatabaseInitializer {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ResourceRepository resourceRepo;

    @Autowired
    private CommentRepository commentRepo;

    @PostConstruct
    public void init() throws IOException, URISyntaxException{

        ArrayList<User> users2Add = new ArrayList<>();

        User admin = new User("admin", "adminpass", "tesseractsbservice@gmail.com", "ADMIN");
        User user1 = new User ("User 1", "123123", "d.corredorpadrino@gmail.com", "USER");
        User user2 = new User ("User 2", "123123", "danielcorredor97@gmail.com", "USER");
        User user3 = new User ("User 3", "123123", "user3@gmail.com", "USER");
        User user4 = new User ("User 4", "123123", "user4@gmail.com", "USER");
        User user5 = new User ("User 5", "123123", "user5@gmail.com", "USER");

        users2Add.add(admin);
        users2Add.add(user1);
        users2Add.add(user2);
        users2Add.add(user3);
        users2Add.add(user4);
        users2Add.add(user5);

        if(userRepo.findAll().isEmpty()){
            userRepo.saveAll(users2Add);
        }


        ArrayList<Resource> resources2Add = new ArrayList<>();

        Resource resource1 = new Resource("Wierd_Sword", "Modelo de espada futurista en multiples formatos");
        setResourcePreview(resource1, "dbinit_images/sword.png");
        setResourceFile(resource1, "resources3d/21-sword.zip",true);
        resource1.setOwner(user1);
        resources2Add.add(resource1);


        Resource resource2 = new Resource("Dao", "Modelo de espada Dao");
        setResourcePreview(resource2, "dbinit_images/Dao_preview01.png");
        setResourceFile(resource2, "resources3d/Dao.zip",true);
        resource2.setOwner(user1);
        resources2Add.add(resource2);


        /*
        Resource resource3 = new Resource("Mechanical_Arm", "Modelo de un brazo robot futurista en distintos formatos");
        setResourcePreview(resource3, "dbinit_images/brazo-mecanico.png");
        setResourceFile(resource3, "resources3d/brazo-mecanico-ModelRmk3.zip",true);
        resourceRepo.save(resource3);
*/

        Resource resource4 = new Resource("Tree", "Modelo de arbol realista");
        setResourcePreview(resource4, "dbinit_images/tree.png");
        setResourceFile(resource4, "resources3d/tree.zip",true);
        resource4.setOwner(user1);
        resources2Add.add(resource4);

        Resource resource5 = new Resource("oldbook", "Modelo de un libro antiguo para decoración");
        setResourcePreview(resource5, "dbinit_images/oldbook.png");
        setResourceFile(resource5, "resources3d/oldbook.zip",true);
        resource5.setOwner(user1);
        resources2Add.add(resource5);

        if(resourceRepo.findAll().isEmpty()){
            resourceRepo.saveAll(resources2Add);
        }

/*
        resourceRepo.save(new Resource("Carro decoración", "Modelo de un carro para ambientación en formato .3DS"));
        resourceRepo.save(new Resource("Espada Rara", "Modelo de espada para videojuegos en formato .3DS"));
*/
        
        ArrayList<Comment> comments2Add = new ArrayList<>();
        //INICIALIZACION COMENTARIOS:
        comments2Add.add(new Comment(user1, resource1, "Comentario de prueba 1"));
        comments2Add.add(new Comment(user2, resource1, "Comentario de prueba 2"));
        comments2Add.add(new Comment(user3, resource1, "Comentario de prueba 3"));
        //commentRepo.save(new Comment(user4, resource3, "Comentario de prueba 4"));
        //commentRepo.save(new Comment(user5, resource3, "Comentario de prueba 5"));

        if(commentRepo.findAll().isEmpty()){
            commentRepo.saveAll(comments2Add);
        }



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
