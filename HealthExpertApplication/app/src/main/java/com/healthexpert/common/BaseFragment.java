package com.healthexpert.common;

import android.support.v4.app.Fragment;

/**
 * Created by shivani on 12/19/2016.
 */

public class BaseFragment extends Fragment implements BaseContract.BaseView {

    @Override
    public void onNetworkException(Throwable e) {
        try {
            ((BaseActivity) getActivity()).onNetworkException(e);
        } catch (ClassCastException cce) {
            throw new ClassCastException("Fragments extending BaseFragment must be placed in a Activity" +
                    "extending BaseActivity");
        }
    }
}
