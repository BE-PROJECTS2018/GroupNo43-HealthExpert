package com.healthexpert.auth.doctor;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.healthexpert.auth.LoginActivity;
import com.healthexpert.common.Config;
import com.healthexpert.dashboard.MainActivity;
import com.healthexpert.R;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.data.local.SharedPreferenceManager;
import com.healthexpert.data.remote.api.DoctorRestService;
import com.healthexpert.data.remote.models.requests.DoctorRegisterRequest;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.dispatcher.RetrofitObj;
import com.healthexpert.ui.widgets.BaseButton;
import com.healthexpert.ui.widgets.BaseEditText;
import com.healthexpert.ui.widgets.BaseRadioButton;
import com.healthexpert.ui.widgets.BaseTextView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * Created by shivani on 1/13/2017.
 */

public class DoctorRegisterActivity extends BaseActivity implements RegisterContract.RegisterView {
    BaseEditText etFullName, etEmailId, etPhoneNo, etPassword, etCPassword, etCity, etPincode, etRegId, etExperince;
    AppCompatSpinner etSpeciality;
    BaseButton bRegister;
    RadioGroup rgGender;
    ImageView ivImage, ivCloseButton;
    FloatingActionButton fabAdd;
    FrameLayout fImage;
    DatabaseReference firebaseDatabase;
    BaseRadioButton rbGender;
    RegisterPresenter presenter;
    private static final int ADDRESS_CAMERA_IMAGE = 1850;
    private static final int ADDRESS_GALLERY_IMAGE = 1851;
    String path = "";
    private FirebaseAuth firebaseAuth;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case ADDRESS_CAMERA_IMAGE:
                    Toast.makeText(getApplicationContext(), "Image upload success", Toast.LENGTH_SHORT).show();
                    Uri cameraImageUri = Uri.fromFile(new File(path));
                    Picasso.with(getApplicationContext()).load(cameraImageUri).fit().into(ivImage);
                    fImage.setVisibility(View.VISIBLE);
                    break;
                case ADDRESS_GALLERY_IMAGE:
                    Uri selectedImageGallery = data.getData();
                    fImage.setVisibility(View.VISIBLE);
                    path = getRealPathFromURI(selectedImageGallery);
                    Picasso.with(getApplicationContext()).load(selectedImageGallery).fit().into(ivImage);
                    fImage.setVisibility(View.VISIBLE);

            }

        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private void showImageDialog() {
        final Dialog dialog = new Dialog(DoctorRegisterActivity.this);
        dialog.setContentView(R.layout.dialog_image);
        dialog.setTitle("Select Image");
        BaseTextView tvTakePhoto = (BaseTextView) dialog.findViewById(R.id.tvTakePhoto);
        BaseTextView tvGallery = (BaseTextView) dialog.findViewById(R.id.tvGallery);
        dialog.show();
        tvTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
                dialog.dismiss();
            }
        });
        tvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, ADDRESS_GALLERY_IMAGE);
                dialog.dismiss();
            }
        });

    }

    public void takePhoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File folder = new File(Environment.getExternalStorageDirectory() + "/" + Config.IMAGE_DIRECTORY_NAME);

        if (!folder.exists()) {
            folder.mkdir();
        }
        final Calendar c = Calendar.getInstance();
        String dateTime = c.get(Calendar.DAY_OF_MONTH) + "-"
                + ((c.get(Calendar.MONTH)) + 1) + "-"
                + c.get(Calendar.YEAR) + "-"
                + c.get(Calendar.HOUR) + "-"
                + c.get(Calendar.MINUTE) + "-"
                + c.get(Calendar.SECOND);
        path = String.format(Environment.getExternalStorageDirectory() + "/" + Config.IMAGE_DIRECTORY_NAME + "/%s.png",
                dateTime);

        File photo = new File(path);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        startActivityForResult(intent, ADDRESS_CAMERA_IMAGE);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);

        if (new SharedPreferenceManager(getApplicationContext()).getMainPage() != 0) {
            Intent i = new Intent(DoctorRegisterActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        initViews();
        DoctorRestService doctorRestService = RetrofitObj.getInstance().create(DoctorRestService.class);
        presenter = new RegisterPresenter(doctorRestService, this);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean status = validate();
                if (status) {
                    showProgressDialog();
                    firebaseAuth.createUserWithEmailAndPassword(etEmailId.getText().toString(), etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                final String uid = firebaseUser.getUid();
                                firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                                String deviceToken = FirebaseInstanceId.getInstance().getToken();

                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("device_token", deviceToken);
                                hashMap.put("name", etFullName.getText().toString());
                                hashMap.put("image", "default");
                                hashMap.put("thumb_image", "default");
                                hashMap.put("emailid", etEmailId.getText().toString());
                                hashMap.put("phoneno", etPhoneNo.getText().toString());
                                firebaseDatabase.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), etFullName.getText().toString().getBytes());
                                            RequestBody emailid = RequestBody.create(MediaType.parse("text/plain"), etEmailId.getText().toString().getBytes());
                                            RequestBody regid = RequestBody.create(MediaType.parse("text/plain"), etRegId.getText().toString().getBytes());
                                            RequestBody speciality = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(etSpeciality.getSelectedItemPosition() + 1).getBytes());
                                            RequestBody city = RequestBody.create(MediaType.parse("text/plain"), etCity.getText().toString().getBytes());
                                            RequestBody gender = RequestBody.create(MediaType.parse("text/plain"), rbGender.getText().toString().getBytes());
                                            RequestBody pincode = RequestBody.create(MediaType.parse("text/plain"), etPincode.getText().toString().getBytes());
                                            RequestBody experience = RequestBody.create(MediaType.parse("text/plain"), etExperince.getText().toString().getBytes());
                                            RequestBody phoneno = RequestBody.create(MediaType.parse("text/plain"), etPhoneNo.getText().toString().getBytes());
                                            RequestBody password = RequestBody.create(MediaType.parse("text/plain"), etPassword.getText().toString().getBytes());
                                            RequestBody fuid = RequestBody.create(MediaType.parse("text/plain"), uid.getBytes());
                                            File f = new File(path);
                                            RequestBody image = RequestBody.create(MediaType.parse("image/*"), f);
                                            presenter.registerDoctor(name, emailid, regid, speciality, city, gender, pincode, experience, phoneno, password, fuid, image);
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(DoctorRegisterActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                                dismissProgressDialog();
                            }

                        }
                    });

                }


            }
        });

    }

    private void initViews() {
        firebaseAuth = FirebaseAuth.getInstance();
        etFullName = (BaseEditText) findViewById(R.id.etFullName);
        etPhoneNo = (BaseEditText) findViewById(R.id.etPhoneno);
        etEmailId = (BaseEditText) findViewById(R.id.etEmailId);
        etCity = (BaseEditText) findViewById(R.id.etCity);
        etPincode = (BaseEditText) findViewById(R.id.etPincode);
        etSpeciality = (AppCompatSpinner) findViewById(R.id.etSpeciality);
        etPassword = (BaseEditText) findViewById(R.id.etPassword);
        etCPassword = (BaseEditText) findViewById(R.id.etCPassword);
        etRegId = (BaseEditText) findViewById(R.id.etRegisterationNo);
        etExperince = (BaseEditText) findViewById(R.id.etExperience);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        bRegister = (BaseButton) findViewById(R.id.bRegister);
        ivCloseButton = (ImageView) findViewById(R.id.ivCloseButton);
        fImage = (FrameLayout) findViewById(R.id.fImage);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageDialog();
            }
        });
        ivCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fImage.setVisibility(View.GONE);
                path = "";
            }
        });

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = rgGender.getCheckedRadioButtonId();
                rbGender = (BaseRadioButton) findViewById(selectedId);

            }
        });

    }

    private boolean validate() {
        if (etFullName.getText().toString().isEmpty()) {
            etFullName.setError("First name cannot be empty");
            etFullName.setFocusable(true);
            return false;
        } else if (etCity.getText().toString().isEmpty()) {
            etCity.setError("City cannot be empty");
            etCity.setFocusable(true);
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmailId.getText().toString()).matches()) {
            etEmailId.setError("Invalid Email id");
            etEmailId.setFocusable(true);

            return false;
        } else if (etPhoneNo.getText().toString().isEmpty() || etPhoneNo
                .getText().length() < 10) {
            etPhoneNo.setError("Mobile number should be of 10 digits");
            etPhoneNo.setFocusable(true);

            return false;
        } else if (etPassword.getText().toString().isEmpty() || etPassword.getText().length() < 6) {
            etPassword.setError("Password length should be more than 6 characters");
            etPassword.setFocusable(true);
            return false;
        } else if (!etPassword.getText().toString().equals(etCPassword.getText().toString())) {
            etCPassword.setError("Password doesn't match");
            etCPassword.setFocusable(true);
            return false;
        } else if (path.isEmpty()) {
            Toast.makeText(DoctorRegisterActivity.this, "Please select profile icon", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etSpeciality.getSelectedItem().toString().equalsIgnoreCase("Select Specialility")) {
            Toast.makeText(DoctorRegisterActivity.this, "Please select speciality", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    @Override
    public void onNetworkException(Throwable e) {
        super.onNetworkException(e);
        Log.d("ServerException", String.valueOf(e));
    }

    @Override
    public void onDoctorRegister(UserRegisterResponse userResponse) {
        dismissProgressDialog();
        boolean status = userResponse.isStatus();
        if (status) {
            Toast.makeText(DoctorRegisterActivity.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(DoctorRegisterActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(DoctorRegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPatientRegister(UserRegisterResponse userResponse) {

    }
}
