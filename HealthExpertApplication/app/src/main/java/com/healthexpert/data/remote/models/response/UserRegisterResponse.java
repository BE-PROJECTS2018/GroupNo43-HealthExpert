package com.healthexpert.data.remote.models.response;

/**
 * Created by Shivani on 2/8/2018.
 */

public class UserRegisterResponse {
    boolean status;
    String message;

    public UserRegisterResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
