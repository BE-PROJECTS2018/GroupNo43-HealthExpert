package com.healthexpert.data.remote.models.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shivani on 2/8/2018.
 */

public class Patient implements Parcelable {
    private String pid;
    private String name;
    private String dob;
    private String gender;
    private String height;
    private String weight;
    private String bloodgroup;
    private String phoneno;
    private String occupation;
    private String symptoms;
    private String history;
    private String investigations;
    private String city;
    private String pincode;
    private String mothername;
    private String mothersymptoms;
    private String fathername;
    private String fathersymptoms;
    private String photo;
    private String accesstoken;
    private String devicetoken;

    public Patient(String pid, String name, String dob, String gender, String height, String weight, String bloodgroup, String phoneno, String occupation, String symptoms, String history, String investigations, String city, String pincode, String mothername, String mothersymptoms, String fathername, String fathersymptoms, String photo, String accesstoken, String devicetoken) {
        this.pid = pid;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.bloodgroup = bloodgroup;
        this.phoneno = phoneno;
        this.occupation = occupation;
        this.symptoms = symptoms;
        this.history = history;
        this.investigations = investigations;
        this.city = city;
        this.pincode = pincode;
        this.mothername = mothername;
        this.mothersymptoms = mothersymptoms;
        this.fathername = fathername;
        this.fathersymptoms = fathersymptoms;
        this.photo = photo;
        this.accesstoken = accesstoken;
        this.devicetoken = devicetoken;
    }

    protected Patient(Parcel in) {
        pid = in.readString();
        name = in.readString();
        dob = in.readString();
        gender = in.readString();
        height = in.readString();
        weight = in.readString();
        bloodgroup = in.readString();
        phoneno = in.readString();
        occupation = in.readString();
        symptoms = in.readString();
        history = in.readString();
        investigations = in.readString();
        city = in.readString();
        pincode = in.readString();
        mothername = in.readString();
        mothersymptoms = in.readString();
        fathername = in.readString();
        fathersymptoms = in.readString();
        photo = in.readString();
        accesstoken = in.readString();
        devicetoken = in.readString();
    }

    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getInvestigations() {
        return investigations;
    }

    public void setInvestigations(String investigations) {
        this.investigations = investigations;
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

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getMothersymptoms() {
        return mothersymptoms;
    }

    public void setMothersymptoms(String mothersymptoms) {
        this.mothersymptoms = mothersymptoms;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getFathersymptoms() {
        return fathersymptoms;
    }

    public void setFathersymptoms(String fathersymptoms) {
        this.fathersymptoms = fathersymptoms;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public String getDevicetoken() {
        return devicetoken;
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pid);
        parcel.writeString(name);
        parcel.writeString(dob);
        parcel.writeString(gender);
        parcel.writeString(height);
        parcel.writeString(weight);
        parcel.writeString(bloodgroup);
        parcel.writeString(phoneno);
        parcel.writeString(occupation);
        parcel.writeString(symptoms);
        parcel.writeString(history);
        parcel.writeString(investigations);
        parcel.writeString(city);
        parcel.writeString(pincode);
        parcel.writeString(mothername);
        parcel.writeString(mothersymptoms);
        parcel.writeString(fathername);
        parcel.writeString(fathersymptoms);
        parcel.writeString(photo);
        parcel.writeString(accesstoken);
        parcel.writeString(devicetoken);
    }
}
