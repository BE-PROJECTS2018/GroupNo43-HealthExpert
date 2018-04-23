package com.healthexpert.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.healthexpert.R;
import com.healthexpert.admin.fragments.AdminHomeFragment;
import com.healthexpert.auth.LoginActivity;
import com.healthexpert.auth.LoginContract;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.common.Config;
import com.healthexpert.common.CustomFontLoader;
import com.healthexpert.data.local.SharedPreferenceManager;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.data.remote.models.response.UserResponse;
import com.healthexpert.doctor.fragments.DoctorHomeFragment;
import com.healthexpert.patient.fragments.PatientHomeFragment;
import com.healthexpert.ui.widgets.BaseTextView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


/***
 * MainActivity
 * includes
 * NavigationDrawer.
 */

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, LoginContract.LoginView {
    NavigationView navigationView = null;
    Toolbar toolbar = null;

    @Override
    public void onNetworkException(Throwable e) {
        super.onNetworkException(e);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
//            RetrofitObj userRepository = ((RakshakApp) getApplication()).getComponent().userRepository();
//            LoginPresenter loginPresenter = new LoginPresenter(userRepository, this);
//            loginPresenter.logout(new SharedPreferenceManager(getApplicationContext()).getAccessToken());
            FirebaseAuth.getInstance().signOut();
            new SharedPreferenceManager(getApplicationContext()).removeAllToken();
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        RepairRequestsListActivity fragment = new RepairRequestsListActivity();
//        android.support.v4.app.FragmentTransaction fragmentTransaction =
//                getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragmentTransaction.commit();
        if (new SharedPreferenceManager(getApplicationContext()).getCategory() == 1) {
            AdminHomeFragment adminHomeFragment = new AdminHomeFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, adminHomeFragment);
            fragmentTransaction.commit();
        } else if (new SharedPreferenceManager(getApplicationContext()).getCategory() == 2) {
            DoctorHomeFragment patientFragment = new DoctorHomeFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, patientFragment);
            fragmentTransaction.commit();

        } else if (new SharedPreferenceManager(getApplicationContext()).getCategory() == 3) {
            PatientHomeFragment patientFragment = new PatientHomeFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, patientFragment);
            fragmentTransaction.commit();

        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Config.changeFontInViewGroup(drawer, CustomFontLoader.MONTSERRAT_BOLD);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        //How to change elements in the header programatically
        View headerView = navigationView.getHeaderView(0);
        BaseTextView role = (BaseTextView) headerView.findViewById(R.id.email);
        role.setText(new SharedPreferenceManager(getApplicationContext()).getEmailId());
        BaseTextView username = (BaseTextView) headerView.findViewById(R.id.username);
        username.setText(new SharedPreferenceManager(getApplicationContext()).getName().isEmpty() ? getString(R.string.app_name) : new SharedPreferenceManager(getApplicationContext()).getName());
        CircleImageView ivProfile = (CircleImageView) headerView.findViewById(R.id.profile_image);
        if (!new SharedPreferenceManager(getApplicationContext()).getImage().isEmpty())
            Picasso.with(getApplicationContext()).load(Config.BASE_URL + new SharedPreferenceManager(getApplicationContext()).getImage()).fit().into(ivProfile);
        navigationView.setNavigationItemSelectedListener(this);
        Config.changeFontInViewGroup(drawer, CustomFontLoader.MONTSERRAT);


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                if (new SharedPreferenceManager(getApplicationContext()).getCategory() == 1) {
                    AdminHomeFragment adminHomeFragment = new AdminHomeFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, adminHomeFragment);
                    fragmentTransaction.commit();
                } else if (new SharedPreferenceManager(getApplicationContext()).getCategory() == 2) {
                    DoctorHomeFragment doctorFragment = new DoctorHomeFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, doctorFragment);
                    fragmentTransaction.commit();

                } else if (new SharedPreferenceManager(getApplicationContext()).getCategory() == 3) {
                    PatientHomeFragment patientFragment = new PatientHomeFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, patientFragment);
                    fragmentTransaction.commit();

                }
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                new SharedPreferenceManager(getApplicationContext()).removeAllToken();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onLogin(UserResponse userResponse) {

    }

    @Override
    public void onFuid(UserRegisterResponse userRegisterResponse) {

    }
//
//    @Override
//    public void onLogout(UserResponse userResponse) {
//        if (userResponse.getSuccess()) {
//            dismissProgressDialog();
//            new SharedPreferenceManager(getApplicationContext()).removeAccessToken();
//            new SharedPreferenceManager(getApplicationContext()).removeCategory();
//            new SharedPreferenceManager(getApplicationContext()).removeMainPage();
//            Intent i = new Intent(MainActivity.this, LoginActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(i);
//            finish();
//
//
//        } else {
//            dismissProgressDialog();
//            Toast.makeText(MainActivity.this, "Oops something went wrong.", Toast.LENGTH_SHORT).show();
//        }
//    }

}
