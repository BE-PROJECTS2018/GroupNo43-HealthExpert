package com.healthexpert.data.remote.models.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shivani on 2/8/2018.
 */

public class Doctor implements Parcelable {
    String name;
    String emailid;
    String pincode;
    String phoneno;
    String city;
    String speciality;
    String gender;
    String experience;
    String regid;
    String accesstoken;
    String photo;
    String fuid;

    public Doctor(String name, String emailid, String pincode, String phoneno, String city, String speciality, String gender, String experience, String regid, String accesstoken, String photo, String fuid) {
        this.name = name;
        this.emailid = emailid;
        this.pincode = pincode;
        this.phoneno = phoneno;
        this.city = city;
        this.speciality = speciality;
        this.gender = gender;
        this.experience = experience;
        this.regid = regid;
        this.accesstoken = accesstoken;
        this.photo = photo;
        this.fuid = fuid;
    }

    protected Doctor(Parcel in) {
        name = in.readString();
        emailid = in.readString();
        pincode = in.readString();
        phoneno = in.readString();
        city = in.readString();
        speciality = in.readString();
        gender = in.readString();
        experience = in.readString();
        regid = in.readString();
        accesstoken = in.readString();
        photo = in.readString();
        fuid = in.readString();
    }

    public static final Creator<Doctor> CREATOR = new Creator<Doctor>() {
        @Override
        public Doctor createFromParcel(Parcel in) {
            return new Doctor(in);
        }

        @Override
        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFuid() {
        return fuid;
    }

    public void setFuid(String fuid) {
        this.fuid = fuid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(emailid);
        dest.writeString(pincode);
        dest.writeString(phoneno);
        dest.writeString(city);
        dest.writeString(speciality);
        dest.writeString(gender);
        dest.writeString(experience);
        dest.writeString(regid);
        dest.writeString(accesstoken);
        dest.writeString(photo);
        dest.writeString(fuid);
    }
}
