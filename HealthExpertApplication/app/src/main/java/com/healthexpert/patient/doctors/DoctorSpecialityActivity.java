package com.healthexpert.patient.doctors;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.healthexpert.R;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.data.remote.api.PatientRestService;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.Speciality;
import com.healthexpert.data.remote.models.response.SpecialityWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.dispatcher.RetrofitObj;
import com.healthexpert.patient.adapters.DoctorSpecialityAdapter;
import com.healthexpert.ui.widgets.BaseEditText;

import java.util.ArrayList;

/**
 * Created by Shivani on 1/28/2017.
 */

public class DoctorSpecialityActivity extends BaseActivity implements DoctorContract.DoctorView, DoctorSpecialityAdapter.LikeItemUpdateListener {

    RecyclerView rvSpeciality;
    ProgressBar pgProgress;
    DoctorPresenter doctorPresenter;
    BaseEditText etSpeciality;
    ArrayList<Speciality> specialities;
    DoctorSpecialityAdapter specialityAdapter;

    @Override
    public void onNetworkException(Throwable e) {
        super.onNetworkException(e);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_speciality);
        initViews();
        rvSpeciality.setHasFixedSize(true);
        rvSpeciality.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        PatientRestService patientRestService = RetrofitObj.getInstance().create(PatientRestService.class);
        doctorPresenter = new DoctorPresenter(patientRestService, this);
        doctorPresenter.specialityDoctors();


        etSpeciality.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    private void filter(String text) {
        ArrayList<Speciality> filterdNames = new ArrayList<>();
        for (int i = 0; i < specialities.size(); i++) {
            if (specialities.get(i).getS_name().toLowerCase().contains(text.toLowerCase())) {
                filterdNames.add(specialities.get(i));
            }
        }

        //calling a method of the adapter class and passing the filtered list
        specialityAdapter.filterList(filterdNames);
    }

//    private void showFilter() {
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DoctorActivity.this);
//        dialogBuilder.setTitle("Filter By");
//        dialogBuilder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String symptoms = "";
//                symptomResponses = patientAdapter.getSymptoms();
//                for (int i = 0; i < symptomResponses.size(); i++) {
//                    if (symptomResponses.get(i).isCheck()) {
//                        symptoms += symptomResponses.get(i).getSname() + ",";
//                        symptomsId += symptomResponses.get(i).getId() + ",";
//                    }
//                }
//                symptoms = symptoms.substring(0, symptoms.length() - 1);
//                symptomsId = symptomsId.substring(0, symptomsId.length() - 1);
//                etSymptoms.setText(symptoms);
//                dialog.dismiss();
//            }
//        });
//        dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        LayoutInflater inflater = this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_symptoms, null);
//        dialogBuilder.setView(dialogView);
//        BaseEditText etSSearch = (BaseEditText) dialogView.findViewById(R.id.etSSymptom);
//        rvSymptoms = (RecyclerView) dialogView.findViewById(R.id.rvSymptoms);
//        rvSymptoms.setHasFixedSize(true);
//        rvSymptoms.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//        AlertDialog dialog = dialogBuilder.create();
//        dialog.show();
//        symptomPatientPresenter.symptomPatient();
//        etSSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                filter(s.toString());
//            }
//        });
//
//    }


    private void initViews() {
        rvSpeciality = (RecyclerView) findViewById(R.id.rvSpeciality);
        pgProgress = (ProgressBar) findViewById(R.id.pgProgress);
        etSpeciality = (BaseEditText) findViewById(R.id.etSpeciality);
    }


    @Override
    public void onItemCardClicked(Speciality speciality) {
        Intent i = new Intent(DoctorSpecialityActivity.this, DoctorActivity.class);
        i.putExtra("speciality", speciality);
        startActivity(i);
    }

    @Override
    public void onInfoClicked(Speciality speciality) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(DoctorSpecialityActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(DoctorSpecialityActivity.this);
        }
        builder.setTitle(speciality.getS_name())
                .setMessage(speciality.getS_description())
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        dialog.dismiss();
                    }
                })
                .setCancelable(true)
                .setIcon(R.drawable.info)
                .show();
    }


    @Override
    public void onDoctorData(DoctorResponseWrapper doctorResponseWrapper) {

    }

    @Override
    public void onSpeciality(SpecialityWrapper specialityWrapper) {
        specialities = new ArrayList<>();
        for (int i = 0; i < specialityWrapper.data.size(); i++) {
            Speciality speciality = new Speciality(specialityWrapper.data.get(i).getS_id()
                    , specialityWrapper.data.get(i).getS_name()
                    , specialityWrapper.data.get(i).getS_description());
            specialities.add(speciality);
        }
        specialityAdapter = new DoctorSpecialityAdapter(specialities, this);
        rvSpeciality.setAdapter(specialityAdapter);
        pgProgress.setVisibility(View.GONE);
    }

    @Override
    public void onCheckDoctorFeedback(UserRegisterResponse userRegisterResponse) {

    }

    @Override
    public void onFeedback(UserRegisterResponse userRegisterResponse) {

    }

    @Override
    public void onBookmarkDoctor(UserRegisterResponse userRegisterResponse) {

    }

    @Override
    public void onCheckBookmark(UserRegisterResponse userRegisterResponse) {

    }
}