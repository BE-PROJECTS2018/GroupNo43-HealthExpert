package com.healthexpert.admin.fragments;

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
import com.healthexpert.admin.activities.AdminDoctorDetailsActivity;
import com.healthexpert.admin.adapters.AdminHomeAdapter;
import com.healthexpert.common.BaseFragment;
import com.healthexpert.data.remote.api.UserRestService;
import com.healthexpert.data.remote.models.response.Doctor;
import com.healthexpert.data.remote.models.response.DoctorWrapper;
import com.healthexpert.dispatcher.RetrofitObj;

import java.util.ArrayList;

/**
 * Created by shivani on 1/28/2017.
 */

public class AdminHomeFragment extends BaseFragment implements HomeContract.HomeView, AdminHomeAdapter.LikeItemUpdateListener {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rvHome;
    HomePresenter homePresenter;
    ProgressBar pgProgress;

    @Override
    public void onNetworkException(Throwable e) {
        super.onNetworkException(e);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvHome = (RecyclerView) view.findViewById(R.id.rvHome);
        pgProgress = (ProgressBar) view.findViewById(R.id.pgProgress);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srlHome);
        rvHome.setHasFixedSize(true);
        rvHome.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        UserRestService userRestService = RetrofitObj.getInstance().create(UserRestService.class);
        homePresenter = new HomePresenter(userRestService, this);
        homePresenter.fetchHomeData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homePresenter.fetchHomeData();
            }
        });
        return view;
    }

    @Override
    public void onHomeData(DoctorWrapper doctorWrapper) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        for (int i = 0; i < doctorWrapper.data.size(); i++) {
            Doctor doctor = new Doctor(doctorWrapper.data.get(i).getName()
                    , doctorWrapper.data.get(i).getEmailid()
                    , doctorWrapper.data.get(i).getPincode()
                    , doctorWrapper.data.get(i).getPhoneno()
                    , doctorWrapper.data.get(i).getCity()
                    , doctorWrapper.data.get(i).getSpeciality()
                    , doctorWrapper.data.get(i).getGender()
                    , doctorWrapper.data.get(i).getExperience()
                    , doctorWrapper.data.get(i).getRegid()
                    , doctorWrapper.data.get(i).getAccesstoken()
                    , doctorWrapper.data.get(i).getPhoto()
                    , doctorWrapper.data.get(i).getFuid());
            doctors.add(doctor);
        }
        AdminHomeAdapter adminHomeAdapter = new AdminHomeAdapter(doctors, this);
        rvHome.setAdapter(adminHomeAdapter);
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
        pgProgress.setVisibility(View.GONE);
    }


    @Override
    public void onItemCardClicked(Doctor home) {
        Intent i = new Intent(getActivity(), AdminDoctorDetailsActivity.class);
        i.putExtra("doctor", home);
        i.putExtra("fuid",home.getFuid());
        startActivity(i);
        getActivity().finish();
    }
}