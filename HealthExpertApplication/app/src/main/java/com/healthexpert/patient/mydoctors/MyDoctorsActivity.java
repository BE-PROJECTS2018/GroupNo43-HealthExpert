package com.healthexpert.patient.mydoctors;

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
import com.healthexpert.data.remote.api.PatientRestService;
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.response.DoctorResponse;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.DoctorWrapper;
import com.healthexpert.data.remote.models.response.Speciality;
import com.healthexpert.data.remote.models.response.SpecialityWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.dispatcher.RetrofitObj;
import com.healthexpert.patient.adapters.DoctorAdapter;
import com.healthexpert.patient.doctors.DoctorContract;
import com.healthexpert.patient.doctors.DoctorDetailsActivity;
import com.healthexpert.patient.doctors.DoctorPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shivani on 1/28/2017.
 */

public class MyDoctorsActivity extends BaseActivity implements MyDoctorsContract.MyDoctorsView, MyDoctorsAdapter.LikeItemUpdateListener, SearchView.OnQueryTextListener {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rvHome;
    ProgressBar pgProgress;
    MyDoctorsPresenter doctorPresenter;
    MyDoctorsAdapter doctorAdapter;
    ArrayList<DoctorResponse> doctors;

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
        setContentView(R.layout.fragment_doctor);
        initViews();
        PatientRestService patientRestService = RetrofitObj.getInstance().create(PatientRestService.class);
        doctorPresenter = new MyDoctorsPresenter(patientRestService, this);
        doctorPresenter.getDoctors(new MyRequest(new SharedPreferenceManager(getApplicationContext()).getAccessToken()));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doctorPresenter.getDoctors(new MyRequest(new SharedPreferenceManager(getApplicationContext()).getAccessToken()));
            }
        });



    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final ArrayList<DoctorResponse> filteredModelList = filter(doctors, newText);
        doctorAdapter.animateTo(filteredModelList);
        rvHome.scrollToPosition(0);
        return false;
    }

    private ArrayList<DoctorResponse> filter(List<DoctorResponse> models, String query) {
        query = query.toLowerCase();

        final ArrayList<DoctorResponse> filteredModelList = new ArrayList<>();
        for (DoctorResponse model : models) {
            final String text = model.getCity();
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
        rvHome.setHasFixedSize(true);
        rvHome.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

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
    public void onItemCardClicked(DoctorResponse doctorResponse) {
        Intent i = new Intent(MyDoctorsActivity.this, DoctorDetailsActivity.class);
        i.putExtra("doctor", doctorResponse);
        startActivity(i);
    }

    @Override
    public void onCallClicked(DoctorResponse doctorResponse) {
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse(doctorResponse.getPhoneno()));
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(i);
    }


    @Override
    public void onDoctorsData(DoctorResponseWrapper doctorResponseWrapper) {
        doctors = new ArrayList<>();
        for (int i = 0; i < doctorResponseWrapper.data.size(); i++) {
            DoctorResponse doctor = new DoctorResponse(doctorResponseWrapper.data.get(i).getName()
                    , doctorResponseWrapper.data.get(i).getEmailid()
                    , doctorResponseWrapper.data.get(i).getPincode()
                    , doctorResponseWrapper.data.get(i).getPhoneno()
                    , doctorResponseWrapper.data.get(i).getCity()
                    , doctorResponseWrapper.data.get(i).getSpeciality()
                    , doctorResponseWrapper.data.get(i).getGender()
                    , doctorResponseWrapper.data.get(i).getExperience()
                    , doctorResponseWrapper.data.get(i).getRegid()
                    , doctorResponseWrapper.data.get(i).getFuid()
                    , doctorResponseWrapper.data.get(i).getAccesstoken()
                    , doctorResponseWrapper.data.get(i).getPhoto()
                    , doctorResponseWrapper.data.get(i).getLikes()
                    , doctorResponseWrapper.data.get(i).getRatings()
            );
            doctors.add(doctor);
        }
        doctorAdapter = new MyDoctorsAdapter(doctors, this);
        rvHome.setAdapter(doctorAdapter);
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
        pgProgress.setVisibility(View.GONE);

    }
}