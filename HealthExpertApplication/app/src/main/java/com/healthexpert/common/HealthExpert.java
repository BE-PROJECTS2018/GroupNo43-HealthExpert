package com.healthexpert.common;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Shivani on 2/11/2018.
 */

public class HealthExpert extends Application {
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate() {
        super.onCreate();
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//        firebaseAuth = FirebaseAuth.getInstance();
//        if (firebaseAuth.getCurrentUser() != null) {
//            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users")
//                    .child(firebaseAuth.getCurrentUser().getUid());
//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    if (dataSnapshot != null) {
//                        databaseReference.child("online").setValue(ServerValue.TIMESTAMP);
//
//                    }
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }
    }
}
