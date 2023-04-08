package dad.aplicacionweb.openars.models;

import java.sql.Blob;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;     //CUIDAO NULL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    private String name;

    private String description;

    private boolean bpreview;

    private boolean bfile;

    private boolean is3d;

    @Lob
    private Blob preview;   //Preview del recurso jpg o png tanto si es 3d como

    @Lob
    private Blob file;      //Archivo en si

    public Resource(){}

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

    @Override
    public String toString(){
        return "Nombre de la Obra:" + this.name + " | [Descripción]: " + this.description;
    }

}
