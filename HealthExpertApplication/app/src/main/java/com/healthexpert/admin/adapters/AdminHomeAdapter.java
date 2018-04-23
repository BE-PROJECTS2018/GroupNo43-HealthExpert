package com.healthexpert.admin.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.healthexpert.R;
import com.healthexpert.common.Config;
import com.healthexpert.data.remote.models.response.Doctor;
import com.healthexpert.ui.widgets.BaseTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by shivani on 12/19/2016.
 */

public class AdminHomeAdapter extends RecyclerView.Adapter<AdminHomeAdapter.NewsFeedViewHolder> {

    ArrayList<Doctor> data;
    private LikeItemUpdateListener commander;

    public AdminHomeAdapter(ArrayList<Doctor> data, LikeItemUpdateListener commander) {
        this.data = data;
        this.commander = commander;
    }

    public interface LikeItemUpdateListener {
        void onItemCardClicked(Doctor home);
    }

    @Override
    public AdminHomeAdapter.NewsFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home, parent, false);
        NewsFeedViewHolder holder = new NewsFeedViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final AdminHomeAdapter.NewsFeedViewHolder holder, final int position) {
        holder.tvName.setText(data.get(position).getName());
        holder.tvEmailId.setText(data.get(position).getEmailid());
        holder.tvPhoneno.setText(data.get(position).getPhoneno());
        if (!data.get(position).getPhoto().isEmpty())
            Picasso.with(holder.itemView.getContext()).load(Config.BASE_URL + data.get(position).getPhoto()).into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NewsFeedViewHolder extends RecyclerView.ViewHolder {

        BaseTextView tvName, tvEmailId, tvPhoneno;
        LinearLayout llItem;
        CircleImageView ivImage;

        public NewsFeedViewHolder(final View itemView) {
            super(itemView);
            tvName = (BaseTextView) itemView.findViewById(R.id.tvName);
            tvEmailId = (BaseTextView) itemView.findViewById(R.id.tvEmailId);
            tvPhoneno = (BaseTextView) itemView.findViewById(R.id.tvPhoneno);
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


}