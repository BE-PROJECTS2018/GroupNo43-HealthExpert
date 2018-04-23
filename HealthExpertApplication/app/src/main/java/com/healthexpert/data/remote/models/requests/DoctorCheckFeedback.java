package com.healthexpert.data.remote.models.requests;

/**
 * Created by Shivani on 3/4/2018.
 */

public class DoctorCheckFeedback {
    String d_accesstoken;
    String p_accesstoken;

    public DoctorCheckFeedback(String d_accesstoken, String p_accesstoken) {
        this.d_accesstoken = d_accesstoken;
        this.p_accesstoken = p_accesstoken;
    }

    public String getD_accesstoken() {
        return d_accesstoken;
    }

    public void setD_accesstoken(String d_accesstoken) {
        this.d_accesstoken = d_accesstoken;
    }

    public String getP_accesstoken() {
        return p_accesstoken;
    }

    public void setP_accesstoken(String p_accesstoken) {
        this.p_accesstoken = p_accesstoken;
    }
}
