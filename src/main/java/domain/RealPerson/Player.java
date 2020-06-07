package domain.RealPerson;

import domain.RealPerson.Person;

import java.sql.Date;

public class Player extends Person {
    //id
    //fname
    //sname
    //password
    //birthday
    private String username;

    //constructor
    public Player() {

    }

    public Player(String fname, String sname, String password, Date birthday, String username) {
        super(fname, sname, password, birthday);
        setUsername(username);
    }

    public Player(long id, String fname, String sname, String password, Date birthday, String username) {
        super(id, fname, sname, password, birthday);
        setUsername(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
