package com.healthexpert.patient.summary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.healthexpert.R;
import com.healthexpert.common.Config;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.PatientSummaryResponse;
import com.healthexpert.ui.widgets.BaseTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Shivani on 12/19/2016.
 */

public class PatientSummaryAdapter extends RecyclerView.Adapter<PatientSummaryAdapter.NewsFeedViewHolder> {

    private ArrayList<PatientSummaryResponse> data;
    private LikeItemUpdateListener commander;

    public PatientSummaryAdapter(ArrayList<PatientSummaryResponse> data, LikeItemUpdateListener commander) {
        this.data = data;
        this.commander = commander;
    }

    public interface LikeItemUpdateListener {
        void onItemCardClicked(PatientSummaryResponse patient);
    }

    @Override
    public PatientSummaryAdapter.NewsFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_summary, parent, false);
        NewsFeedViewHolder holder = new NewsFeedViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final PatientSummaryAdapter.NewsFeedViewHolder holder, final int position) {
        holder.tvName.setText(data.get(position).getDname());
        holder.tvSpeciality.setText(data.get(position).getDspeciality());
        holder.tvPTitle.setText(data.get(position).getPtitle());
        holder.tvTime.setText(data.get(position).getPtimestamp());
        if (!data.get(position).getDphoto().isEmpty())
            Picasso.with(holder.itemView.getContext()).load(Config.BASE_URL + data.get(position).getDphoto()).into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NewsFeedViewHolder extends RecyclerView.ViewHolder {

        CircleImageView ivImage;
        BaseTextView tvName, tvSpeciality, tvPTitle,tvTime;
        LinearLayout llItem;

        public NewsFeedViewHolder(final View itemView) {
            super(itemView);
            tvName = (BaseTextView) itemView.findViewById(R.id.tvName);
            tvSpeciality = (BaseTextView) itemView.findViewById(R.id.tvSpeciality);
            tvPTitle = (BaseTextView) itemView.findViewById(R.id.tvPTitle);
            tvTime = (BaseTextView) itemView.findViewById(R.id.tvTime);
            ivImage = (CircleImageView) itemView.findViewById(R.id.ivImage);
            llItem = (LinearLayout) itemView.findViewById(R.id.llItem);
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

    public void animateTo(ArrayList<PatientSummaryResponse> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(ArrayList<PatientSummaryResponse> newModels) {
        for (int i = data.size() - 1; i >= 0; i--) {
            final PatientSummaryResponse model = data.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(ArrayList<PatientSummaryResponse> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final PatientSummaryResponse model = newModels.get(i);
            if (!data.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(ArrayList<PatientSummaryResponse> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final PatientSummaryResponse model = newModels.get(toPosition);
            final int fromPosition = data.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public PatientSummaryResponse removeItem(int position) {
        final PatientSummaryResponse model = data.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, PatientSummaryResponse model) {
        data.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final PatientSummaryResponse model = data.remove(fromPosition);
        data.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }


}