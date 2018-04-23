package com.healthexpert.patient.summary;

import com.healthexpert.data.remote.api.PatientRestService;
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.PatientSummaryResponse;
import com.healthexpert.data.remote.models.response.PatientSummaryResponseWrapper;
import com.healthexpert.patient.mydoctors.MyDoctorsContract;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shivani on 1/29/2017.
 */

public class PatientSummaryPresenter implements PatientSummaryContract.PatientPresenter{

    PatientRestService patientRestService;
    PatientSummaryContract.PatientView patientView;

    public PatientSummaryPresenter(PatientRestService patientRestService, PatientSummaryContract.PatientView patientView) {
        this.patientRestService = patientRestService;
        this.patientView = patientView;
    }



    @Override
    public void getSummary(MyRequest myRequest) {
        patientRestService.getSummary(myRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PatientSummaryResponseWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (patientView != null)
                            patientView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(PatientSummaryResponseWrapper patientSummaryResponse) {
                        if (patientView != null)
                            patientView.onSummary(patientSummaryResponse);
                    }
                });
    }
}
