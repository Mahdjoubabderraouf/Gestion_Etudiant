
package javaapplication1;

public class Users {
    private String id;
    private String pswd;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Users(String id, String pswd, String type) {
        this.id = id;
        this.pswd = pswd;
        this.type = type;
    }

    public Users(String id, String pswd) {
        this.id = id;
        this.pswd = pswd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
    
    
}
