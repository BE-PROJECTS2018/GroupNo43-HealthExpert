package com.healthexpert.data.remote.models.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shivani on 2/10/2018.
 */

public class Speciality implements Parcelable{
    int s_id;
    String s_name;
    String s_description;

    public Speciality(int s_id, String s_name, String s_description) {
        this.s_id = s_id;
        this.s_name = s_name;
        this.s_description = s_description;
    }

    protected Speciality(Parcel in) {
        s_id = in.readInt();
        s_name = in.readString();
        s_description = in.readString();
    }

    public static final Creator<Speciality> CREATOR = new Creator<Speciality>() {
        @Override
        public Speciality createFromParcel(Parcel in) {
            return new Speciality(in);
        }

        @Override
        public Speciality[] newArray(int size) {
            return new Speciality[size];
        }
    };

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_description() {
        return s_description;
    }

    public void setS_description(String s_description) {
        this.s_description = s_description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(s_id);
        dest.writeString(s_name);
        dest.writeString(s_description);
    }
}
