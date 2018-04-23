package com.healthexpert.patient.symptom;

import com.healthexpert.common.BaseContract;
import com.healthexpert.data.remote.models.response.SymptomResponse;
import com.healthexpert.data.remote.models.response.SymptomResponseWrapper;

/**
 * Created by Shivani on 2/9/2018.
 */

public interface SymptomPatientContracts {
    interface SymptomPatientView extends BaseContract.BaseView {
        void onSymptomPatient(SymptomResponseWrapper symptomResponseWrapper);
        void getSymptoms(SymptomResponseWrapper symptomResponseWrapper);
    }

    interface SymptomPatientPresenter {
        void symptomPatient();
        void symptomPatientAll();
    }
}
