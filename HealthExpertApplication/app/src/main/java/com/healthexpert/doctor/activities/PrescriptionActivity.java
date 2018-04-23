package com.healthexpert.doctor.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.healthexpert.R;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.data.local.SharedPreferenceManager;
import com.healthexpert.data.remote.api.DoctorRestService;
import com.healthexpert.data.remote.models.requests.MessageRequest;
import com.healthexpert.data.remote.models.requests.NotifyRequest;
import com.healthexpert.data.remote.models.requests.PrescriptionRequest;
import com.healthexpert.data.remote.models.response.DoctorResponse;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.dispatcher.RetrofitObj;
import com.healthexpert.doctor.chat.ChatContracts;
import com.healthexpert.doctor.chat.ChatPresenter;
import com.healthexpert.doctor.doctors.DoctorContract;
import com.healthexpert.doctor.doctors.DoctorPresenter;
import com.healthexpert.ui.widgets.BaseButton;
import com.healthexpert.ui.widgets.BaseEditText;
import com.healthexpert.ui.widgets.BaseMaterialEditText;

/**
 * Created by Shivani on 3/6/2018.
 */

public class PrescriptionActivity extends BaseActivity implements DoctorContract.DoctorView,ChatContracts.ChatView {


    BaseEditText etPrescriptionTitle;
    BaseMaterialEditText etPrescription;
    BaseButton bSubmit;
    DoctorPresenter doctorPresenter;
    Patient patient;
    ChatPresenter chatPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);
        patient = getIntent().getParcelableExtra("patient");
        initViews();
        DoctorRestService doctorRestService = RetrofitObj.getInstance().create(DoctorRestService.class);
        doctorPresenter = new DoctorPresenter(doctorRestService, this);
        chatPresenter = new ChatPresenter(doctorRestService,this);
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status = validate();
                if (status) {
                    showProgressDialog();
                    doctorPresenter.prescription(new PrescriptionRequest(patient.getAccesstoken(),
                            new SharedPreferenceManager(getApplicationContext()).getAccessToken(),
                            etPrescriptionTitle.getText().toString(), etPrescription.getText().toString()));
                }
            }
        });
    }

    private boolean validate() {
        if (etPrescriptionTitle.getText().toString().isEmpty()) {
            etPrescription.setFocusable(true);
            etPrescription.setError("Prescription title cannot be empty");
            return false;
        }
        return true;
    }

    private void initViews() {
        etPrescriptionTitle = (BaseEditText) findViewById(R.id.etPtitle);
        etPrescription = (BaseMaterialEditText) findViewById(R.id.etPrescription);
        bSubmit = (BaseButton) findViewById(R.id.bSubmit);
    }

    @Override
    public void onDoctorData(DoctorResponseWrapper doctorResponseWrapper) {

    }

    @Override
    public void onPrescription(UserRegisterResponse userRegisterResponse) {
        if (userRegisterResponse.isStatus()) {
            Log.d("DeviceToken",new SharedPreferenceManager(getApplicationContext()).getDeviceToken());
            chatPresenter.sendNotify(new NotifyRequest(new SharedPreferenceManager(getApplicationContext()).getDeviceToken(),patient.getDevicetoken(),
                    "1 doctor has prescribed","com.healthexpert.patientsummary_TARGET_NOTIFICATION"));

        }
    }

    @Override
    public void onNotification(UserRegisterResponse userRegisterResponse) {
        dismissProgressDialog();
        Toast.makeText(getApplicationContext(), userRegisterResponse.getMessage(), Toast.LENGTH_SHORT).show();
        finish();
    }
}
