package com.healthexpert.data.remote.models.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shivani on 3/6/2018.
 */

public class PatientSummaryResponse implements Parcelable{
    int pid;
    String dname;
    String dspeciality;
    String dphoto;
    String ptitle,pprescription,ptimestamp,paccesstoken;
    String daccesstoken;

    public PatientSummaryResponse(int pid, String dname, String dspeciality, String dphoto, String ptitle, String pprescription, String ptimestamp, String paccesstoken, String daccesstoken) {
        this.pid = pid;
        this.dname = dname;
        this.dspeciality = dspeciality;
        this.dphoto = dphoto;
        this.ptitle = ptitle;
        this.pprescription = pprescription;
        this.ptimestamp = ptimestamp;
        this.paccesstoken = paccesstoken;
        this.daccesstoken = daccesstoken;
    }

    protected PatientSummaryResponse(Parcel in) {
        pid = in.readInt();
        dname = in.readString();
        dspeciality = in.readString();
        dphoto = in.readString();
        ptitle = in.readString();
        pprescription = in.readString();
        ptimestamp = in.readString();
        paccesstoken = in.readString();
        daccesstoken = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pid);
        dest.writeString(dname);
        dest.writeString(dspeciality);
        dest.writeString(dphoto);
        dest.writeString(ptitle);
        dest.writeString(pprescription);
        dest.writeString(ptimestamp);
        dest.writeString(paccesstoken);
        dest.writeString(daccesstoken);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PatientSummaryResponse> CREATOR = new Creator<PatientSummaryResponse>() {
        @Override
        public PatientSummaryResponse createFromParcel(Parcel in) {
            return new PatientSummaryResponse(in);
        }

        @Override
        public PatientSummaryResponse[] newArray(int size) {
            return new PatientSummaryResponse[size];
        }
    };

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDspeciality() {
        return dspeciality;
    }

    public void setDspeciality(String dspeciality) {
        this.dspeciality = dspeciality;
    }

    public String getDphoto() {
        return dphoto;
    }

    public void setDphoto(String dphoto) {
        this.dphoto = dphoto;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPprescription() {
        return pprescription;
    }

    public void setPprescription(String pprescription) {
        this.pprescription = pprescription;
    }

    public String getPtimestamp() {
        return ptimestamp;
    }

    public void setPtimestamp(String ptimestamp) {
        this.ptimestamp = ptimestamp;
    }

    public String getPaccesstoken() {
        return paccesstoken;
    }

    public void setPaccesstoken(String paccesstoken) {
        this.paccesstoken = paccesstoken;
    }

    public String getDaccesstoken() {
        return daccesstoken;
    }

    public void setDaccesstoken(String daccesstoken) {
        this.daccesstoken = daccesstoken;
    }
}
