package jp.ace;

/**
 * Created by JP on 9/22/2017.
 */

public class UserClass {
    public String name;
    public String birthday;
    public String email;
    public String password;

    public UserClass(){

    }

    public UserClass(String name, String birthday, String email, String password) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
