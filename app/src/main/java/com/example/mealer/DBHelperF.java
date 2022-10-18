package com.example.mealer.Firebase;

public class DBHelperF {
    String userName,address,email;
    long pass;
    public DBHelperF(String userName, String address, String email, long pass) {
        this.userName = userName;
        this.address = address;
        this.email = email;
        this.pass = pass;
    }
    public String getUserName() {
        return userName;
    }
    public String getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }
    public long getPass() {
        return pass;
    }
}