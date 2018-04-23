package com.healthexpert.data.remote.models.requests;

/**
 * Created by Shivani on 2/8/2018.
 */

public class UserRegisterRequest {
    String name;
    String emailid;
    String speciality;
    String city;
    String pincode;
    String phoneno;
    String password;

    public UserRegisterRequest(String name, String emailid, String speciality, String city, String pincode, String phoneno, String password) {
        this.name = name;
        this.emailid = emailid;
        this.speciality = speciality;
        this.city = city;
        this.pincode = pincode;
        this.phoneno = phoneno;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
