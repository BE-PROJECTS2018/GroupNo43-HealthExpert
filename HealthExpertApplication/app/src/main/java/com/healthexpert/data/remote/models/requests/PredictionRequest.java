package com.healthexpert.data.remote.models.requests;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shivani on 3/2/2018.
 */

public class PredictionRequest implements Parcelable {
    String accessToken, symptoms;
    int parent_status;

    public PredictionRequest(String accessToken, String symptoms, int parent_status) {
        this.accessToken = accessToken;
        this.symptoms = symptoms;
        this.parent_status = parent_status;
    }

    protected PredictionRequest(Parcel in) {
        accessToken = in.readString();
        symptoms = in.readString();
        parent_status = in.readInt();
    }

    public static final Creator<PredictionRequest> CREATOR = new Creator<PredictionRequest>() {
        @Override
        public PredictionRequest createFromParcel(Parcel in) {
            return new PredictionRequest(in);
        }

        @Override
        public PredictionRequest[] newArray(int size) {
            return new PredictionRequest[size];
        }
    };

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public int getParent_status() {
        return parent_status;
    }

    public void setParent_status(int parent_status) {
        this.parent_status = parent_status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(accessToken);
        dest.writeString(symptoms);
        dest.writeInt(parent_status);
    }
}
