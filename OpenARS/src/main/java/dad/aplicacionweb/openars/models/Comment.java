package dad.aplicacionweb.openars.models;

import javax.persistence.*;

@Entity(name = "commentTable")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Resource resource;

    private String content;



    public Comment(){}
    public Comment(User user, Resource resource, String content){
        this.user = user;
        this.resource = resource;
        this.content = content;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public User getUser(){
        return this.user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public Resource getResource(){
        return this.resource;
    }

    public void setResource(Resource resource){
        this.resource = resource;
    }

}
