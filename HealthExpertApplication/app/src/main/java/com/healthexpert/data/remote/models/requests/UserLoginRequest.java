package com.healthexpert.data.remote.models.requests;

/**
 * Created by Shivani on 2/8/2018.
 */

public class UserLoginRequest {
    String emailid;
    String password;

    public UserLoginRequest(String emailid, String password) {
        this.emailid = emailid;
        this.password = password;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
