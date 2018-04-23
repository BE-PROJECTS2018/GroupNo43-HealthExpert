package com.healthexpert.data.remote.models.requests;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.jar.Pack200;

/**
 * Created by Shivani on 3/5/2018.
 */

public class MyRequest implements Parcelable {
    String accesstoken;

    public MyRequest(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    protected MyRequest(Parcel in) {
        accesstoken = in.readString();
    }

    public static final Creator<MyRequest> CREATOR = new Creator<MyRequest>() {
        @Override
        public MyRequest createFromParcel(Parcel in) {
            return new MyRequest(in);
        }

        @Override
        public MyRequest[] newArray(int size) {
            return new MyRequest[size];
        }
    };

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(accesstoken);
    }
}
