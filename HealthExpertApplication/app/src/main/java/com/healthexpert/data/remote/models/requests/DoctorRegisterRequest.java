package com.healthexpert.data.remote.models.requests;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shivani on 2/8/2018.
 */

public class DoctorRegisterRequest implements Parcelable {
    String name;
    String emailid;
    String speciality;
    String city;
    String pincode;
    String phoneno;
    String password;
    String regid;
    String gender;
    String experience;
    String fuid;
    String photo;

    public DoctorRegisterRequest(String name, String emailid, String speciality, String city, String pincode, String phoneno, String password, String regid, String gender, String experience, String fuid, String photo) {
        this.name = name;
        this.emailid = emailid;
        this.speciality = speciality;
        this.city = city;
        this.pincode = pincode;
        this.phoneno = phoneno;
        this.password = password;
        this.regid = regid;
        this.gender = gender;
        this.experience = experience;
        this.fuid = fuid;
        this.photo = photo;
    }

    protected DoctorRegisterRequest(Parcel in) {
        name = in.readString();
        emailid = in.readString();
        speciality = in.readString();
        city = in.readString();
        pincode = in.readString();
        phoneno = in.readString();
        password = in.readString();
        regid = in.readString();
        gender = in.readString();
        experience = in.readString();
        fuid = in.readString();
        photo = in.readString();
    }

    public static final Creator<DoctorRegisterRequest> CREATOR = new Creator<DoctorRegisterRequest>() {
        @Override
        public DoctorRegisterRequest createFromParcel(Parcel in) {
            return new DoctorRegisterRequest(in);
        }

        @Override
        public DoctorRegisterRequest[] newArray(int size) {
            return new DoctorRegisterRequest[size];
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

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(emailid);
        dest.writeString(speciality);
        dest.writeString(city);
        dest.writeString(pincode);
        dest.writeString(phoneno);
        dest.writeString(password);
        dest.writeString(regid);
        dest.writeString(gender);
        dest.writeString(experience);
        dest.writeString(fuid);
        dest.writeString(photo);
    }
}
