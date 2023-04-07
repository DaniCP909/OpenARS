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

    private boolean bpreview;

    private boolean bfile;

    private boolean is3d;

    @Lob
    private Blob preview;   //Preview del recurso jpg o png tanto si es 3d como

    @Lob
    private Blob file;      //Archivo en si

    public Resource(){}

    public 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
