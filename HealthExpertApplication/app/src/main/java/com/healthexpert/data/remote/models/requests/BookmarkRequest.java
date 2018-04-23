package com.healthexpert.data.remote.models.requests;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shivani on 3/5/2018.
 */

public class BookmarkRequest implements Parcelable {
    String source_token;
    String destination_token;
    int status;
    int role;

    public BookmarkRequest(String source_token, String destination_token,int status, int role) {
        this.source_token = source_token;
        this.destination_token = destination_token;
        this.status = status;
        this.role = role;
    }

    protected BookmarkRequest(Parcel in) {
        source_token = in.readString();
        destination_token = in.readString();
        status = in.readInt();
        role = in.readInt();
    }

    public static final Creator<BookmarkRequest> CREATOR = new Creator<BookmarkRequest>() {
        @Override
        public BookmarkRequest createFromParcel(Parcel in) {
            return new BookmarkRequest(in);
        }

        @Override
        public BookmarkRequest[] newArray(int size) {
            return new BookmarkRequest[size];
        }
    };

    public String getSource_token() {
        return source_token;
    }

    public void setSource_token(String source_token) {
        this.source_token = source_token;
    }

    public String getDestination_token() {
        return destination_token;
    }

    public void setDestination_token(String destination_token) {
        this.destination_token = destination_token;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static Creator<BookmarkRequest> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(source_token);
        parcel.writeString(destination_token);
        parcel.writeInt(status);
        parcel.writeInt(role);
    }
}
