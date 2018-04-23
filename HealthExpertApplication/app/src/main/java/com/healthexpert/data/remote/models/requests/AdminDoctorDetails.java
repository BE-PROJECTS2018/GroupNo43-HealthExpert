package com.healthexpert.data.remote.models.requests;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shivani on 2/10/2018.
 */

public class AdminDoctorDetails implements Parcelable{
    String accesstoken;
    String firebaseid;
    int status;

    public AdminDoctorDetails(String accesstoken, String firebaseid, int status) {
        this.accesstoken = accesstoken;
        this.firebaseid = firebaseid;
        this.status = status;
    }

    protected AdminDoctorDetails(Parcel in) {
        accesstoken = in.readString();
        firebaseid = in.readString();
        status = in.readInt();
    }

    public static final Creator<AdminDoctorDetails> CREATOR = new Creator<AdminDoctorDetails>() {
        @Override
        public AdminDoctorDetails createFromParcel(Parcel in) {
            return new AdminDoctorDetails(in);
        }

        @Override
        public AdminDoctorDetails[] newArray(int size) {
            return new AdminDoctorDetails[size];
        }
    };

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public String getFirebaseid() {
        return firebaseid;
    }

    public void setFirebaseid(String firebaseid) {
        this.firebaseid = firebaseid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(accesstoken);
        dest.writeString(firebaseid);
        dest.writeInt(status);
    }
}
