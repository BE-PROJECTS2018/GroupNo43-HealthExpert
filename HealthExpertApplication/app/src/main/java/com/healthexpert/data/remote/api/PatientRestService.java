package com.healthexpert.data.remote.api;

import com.healthexpert.data.remote.models.requests.BookmarkRequest;
import com.healthexpert.data.remote.models.requests.DoctorCheckFeedback;
import com.healthexpert.data.remote.models.requests.DoctorFeedback;
import com.healthexpert.data.remote.models.requests.DoctorRegisterRequest;
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.requests.PatientRegisterRequest;
import com.healthexpert.data.remote.models.requests.PatientRequest;
import com.healthexpert.data.remote.models.requests.PatientRequestNoIcon;
import com.healthexpert.data.remote.models.requests.PatientUpdateRequestNoIcon;
import com.healthexpert.data.remote.models.requests.PrescriptionRequest;
import com.healthexpert.data.remote.models.response.DoctorResponse;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.DoctorWrapper;
import com.healthexpert.data.remote.models.response.PatientSummaryResponse;
import com.healthexpert.data.remote.models.response.PatientSummaryResponseWrapper;
import com.healthexpert.data.remote.models.response.PatientWrapper;
import com.healthexpert.data.remote.models.response.Speciality;
import com.healthexpert.data.remote.models.response.SpecialityWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by Shivani on 2/9/2018.
 */

public interface PatientRestService {
    @POST("/auth/register/patient")
    Observable<UserRegisterResponse> doPatientRegister(@Body PatientRegisterRequest userRegisterRequest);

    @POST("/auth/register/patient/noicon")
    Observable<UserRegisterResponse> addPatientNoIcon(@Body PatientRequestNoIcon patientRequestNoIcon);

    @POST("/patient/doctors")
    Observable<DoctorResponseWrapper> getDoctors(@Body Speciality speciality);

    @POST("/doctor/speciality")
    Observable<SpecialityWrapper> specialityDoctors();

    @POST("/patient/doctor/feedback/check")
    Observable<UserRegisterResponse> checkDoctorFeedback(@Body DoctorCheckFeedback doctorCheckFeedback);

    @POST("/patient/doctor/feedback")
    Observable<UserRegisterResponse> doctorFeedback(@Body DoctorFeedback doctorFeedback);

    @POST("/bookmark/add")
    Observable<UserRegisterResponse> bookmark(@Body BookmarkRequest bookmarkRequest);

    @POST("/bookmark/check")
    Observable<UserRegisterResponse> bookmarkCheck(@Body BookmarkRequest bookmarkRequest);

    @POST("/patient/bookmark/doctors")
    Observable<DoctorResponseWrapper> getBookmarkDoctors(@Body MyRequest myRequest);

    @POST("/patient/summary")
    Observable<PatientSummaryResponseWrapper> getSummary(@Body MyRequest myRequest);

    @POST("/patient/profile")
    Observable<PatientWrapper> getProfile(@Body MyRequest myRequest);

    @POST("/patient/update/profile/icon")
    Observable<UserRegisterResponse> updateProfile(@Part("name") RequestBody name, @Part("dob")RequestBody dob, @Part("gender") RequestBody gender,
                                                   @Part("height")RequestBody height, @Part("weight") RequestBody weight,@Part("emailid") RequestBody emailid,
                                                   @Part("phoneno")RequestBody phoneno,@Part("occupation") RequestBody occupation,@Part("bloodgroup") RequestBody bloodgroup,
                                                   @Part("symptoms") RequestBody symptoms,@Part("history") RequestBody history,@Part("investigations") RequestBody investigations,
                                                   @Part("city") RequestBody city,@Part("pincode") RequestBody pincode,@Part("mothername") RequestBody mothername,@Part("mothersymptoms") RequestBody mothersymptoms,
                                                   @Part("fathername")RequestBody fathername, @Part("fathersymptoms")RequestBody fathersymptoms,                                                       @Part("image\"; filename=\"image.jpg\"") RequestBody image,
                                                   @Part("devicetoken")RequestBody devicetoken, @Part("accesstoken")RequestBody accesstoken);
    @POST("/patient/update/profile/noicon")
    Observable<UserRegisterResponse> updateProfileNoIcon(@Body PatientUpdateRequestNoIcon patientUpdateRequestNoIcon);

}
