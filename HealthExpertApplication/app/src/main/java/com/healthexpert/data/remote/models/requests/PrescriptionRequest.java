package com.healthexpert.data.remote.models.requests;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shivani on 3/6/2018.
 */

public class PrescriptionRequest implements Parcelable {
    String p_paccesstoken;
    String p_daccesstoken;
    String p_title;
    String p_prescription;

    public PrescriptionRequest(String p_paccesstoken, String p_daccesstoken, String p_title, String p_prescription) {
        this.p_paccesstoken = p_paccesstoken;
        this.p_daccesstoken = p_daccesstoken;
        this.p_title = p_title;
        this.p_prescription = p_prescription;
    }

    protected PrescriptionRequest(Parcel in) {
        p_paccesstoken = in.readString();
        p_daccesstoken = in.readString();
        p_title = in.readString();
        p_prescription = in.readString();
    }

    public static final Creator<PrescriptionRequest> CREATOR = new Creator<PrescriptionRequest>() {
        @Override
        public PrescriptionRequest createFromParcel(Parcel in) {
            return new PrescriptionRequest(in);
        }

        @Override
        public PrescriptionRequest[] newArray(int size) {
            return new PrescriptionRequest[size];
        }
    };

    public String getP_paccesstoken() {
        return p_paccesstoken;
    }

    public void setP_paccesstoken(String p_paccesstoken) {
        this.p_paccesstoken = p_paccesstoken;
    }

    public String getP_daccesstoken() {
        return p_daccesstoken;
    }

    public void setP_daccesstoken(String p_daccesstoken) {
        this.p_daccesstoken = p_daccesstoken;
    }

    public String getP_title() {
        return p_title;
    }

    public void setP_title(String p_title) {
        this.p_title = p_title;
    }

    public String getP_prescription() {
        return p_prescription;
    }

    public void setP_prescription(String p_prescription) {
        this.p_prescription = p_prescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(p_paccesstoken);
        parcel.writeString(p_daccesstoken);
        parcel.writeString(p_title);
        parcel.writeString(p_prescription);
    }
}
