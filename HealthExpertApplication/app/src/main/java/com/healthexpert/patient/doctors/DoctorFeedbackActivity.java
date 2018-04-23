package com.healthexpert.patient.doctors;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.healthexpert.R;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.common.Config;
import com.healthexpert.data.local.SharedPreferenceManager;
import com.healthexpert.data.remote.api.PatientRestService;
import com.healthexpert.data.remote.models.requests.DoctorFeedback;
import com.healthexpert.data.remote.models.response.DoctorResponse;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.SpecialityWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.dispatcher.RetrofitObj;
import com.healthexpert.ui.widgets.BaseButton;
import com.healthexpert.ui.widgets.BaseTextView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Shivani on 3/4/2018.
 */

public class DoctorFeedbackActivity extends BaseActivity implements DoctorContract.DoctorView {

    CoordinatorLayout clFeedback;
    CircleImageView ivImage;
    BaseTextView tvName, tvSpeciality;
    ToggleButton tbYes, tbNo;
    RatingBar rbRating;
    BaseButton bSubmit;
    DoctorPresenter doctorPresenter;
    DoctorResponse doctorResponse;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_doctor_feedback);
        initViews();
        doctorResponse = getIntent().getParcelableExtra("doctor");
        tvName.setText(doctorResponse.getName());
        tvSpeciality.setText(doctorResponse.getSpeciality());
        tbYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tbNo.setChecked(false);
                } else {
                    tbNo.setChecked(true);
                }
                tbYes.setTextOff(getString(R.string.yes));
                tbYes.setTextOn(getString(R.string.yes));
            }
        });
        tbNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tbYes.setChecked(false);
                } else {
                    tbYes.setChecked(true);
                }
            }
        });
        PatientRestService patientRestService = RetrofitObj.getInstance().create(PatientRestService.class);
        doctorPresenter = new DoctorPresenter(patientRestService, this);
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status = validate();
                if (status) {
                    int vote = 0;
                    if (tbYes.isChecked())
                        vote = 1;
                    else if (tbNo.isChecked())
                        vote = 0;
                    doctorPresenter.doctorFeedback(new DoctorFeedback(doctorResponse.getAccesstoken(), new SharedPreferenceManager(getApplicationContext()).getAccessToken(), (int) rbRating.getRating(), vote));
                } else {
                    Snackbar.make(clFeedback, "Please cast your vote for this doctor", Snackbar.LENGTH_SHORT).show();
                }

            }
        });
        if(!doctorResponse.getPhoto().isEmpty())
            Picasso.with(getApplicationContext()).load(Config.BASE_URL+doctorResponse.getPhoto()).fit().into(ivImage);
    }

    private void initViews() {
        clFeedback = (CoordinatorLayout) findViewById(R.id.clFeedback);
        ivImage = (CircleImageView) findViewById(R.id.ivImage);
        tvName = (BaseTextView) findViewById(R.id.tvName);
        tvSpeciality = (BaseTextView) findViewById(R.id.tvSpeciality);
        tbYes = (ToggleButton) findViewById(R.id.tbYes);
        tbNo = (ToggleButton) findViewById(R.id.tbNo);
        rbRating = (RatingBar) findViewById(R.id.rbRating);
        bSubmit = (BaseButton) findViewById(R.id.bSubmit);

        tbYes.setChecked(false);
        tbNo.setChecked(false);

    }

    private boolean validate() {
        if (!tbYes.isChecked() && !tbNo.isChecked())
            return false;
        return true;
    }

    @Override
    public void onDoctorData(DoctorResponseWrapper doctorResponseWrapper) {

    }

    @Override
    public void onSpeciality(SpecialityWrapper specialityWrapper) {

    }

    @Override
    public void onCheckDoctorFeedback(UserRegisterResponse userRegisterResponse) {

    }

    @Override
    public void onFeedback(UserRegisterResponse userRegisterResponse) {
        if (userRegisterResponse.isStatus()) {
            Toast.makeText(getApplicationContext(), userRegisterResponse.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onBookmarkDoctor(UserRegisterResponse userRegisterResponse) {

    }

    @Override
    public void onCheckBookmark(UserRegisterResponse userRegisterResponse) {

    }
}
