package com.healthexpert.auth.doctor;


import com.healthexpert.common.BaseContract;
import com.healthexpert.data.remote.models.requests.DoctorRegisterRequest;
import com.healthexpert.data.remote.models.requests.UserRegisterRequest;
import com.healthexpert.data.remote.models.response.Doctor;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.data.remote.models.response.UserResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.Part;

/**
 * Created by Shivani on 1/13/2017.
 */

public interface RegisterContract {
    interface RegisterView extends BaseContract.BaseView {
        void onDoctorRegister(UserRegisterResponse userResponse);
        void onPatientRegister(UserRegisterResponse userResponse);

    }

    interface RegisterPresenter {
        void registerDoctor(RequestBody name,
                            RequestBody emailid,
                            RequestBody regid,
                            RequestBody speciality,
                            RequestBody city,
                            RequestBody gender,
                            RequestBody pincode,
                            RequestBody experience,
                            RequestBody phoneno,
                            RequestBody password,
                            RequestBody fuid,
                            RequestBody i_name);

        void registerPatient(UserRegisterRequest userRegisterRequest);

    }
}
