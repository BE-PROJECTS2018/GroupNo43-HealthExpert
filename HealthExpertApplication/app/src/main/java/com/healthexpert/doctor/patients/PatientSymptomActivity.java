package com.healthexpert.doctor.patients;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.healthexpert.R;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.data.local.SharedPreferenceManager;
import com.healthexpert.data.remote.api.DoctorRestService;
import com.healthexpert.data.remote.api.PatientRestService;
import com.healthexpert.data.remote.api.UserRestService;
import com.healthexpert.data.remote.models.requests.BookmarkRequest;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.SpecialityWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.dispatcher.RetrofitObj;
import com.healthexpert.doctor.activities.PredictionActivity;
import com.healthexpert.doctor.activities.PrescriptionActivity;
import com.healthexpert.patient.doctors.DoctorContract;
import com.healthexpert.patient.doctors.DoctorPresenter;
import com.healthexpert.patient.summary.PatientSummaryActivity;
import com.healthexpert.patient.symptom.SymptomPatientPresenter;
import com.healthexpert.ui.widgets.BaseButton;
import com.healthexpert.ui.widgets.BaseCheckbox;
import com.healthexpert.ui.widgets.BaseEditText;
import com.healthexpert.ui.widgets.BaseTextView;


/**
 * Created by Shivani on 2/8/2018.
 */

public class PatientSymptomActivity extends BaseActivity implements DoctorContract.DoctorView {
    Patient patient;
    BaseTextView tvFullName, tvAddress, tvDob, tvHeight, tvWeight, tvPhoneno, tvHistory, tvOccupation, tvInvestigations, tvMname, tvMSymptoms, tvFname, tvFSymptoms, tvBloodGroup;
    DoctorPresenter doctorPresenter;
    CoordinatorLayout clLayout;
    FloatingActionButton fabBookmark, fabPredict, fabPrescription, fabSummary;


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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_patient_symptoms);
        patient = getIntent().getParcelableExtra("patient");
        initViews();
        PatientRestService patientRestService = RetrofitObj.getInstance().create(PatientRestService.class);
        doctorPresenter = new DoctorPresenter(patientRestService, this);
        fabBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog();
                if (fabBookmark.getTag().equals("1"))
                    doctorPresenter.bookmarkDoctor(new BookmarkRequest(new SharedPreferenceManager(getApplicationContext()).getAccessToken(), patient.getAccesstoken(),
                            2,
                            3));
                else
                    doctorPresenter.bookmarkDoctor(new BookmarkRequest(new SharedPreferenceManager(getApplicationContext()).getAccessToken(), patient.getAccesstoken(),
                            1,
                            3));
            }
        });
        setViews();
        doctorPresenter.bookmarkCheck(new BookmarkRequest(new SharedPreferenceManager(getApplicationContext()).getAccessToken(), patient.getAccesstoken(), 2, 2));
    }

    private void setViews() {
        tvFullName.setText(patient.getName());
        tvAddress.setText(patient.getCity() + " - " + patient.getPincode());
        tvHeight.setText(patient.getHeight());
        tvWeight.setText(patient.getWeight());
        tvPhoneno.setText(patient.getPhoneno());
        tvBloodGroup.setText(patient.getBloodgroup());
        tvHistory.setText(patient.getHistory());
        tvInvestigations.setText(patient.getInvestigations());
        tvOccupation.setText(patient.getOccupation());
        tvMname.setText(patient.getMothername());
        tvMSymptoms.setText(patient.getMothersymptoms());
        tvFname.setText(patient.getFathername());
        tvFSymptoms.setText(patient.getFathersymptoms());
        tvDob.setText(patient.getDob());
        fabPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PatientSymptomActivity.this, PredictionActivity.class);
                i.putExtra("patient", patient);
                startActivity(i);
            }
        });
        fabPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PatientSymptomActivity.this, PrescriptionActivity.class);
                i.putExtra("patient", patient);
                startActivity(i);
            }
        });
        fabSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PatientSymptomActivity.this, PatientSummaryActivity.class);
                i.putExtra("accesstoken", patient.getAccesstoken());
                i.putExtra("title", patient.getName());
                startActivity(i);
            }
        });
    }


    private void initViews() {
        tvFullName = (BaseTextView) findViewById(R.id.tvFullName);
        tvAddress = (BaseTextView) findViewById(R.id.tvAddress);
        tvHeight = (BaseTextView) findViewById(R.id.tvHeight);
        tvWeight = (BaseTextView) findViewById(R.id.tvWeight);
        tvOccupation = (BaseTextView) findViewById(R.id.tvOccupation);
        tvPhoneno = (BaseTextView) findViewById(R.id.tvPhoneno);
        tvHistory = (BaseTextView) findViewById(R.id.tvHistory);
        tvBloodGroup = (BaseTextView) findViewById(R.id.tvBloodGroup);
        tvInvestigations = (BaseTextView) findViewById(R.id.tvInvestigations);
        tvMname = (BaseTextView) findViewById(R.id.tvMname);
        tvMSymptoms = (BaseTextView) findViewById(R.id.tvMSymptoms);
        tvFname = (BaseTextView) findViewById(R.id.tvFname);
        tvDob = (BaseTextView) findViewById(R.id.tvDob);
        tvFSymptoms = (BaseTextView) findViewById(R.id.tvFSymptoms);
        fabBookmark = (FloatingActionButton) findViewById(R.id.fabBookmark);
        clLayout = (CoordinatorLayout) findViewById(R.id.clLayout);
        fabPredict = (FloatingActionButton) findViewById(R.id.fabPredict);
        fabPrescription = (FloatingActionButton) findViewById(R.id.fabPrescription);
        fabSummary = (FloatingActionButton) findViewById(R.id.fabSummary);


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

    }

    @Override
    public void onBookmarkDoctor(UserRegisterResponse userRegisterResponse) {
        dismissProgressDialog();
        if (userRegisterResponse.isStatus()) {
            fabBookmark.setImageResource(R.drawable.star_fill);
            fabBookmark.setTag("1");

        } else {
            fabBookmark.setImageResource(R.drawable.star_outline);
            fabBookmark.setTag("2");
        }
        Snackbar.make(clLayout, userRegisterResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckBookmark(UserRegisterResponse userRegisterResponse) {
        dismissProgressDialog();
        if (userRegisterResponse.isStatus()) {
            fabBookmark.setImageResource(R.drawable.star_fill);
            fabBookmark.setTag("1");

        } else {
            fabBookmark.setImageResource(R.drawable.star_outline);
            fabBookmark.setTag("2");
        }
    }
}
