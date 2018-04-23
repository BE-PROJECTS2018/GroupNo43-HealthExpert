package com.healthexpert.doctor.mypatients;

import com.healthexpert.common.BaseContract;
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.PatientWrapper;

/**
 * Created by Shivani on 1/29/2017.
 */

public interface MyPatientsContract {
    interface MyPatientsView extends BaseContract.BaseView{
        void onPatientsData(PatientWrapper patientWrapper);
    }
    interface MyPatientsPresenter{
        void getPatients(MyRequest myRequest);
    }
}
