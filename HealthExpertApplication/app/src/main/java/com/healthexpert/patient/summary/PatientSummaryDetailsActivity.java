package com.healthexpert.patient.summary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.healthexpert.R;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.common.Config;
import com.healthexpert.data.local.SharedPreferenceManager;
import com.healthexpert.data.remote.api.PatientRestService;
import com.healthexpert.data.remote.models.requests.BookmarkRequest;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.PatientSummaryResponse;
import com.healthexpert.data.remote.models.response.SpecialityWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.dispatcher.RetrofitObj;
import com.healthexpert.patient.doctors.DoctorContract;
import com.healthexpert.patient.doctors.DoctorPresenter;
import com.healthexpert.ui.widgets.BaseTextView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Shivani on 2/8/2018.
 */

public class PatientSummaryDetailsActivity extends BaseActivity  {
    PatientSummaryResponse patient;
    BaseTextView tvName, tvSpeciality, tvTime, tvPTitle, tvPrescription;
    CircleImageView ivImage;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        patient = getIntent().getParcelableExtra("psummary");
        String title = getIntent().getStringExtra("title");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);
        setContentView(R.layout.activity_patient_summary);

        initViews();
        setViews();

    }

    private void setViews() {
        tvName.setText(patient.getDname());
        tvSpeciality.setText(patient.getDspeciality());
        tvPTitle.setText(patient.getPtitle());
        tvPrescription.setText(patient.getPprescription());
        tvTime.setText(patient.getPtimestamp());
        if(!patient.getDphoto().isEmpty())
            Picasso.with(getApplicationContext()).load(Config.BASE_URL+patient.getDphoto()).fit().into(ivImage);
    }


    private void initViews() {
        tvName = (BaseTextView) findViewById(R.id.tvName);
        tvSpeciality = (BaseTextView) findViewById(R.id.tvSpeciality);
        tvPTitle = (BaseTextView) findViewById(R.id.tvPTitle);
        tvTime = (BaseTextView) findViewById(R.id.tvTime);
        tvPrescription = (BaseTextView) findViewById(R.id.tvPrescription);
        ivImage = (CircleImageView) findViewById(R.id.ivImage);
    }

}
