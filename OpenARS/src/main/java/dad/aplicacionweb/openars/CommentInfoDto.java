package dad.aplicacionweb.openars;

public class CommentInfoDto {

    private Long id = null;

    private String sender;

    private String resourcename;

    private String emaildest;

    public CommentInfoDto(){}

    public CommentInfoDto(String sender, String resourcename, String emaildest){
        super();
        this.sender = sender;
        this.resourcename = resourcename;
        this.emaildest = emaildest;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getResourcename(){
        return this.resourcename;
    }

    public void setResourcename(String resourcename){
        this.resourcename = resourcename;
    }

    public String getSender(){
        return this.sender;
    }
    public void setSender(String sender){
        this.sender = sender;
    }

    public String getEmaildest(){
        return this.emaildest;
    }

    public void setEmaildest(String emaildest){
        this.emaildest = emaildest;
    }

    @Override
    public String toString(){
        return "Sender: " + this.sender + " | Resource name: " + this.resourcename;
    }



}

