package com.healthexpert.admin.activities;

import com.healthexpert.common.BaseContract;
import com.healthexpert.data.remote.models.requests.AdminDoctorDetails;
import com.healthexpert.data.remote.models.response.DoctorWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.data.remote.models.response.UserResponse;

/**
 * Created by shivani on 1/29/2017.
 */

public interface AdminDoctorDetailsContract {
    interface AdminDoctorDetailsView extends BaseContract.BaseView{
        void onData(UserRegisterResponse userRegisterResponse);
    }
    interface AdminDoctorDetailsPresenter{
        void statusDoctor(AdminDoctorDetails adminDoctorDetails);
    }
}
