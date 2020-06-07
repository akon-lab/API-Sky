package domain.RealPerson;

import java.sql.Date;

public abstract class Person {

    private long id;
    private String fname;
    private String sname;
    private String password;
    private Date birthday;

    public Person() {

    }

    //player const
    public Person(String fname, String sname, String password, Date birthday) {
        this.fname = fname;
        this.sname = sname;
        this.password = password;
        this.birthday = birthday;
    }

    public Person(long id, String fname, String sname, String password, Date birthday) {
        this.id = id;
        this.fname = fname;
        this.sname = sname;
        this.password = password;
        this.birthday = birthday;
    }

    //admin const
    public Person(String fname, String sname, String password ) {
        this.fname = fname;
        this.sname = sname;
        this.password = password;
    }

    public Person(long id, String fname, String sname, String password ) {
        this.id = id;
        this.fname = fname;
        this.sname = sname;
        this.password = password;
    }

    //get
    public long getId() {
        return id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getSname() {
        return sname;
    }

    public String getPassword() {
        return password;
    }

    public String getFname() {
        return fname;
    }

    //set
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", sname='" + sname + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
