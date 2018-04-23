package com.healthexpert.doctor.patients;

import com.healthexpert.common.BaseContract;
import com.healthexpert.data.remote.models.requests.DoctorRequest;
import com.healthexpert.data.remote.models.requests.PredictionRequest;
import com.healthexpert.data.remote.models.response.PatientWrapper;
import com.healthexpert.data.remote.models.response.PredictionResponse;

/**
 * Created by Shivani on 1/29/2017.
 */

public interface PatientContract {
    interface PatientView extends BaseContract.BaseView{
        void onPatientData(PatientWrapper patientWrapper);
        void onPredict(PredictionResponse predictionResponse);
    }
    interface PatientPresenter {
        void fetchPatientData(DoctorRequest patientRequest);
        void predictDisease(PredictionRequest predictionRequest);
    }
}
