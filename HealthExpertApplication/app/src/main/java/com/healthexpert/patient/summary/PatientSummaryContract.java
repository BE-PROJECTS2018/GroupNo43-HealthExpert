package com.healthexpert.patient.summary;

import com.healthexpert.common.BaseContract;
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.response.PatientSummaryResponse;
import com.healthexpert.data.remote.models.response.PatientSummaryResponseWrapper;
import com.healthexpert.data.remote.models.response.PatientWrapper;

/**
 * Created by Shivani on 1/29/2017.
 */

public interface PatientSummaryContract {
    interface PatientView extends BaseContract.BaseView{
        void onSummary(PatientSummaryResponseWrapper patientSummaryResponseWrapper);
    }
    interface PatientPresenter {
        void getSummary(MyRequest myRequest);
    }
}
