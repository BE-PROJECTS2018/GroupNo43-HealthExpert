package com.healthexpert.doctor.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.healthexpert.R;
import com.healthexpert.data.remote.models.response.Doctor;
import com.healthexpert.ui.widgets.BaseTextView;

import java.util.ArrayList;


/**
 * Created by Shivani on 12/19/2016.
 */

public class DoctorHomeAdapter extends RecyclerView.Adapter<DoctorHomeAdapter.NewsFeedViewHolder> {

    ArrayList<String> data;
    private LikeItemUpdateListener commander;

    public DoctorHomeAdapter(ArrayList<String> data, LikeItemUpdateListener commander) {
        this.data = data;
        this.commander = commander;
    }

    public interface LikeItemUpdateListener {
        void onItemCardClicked(int position);
    }

    @Override
    public DoctorHomeAdapter.NewsFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_patient_home, parent, false);
        NewsFeedViewHolder holder = new NewsFeedViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final DoctorHomeAdapter.NewsFeedViewHolder holder, final int position) {
        holder.categoryName.setText(data.get(position));
        switch (data.get(position)) {
            case "Doctors":
                holder.categoryIcon.setImageResource(R.drawable.doctor);
                break;
            case "My Doctors":
                holder.categoryIcon.setImageResource(R.drawable.mydoctors);
                break;
            case "Profile":
                holder.categoryIcon.setImageResource(R.drawable.profile);
                break;
            case "Summary":
                holder.categoryIcon.setImageResource(R.drawable.summary);
                break;
            case "Patients":
                holder.categoryIcon.setImageResource(R.drawable.patient);
                break;
            case "My Patients":
                holder.categoryIcon.setImageResource(R.drawable.mypatients);
                break;
            case "Messaging":
                holder.categoryIcon.setImageResource(R.drawable.chats);
                break;

        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NewsFeedViewHolder extends RecyclerView.ViewHolder {
        FrameLayout container;
        private BaseTextView categoryName;
        private ImageView categoryIcon;
        private ImageView categoryBackground;

        public NewsFeedViewHolder(final View itemView) {
            super(itemView);
            container = (FrameLayout) itemView.findViewById(R.id.categoryAdapterContainer);
            categoryName = (BaseTextView) itemView.findViewById(R.id.tvCatName);
            categoryIcon = (ImageView) itemView.findViewById(R.id.ivIconChar);
            categoryBackground = (ImageView) itemView.findViewById(R.id.categoryBackground);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(commander!=null)
                        commander.onItemCardClicked(getAdapterPosition());
                }
            });
        }

    }


}