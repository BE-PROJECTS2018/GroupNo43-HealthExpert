package com.healthexpert.data.remote.models.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shivani on 2/8/2018.
 */

public class DoctorResponse implements Parcelable {
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
    String fuid;
    String photo;
    String likes;
    String ratings;

    public DoctorResponse(String name, String emailid, String pincode, String phoneno, String city, String speciality, String gender, String experience, String regid, String accesstoken, String fuid, String photo, String likes, String ratings) {
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
        this.fuid = fuid;
        this.photo = photo;
        this.likes = likes;
        this.ratings = ratings;
    }

    protected DoctorResponse(Parcel in) {
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
        fuid = in.readString();
        photo = in.readString();
        likes = in.readString();
        ratings = in.readString();
    }

    public static final Creator<DoctorResponse> CREATOR = new Creator<DoctorResponse>() {
        @Override
        public DoctorResponse createFromParcel(Parcel in) {
            return new DoctorResponse(in);
        }

        @Override
        public DoctorResponse[] newArray(int size) {
            return new DoctorResponse[size];
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

    public String getFuid() {
        return fuid;
    }

    public void setFuid(String fuid) {
        this.fuid = fuid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
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
        dest.writeString(fuid);
        dest.writeString(photo);
        dest.writeString(likes);
        dest.writeString(ratings);
    }
}
