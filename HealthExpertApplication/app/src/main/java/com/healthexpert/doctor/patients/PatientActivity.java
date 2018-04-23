package com.healthexpert.doctor.patients;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.healthexpert.R;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.data.local.SharedPreferenceManager;
import com.healthexpert.data.remote.api.DoctorRestService;
import com.healthexpert.data.remote.models.requests.DoctorRequest;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.PatientWrapper;
import com.healthexpert.data.remote.models.response.PredictionResponse;
import com.healthexpert.dispatcher.RetrofitObj;
import com.healthexpert.doctor.adapters.PatientAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shivani on 1/28/2017.
 */

public class PatientActivity extends BaseActivity implements PatientContract.PatientView, PatientAdapter.LikeItemUpdateListener, SearchView.OnQueryTextListener {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rvHome;
    PatientPresenter patientPresenter;
    ProgressBar pgProgress;
    ArrayList<Patient> patients;
    PatientAdapter patientAdapter;

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

        setContentView(R.layout.fragment_patient);
        rvHome = (RecyclerView) findViewById(R.id.rvHome);
        pgProgress = (ProgressBar) findViewById(R.id.pgProgress);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srlHome);
        rvHome.setHasFixedSize(true);
        rvHome.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        DoctorRestService doctorRestService = RetrofitObj.getInstance().create(DoctorRestService.class);
        patientPresenter = new PatientPresenter(doctorRestService, this);
        patientPresenter.fetchPatientData(new DoctorRequest(new SharedPreferenceManager(getApplicationContext()).getAccessToken()));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                patientPresenter.fetchPatientData(new DoctorRequest(new SharedPreferenceManager(getApplicationContext()).getAccessToken()));
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final ArrayList<Patient> filteredModelList = filter(patients, newText);
        patientAdapter.animateTo(filteredModelList);
        rvHome.scrollToPosition(0);
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem item = menu.findItem(R.id.action_search);//Menu Resource, Menu
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    private ArrayList<Patient> filter(List<Patient> models, String query) {
        query = query.toLowerCase();

        final ArrayList<Patient> filteredModelList = new ArrayList<>();
        for (Patient model : models) {
            final String text = model.getName();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }


    @Override
    public void onPatientData(PatientWrapper patientWrapper) {
        patients = new ArrayList<>();
        for (int i = 0; i < patientWrapper.data.size(); i++) {
            Patient patient = new Patient(patientWrapper.data.get(i).getPid(),
                    patientWrapper.data.get(i).getName(),
                    patientWrapper.data.get(i).getDob(),
                    patientWrapper.data.get(i).getGender(),
                    patientWrapper.data.get(i).getHeight(),
                    patientWrapper.data.get(i).getWeight(),
                    patientWrapper.data.get(i).getBloodgroup(),
                    patientWrapper.data.get(i).getPhoneno(),
                    patientWrapper.data.get(i).getOccupation(),
                    patientWrapper.data.get(i).getSymptoms(),
                    patientWrapper.data.get(i).getHistory(),
                    patientWrapper.data.get(i).getInvestigations(),
                    patientWrapper.data.get(i).getCity(),
                    patientWrapper.data.get(i).getPincode(),
                    patientWrapper.data.get(i).getMothername(),
                    patientWrapper.data.get(i).getMothersymptoms(),
                    patientWrapper.data.get(i).getFathername(),
                    patientWrapper.data.get(i).getFathersymptoms(),
                    patientWrapper.data.get(i).getPhoto(),
                    patientWrapper.data.get(i).getAccesstoken(),
                    patientWrapper.data.get(i).getDevicetoken());
            patients.add(patient);
        }
        patientAdapter = new PatientAdapter(patients, PatientActivity.this);
        rvHome.setAdapter(patientAdapter);
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
        pgProgress.setVisibility(View.GONE);
    }

    @Override
    public void onPredict(PredictionResponse predictionResponse) {

    }


    @Override
    public void onItemCardClicked(Patient patient) {
        Intent i = new Intent(PatientActivity.this, PatientSymptomActivity.class);
        i.putExtra("patient", patient);
        startActivity(i);
    }
}