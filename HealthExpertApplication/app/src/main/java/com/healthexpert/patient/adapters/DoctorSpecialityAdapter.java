package com.healthexpert.patient.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.healthexpert.R;
import com.healthexpert.common.Config;
import com.healthexpert.data.remote.models.response.DoctorResponse;
import com.healthexpert.data.remote.models.response.Speciality;
import com.healthexpert.data.remote.models.response.SymptomResponse;
import com.healthexpert.ui.widgets.BaseButton;
import com.healthexpert.ui.widgets.BaseTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Shivani on 12/19/2016.
 */

public class DoctorSpecialityAdapter extends RecyclerView.Adapter<DoctorSpecialityAdapter.NewsFeedViewHolder> {

    ArrayList<Speciality> data;
    private LikeItemUpdateListener commander;

    public DoctorSpecialityAdapter(ArrayList<Speciality> data, LikeItemUpdateListener commander) {
        this.data = data;
        this.commander = commander;
    }
    public void filterList(ArrayList<Speciality> filterdNames) {
        data = filterdNames;
        notifyDataSetChanged();
    }


    public interface LikeItemUpdateListener {
        void onItemCardClicked(Speciality speciality);
        void onInfoClicked(Speciality speciality);
    }

    @Override
    public DoctorSpecialityAdapter.NewsFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_speciality, parent, false);
        NewsFeedViewHolder holder = new NewsFeedViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final DoctorSpecialityAdapter.NewsFeedViewHolder holder, final int position) {
        holder.tvName.setText(data.get(position).getS_name());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NewsFeedViewHolder extends RecyclerView.ViewHolder {

        BaseTextView tvName;
        LinearLayout llItem;
        ImageView ivImage;

        public NewsFeedViewHolder(final View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivInfo);
            tvName = (BaseTextView) itemView.findViewById(R.id.tvSname);
            llItem = (LinearLayout) itemView.findViewById(R.id.llItem);
            ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(commander!=null){
                        commander.onInfoClicked(data.get(getAdapterPosition()));
                    }
                }
            });
            llItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (commander != null) {
                        commander.onItemCardClicked(data.get(getAdapterPosition()));
                    }
                }
            });

        }

    }

    public ArrayList<Speciality> getSpecialities(){
        return data;
    }


}