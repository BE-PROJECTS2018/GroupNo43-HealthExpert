package com.healthexpert.data.remote.api;

import com.healthexpert.data.remote.models.requests.AdminDoctorDetails;
import com.healthexpert.data.remote.models.requests.DoctorRegisterRequest;
import com.healthexpert.data.remote.models.requests.DoctorRequest;
import com.healthexpert.data.remote.models.requests.FirebaseRequest;
import com.healthexpert.data.remote.models.requests.PatientRequest;
import com.healthexpert.data.remote.models.requests.PatientRequestNoIcon;
import com.healthexpert.data.remote.models.requests.UserLoginRequest;
import com.healthexpert.data.remote.models.requests.UserRegisterRequest;
import com.healthexpert.data.remote.models.response.DoctorWrapper;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.PatientWrapper;
import com.healthexpert.data.remote.models.response.SpecialityWrapper;
import com.healthexpert.data.remote.models.response.SymptomResponse;
import com.healthexpert.data.remote.models.response.SymptomResponseWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.data.remote.models.response.UserResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Shivani on 2/8/2018.
 */

public interface UserRestService {
    @POST("/auth/login")
    Observable<UserResponse> doLogin(@Body UserLoginRequest userLoginRequest);

    @POST("/auth/login/fuid")
    Observable<UserRegisterResponse> doFuid(@Body FirebaseRequest firebaseRequest);


    @POST("/admin/doctors")
    Observable<DoctorWrapper> getHomeData();



    @POST("/admin/doctors/status")
    Observable<UserRegisterResponse> statusDoctor(@Body AdminDoctorDetails adminDoctorDetails);


    @POST("/doctor/patient/symptoms")
    Observable<SymptomResponseWrapper> symptomPatient();


}
