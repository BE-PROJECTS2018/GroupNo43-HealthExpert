package com.healthexpert.patient.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.healthexpert.R;
import com.healthexpert.common.BaseFragment;
import com.healthexpert.data.local.SharedPreferenceManager;
import com.healthexpert.patient.adapters.PatientHomeAdapter;
import com.healthexpert.patient.doctors.DoctorSpecialityActivity;
import com.healthexpert.patient.mydoctors.MyDoctorsActivity;
import com.healthexpert.patient.profile.PatientProfileActivity;
import com.healthexpert.patient.summary.PatientSummaryActivity;

import java.util.ArrayList;

/**
 * Created by Shivani on 1/28/2017.
 */

public class PatientHomeFragment extends BaseFragment implements PatientHomeAdapter.LikeItemUpdateListener {

    RecyclerView rvHome;

    @Override
    public void onNetworkException(Throwable e) {
        super.onNetworkException(e);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_home, container, false);
        rvHome = (RecyclerView) view.findViewById(R.id.rvHome);
        rvHome.setHasFixedSize(true);
        rvHome.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2, GridLayoutManager.VERTICAL, false));
        fetchData();
        return view;
    }

    public void fetchData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("Doctors");
        data.add("Summary");
        data.add("My Doctors");
        data.add("Profile");
        PatientHomeAdapter homeAdapter = new PatientHomeAdapter(data, this);
        rvHome.setAdapter(homeAdapter);

    }


    @Override
    public void onItemCardClicked(int position) {
        switch (position) {
            case 0:
                Intent i = new Intent(getActivity(), DoctorSpecialityActivity.class);
                startActivity(i);
                break;
            case 1:
                Intent i1 = new Intent(getActivity(), PatientSummaryActivity.class);
                i1.putExtra("accesstoken", new SharedPreferenceManager(getActivity().getApplicationContext()).getAccessToken());
                startActivity(i1);
                break;
            case 2:
                Intent i2 = new Intent(getActivity(), MyDoctorsActivity.class);
                startActivity(i2);
                break;
            case 3:
                Intent i3 = new Intent(getActivity(), PatientProfileActivity.class);
                startActivity(i3);
                break;
        }
    }
}