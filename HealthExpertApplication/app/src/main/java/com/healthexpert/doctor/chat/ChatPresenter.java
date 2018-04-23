package com.healthexpert.doctor.chat;

import com.healthexpert.data.remote.api.DoctorRestService;
import com.healthexpert.data.remote.models.requests.MessageRequest;
import com.healthexpert.data.remote.models.requests.NotifyRequest;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shivani on 2/18/2018.
 */

public class ChatPresenter implements ChatContracts.ChatPresenter {
    DoctorRestService doctorRestService;
    ChatContracts.ChatView chatView;
    public ChatPresenter(DoctorRestService doctorRestService, ChatContracts.ChatView chatView) {
        this.doctorRestService = doctorRestService;
        this.chatView = chatView;
    }

    @Override
    public void sendNotification(MessageRequest messageRequest) {
        doctorRestService.sendNotification(messageRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserRegisterResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (chatView != null)
                            chatView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(UserRegisterResponse userRegisterResponse) {
                        if (chatView != null)
                            chatView.onNotification(userRegisterResponse);
                    }
                });

    }
    @Override
    public void sendNotify(NotifyRequest notifyRequest) {
        doctorRestService.sendNotify(notifyRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserRegisterResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (chatView != null)
                            chatView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(UserRegisterResponse userRegisterResponse) {
                        if (chatView != null)
                            chatView.onNotification(userRegisterResponse);
                    }
                });

    }
}
