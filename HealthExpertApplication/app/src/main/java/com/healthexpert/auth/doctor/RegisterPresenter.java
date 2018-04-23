package com.healthexpert.auth.doctor;

import android.util.Log;

import com.healthexpert.data.remote.api.DoctorRestService;
import com.healthexpert.data.remote.api.UserRestService;
import com.healthexpert.data.remote.models.requests.DoctorRegisterRequest;
import com.healthexpert.data.remote.models.requests.UserRegisterRequest;
import com.healthexpert.data.remote.models.response.Doctor;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shivani on 1/13/2017.
 */

public class RegisterPresenter implements RegisterContract.RegisterPresenter {

    RegisterContract.RegisterView view;
    DoctorRestService doctorRestService;

    public RegisterPresenter(DoctorRestService doctorRestService, RegisterContract.RegisterView view) {
        this.view = view;
        this.doctorRestService = doctorRestService;
    }

    @Override
    public void registerDoctor(RequestBody name, RequestBody emailid, RequestBody regid, RequestBody speciality, RequestBody city, RequestBody gender, RequestBody pincode, RequestBody experience, RequestBody phoneno, RequestBody password, RequestBody fuid, RequestBody i_name) {
        doctorRestService
                .doDoctorRegister(name, emailid, regid, speciality, city, gender, pincode, experience, phoneno, password, fuid, i_name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<UserRegisterResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d("UserMessageComplete", "Complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null)
                            view.onNetworkException(e);
                    }

                    @Override
                    public void onNext(UserRegisterResponse user) {
                        if (view != null)
                            view.onDoctorRegister(user);
                    }
                });

    }

    @Override
    public void registerPatient(UserRegisterRequest userRegisterRequest) {

    }


}
