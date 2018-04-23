package com.healthexpert.data.remote.models.requests;

/**
 * Created by Shivani on 2/12/2018.
 */

public class FirebaseRequest {
    String accesstoken;
    String fuid;

    public FirebaseRequest(String accesstoken, String fuid) {
        this.accesstoken = accesstoken;
        this.fuid = fuid;
    }

    public String getFuid() {
        return fuid;
    }

    public void setFuid(String fuid) {
        this.fuid = fuid;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }
}
