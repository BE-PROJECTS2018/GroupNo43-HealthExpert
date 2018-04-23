package com.healthexpert.doctor.summary;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.healthexpert.R;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.data.local.SharedPreferenceManager;
import com.healthexpert.data.remote.api.DoctorRestService;
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.response.DoctorSummaryResponse;
import com.healthexpert.dispatcher.RetrofitObj;
import com.healthexpert.ui.widgets.BaseTextView;

/**
 * Created by Shivani on 3/5/2018.
 */

public class DoctorSummaryActivity extends BaseActivity implements DoctorSummaryContract.DoctorView {


    BaseTextView tvRating,tvVotes,tvBookmarkCount;
    DoctorSummaryPresenter doctorSummaryPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_summary);
        showProgressDialog();
        initViews();
        DoctorRestService doctorRestService = RetrofitObj.getInstance().create(DoctorRestService.class);
        doctorSummaryPresenter = new DoctorSummaryPresenter(doctorRestService,this);
        doctorSummaryPresenter.fetchSummary(new MyRequest(new SharedPreferenceManager(getApplicationContext()).getAccessToken()));
    }
    private void initViews(){
        tvRating = (BaseTextView) findViewById(R.id.tvRating);
        tvVotes = (BaseTextView) findViewById(R.id.tvVotes);
        tvBookmarkCount = (BaseTextView) findViewById(R.id.tvBookmarkCount);
    }

    @Override
    public void onSummary(DoctorSummaryResponse doctorSummaryResponse) {
        dismissProgressDialog();
        tvRating.setText(doctorSummaryResponse.getScore());
        tvVotes.setText(doctorSummaryResponse.getVotes());
        tvBookmarkCount.setText(doctorSummaryResponse.getBookmarks());

    }
}
