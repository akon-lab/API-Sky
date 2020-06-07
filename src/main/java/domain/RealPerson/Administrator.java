package domain.RealPerson;

import java.sql.Date;

public class Administrator extends Person {
    //id
    //fname
    //sname
    //password
    private String identityName;
    private String rang;


    //constructor
    public Administrator() {

    }

    public Administrator(String fname, String sname, String password, String rang,String identityName) {
        super(fname, sname, password);
        setRang(rang);
        setIdentityName(identityName);
    }

    public Administrator(long id, String fname, String sname, String password, String rang,String identityName) {
        super(id, fname, sname, password);
        setRang(rang);
        setIdentityName(identityName);
    }

    //getter
    public String getRang() {
        return rang;
    }

    public String getIdentityName() {
        return identityName;
    }

    //setter
    public void setRang(String rang) {
        this.rang = rang;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }
}
