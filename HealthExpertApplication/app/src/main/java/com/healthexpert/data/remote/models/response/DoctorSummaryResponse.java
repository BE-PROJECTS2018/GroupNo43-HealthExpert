package com.healthexpert.data.remote.models.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shivani on 3/5/2018.
 */

public class DoctorSummaryResponse implements Parcelable {
    String score,votes,bookmarks;

    public DoctorSummaryResponse(String score, String votes, String bookmarks) {
        this.score = score;
        this.votes = votes;
        this.bookmarks = bookmarks;
    }

    protected DoctorSummaryResponse(Parcel in) {
        score = in.readString();
        votes = in.readString();
        bookmarks = in.readString();
    }

    public static final Creator<DoctorSummaryResponse> CREATOR = new Creator<DoctorSummaryResponse>() {
        @Override
        public DoctorSummaryResponse createFromParcel(Parcel in) {
            return new DoctorSummaryResponse(in);
        }

        @Override
        public DoctorSummaryResponse[] newArray(int size) {
            return new DoctorSummaryResponse[size];
        }
    };

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(String bookmarks) {
        this.bookmarks = bookmarks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(score);
        parcel.writeString(votes);
        parcel.writeString(bookmarks);
    }
}
