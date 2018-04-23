package com.healthexpert.doctor.chat;

import com.healthexpert.common.BaseContract;
import com.healthexpert.data.remote.models.requests.MessageRequest;
import com.healthexpert.data.remote.models.requests.NotifyRequest;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;

/**
 * Created by Shivani on 2/18/2018.
 */

public interface ChatContracts {
    interface ChatView extends BaseContract.BaseView{
        void onNotification(UserRegisterResponse userRegisterResponse);
    }
    interface ChatPresenter {
        void sendNotification(MessageRequest messageRequest);
        void sendNotify(NotifyRequest notifyRequest);
    }
}
