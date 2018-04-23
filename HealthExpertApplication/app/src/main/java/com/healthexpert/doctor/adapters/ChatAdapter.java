package com.healthexpert.doctor.adapters;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.healthexpert.R;
import com.healthexpert.common.Config;
import com.healthexpert.data.remote.models.response.Messages;
import com.healthexpert.ui.widgets.BaseTextView;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Shivani on 2/11/2018.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {

    private List<Messages> mMessageList;
    private DatabaseReference mUserDatabase;

    public ChatAdapter(List<Messages> mMessageList) {

        this.mMessageList = mMessageList;

    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_chat, parent, false);

        return new MessageViewHolder(v);

    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public BaseTextView tvChat, tvChatName;
        LinearLayout chatLayout;
        public CircleImageView ivProfile;
        public BaseTextView tvName;

        public MessageViewHolder(View view) {
            super(view);

            tvChat = (BaseTextView) view.findViewById(R.id.tvChat);
            tvChatName = (BaseTextView) view.findViewById(R.id.tvChatName);
            ivProfile = (CircleImageView) view.findViewById(R.id.ivProfile);
            chatLayout = (LinearLayout) view.findViewById(R.id.chatLayout);

        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(final MessageViewHolder viewHolder, int i) {

        Messages c = mMessageList.get(i);
        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String from_user = c.getFrom();
        if (from_user.equals(currentUserId)) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewHolder.ivProfile.getLayoutParams();
            params.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.removeRule(RelativeLayout.ALIGN_PARENT_START);

            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.ALIGN_PARENT_END);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            viewHolder.ivProfile.setLayoutParams(params);
            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) viewHolder.chatLayout.getLayoutParams();
            params1.removeRule(RelativeLayout.RIGHT_OF);
            params1.removeRule(RelativeLayout.END_OF);

            params1.addRule(RelativeLayout.LEFT_OF, R.id.ivProfile);
            params1.addRule(RelativeLayout.START_OF, R.id.ivProfile);

            viewHolder.chatLayout.setLayoutParams(params1);

            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) viewHolder.tvChatName.getLayoutParams();
            params2.gravity = Gravity.END;
            viewHolder.tvChatName.setLayoutParams(params2);
        } else {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewHolder.ivProfile.getLayoutParams();
            params.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.removeRule(RelativeLayout.ALIGN_PARENT_END);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.addRule(RelativeLayout.ALIGN_PARENT_START);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            viewHolder.ivProfile.setLayoutParams(params);
            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) viewHolder.chatLayout.getLayoutParams();
            params1.removeRule(RelativeLayout.LEFT_OF);
            params1.removeRule(RelativeLayout.START_OF);

            params1.addRule(RelativeLayout.RIGHT_OF, R.id.ivProfile);
            params1.addRule(RelativeLayout.END_OF, R.id.ivProfile);

            viewHolder.chatLayout.setLayoutParams(params1);
            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) viewHolder.tvChatName.getLayoutParams();
            params2.gravity = Gravity.START;
            viewHolder.tvChatName.setLayoutParams(params2);
        }

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(from_user);


        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                viewHolder.tvChatName.setText(name);
                if (!image.equals("default"))
                    Picasso.with(viewHolder.ivProfile.getContext()).load(Config.BASE_URL + image).fit().into(viewHolder.ivProfile);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        viewHolder.tvChat.setText(c.getMessage());


    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }


}