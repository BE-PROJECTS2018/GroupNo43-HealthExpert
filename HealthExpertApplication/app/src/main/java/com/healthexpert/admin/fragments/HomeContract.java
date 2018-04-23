package com.healthexpert.admin.fragments;

import com.healthexpert.common.BaseContract;
import com.healthexpert.data.remote.models.response.DoctorWrapper;

/**
 * Created by shivani on 1/29/2017.
 */

public interface HomeContract {
    interface HomeView extends BaseContract.BaseView{
        void onHomeData(DoctorWrapper doctorWrapper);
    }
    interface HomePresenter{
        void fetchHomeData();
    }
}
