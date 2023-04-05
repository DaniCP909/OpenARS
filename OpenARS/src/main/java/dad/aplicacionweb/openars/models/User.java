package dad.aplicacionweb.openars.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "UserTable")
public class User {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String encodedPassword;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    public User(){
    }

    public User(String username, String encodedPassword, String... roles){
        PasswordEncoder passEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        this.username = username;
        this.encodedPassword = passEncoder.encode(encodedPassword);
        this.roles = List.of(roles);
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        PasswordEncoder passEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        this.encodedPassword = passEncoder.encode(encodedPassword);
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
