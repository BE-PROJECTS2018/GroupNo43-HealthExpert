package com.healthexpert.data.remote.api;

import com.healthexpert.data.remote.models.requests.DoctorRequest;
import com.healthexpert.data.remote.models.requests.MessageRequest;
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.requests.NotifyRequest;
import com.healthexpert.data.remote.models.requests.PredictionRequest;
import com.healthexpert.data.remote.models.requests.PrescriptionRequest;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.PatientWrapper;
import com.healthexpert.data.remote.models.response.PredictionResponse;
import com.healthexpert.data.remote.models.response.DoctorSummaryResponse;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by Shivani on 2/9/2018.
 */

public interface DoctorRestService {

    @Multipart
    @POST("/auth/register/doctor")
    Observable<UserRegisterResponse> doDoctorRegister(@Part("name") RequestBody name,
                                                      @Part("emailid") RequestBody emailid,
                                                      @Part("regid") RequestBody regid,
                                                      @Part("speciality") RequestBody speciality,
                                                      @Part("city") RequestBody city,
                                                      @Part("gender") RequestBody gender,
                                                      @Part("pincode") RequestBody pincode,
                                                      @Part("experience") RequestBody experience,
                                                      @Part("phoneno") RequestBody phoneno,
                                                      @Part("password") RequestBody password,
                                                      @Part("fuid") RequestBody fuid,
                                                      @Part("image\"; filename=\"image.jpg\"") RequestBody i_name);

    @POST("/doctor/alldoctors")
    Observable<DoctorResponseWrapper> getDoctors();

    @POST("/doctor/bookmark/patients")
    Observable<PatientWrapper> getPatients(@Body MyRequest myRequest);

    @POST("/doctor/patients")
    Observable<PatientWrapper> getPatientData(@Body DoctorRequest doctorRequest);

    @POST("/doctor/patients/prediction")
    Observable<PredictionResponse> predictDisease(@Body PredictionRequest predictionRequest);

    @POST("/messaging/notify")
    Observable<UserRegisterResponse> sendNotification(@Body MessageRequest messageRequest);

    @POST("/notify")
    Observable<UserRegisterResponse> sendNotify(@Body NotifyRequest notifyRequest);

    @POST("/doctor/summary")
    Observable<DoctorSummaryResponse> fetchSummary(@Body MyRequest summaryRequest);

    @POST("/doctor/patient/prescription")
    Observable<UserRegisterResponse> prescription(@Body PrescriptionRequest prescriptionRequest);


}
