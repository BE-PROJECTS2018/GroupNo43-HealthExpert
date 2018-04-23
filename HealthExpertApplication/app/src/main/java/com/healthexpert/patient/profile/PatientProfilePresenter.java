package com.healthexpert.patient.profile;

import com.healthexpert.data.remote.api.PatientRestService;
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.requests.PatientUpdateRequestNoIcon;
import com.healthexpert.data.remote.models.response.PatientWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.doctor.patients.PatientContract;

import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shivani on 1/29/2017.
 */

public class PatientProfilePresenter implements PatientProfileContract.PatientPresenter {

    PatientRestService patientRestService;
    PatientProfileContract.PatientView patientView;

    public PatientProfilePresenter(PatientRestService patientRestService, PatientProfileContract.PatientView patientView) {
        this.patientRestService = patientRestService;
        this.patientView = patientView;
    }

    @Override
    public void getPatient(MyRequest myRequest) {
        patientRestService.getProfile(myRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PatientWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (patientView != null)
                            patientView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(PatientWrapper patientWrapper) {
                        if (patientView != null)
                            patientView.onPatient(patientWrapper);
                    }
                });
    }

    @Override
    public void updatePatient(RequestBody name, RequestBody dob, RequestBody gender, RequestBody height, RequestBody weight, RequestBody emailid, RequestBody phoneno, RequestBody occupation, RequestBody bloodgroup, RequestBody symptoms, RequestBody history, RequestBody investigations, RequestBody city, RequestBody pincode, RequestBody mothername, RequestBody mothersymptoms, RequestBody fathername, RequestBody fathersymptoms, RequestBody image, RequestBody devicetoken, RequestBody accesstoken) {
        patientRestService.updateProfile(name, dob, gender, height, weight, emailid, phoneno, occupation, bloodgroup, symptoms, history, investigations, city, pincode, mothername, mothersymptoms, fathername, fathersymptoms, image, devicetoken, accesstoken)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserRegisterResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (patientView != null)
                            patientView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(UserRegisterResponse userRegisterResponse) {
                        if (patientView != null)
                            patientView.onUpdate(userRegisterResponse);
                    }
                });
    }

    @Override
    public void updatePatientNoIcon(PatientUpdateRequestNoIcon patientUpdateRequestNoIcon) {
        patientRestService.updateProfileNoIcon(patientUpdateRequestNoIcon)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserRegisterResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (patientView != null)
                            patientView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(UserRegisterResponse userRegisterResponse) {
                        if (patientView != null)
                            patientView.onUpdate(userRegisterResponse);
                    }
                });
    }
}
