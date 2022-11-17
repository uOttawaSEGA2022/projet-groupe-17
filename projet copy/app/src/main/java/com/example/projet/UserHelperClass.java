package com.example.projet;

public class UserHelperClass {
    String id, email, pass;

    public UserHelperClass() {
    }
    public UserHelperClass(String id, String email, String pass) {
        this.id = id;
        this.email = email;
        this.pass = pass;
    }



    public String getId() {
        return id;
    }

    public void setId(String idd) {
        this.id = idd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPass() {
        return pass;
    }

    public void setPass(String password) {
        this.pass = password;
    }
}
