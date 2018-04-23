package com.healthexpert.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.healthexpert.R;
import com.healthexpert.common.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Shivani on 1/28/2017.
 */

public class HomeFragment extends BaseFragment {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rvHome;
    ProgressBar pgProgress;

    @Override
    public void onNetworkException(Throwable e) {
        super.onNetworkException(e);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }


}