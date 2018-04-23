package com.healthexpert.auth.patient;

import com.healthexpert.common.BaseContract;
import com.healthexpert.data.remote.models.requests.PatientRegisterRequest;
import com.healthexpert.data.remote.models.requests.PatientRequest;
import com.healthexpert.data.remote.models.requests.PatientRequestNoIcon;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;

/**
 * Created by Shivani on 2/9/2018.
 */

public interface PatientRegisterContracts extends BaseContract {
    interface PatientRegisterView extends BaseContract.BaseView {
        void onRegisterPatient(UserRegisterResponse userRegisterResponse);
    }

    interface PatientRegisterPresenter {
        void registerPatient(PatientRegisterRequest patientRegsiterRequest);
        void addPatientNoIcon(PatientRequestNoIcon patientRequestNoIcon);
    }
}
