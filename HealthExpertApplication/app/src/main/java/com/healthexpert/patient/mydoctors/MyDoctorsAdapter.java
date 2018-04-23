package com.healthexpert.patient.mydoctors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.healthexpert.R;
import com.healthexpert.common.Config;
import com.healthexpert.data.remote.models.response.DoctorResponse;
import com.healthexpert.ui.widgets.BaseButton;
import com.healthexpert.ui.widgets.BaseTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Shivani on 12/19/2016.
 */

public class MyDoctorsAdapter extends RecyclerView.Adapter<MyDoctorsAdapter.NewsFeedViewHolder> {

    ArrayList<DoctorResponse> data;
    private LikeItemUpdateListener commander;
    BaseButton bCall;

    public MyDoctorsAdapter(ArrayList<DoctorResponse> data, LikeItemUpdateListener commander) {
        this.data = data;
        this.commander = commander;
    }

    public interface LikeItemUpdateListener {
        void onItemCardClicked(DoctorResponse doctorResponse);

        void onCallClicked(DoctorResponse doctorResponse);
    }

    @Override
    public MyDoctorsAdapter.NewsFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_doctor, parent, false);
        NewsFeedViewHolder holder = new NewsFeedViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyDoctorsAdapter.NewsFeedViewHolder holder, final int position) {
        holder.tvName.setText(data.get(position).getName());
        holder.tvSpeciality.setText(data.get(position).getSpeciality());
        holder.tvExp.setText(data.get(position).getExperience() + " years experience");
        holder.tvLikes.setText(data.get(position).getLikes() + " votes");
        holder.tvRating.setText(data.get(position).getRatings().substring(0, 3));
        if (!data.get(position).getPhoto().isEmpty())
            Picasso.with(holder.itemView.getContext()).load(Config.BASE_URL + data.get(position).getPhoto()).into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NewsFeedViewHolder extends RecyclerView.ViewHolder {

        BaseTextView tvName, tvSpeciality, tvExp, tvLikes, tvRating;
        LinearLayout llItem;
        CircleImageView ivImage;

        public NewsFeedViewHolder(final View itemView) {
            super(itemView);
            ivImage = (CircleImageView) itemView.findViewById(R.id.ivImage);
            tvName = (BaseTextView) itemView.findViewById(R.id.tvName);
            bCall = (BaseButton) itemView.findViewById(R.id.bCall);
            tvSpeciality = (BaseTextView) itemView.findViewById(R.id.tvSpeciality);
            tvExp = (BaseTextView) itemView.findViewById(R.id.tvExp);
            tvLikes = (BaseTextView) itemView.findViewById(R.id.tvLikes);
            tvRating = (BaseTextView) itemView.findViewById(R.id.tvRating);
            llItem = (LinearLayout) itemView.findViewById(R.id.llItem);
            bCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (commander != null) {
                        commander.onCallClicked(data.get(getAdapterPosition()));
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

    public void animateTo(ArrayList<DoctorResponse> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(ArrayList<DoctorResponse> newModels) {
        for (int i = data.size() - 1; i >= 0; i--) {
            final DoctorResponse model = data.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(ArrayList<DoctorResponse> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final DoctorResponse model = newModels.get(i);
            if (!data.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(ArrayList<DoctorResponse> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final DoctorResponse model = newModels.get(toPosition);
            final int fromPosition = data.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public DoctorResponse removeItem(int position) {
        final DoctorResponse model = data.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, DoctorResponse model) {
        data.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final DoctorResponse model = data.remove(fromPosition);
        data.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }


}