package com.healthexpert.doctor.doctors;

import com.healthexpert.common.BaseContract;
import com.healthexpert.data.remote.models.requests.PrescriptionRequest;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.Speciality;
import com.healthexpert.data.remote.models.response.SpecialityWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;

/**
 * Created by Shivani on 1/29/2017.
 */

public interface DoctorContract {
    interface DoctorView extends BaseContract.BaseView{
        void onDoctorData(DoctorResponseWrapper doctorResponseWrapper);
        void onPrescription(UserRegisterResponse userRegisterResponse);
    }
    interface DoctorPresenter{
        void getDoctors();
        void prescription(PrescriptionRequest prescriptionRequest);
    }
}
