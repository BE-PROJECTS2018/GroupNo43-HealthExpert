package com.healthexpert.doctor.fragments;

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
import com.healthexpert.doctor.adapters.DoctorHomeAdapter;
import com.healthexpert.doctor.chat.ChatListActivity;
import com.healthexpert.doctor.doctors.DoctorActivity;
import com.healthexpert.doctor.mypatients.MyPatientsActivity;
import com.healthexpert.doctor.patients.PatientActivity;
import com.healthexpert.doctor.summary.DoctorSummaryActivity;

import java.util.ArrayList;

/**
 * Created by Shivani on 1/28/2017.
 */

public class DoctorHomeFragment extends BaseFragment implements DoctorHomeAdapter.LikeItemUpdateListener {

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
        data.add("Patients");
        data.add("Doctor");
        data.add("My Patients");
        data.add("Summary");
        data.add("Messaging");
        data.add("Profile");
        DoctorHomeAdapter homeAdapter = new DoctorHomeAdapter(data, this);
        rvHome.setAdapter(homeAdapter);

    }


    @Override
    public void onItemCardClicked(int position) {
        switch (position) {
            case 0:
                Intent i = new Intent(getActivity(), PatientActivity.class);
                startActivity(i);
                break;
            case 1:
                Intent i1 = new Intent(getActivity(), DoctorActivity.class);
                startActivity(i1);
                break;
            case 2:
                Intent i2 = new Intent(getActivity(), MyPatientsActivity.class);
                startActivity(i2);
                break;
            case 3:
                Intent i3 = new Intent(getActivity(), DoctorSummaryActivity.class);
                startActivity(i3);
                break;
            case 4:
                Intent i4 = new Intent(getActivity(), ChatListActivity.class);
                startActivity(i4);
                break;
        }

    }
}