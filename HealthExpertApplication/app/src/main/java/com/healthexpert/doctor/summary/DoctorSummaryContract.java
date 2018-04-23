package com.healthexpert.doctor.summary;

import com.healthexpert.common.BaseContract;
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.response.DoctorSummaryResponse;

/**
 * Created by Shivani on 1/29/2017.
 */

public interface DoctorSummaryContract {
    interface DoctorView extends BaseContract.BaseView{
        void onSummary(DoctorSummaryResponse doctorSummaryResponse);

    }
    interface DoctorPresenter {
        void fetchSummary(MyRequest summaryRequest);

    }
}
