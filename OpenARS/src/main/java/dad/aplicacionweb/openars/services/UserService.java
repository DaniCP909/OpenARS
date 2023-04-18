package dad.aplicacionweb.openars.services;

import dad.aplicacionweb.openars.models.User;
import dad.aplicacionweb.openars.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);

    }

    public List<User> findAll(){//buscar todos los usuarios

        return userRepo.findAll();
    }

    public User findByUsername(String username){
        return userRepo.findByUsername(username).get();
    }

    public User getByUsername(String username) {
        return userRepo.getByUsername(username);
    }

    public boolean exist(Long id){//ver si existen loos usuarios

        return userRepo.existsById(id);

    }
    public void save(User user){//guardar el usuario

        userRepo.save(user);

    }

    public void replace (User updatedUser){//para modificar el usuario

        userRepo.findById(updatedUser.getId()).orElseThrow();
        userRepo.save(updatedUser);
    }

    public void delete(Long id){//borramos usuario

        this.userRepo.deleteById(id);

    }

}
