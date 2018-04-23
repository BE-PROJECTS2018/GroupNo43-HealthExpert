package com.healthexpert.doctor.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.healthexpert.R;

import com.healthexpert.common.Config;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.ui.widgets.BaseTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Shivani on 12/19/2016.
 */

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.NewsFeedViewHolder> {

    private ArrayList<Patient> data;
    private LikeItemUpdateListener commander;

    public PatientAdapter(ArrayList<Patient> data, LikeItemUpdateListener commander) {
        this.data = data;
        this.commander = commander;
    }

    public interface LikeItemUpdateListener {
        void onItemCardClicked(Patient patient);
    }

    @Override
    public PatientAdapter.NewsFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_patient, parent, false);
        NewsFeedViewHolder holder = new NewsFeedViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final PatientAdapter.NewsFeedViewHolder holder, final int position) {
        holder.tvName.setText(data.get(position).getName());
        holder.tvPhoneno.setText(data.get(position).getPhoneno());
        holder.tvCity.setText(data.get(position).getCity());
        if (!data.get(position).getPhoto().isEmpty())
            Picasso.with(holder.itemView.getContext()).load(Config.BASE_URL + data.get(position).getPhoto()).into(holder.ivImage);
//        else
//            holder.ivImage.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NewsFeedViewHolder extends RecyclerView.ViewHolder {

        BaseTextView tvName, tvPhoneno, tvCity;
        CircleImageView ivImage;
        LinearLayout llItem;

        public NewsFeedViewHolder(final View itemView) {
            super(itemView);
            tvName = (BaseTextView) itemView.findViewById(R.id.tvName);
            tvPhoneno = (BaseTextView) itemView.findViewById(R.id.tvPhoneno);
            tvCity = (BaseTextView) itemView.findViewById(R.id.tvCity);
            ivImage = (CircleImageView) itemView.findViewById(R.id.ivImage);
            llItem = (LinearLayout) itemView.findViewById(R.id.llItem);
            llItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (commander != null)
                        commander.onItemCardClicked(data.get(getAdapterPosition()));

                }
            });

        }

    }

    public void animateTo(ArrayList<Patient> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(ArrayList<Patient> newModels) {
        for (int i = data.size() - 1; i >= 0; i--) {
            final Patient model = data.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(ArrayList<Patient> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Patient model = newModels.get(i);
            if (!data.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(ArrayList<Patient> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Patient model = newModels.get(toPosition);
            final int fromPosition = data.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Patient removeItem(int position) {
        final Patient model = data.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Patient model) {
        data.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Patient model = data.remove(fromPosition);
        data.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }



}