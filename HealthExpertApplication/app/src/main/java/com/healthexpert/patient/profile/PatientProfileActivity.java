package com.healthexpert.patient.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.healthexpert.R;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.common.Config;
import com.healthexpert.data.local.SharedPreferenceManager;
import com.healthexpert.data.remote.api.PatientRestService;
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.PatientWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.dispatcher.RetrofitObj;
import com.healthexpert.ui.widgets.BaseTextView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Shivani on 3/11/2018.
 */

public class PatientProfileActivity extends BaseActivity implements PatientProfileContract.PatientView {

    CircleImageView ivProfile;
    BaseTextView tvFullName, tvAddress, tvDob, tvHeight, tvWeight, tvPhoneno, tvHistory, tvOccupation, tvInvestigations, tvMname, tvMSymptoms, tvFname, tvFSymptoms, tvBloodGroup;
    PatientProfilePresenter patientProfilePresenter;
    FloatingActionButton fabEdit;
    Patient patient;

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
        setContentView(R.layout.activity_patient_profile);
        initViews();
        PatientRestService patientRestService = RetrofitObj.getInstance().create(PatientRestService.class);
        showProgressDialog();
        patientProfilePresenter = new PatientProfilePresenter(patientRestService, this);
        patientProfilePresenter.getPatient(new MyRequest(new SharedPreferenceManager(getApplicationContext()).getAccessToken()));
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PatientProfileActivity.this, PatientEditProfileActivity.class);
                i.putExtra("patient", patient);
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
        ivProfile = (CircleImageView) findViewById(R.id.ivProfile);
        fabEdit = (FloatingActionButton) findViewById(R.id.fabEdit);
    }

    @Override
    public void onNetworkException(Throwable e) {

    }

    @Override
    public void onPatient(PatientWrapper patientWrapper) {
        dismissProgressDialog();
        patient = patientWrapper.data.get(0);
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
        new SharedPreferenceManager(getApplicationContext()).saveImage(patient.getPhoto());
        new SharedPreferenceManager(getApplicationContext()).saveName(patient.getName());
        if (!patient.getPhoto().isEmpty())
            Picasso.with(getApplicationContext()).load(Config.BASE_URL + patient.getPhoto()).fit().into(ivProfile);

    }

    @Override
    public void onUpdate(UserRegisterResponse userRegisterResponse) {

    }
}
