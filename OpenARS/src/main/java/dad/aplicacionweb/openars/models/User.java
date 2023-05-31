package dad.aplicacionweb.openars.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.List;

import javax.persistence.*;

@Entity(name = "UserTable")
public class User {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    private String encodedPassword;

    @Column(unique = true)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Resource> useruploads;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> usercomments;

    public User(){
    }

    public User(String username, String encodedPassword, String email, String... roles){
        PasswordEncoder passEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        this.username = username;
        this.encodedPassword = passEncoder.encode(encodedPassword);
        this.email = email;
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

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<Resource> getUseruploads(){
        return this.useruploads;
    }

    public void setUseruploads(List<Resource> useruploads){
        this.useruploads = useruploads;
    }

}
