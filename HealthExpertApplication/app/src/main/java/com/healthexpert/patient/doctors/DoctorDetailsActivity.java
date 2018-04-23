package com.healthexpert.patient.doctors;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.healthexpert.R;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.common.Config;
import com.healthexpert.data.local.SharedPreferenceManager;
import com.healthexpert.data.remote.api.PatientRestService;
import com.healthexpert.data.remote.models.requests.BookmarkRequest;
import com.healthexpert.data.remote.models.requests.DoctorCheckFeedback;
import com.healthexpert.data.remote.models.requests.DoctorFeedback;
import com.healthexpert.data.remote.models.response.DoctorResponse;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.SpecialityWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.dispatcher.RetrofitObj;
import com.healthexpert.ui.widgets.BaseButton;
import com.healthexpert.ui.widgets.BaseTextView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Shivani on 2/11/2018.
 */

public class DoctorDetailsActivity extends BaseActivity implements DoctorContract.DoctorView {
    CircleImageView circleImageView;
    BaseTextView tvFullName, tvSpeciality, tvExperience, tvLikes, tvEmailId, tvAddress;
    AppCompatRatingBar rbRating;
    BaseButton bCall, bFeedback;
    FloatingActionButton fabBookmark;
    DoctorResponse doctorResponse;
    CoordinatorLayout clLayout;
    DoctorPresenter doctorPresenter;

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
        setContentView(R.layout.layout_doctor_details);
        initViews();
        doctorResponse = getIntent().getParcelableExtra("doctor");
        showProgressDialog();
        tvFullName.setText(doctorResponse.getName());
        tvExperience.setText(doctorResponse.getExperience() + " years experience");
        tvEmailId.setText(doctorResponse.getEmailid());
        tvSpeciality.setText(doctorResponse.getSpeciality());
        tvAddress.setText(doctorResponse.getCity() + " - " + doctorResponse.getPincode());
        tvLikes.setText(doctorResponse.getLikes() + " votes");
        if (!doctorResponse.getRatings().equalsIgnoreCase("None"))
            rbRating.setRating(Float.parseFloat(doctorResponse.getRatings()));
        if (!doctorResponse.getPhoto().isEmpty())
            Picasso.with(getApplicationContext()).load(Config.BASE_URL + doctorResponse.getPhoto()).fit().centerCrop().into(circleImageView);
        PatientRestService patientRestService = RetrofitObj.getInstance().create(PatientRestService.class);
        doctorPresenter = new DoctorPresenter(patientRestService, this);
        doctorPresenter.bookmarkCheck(new BookmarkRequest(new SharedPreferenceManager(getApplicationContext()).getAccessToken(),doctorResponse.getAccesstoken(), 2, 3));
        bFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog();
                doctorPresenter.checkDoctorFeedback(new DoctorCheckFeedback(doctorResponse.getAccesstoken(),
                        new SharedPreferenceManager(getApplicationContext()).getAccessToken()));
            }
        });
        fabBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog();
                if (fabBookmark.getTag().equals("1"))
                    doctorPresenter.bookmarkDoctor(new BookmarkRequest(new SharedPreferenceManager(getApplicationContext()).getAccessToken(),doctorResponse.getAccesstoken(),
                            2,
                            3));
                else
                    doctorPresenter.bookmarkDoctor(new BookmarkRequest(new SharedPreferenceManager(getApplicationContext()).getAccessToken(),doctorResponse.getAccesstoken(),
                            1,
                            3));
            }
        });
    }

    private void initViews() {
        fabBookmark = (FloatingActionButton) findViewById(R.id.fabBookmark);
        bCall = (BaseButton) findViewById(R.id.bCall);
        bFeedback = (BaseButton) findViewById(R.id.bFeedback);
        circleImageView = (CircleImageView) findViewById(R.id.ivProfile);
        tvFullName = (BaseTextView) findViewById(R.id.tvFullName);
        tvSpeciality = (BaseTextView) findViewById(R.id.tvSpeciality);
        tvExperience = (BaseTextView) findViewById(R.id.tvExp);
        tvLikes = (BaseTextView) findViewById(R.id.tvLikes);
        tvEmailId = (BaseTextView) findViewById(R.id.tvEmailId);
        tvAddress = (BaseTextView) findViewById(R.id.tvAddress);
        rbRating = (AppCompatRatingBar) findViewById(R.id.rbRate);
        clLayout = (CoordinatorLayout) findViewById(R.id.clLayout);
        bCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                String p = "tel:" + doctorResponse.getPhoneno();
                i.setData(Uri.parse(p));
                startActivity(i);
            }
        });
    }

    @Override
    public void onDoctorData(DoctorResponseWrapper doctorResponseWrapper) {
        //NOT REQUIRED
    }

    @Override
    public void onSpeciality(SpecialityWrapper specialityWrapper) {
        //NOT REQUIRED
    }

    @Override
    public void onCheckDoctorFeedback(UserRegisterResponse userRegisterResponse) {
        dismissProgressDialog();
        if (userRegisterResponse.isStatus()) {
            Intent i = new Intent(DoctorDetailsActivity.this, DoctorFeedbackActivity.class);
            i.putExtra("doctor", doctorResponse);
            startActivity(i);
        } else {
            Snackbar.make(clLayout, userRegisterResponse.getMessage(), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFeedback(UserRegisterResponse userRegisterResponse) {

    }

    @Override
    public void onBookmarkDoctor(UserRegisterResponse userRegisterResponse) {
        dismissProgressDialog();
        if (userRegisterResponse.isStatus()) {
            fabBookmark.setImageResource(R.drawable.star_fill);
            fabBookmark.setTag("1");

        } else {
            fabBookmark.setImageResource(R.drawable.star_outline);
            fabBookmark.setTag("2");
        }
        Snackbar.make(clLayout, userRegisterResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckBookmark(UserRegisterResponse userRegisterResponse) {
        dismissProgressDialog();
        if (userRegisterResponse.isStatus()) {
            fabBookmark.setImageResource(R.drawable.star_fill);
            fabBookmark.setTag("1");

        } else {
            fabBookmark.setImageResource(R.drawable.star_outline);
            fabBookmark.setTag("2");
        }
    }
}
