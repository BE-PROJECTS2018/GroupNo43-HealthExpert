package com.healthexpert.doctor.chat;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.healthexpert.R;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.common.Config;
import com.healthexpert.common.GetTimeAgo;
import com.healthexpert.data.remote.api.DoctorRestService;
import com.healthexpert.data.remote.models.requests.MessageRequest;
import com.healthexpert.data.remote.models.response.Messages;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.dispatcher.RetrofitObj;
import com.healthexpert.doctor.adapters.ChatAdapter;
import com.healthexpert.ui.widgets.BaseEditText;
import com.healthexpert.ui.widgets.BaseTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Shivani on 2/11/2018.
 */

public class ChatActivity extends BaseActivity implements ChatContracts.ChatView {
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference, rootReference;
    ImageButton ibSend;
    BaseEditText etMessage;
    BaseTextView tvTitle, tvLastSeen;
    CircleImageView custom_bar_image;
    String currentUserId;
    String userId;
    RecyclerView rvChats;
    List<Messages> messagesList;
    LinearLayoutManager linearLayoutManager;
    ChatAdapter chatAdapter;
    SwipeRefreshLayout srlMessages;
    final static int TOTAL_ITEMS_TO_LOAD = 10;
    int currentPage = 1;
    int itemPos = 0;
    String lastKey = "";
    String prevKey = "";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.chat_app_bar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        messagesList = new ArrayList<>();
        userId = getIntent().getStringExtra("from_did");
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.chat_custom_layout, null);
        actionBar.setCustomView(view);
        initViews();
        rootReference.child("Users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String chat_name = (String) dataSnapshot.child("name").getValue();
                String image = (String) dataSnapshot.child("image").getValue();
                tvTitle.setText(chat_name);
                if(!image.equals("default"))
                    Picasso.with(getApplicationContext()).load(Config.BASE_URL+image).fit().into(custom_bar_image);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        rootReference.child("Users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String online = dataSnapshot.child("online").getValue().toString();
                String image = dataSnapshot.child("image").getValue().toString();
                if (online.equals("true")) {
                    tvLastSeen.setText("Online");
                } else {
                    GetTimeAgo getTimeAgo = new GetTimeAgo();
                    Long lastTime = Long.parseLong(online);
                    String lastSeenTime = getTimeAgo.getTimeAgo(lastTime, getApplicationContext());
                    tvLastSeen.setText(lastSeenTime);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        rootReference.child("Chat").child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(userId)) {
                    Map<String, Object> chatAddMap = new HashMap<>();
                    chatAddMap.put("seen", false);
                    chatAddMap.put("timestamp", ServerValue.TIMESTAMP);

                    Map chatUserMap = new HashMap();
                    chatUserMap.put("Chat/" + currentUserId + "/" + userId, chatAddMap);
                    chatUserMap.put("Chat/" + userId + "/" + currentUserId, chatAddMap);

                    rootReference.updateChildren(chatUserMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError != null) {
                                Log.d("Chat Log", databaseError.getMessage());
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ibSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }


    private void sendMessage() {
        String message = etMessage.getText().toString();
        if (!message.isEmpty()) {
            String currentUserRef = "messages/" + currentUserId + "/" + userId;
            String chatUserRef = "messages/" + userId + "/" + currentUserId;
            Map messageMap = new HashMap();
            messageMap.put("message", message);
            messageMap.put("seen", false);
            messageMap.put("type", "text");
            messageMap.put("time", ServerValue.TIMESTAMP);
            messageMap.put("from", currentUserId);

            DatabaseReference userMessagePush = databaseReference.child("messages")
                    .child(currentUserId).child(userId).push();

            String pushId = userMessagePush.getKey();
            Map messageUserMap = new HashMap();
            messageUserMap.put(currentUserRef + "/" + pushId, messageMap);
            messageUserMap.put(chatUserRef + "/" + pushId, messageMap);

            rootReference.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null)
                        Log.d("Chat Log", databaseError.getMessage());
                }
            });

            sendNotification(currentUserId, userId,etMessage.getText().toString());

            etMessage.setText("");
        }
        srlMessages.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage++;
                itemPos = 0;
                loadMoreMessages();
            }
        });
    }

    private void loadMoreMessages() {
        DatabaseReference messageRef = rootReference.child("messages").child(currentUserId).child(userId);
        final Query messageQuery = messageRef.orderByKey().endAt(lastKey).limitToLast(10);
        messageQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Messages messages = dataSnapshot.getValue(Messages.class);
                String messageKey = dataSnapshot.getKey();
                messagesList.add(itemPos++, messages);
                if (!prevKey.equals(messageKey)) {
                    messagesList.add(itemPos++, messages);
                } else {
                    prevKey = lastKey;
                }

                if (itemPos == 1) {
                    lastKey = messageKey;
                }
                chatAdapter.notifyDataSetChanged();
                rvChats.scrollToPosition(messagesList.size() - 1);
                if (srlMessages.isRefreshing())
                    srlMessages.setRefreshing(false);
                linearLayoutManager.scrollToPositionWithOffset(10, 0);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.child("online").setValue("true");
    }

    @Override
    protected void onStop() {
        super.onStop();
        databaseReference.child("online").setValue(ServerValue.TIMESTAMP);
    }

    private void initViews() {
        firebaseAuth = FirebaseAuth.getInstance();
        currentUserId = firebaseAuth.getCurrentUser().getUid();
        if (firebaseAuth.getCurrentUser() != null)
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getCurrentUser().getUid());
        rootReference = FirebaseDatabase.getInstance().getReference();
        ibSend = (ImageButton) findViewById(R.id.ibSend);
        etMessage = (BaseEditText) findViewById(R.id.etMessage);
        custom_bar_image = (CircleImageView) findViewById(R.id.custom_bar_image);
        tvTitle = (BaseTextView) findViewById(R.id.tvTitle);
        tvLastSeen = (BaseTextView) findViewById(R.id.tvLastSeen);
        rvChats = (RecyclerView) findViewById(R.id.rvChats);
        srlMessages = (SwipeRefreshLayout) findViewById(R.id.srlMessages);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvChats.setLayoutManager(linearLayoutManager);
        rvChats.setHasFixedSize(true);
        chatAdapter = new ChatAdapter(messagesList);
        rvChats.setAdapter(chatAdapter);
        rootReference.child("Chat").child(currentUserId).child(userId).child("seen").setValue(true);
        loadMessages();


    }

    private void loadMessages() {
        DatabaseReference messageRef = rootReference.child("messages").child(currentUserId).child(userId);
        Query messageQuery = messageRef.limitToLast(currentPage * TOTAL_ITEMS_TO_LOAD);
        messageQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Messages messages = dataSnapshot.getValue(Messages.class);
                itemPos++;
                if (itemPos == 1) {
                    lastKey = dataSnapshot.getKey();
                    prevKey = dataSnapshot.getKey();
                }
                messagesList.add(messages);
                chatAdapter.notifyDataSetChanged();
                rvChats.scrollToPosition(messagesList.size() - 1);
                if (srlMessages.isRefreshing())
                    srlMessages.setRefreshing(false);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void sendNotification(String source_fuid, String destination_fuid,String message) {
        DoctorRestService doctorRestService = RetrofitObj.getInstance().create(DoctorRestService.class);
        ChatPresenter chatPresenter = new ChatPresenter(doctorRestService, this);
        chatPresenter.sendNotification(new MessageRequest(source_fuid, destination_fuid,message,"com.healthexpert.doctorchat_TARGET_NOTIFICATION"));
    }

    @Override
    public void onNotification(UserRegisterResponse userRegisterResponse) {
        Toast.makeText(getApplicationContext(), userRegisterResponse.getMessage(),Toast.LENGTH_SHORT).show();
    }
}
