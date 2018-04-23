package com.healthexpert.data.remote.models.requests;

/**
 * Created by Shivani on 2/8/2018.
 */

public class DoctorRequest {
    String accessToken;

    public DoctorRequest(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
