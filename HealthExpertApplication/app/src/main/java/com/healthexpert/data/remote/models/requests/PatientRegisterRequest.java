package com.healthexpert.data.remote.models.requests;

import android.os.Parcel;
import android.os.Parcelable;

import okhttp3.RequestBody;

/**
 * Created by Shivani on 2/8/2018.
 */

public class PatientRegisterRequest implements Parcelable {
    private String name;
    private String dob;
    private String gender;
    private String height;
    private String weight;
    private String emailid;
    private String phoneno;
    private String occupation;
    private String bloodgroup;
    private String symptoms;
    private String history;
    private String investigations;
    private String city;
    private String pincode;
    private String mothername;
    private String mothersymptoms;
    private String fathername;
    private String fathersymptoms;
    private RequestBody image;
    private String password;
    private String devicetoken;

    public PatientRegisterRequest(String name, String dob, String gender, String height, String weight, String emailid, String phoneno, String occupation, String bloodgroup, String symptoms, String history, String investigations, String city, String pincode, String mothername, String mothersymptoms, String fathername, String fathersymptoms, RequestBody image, String password, String devicetoken) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.emailid = emailid;
        this.phoneno = phoneno;
        this.occupation = occupation;
        this.bloodgroup = bloodgroup;
        this.symptoms = symptoms;
        this.history = history;
        this.investigations = investigations;
        this.city = city;
        this.pincode = pincode;
        this.mothername = mothername;
        this.mothersymptoms = mothersymptoms;
        this.fathername = fathername;
        this.fathersymptoms = fathersymptoms;
        this.image = image;
        this.password = password;
        this.devicetoken = devicetoken;
    }

    protected PatientRegisterRequest(Parcel in) {
        name = in.readString();
        dob = in.readString();
        gender = in.readString();
        height = in.readString();
        weight = in.readString();
        emailid = in.readString();
        phoneno = in.readString();
        occupation = in.readString();
        bloodgroup = in.readString();
        symptoms = in.readString();
        history = in.readString();
        investigations = in.readString();
        city = in.readString();
        pincode = in.readString();
        mothername = in.readString();
        mothersymptoms = in.readString();
        fathername = in.readString();
        fathersymptoms = in.readString();
        password = in.readString();
        devicetoken = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(dob);
        dest.writeString(gender);
        dest.writeString(height);
        dest.writeString(weight);
        dest.writeString(emailid);
        dest.writeString(phoneno);
        dest.writeString(occupation);
        dest.writeString(bloodgroup);
        dest.writeString(symptoms);
        dest.writeString(history);
        dest.writeString(investigations);
        dest.writeString(city);
        dest.writeString(pincode);
        dest.writeString(mothername);
        dest.writeString(mothersymptoms);
        dest.writeString(fathername);
        dest.writeString(fathersymptoms);
        dest.writeString(password);
        dest.writeString(devicetoken);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PatientRegisterRequest> CREATOR = new Creator<PatientRegisterRequest>() {
        @Override
        public PatientRegisterRequest createFromParcel(Parcel in) {
            return new PatientRegisterRequest(in);
        }

        @Override
        public PatientRegisterRequest[] newArray(int size) {
            return new PatientRegisterRequest[size];
        }
    };

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

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
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

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
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

    public RequestBody getImage() {
        return image;
    }

    public void setImage(RequestBody image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevicetoken() {
        return devicetoken;
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }
}
