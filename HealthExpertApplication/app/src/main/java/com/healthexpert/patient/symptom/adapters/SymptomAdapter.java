package com.healthexpert.patient.symptom.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.healthexpert.R;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.SymptomResponse;
import com.healthexpert.ui.widgets.BaseCheckbox;
import com.healthexpert.ui.widgets.BaseTextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Shivani on 12/19/2016.
 */

public class SymptomAdapter extends RecyclerView.Adapter<SymptomAdapter.NewsFeedViewHolder> {

    public ArrayList<SymptomResponse> data;
    public ArrayList<SymptomResponse> maindata;

    public SymptomAdapter(ArrayList<SymptomResponse> data) {
        this.data = data;
        maindata = new ArrayList<>();
    }

    public void filterList(ArrayList<SymptomResponse> filterdNames) {
        data = filterdNames;
        notifyDataSetChanged();
    }


    @Override
    public SymptomAdapter.NewsFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_symptoms, parent, false);
        NewsFeedViewHolder holder = new NewsFeedViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final SymptomAdapter.NewsFeedViewHolder holder, final int position) {
        holder.tvSname.setText(data.get(position).getSname());
        holder.cCheck.setChecked(data.get(position).isCheck());
        holder.cCheck.setTag(position);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NewsFeedViewHolder extends RecyclerView.ViewHolder {

        BaseTextView tvSname;
        BaseCheckbox cCheck;
        LinearLayout llItem;

        public NewsFeedViewHolder(final View itemView) {
            super(itemView);
            tvSname = (BaseTextView) itemView.findViewById(R.id.tvSname);
            cCheck = (BaseCheckbox) itemView.findViewById(R.id.cCheck);
            llItem = (LinearLayout) itemView.findViewById(R.id.llItem);
            llItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (data.get(getAdapterPosition()).isCheck()) {
                        cCheck.setChecked(false);
                        data.get(getAdapterPosition()).setCheck(false);
                        maindata.remove(data.get(getAdapterPosition()));
                    } else {
                        cCheck.setChecked(true);
                        data.get(getAdapterPosition()).setCheck(true);
                        maindata.add(data.get(getAdapterPosition()));
                    }
                }
            });
            cCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    data.get(getAdapterPosition()).setCheck(isChecked);
                    if (isChecked) {
                        maindata.add(data.get(getAdapterPosition()));
                    } else {
                        maindata.remove(data.get(getAdapterPosition()));
                    }
                }
            });


        }

    }

    public ArrayList<SymptomResponse> getSymptoms() {
        return data;
    }

    public ArrayList<SymptomResponse> getAllSymptoms() {
        return maindata;
    }

    public void animateTo(List<SymptomResponse> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<SymptomResponse> newModels) {
        for (int i = data.size() - 1; i >= 0; i--) {
            final SymptomResponse model = data.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<SymptomResponse> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final SymptomResponse model = newModels.get(i);
            if (!data.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<SymptomResponse> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final SymptomResponse model = newModels.get(toPosition);
            final int fromPosition = data.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public SymptomResponse removeItem(int position) {
        final SymptomResponse model = data.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, SymptomResponse model) {
        data.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final SymptomResponse model = data.remove(fromPosition);
        data.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

}