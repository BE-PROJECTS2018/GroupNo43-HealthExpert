package com.healthexpert.patient.mydoctors;

import com.healthexpert.common.BaseContract;
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.DoctorWrapper;
import com.healthexpert.data.remote.models.response.PatientWrapper;

/**
 * Created by Shivani on 1/29/2017.
 */

public interface MyDoctorsContract {
    interface MyDoctorsView extends BaseContract.BaseView{
        void onDoctorsData(DoctorResponseWrapper doctorResponseWrapper);
    }
    interface MyDoctorsPresenter{
        void getDoctors(MyRequest myRequest);
    }
}
