package com.healthexpert.patient.profile;

import com.healthexpert.common.BaseContract;
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.requests.PatientRegisterRequest;
import com.healthexpert.data.remote.models.requests.PatientRequestNoIcon;
import com.healthexpert.data.remote.models.requests.PatientUpdateRequestNoIcon;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.PatientWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.data.remote.models.response.UserResponse;

import okhttp3.RequestBody;

/**
 * Created by Shivani on 3/11/2018.
 */

public interface PatientProfileContract {
    interface PatientView extends BaseContract.BaseView {
        void onPatient(PatientWrapper patientWrapper);

        void onUpdate(UserRegisterResponse userRegisterResponse);
    }

    interface PatientPresenter {
        void getPatient(MyRequest myRequest);

        void updatePatient(RequestBody name, RequestBody dob, RequestBody gender,
                           RequestBody height, RequestBody weight, RequestBody emailid,
                           RequestBody phoneno, RequestBody occupation, RequestBody bloodgroup,
                           RequestBody symptoms, RequestBody history, RequestBody investigations,
                           RequestBody city, RequestBody pincode, RequestBody mothername, RequestBody mothersymptoms,
                           RequestBody fathername, RequestBody fathersymptoms, RequestBody image, RequestBody devicetoken, RequestBody accesstoken);

        void updatePatientNoIcon(PatientUpdateRequestNoIcon patientUpdateRequestNoIcon);
    }
}
