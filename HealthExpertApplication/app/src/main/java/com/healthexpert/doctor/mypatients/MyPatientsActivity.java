package com.healthexpert.doctor.mypatients;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
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
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.response.DoctorResponse;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.PatientWrapper;
import com.healthexpert.dispatcher.RetrofitObj;
import com.healthexpert.doctor.adapters.DoctorAdapter;
import com.healthexpert.doctor.doctors.DoctorDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shivani on 1/28/2017.
 */

public class MyPatientsActivity extends BaseActivity implements MyPatientsContract.MyPatientsView, MyPatientsAdapter.LikeItemUpdateListener, SearchView.OnQueryTextListener {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rvHome;
    ProgressBar pgProgress;
    MyPatientsPresenter myPatientsPresenter;
    MyPatientsAdapter myPatientsAdapter;
    ArrayList<Patient> patients;

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
        setContentView(R.layout.fragment_home);
        initViews();

        rvHome.setHasFixedSize(true);
        rvHome.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        DoctorRestService doctorRestService = RetrofitObj.getInstance().create(DoctorRestService.class);
        myPatientsPresenter = new MyPatientsPresenter(doctorRestService, this);
        myPatientsPresenter.getPatients(new MyRequest(new SharedPreferenceManager(getApplicationContext()).getAccessToken()));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myPatientsPresenter.getPatients(new MyRequest(new SharedPreferenceManager(getApplicationContext()).getAccessToken()));
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
        myPatientsAdapter.animateTo(filteredModelList);
        rvHome.scrollToPosition(0);
        return false;
    }

    private ArrayList<Patient> filter(List<Patient> models, String query) {
        query = query.toLowerCase();

        final ArrayList<Patient> filteredModelList = new ArrayList<>();
        for (Patient model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
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
        rvHome = (RecyclerView) findViewById(R.id.rvHome);
        pgProgress = (ProgressBar) findViewById(R.id.pgProgress);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srlHome);
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

    @Override
    public void onItemCardClicked(Patient patient) {
        Intent i = new Intent(MyPatientsActivity.this, PatientDetailsActivity.class);
        i.putExtra("patient", patient);
        startActivity(i);
    }


    @Override
    public void onPatientsData(PatientWrapper myPatientsWrapper) {
        patients = new ArrayList<>();
        for (int i = 0; i < myPatientsWrapper.data.size(); i++) {
            Patient patient = new Patient(myPatientsWrapper.data.get(i).getPid(),
                    myPatientsWrapper.data.get(i).getName(),
                    myPatientsWrapper.data.get(i).getDob(),
                    myPatientsWrapper.data.get(i).getGender(),
                    myPatientsWrapper.data.get(i).getHeight(),
                    myPatientsWrapper.data.get(i).getWeight(),
                    myPatientsWrapper.data.get(i).getBloodgroup(),
                    myPatientsWrapper.data.get(i).getPhoneno(),
                    myPatientsWrapper.data.get(i).getOccupation(),
                    myPatientsWrapper.data.get(i).getSymptoms(),
                    myPatientsWrapper.data.get(i).getHistory(),
                    myPatientsWrapper.data.get(i).getInvestigations(),
                    myPatientsWrapper.data.get(i).getCity(),
                    myPatientsWrapper.data.get(i).getPincode(),
                    myPatientsWrapper.data.get(i).getMothername(),
                    myPatientsWrapper.data.get(i).getMothersymptoms(),
                    myPatientsWrapper.data.get(i).getFathername(),
                    myPatientsWrapper.data.get(i).getFathersymptoms(),
                    myPatientsWrapper.data.get(i).getPhoto(),
                    myPatientsWrapper.data.get(i).getAccesstoken(),
                    myPatientsWrapper.data.get(i).getDevicetoken());
            patients.add(patient);

        }
        myPatientsAdapter = new MyPatientsAdapter(patients, this);
        rvHome.setAdapter(myPatientsAdapter);
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
        pgProgress.setVisibility(View.GONE);
    }

}