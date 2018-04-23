package com.healthexpert.patient.doctors;

import com.healthexpert.common.BaseContract;
import com.healthexpert.data.remote.models.requests.BookmarkRequest;
import com.healthexpert.data.remote.models.requests.DoctorCheckFeedback;
import com.healthexpert.data.remote.models.requests.DoctorFeedback;
import com.healthexpert.data.remote.models.response.DoctorResponse;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.DoctorWrapper;
import com.healthexpert.data.remote.models.response.Speciality;
import com.healthexpert.data.remote.models.response.SpecialityWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;

/**
 * Created by Shivani on 1/29/2017.
 */

public interface DoctorContract {
    interface DoctorView extends BaseContract.BaseView{
        void onDoctorData(DoctorResponseWrapper doctorResponseWrapper);
        void onSpeciality(SpecialityWrapper specialityWrapper);
        void onCheckDoctorFeedback(UserRegisterResponse userRegisterResponse);
        void onFeedback(UserRegisterResponse userRegisterResponse);
        void onBookmarkDoctor(UserRegisterResponse userRegisterResponse);
        void onCheckBookmark(UserRegisterResponse userRegisterResponse);
    }
    interface DoctorPresenter{
        void getDoctors(Speciality speciality);
        void specialityDoctors();
        void checkDoctorFeedback(DoctorCheckFeedback doctorCheckFeedback);
        void doctorFeedback(DoctorFeedback doctorFeedback);
        void bookmarkDoctor(BookmarkRequest bookmarkRequest);
        void bookmarkCheck(BookmarkRequest bookmarkRequest);
    }
}
