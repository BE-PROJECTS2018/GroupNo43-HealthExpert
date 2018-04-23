package com.healthexpert.data.remote.models.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shivani on 2/9/2018.
 */

public class SymptomResponse implements Parcelable {
    int id;
    String sname;
    boolean check;
    public SymptomResponse(int id, String sname,boolean check) {
        this.id = id;
        this.sname = sname;
        this.check = check;
    }


    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public static Creator<SymptomResponse> getCREATOR() {
        return CREATOR;
    }

    protected SymptomResponse(Parcel in) {
        id = in.readInt();
        sname = in.readString();
    }

    public static final Creator<SymptomResponse> CREATOR = new Creator<SymptomResponse>() {
        @Override
        public SymptomResponse createFromParcel(Parcel in) {
            return new SymptomResponse(in);
        }

        @Override
        public SymptomResponse[] newArray(int size) {
            return new SymptomResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(sname);
    }
}
