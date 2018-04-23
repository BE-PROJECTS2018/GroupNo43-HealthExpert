package com.healthexpert.patient.doctors;

import com.healthexpert.admin.fragments.HomeContract;
import com.healthexpert.data.remote.api.DoctorRestService;
import com.healthexpert.data.remote.api.PatientRestService;
import com.healthexpert.data.remote.api.UserRestService;
import com.healthexpert.data.remote.models.requests.BookmarkRequest;
import com.healthexpert.data.remote.models.requests.DoctorCheckFeedback;
import com.healthexpert.data.remote.models.requests.DoctorFeedback;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.DoctorWrapper;
import com.healthexpert.data.remote.models.response.Speciality;
import com.healthexpert.data.remote.models.response.SpecialityWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shivani on 1/29/2017.
 */

public class DoctorPresenter implements DoctorContract.DoctorPresenter {

    PatientRestService patientRestService;
    DoctorContract.DoctorView doctorView;

    public DoctorPresenter(PatientRestService patientRestService, DoctorContract.DoctorView doctorView) {
        this.patientRestService = patientRestService;
        this.doctorView = doctorView;
    }

    @Override
    public void getDoctors(Speciality speciality) {
        patientRestService.getDoctors(speciality)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DoctorResponseWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (doctorView != null)
                            doctorView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(DoctorResponseWrapper doctorWrapper) {
                        if (doctorView != null)
                            doctorView.onDoctorData(doctorWrapper);
                    }
                });
    }

    @Override
    public void specialityDoctors() {
        patientRestService.specialityDoctors()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SpecialityWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (doctorView != null)
                            doctorView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(SpecialityWrapper specialityWrapper) {
                        if (doctorView != null)
                            doctorView.onSpeciality(specialityWrapper);
                    }
                });
    }

    @Override
    public void checkDoctorFeedback(DoctorCheckFeedback doctorCheckFeedback) {
        patientRestService.checkDoctorFeedback(doctorCheckFeedback)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserRegisterResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (doctorView != null)
                            doctorView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(UserRegisterResponse userRegisterResponse) {
                        if (doctorView != null)
                            doctorView.onCheckDoctorFeedback(userRegisterResponse);
                    }
                });
    }

    @Override
    public void doctorFeedback(DoctorFeedback doctorFeedback) {
        patientRestService.doctorFeedback(doctorFeedback)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserRegisterResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (doctorView != null)
                            doctorView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(UserRegisterResponse userRegisterResponse) {
                        if (doctorView != null)
                            doctorView.onFeedback(userRegisterResponse);
                    }
                });
    }

    @Override
    public void bookmarkDoctor(BookmarkRequest bookmarkRequest) {
        patientRestService.bookmark(bookmarkRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserRegisterResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (doctorView != null)
                            doctorView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(UserRegisterResponse userRegisterResponse) {
                        if (doctorView != null)
                            doctorView.onBookmarkDoctor(userRegisterResponse);
                    }
                });

    }

    @Override
    public void bookmarkCheck(BookmarkRequest bookmarkRequest) {
        patientRestService.bookmarkCheck(bookmarkRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserRegisterResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (doctorView != null)
                            doctorView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(UserRegisterResponse userRegisterResponse) {
                        if (doctorView != null)
                            doctorView.onCheckBookmark(userRegisterResponse);
                    }
                });
    }
}
