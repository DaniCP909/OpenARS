package dad.aplicacionweb.openars.services;

import dad.aplicacionweb.openars.models.User;
import dad.aplicacionweb.openars.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class DatabaseInitializer {

    @Autowired
    private UserRepository userRepo;

    @PostConstruct
    public void init() throws IOException, URISyntaxException{

        userRepo.save(new User("admin", "adminpass", "ADMIN"));
        userRepo.save(new User ("User 1", "123123", "USER"));
        userRepo.save(new User ("User 2", "123123", "USER"));
        userRepo.save(new User ("User 3", "123123", "USER"));
        userRepo.save(new User ("User 4", "123123", "USER"));

    }

}
