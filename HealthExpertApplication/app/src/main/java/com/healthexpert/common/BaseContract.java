package com.healthexpert.common;

/**
 * Created by shivani on 12/19/2016.
 */

public interface BaseContract {
    interface BaseView {
        void onNetworkException(Throwable e);
    }
}
