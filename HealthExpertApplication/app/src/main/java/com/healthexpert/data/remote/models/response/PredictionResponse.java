package com.healthexpert.data.remote.models.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shivani on 3/2/2018.
 */

public class PredictionResponse implements Parcelable {
    String result;

    public PredictionResponse(String result) {
        this.result = result;
    }

    protected PredictionResponse(Parcel in) {
        result = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(result);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PredictionResponse> CREATOR = new Creator<PredictionResponse>() {
        @Override
        public PredictionResponse createFromParcel(Parcel in) {
            return new PredictionResponse(in);
        }

        @Override
        public PredictionResponse[] newArray(int size) {
            return new PredictionResponse[size];
        }
    };

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
