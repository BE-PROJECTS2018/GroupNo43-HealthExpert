package com.healthexpert.doctor.doctors;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.healthexpert.R;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.common.Config;
import com.healthexpert.data.remote.models.response.DoctorResponse;
import com.healthexpert.doctor.chat.ChatActivity;
import com.healthexpert.ui.widgets.BaseButton;
import com.healthexpert.ui.widgets.BaseTextView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Shivani on 2/11/2018.
 */

public class DoctorDetailsActivity extends BaseActivity {
    CircleImageView circleImageView;
    BaseTextView tvFullName, tvSpeciality, tvExperience, tvLikes, tvEmailId, tvAddress,tvPhoneno;
    AppCompatRatingBar rbRating;
    BaseButton bChat;
    FloatingActionButton fabBookmark;
    DoctorResponse doctorResponse;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_doctor_details, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_TEXT, doctorResponse.getName() + "\n" + doctorResponse.getSpeciality() + "\n" + doctorResponse.getCity() + "\n" + doctorResponse.getPhoneno() + "\n Details from " + getString(R.string.app_name));
                share.setType("text/plain");
                startActivity(share);
                return true;
            case android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.layout_doctors_details);
        initViews();
        doctorResponse = getIntent().getParcelableExtra("doctor");
        tvFullName.setText(doctorResponse.getName());
        tvExperience.setText(doctorResponse.getExperience() + " years experience");
        tvEmailId.setText(doctorResponse.getEmailid());
        tvSpeciality.setText(doctorResponse.getSpeciality());
        tvAddress.setText(doctorResponse.getCity() + " - " + doctorResponse.getPincode());
        tvLikes.setText(doctorResponse.getLikes() + " votes");
        tvPhoneno.setText(doctorResponse.getPhoneno());
        if (!doctorResponse.getRatings().equalsIgnoreCase("None"))
            rbRating.setRating(Float.parseFloat(doctorResponse.getRatings()));
        if (!doctorResponse.getPhoto().isEmpty())
            Picasso.with(getApplicationContext()).load(Config.BASE_URL + doctorResponse.getPhoto()).fit().into(circleImageView);
        bChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorDetailsActivity.this, ChatActivity.class);
                i.putExtra("from_did", doctorResponse.getFuid());
                startActivity(i);
            }
        });
    }

    private void initViews() {
        fabBookmark = (FloatingActionButton) findViewById(R.id.fabBookmark);
        bChat = (BaseButton) findViewById(R.id.bChat);
        circleImageView = (CircleImageView) findViewById(R.id.ivProfile);
        tvFullName = (BaseTextView) findViewById(R.id.tvFullName);
        tvSpeciality = (BaseTextView) findViewById(R.id.tvSpeciality);
        tvExperience = (BaseTextView) findViewById(R.id.tvExp);
        tvLikes = (BaseTextView) findViewById(R.id.tvLikes);
        tvEmailId = (BaseTextView) findViewById(R.id.tvEmailId);
        tvAddress = (BaseTextView) findViewById(R.id.tvAddress);
        tvPhoneno = (BaseTextView) findViewById(R.id.tvPhoneno);
        rbRating = (AppCompatRatingBar) findViewById(R.id.rbRate);
    }

}
