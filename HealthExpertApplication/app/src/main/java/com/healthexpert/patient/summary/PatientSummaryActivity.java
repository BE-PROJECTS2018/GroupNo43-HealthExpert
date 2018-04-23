package com.healthexpert.patient.summary;

import android.content.Intent;
import android.os.Bundle;
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
import com.healthexpert.data.remote.api.PatientRestService;
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.PatientSummaryResponse;
import com.healthexpert.data.remote.models.response.PatientSummaryResponseWrapper;
import com.healthexpert.data.remote.models.response.PatientWrapper;
import com.healthexpert.dispatcher.RetrofitObj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shivani on 1/28/2017.
 */

public class PatientSummaryActivity extends BaseActivity implements PatientSummaryContract.PatientView, PatientSummaryAdapter.LikeItemUpdateListener, SearchView.OnQueryTextListener {

    String accesstoken;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rvHome;
    ProgressBar pgProgress;
    PatientSummaryPresenter myPatientsPresenter;
    PatientSummaryAdapter myPatientsAdapter;
    ArrayList<PatientSummaryResponse> patients;
    String title;

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
        accesstoken = getIntent().getStringExtra("accesstoken");
        boolean check = getIntent().hasExtra("title");
        if (check)
            title = getIntent().getStringExtra("title");
        else
            title = new SharedPreferenceManager(getApplicationContext()).getName().isEmpty() ? getString(R.string.app_name) : new SharedPreferenceManager(getApplicationContext()).getName();

        initViews();
        rvHome.setHasFixedSize(true);
        rvHome.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        PatientRestService patientRestService = RetrofitObj.getInstance().create(PatientRestService.class);
        myPatientsPresenter = new PatientSummaryPresenter(patientRestService, this);
        myPatientsPresenter.getSummary(new MyRequest(accesstoken));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myPatientsPresenter.getSummary(new MyRequest(accesstoken));
            }
        });


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final ArrayList<PatientSummaryResponse> filteredModelList = filter(patients, newText);
        myPatientsAdapter.animateTo(filteredModelList);
        rvHome.scrollToPosition(0);
        return false;
    }

    private ArrayList<PatientSummaryResponse> filter(List<PatientSummaryResponse> models, String query) {
        query = query.toLowerCase();

        final ArrayList<PatientSummaryResponse> filteredModelList = new ArrayList<>();
        for (PatientSummaryResponse model : models) {
            final String text = model.getPtitle().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }


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
    public void onSummary(PatientSummaryResponseWrapper patientSummaryResponseWrapper) {
        patients = new ArrayList<>();
        for (int i = 0; i < patientSummaryResponseWrapper.data.size(); i++) {
            PatientSummaryResponse patient = new PatientSummaryResponse(patientSummaryResponseWrapper.data.get(i).getPid(),
                    patientSummaryResponseWrapper.data.get(i).getDname(),
                    patientSummaryResponseWrapper.data.get(i).getDspeciality(),
                    patientSummaryResponseWrapper.data.get(i).getDphoto(),
                    patientSummaryResponseWrapper.data.get(i).getPtitle(),
                    patientSummaryResponseWrapper.data.get(i).getPprescription(),
                    patientSummaryResponseWrapper.data.get(i).getPtimestamp(),
                    patientSummaryResponseWrapper.data.get(i).getPaccesstoken(),
                    patientSummaryResponseWrapper.data.get(i).getDaccesstoken());
            patients.add(patient);

        }
        myPatientsAdapter = new PatientSummaryAdapter(patients, this);
        rvHome.setAdapter(myPatientsAdapter);
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
        pgProgress.setVisibility(View.GONE);
    }

    @Override
    public void onItemCardClicked(PatientSummaryResponse patient) {
        Intent i = new Intent(PatientSummaryActivity.this, PatientSummaryDetailsActivity.class);
        i.putExtra("psummary", patient);
        i.putExtra("title", title);
        startActivity(i);
    }
}