package com.healthexpert.doctor.mypatients;

import android.content.DialogInterface;
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

public class PatientDetailsActivity extends BaseActivity implements DoctorContract.DoctorView {
    Patient patient;
    BaseTextView tvFullName, tvAddress, tvDob, tvHeight, tvWeight, tvPhoneno, tvHistory, tvOccupation, tvInvestigations, tvMname, tvMSymptoms, tvFname, tvFSymptoms, tvBloodGroup;
    CircleImageView ivImage;
    DoctorPresenter doctorPresenter;
    CoordinatorLayout clLayout;
    FloatingActionButton fabBookmark;

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

        setContentView(R.layout.activity_patient_details);
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
        showProgressDialog();
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
        if(!patient.getPhoto().isEmpty())
            Picasso.with(getApplicationContext()).load(Config.BASE_URL+patient.getPhoto()).fit().into(ivImage);
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
        ivImage = (CircleImageView) findViewById(R.id.ivProfile);
        clLayout = (CoordinatorLayout) findViewById(R.id.clLayout);


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
        Toast.makeText(getApplicationContext(), userRegisterResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
