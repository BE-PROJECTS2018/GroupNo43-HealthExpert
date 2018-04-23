package com.healthexpert.doctor.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.healthexpert.R;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.data.remote.api.DoctorRestService;
import com.healthexpert.data.remote.api.UserRestService;
import com.healthexpert.data.remote.models.requests.PredictionRequest;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.PatientWrapper;
import com.healthexpert.data.remote.models.response.PredictionResponse;
import com.healthexpert.data.remote.models.response.SymptomResponse;
import com.healthexpert.data.remote.models.response.SymptomResponseWrapper;
import com.healthexpert.dispatcher.RetrofitObj;
import com.healthexpert.doctor.patients.PatientContract;
import com.healthexpert.doctor.patients.PatientPresenter;
import com.healthexpert.doctor.patients.PatientSymptomActivity;
import com.healthexpert.patient.symptom.SymptomPatientContracts;
import com.healthexpert.patient.symptom.SymptomPatientPresenter;
import com.healthexpert.patient.symptom.adapters.SymptomAdapter;
import com.healthexpert.ui.widgets.BaseButton;
import com.healthexpert.ui.widgets.BaseCheckbox;
import com.healthexpert.ui.widgets.BaseEditText;
import com.healthexpert.ui.widgets.BaseTextView;

import java.util.ArrayList;

/**
 * Created by Shivani on 3/6/2018.
 */

public class PredictionActivity extends BaseActivity implements SymptomPatientContracts.SymptomPatientView,PatientContract.PatientView {
    Patient patient;
    RecyclerView rvSymptoms;
    ArrayList<SymptomResponse> symptomResponses;
    SymptomAdapter symptomAdapter;
    PatientPresenter patientPresenter;
    LinearLayout llParentStatus;
    BaseCheckbox cbParentStatus;
    BaseTextView tvResults;
    BaseEditText etSymptom;
    BaseButton bSubmit;
    SymptomPatientPresenter symptomPatientPresenter;

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

        setContentView(R.layout.activity_prediction);
        patient = getIntent().getParcelableExtra("patient");
        initViews();
        etSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSymptomsDialog();
            }
        });
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etSymptom.getText().toString().isEmpty()) {
                    showProgressDialog("Predicting please wait...");
                    patientPresenter.predictDisease(new PredictionRequest(patient.getAccesstoken(), etSymptom.getText().toString(), cbParentStatus.isChecked() ? 1 : 0));
                } else
                    Toast.makeText(getApplicationContext(), "Select symptoms", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initViews(){
        etSymptom = (BaseEditText) findViewById(R.id.etSymptom);
        tvResults = (BaseTextView) findViewById(R.id.tvResults);
        bSubmit = (BaseButton) findViewById(R.id.bSubmit);
        llParentStatus = (LinearLayout) findViewById(R.id.llParentStatus);
        cbParentStatus = (BaseCheckbox) findViewById(R.id.cbParentStatus);
        llParentStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbParentStatus.isChecked()) {
                    cbParentStatus.setChecked(false);
                } else {
                    cbParentStatus.setChecked(true);
                }
            }
        });
        UserRestService userRestService = RetrofitObj.getInstance().create(UserRestService.class);
        symptomPatientPresenter = new SymptomPatientPresenter(userRestService, this);
        DoctorRestService doctorRestService = RetrofitObj.getInstance().create(DoctorRestService.class);
        patientPresenter = new PatientPresenter(doctorRestService, this);


    }

    @Override
    public void onSymptomPatient(SymptomResponseWrapper symptomResponseWrapper) {
        symptomResponses = new ArrayList<>();
        for (int i = 0; i < symptomResponseWrapper.data.size(); i++) {
            SymptomResponse symptomResponse = new SymptomResponse(symptomResponseWrapper.data.get(i).getId(),
                    symptomResponseWrapper.data.get(i).getSname(), false);
            symptomResponses.add(symptomResponse);
        }
        symptomAdapter = new SymptomAdapter(symptomResponses);
        rvSymptoms.setAdapter(symptomAdapter);

    }

    @Override
    public void getSymptoms(SymptomResponseWrapper symptomResponseWrapper) {

    }

    @Override
    public void onPatientData(PatientWrapper patientWrapper) {

    }

    @Override
    public void onPredict(PredictionResponse predictionResponse) {
        tvResults.setText(predictionResponse.getResult());
        dismissProgressDialog();
    }
    private void showSymptomsDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(PredictionActivity.this);
        dialogBuilder.setTitle("Select Symptoms");
        dialogBuilder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String symptoms = "";
                ArrayList<SymptomResponse> symptomResponses = symptomAdapter.getAllSymptoms();
                for (int i = 0; i < symptomResponses.size(); i++) {
                    symptoms += symptomResponses.get(i).getSname() + ",";
                }
                symptoms = symptoms.substring(0, symptoms.length() - 1);
                etSymptom.setText(symptoms);
                dialog.dismiss();
            }
        });
        dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_symptoms, null);
        dialogBuilder.setView(dialogView);
        rvSymptoms = (RecyclerView) dialogView.findViewById(R.id.rvSymptoms);
        rvSymptoms.setHasFixedSize(true);
        rvSymptoms.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
        symptomPatientPresenter.symptomPatient();

    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<SymptomResponse> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (int i = 0; i < symptomResponses.size(); i++) {
            //if the existing elements contains the search input
            if (symptomResponses.get(i).getSname().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(symptomResponses.get(i));
            }
        }

        //calling a method of the adapter class and passing the filtered list
        symptomAdapter.filterList(filterdNames);
    }


}
