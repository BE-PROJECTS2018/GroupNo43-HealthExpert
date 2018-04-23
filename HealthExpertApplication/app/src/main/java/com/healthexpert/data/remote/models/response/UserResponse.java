package com.healthexpert.data.remote.models.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shivani on 1/13/2017.
 */

public class UserResponse implements Parcelable {
    boolean status;
    String message;
    String accessToken;
    int role;

    public UserResponse(boolean status, String message, String accessToken, int role) {
        this.status = status;
        this.message = message;
        this.accessToken = accessToken;
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public int getCategory() {
        return role;
    }

    public void setCategory(int category) {
        this.role = category;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean getSuccess() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    protected UserResponse(Parcel in) {
    }

    public static final Creator<UserResponse> CREATOR = new Creator<UserResponse>() {
        @Override
        public UserResponse createFromParcel(Parcel in) {
            return new UserResponse(in);
        }

        @Override
        public UserResponse[] newArray(int size) {
            return new UserResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
