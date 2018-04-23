package com.healthexpert.data.remote.models.requests;

/**
 * Created by Shivani on 3/4/2018.
 */

public class DoctorFeedback {
    String d_accesstoken,p_accesstoken;
    int rating,vote;

    public DoctorFeedback(String d_accesstoken, String p_accesstoken, int rating, int vote) {
        this.d_accesstoken = d_accesstoken;
        this.p_accesstoken = p_accesstoken;
        this.rating = rating;
        this.vote = vote;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
