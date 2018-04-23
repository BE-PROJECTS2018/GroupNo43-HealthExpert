package com.healthexpert.auth;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.healthexpert.auth.doctor.DoctorRegisterActivity;
import com.healthexpert.auth.patient.PatientRegisterActivity;
import com.healthexpert.dashboard.MainActivity;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.data.local.SharedPreferenceManager;
import com.healthexpert.data.remote.api.UserRestService;
import com.healthexpert.data.remote.models.requests.FirebaseRequest;
import com.healthexpert.data.remote.models.requests.UserLoginRequest;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.data.remote.models.response.UserResponse;
import com.healthexpert.R;
import com.healthexpert.dispatcher.RetrofitObj;
import com.healthexpert.ui.widgets.BaseButton;
import com.healthexpert.ui.widgets.BaseEditText;
import com.healthexpert.ui.widgets.BaseTextView;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.GET_ACCOUNTS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by Shivani on 1/20/2017.
 */

public class LoginActivity extends BaseActivity implements LoginContract.LoginView {

    BaseTextView tvForgotPassword, tvRegister;
    BaseEditText etEmailId, etPassword;
    BaseButton bLogin;
    LoginPresenter loginPresenter;
    BaseButton bRegisterPatient, bRegisterDoctor;
    ProgressDialog _dialog;
    FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabase;

    @Override
    public void onNetworkException(Throwable e) {
        super.onNetworkException(e);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermission();
        if (new SharedPreferenceManager(getApplicationContext()).getMainPage() != 0) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        setContentView(R.layout.activity_login);
        initViews();
        UserRestService restService = RetrofitObj.getInstance().create(UserRestService.class);
        loginPresenter = new LoginPresenter(restService, this);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status = validate();
                if (status) {
                    showProgressDialog();
                    loginPresenter.login(new UserLoginRequest(etEmailId.getText().toString(), etPassword.getText().toString()));
                    new SharedPreferenceManager(getApplicationContext()).saveEmailId(etEmailId.getText().toString());
                }
            }
        });
        bRegisterDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DoctorRegisterActivity.class);
                startActivity(i);

            }
        });
        bRegisterPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PatientRegisterActivity.class);
                startActivity(i);

            }
        });
    }

    private void initViews() {
        etEmailId = (BaseEditText) findViewById(R.id.etEmailId);
        etPassword = (BaseEditText) findViewById(R.id.etPassword);
        bRegisterPatient = (BaseButton) findViewById(R.id.bRegisterPatient);
        bRegisterDoctor = (BaseButton) findViewById(R.id.bRegisterDoctor);
        bLogin = (BaseButton) findViewById(R.id.bLogin);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
    }

    @Override
    public void onLogin(final UserResponse userResponse) {
        if (userResponse.getSuccess()) {
            if (userResponse.getCategory() == 2)
                FirebaseAuth.getInstance().signInWithEmailAndPassword(etEmailId.getText().toString(), etPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String current_id = firebaseAuth.getCurrentUser().getUid();
                                    String deviceToken = FirebaseInstanceId.getInstance().getToken();
                                    firebaseDatabase.child(current_id).child("device_token").setValue(deviceToken);
                                    new SharedPreferenceManager(getApplicationContext()).saveMainPage(1);
                                    new SharedPreferenceManager(getApplicationContext()).saveAccessToken(userResponse.getAccessToken());
                                    new SharedPreferenceManager(getApplicationContext()).saveCategory(userResponse.getCategory());
                                    new SharedPreferenceManager(getApplicationContext()).saveDeviceToken(deviceToken);

                                    sendFuid(userResponse, current_id);
                                }
                            }
                        });
            else
                login(userResponse);
        } else {
            dismissProgressDialog();
            Toast.makeText(LoginActivity.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFuid(UserRegisterResponse userRegisterResponse) {
        dismissProgressDialog();

        if (userRegisterResponse.isStatus()) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void sendFuid(UserResponse userResponse, String deviceToken) {
        loginPresenter.Fuid(new FirebaseRequest(userResponse.getAccessToken(), deviceToken));
    }

    private void login(UserResponse userResponse) {
        dismissProgressDialog();
        new SharedPreferenceManager(getApplicationContext()).saveMainPage(1);
        new SharedPreferenceManager(getApplicationContext()).saveAccessToken(userResponse.getAccessToken());
        new SharedPreferenceManager(getApplicationContext()).saveCategory(userResponse.getCategory());
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }


    private boolean validate() {
        if (etEmailId.getText().toString().isEmpty()) {
            etEmailId.setError("Email Id cannot be empty");
            etEmailId.setFocusable(true);
            return false;
        } else if (etPassword.getText().toString().isEmpty()) {
            etPassword.setError("Password cannot be empty");
            etPassword.setFocusable(true);
            return false;
        }
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, GET_ACCOUNTS}, 1001);

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(LoginActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1001:
                if (grantResults.length > 0) {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                            showMessageOKCancel("You need to allow access all the permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{ACCESS_FINE_LOCATION, CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, GET_ACCOUNTS},
                                                        1001);
                                            }
                                        }
                                    });
                            return;
                        }
                    }

                }
                break;
        }
    }

}
