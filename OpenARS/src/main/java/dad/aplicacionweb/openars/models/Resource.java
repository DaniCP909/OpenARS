package dad.aplicacionweb.openars.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dad.aplicacionweb.openars.services.ResourceService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.*;

@Entity(name = "resourceTable")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;     //CUIDAO NULL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


    private String name;

    private String description;

    private boolean bpreview;

    private boolean bfile;

    private boolean is3d;

    @ManyToOne
    @JsonIgnore
    private User owner;

    @OneToMany(mappedBy = "resource")
    @JsonIgnore
    private List<Comment> opinions;

    @Lob
    private Blob preview;   //Preview del recurso jpg o png tanto si es 3d como

    @Lob
    private Blob file;      //Archivo en si

    protected Resource(){}

    public Resource(String name, String description){
        this.name = name;
        this.description = description;
        this.bfile = false;
        this.bpreview = false;
        this.is3d = false;
        this.preview = null;
        this.file = null;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public boolean getBpreview(){
        return this.bpreview;
    }

    public void setBpreview(boolean bpreview){
        this.bpreview = bpreview;
    }

    public boolean getBfile(){
        return this.bfile;
    }

    public void setBfile(boolean bfile){
        this.bfile = bfile;
    }

    public boolean getIs3d(){
        return this.is3d;
    }

    public void setIs3d(boolean is3d){
        this.is3d = is3d;
    }

    public Blob getPreview(){
        return this.preview;
    }

    public void setPreview(Blob preview){
        this.preview = preview;
    }

    public Blob getFile(){
        return this.file;
    }

    public void setFile(Blob file){
        this.file = file;
    }

    public User getOwner(){
        return this.owner;
    }

    public void setOwner(User owner){
        this.owner=owner;
    }

    public List<Comment> getOpinions(){
        return this.opinions;
    }

    public void setOpinions(List<Comment> opinions){
        this.opinions = opinions;
    }

    @Override
    public String toString(){
        return "Nombre de la Obra:" + this.name + " | [Descripción]: " + this.description;
    }





}
