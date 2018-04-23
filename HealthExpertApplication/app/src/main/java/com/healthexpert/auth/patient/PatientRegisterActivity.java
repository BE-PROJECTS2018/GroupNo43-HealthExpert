package com.healthexpert.auth.patient;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.healthexpert.R;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.common.Config;
import com.healthexpert.dashboard.MainActivity;
import com.healthexpert.data.local.SharedPreferenceManager;
import com.healthexpert.data.remote.api.PatientRestService;
import com.healthexpert.data.remote.api.UserRestService;
import com.healthexpert.data.remote.models.requests.PatientRegisterRequest;
import com.healthexpert.data.remote.models.requests.PatientRequest;
import com.healthexpert.data.remote.models.requests.PatientRequestNoIcon;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.SymptomResponse;
import com.healthexpert.data.remote.models.response.SymptomResponseWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.dispatcher.RetrofitObj;
import com.healthexpert.patient.symptom.SymptomPatientContracts;
import com.healthexpert.patient.symptom.SymptomPatientPresenter;
import com.healthexpert.patient.symptom.adapters.SymptomAdapter;
import com.healthexpert.ui.widgets.BaseButton;
import com.healthexpert.ui.widgets.BaseEditText;
import com.healthexpert.ui.widgets.BaseRadioButton;
import com.healthexpert.ui.widgets.BaseTextView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Shivani on 2/8/2018.
 */

public class PatientRegisterActivity extends BaseActivity implements PatientRegisterContracts.PatientRegisterView, SymptomPatientContracts.SymptomPatientView {
    BaseEditText etName, etDob, etHeight, etWeight, etEmailId, etPhoneno, etPassword, etCPassword;
    BaseEditText etOccupation, etSymptoms, etHistory, etInvestigations;
    BaseEditText etPincode, etMotherName, etMotherSymptoms, etFatherName, etFatherSymptoms;
    String symptomsId = "";
    AutoCompleteTextView etCity;
    ImageView ivImage, ivCloseButton;
    FloatingActionButton fabAdd;
    FrameLayout fImage;
    RadioGroup rgGender;
    RadioButton rbGender;
    BaseButton bSubmit;
    PatientRegisterPresenter patientRegisterPresenter;
    SymptomPatientPresenter symptomPatientPresenter;
    RecyclerView rvSymptoms;
    AppCompatSpinner sBloodGroup;
    ArrayList<SymptomResponse> symptomResponses;

    private static final int ADDRESS_CAMERA_IMAGE = 1850;
    private static final int ADDRESS_GALLERY_IMAGE = 1851;
    String path = "";
    SymptomAdapter patientAdapter;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(PatientRegisterActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_register_patient);
        initViews();
    }

    private void initViews() {
        etName = (BaseEditText) findViewById(R.id.etName);
        etDob = (BaseEditText) findViewById(R.id.etDob);
        etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PatientRegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        etHeight = (BaseEditText) findViewById(R.id.etHeight);
        etWeight = (BaseEditText) findViewById(R.id.etWeight);
        etEmailId = (BaseEditText) findViewById(R.id.etEmailId);
        etPassword = (BaseEditText) findViewById(R.id.etPassword);
        etCPassword = (BaseEditText) findViewById(R.id.etCPassword);
        etPhoneno = (BaseEditText) findViewById(R.id.etPhoneno);
        etOccupation = (BaseEditText) findViewById(R.id.etOccupation);
        etSymptoms = (BaseEditText) findViewById(R.id.etSymptom);
        etHistory = (BaseEditText) findViewById(R.id.etHistory);
        sBloodGroup = (AppCompatSpinner) findViewById(R.id.sBloodGroup);
        etInvestigations = (BaseEditText) findViewById(R.id.etInvestigations);
        etCity = (AutoCompleteTextView) findViewById(R.id.etCity);
        etPincode = (BaseEditText) findViewById(R.id.etPincode);
        etMotherName = (BaseEditText) findViewById(R.id.etMname);
        etMotherSymptoms = (BaseEditText) findViewById(R.id.etMS);
        etFatherName = (BaseEditText) findViewById(R.id.etFname);
        etFatherSymptoms = (BaseEditText) findViewById(R.id.etFS);
        bSubmit = (BaseButton) findViewById(R.id.bSubmit);
        ivCloseButton = (ImageView) findViewById(R.id.ivCloseButton);
        fImage = (FrameLayout) findViewById(R.id.fImage);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        PatientRestService patientRestService = RetrofitObj.getInstance().create(PatientRestService.class);
        UserRestService userRestService = RetrofitObj.getInstance().create(UserRestService.class);
        patientRegisterPresenter = new PatientRegisterPresenter(patientRestService, this);
        symptomPatientPresenter = new SymptomPatientPresenter(userRestService, this);
        String[] countries = getResources().getStringArray(R.array.cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        etCity.setAdapter(adapter);
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean status = validate();
                if (status) {
                    if (!path.isEmpty()) {

                        File f = new File(path);
                        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), f);
                        patientRegisterPresenter.registerPatient(new PatientRegisterRequest(etName.getText().toString(),
                                etDob.getText().toString(),
                                rbGender.getText().toString(),
                                etHeight.getText().toString(),
                                etWeight.getText().toString(),
                                etEmailId.getText().toString(),
                                etPhoneno.getText().toString(),
                                etOccupation.getText().toString(),
                                sBloodGroup.getSelectedItem().toString(),
                                symptomsId,
                                etHistory.getText().toString(),
                                etInvestigations.getText().toString(),
                                etCity.getText().toString(),
                                etPincode.getText().toString(),
                                etMotherName.getText().toString(),
                                etMotherSymptoms.getText().toString(),
                                etFatherName.getText().toString(),
                                etFatherSymptoms.getText().toString(),
                                reqFile,
                                etPassword.getText().toString(),
                                FirebaseInstanceId.getInstance().getToken()));
                    } else {
                        patientRegisterPresenter.addPatientNoIcon(new PatientRequestNoIcon(etName.getText().toString(),
                                etDob.getText().toString(),
                                rbGender.getText().toString(),
                                etHeight.getText().toString(),
                                etWeight.getText().toString(),
                                etEmailId.getText().toString(),
                                etPhoneno.getText().toString(),
                                etOccupation.getText().toString(),
                                symptomsId,
                                etHistory.getText().toString(),
                                etInvestigations.getText().toString(),
                                etCity.getText().toString(),
                                etPincode.getText().toString(),
                                etMotherName.getText().toString(),
                                etMotherSymptoms.getText().toString(),
                                etFatherName.getText().toString(),
                                etFatherSymptoms.getText().toString(),
                                sBloodGroup.getSelectedItem().toString(),
                                etPassword.getText().toString(),
                                FirebaseInstanceId.getInstance().getToken()));
                    }
                }
            }
        });
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = rgGender.getCheckedRadioButtonId();
                rbGender = (BaseRadioButton) findViewById(selectedId);

            }
        });
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

        etSymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSymptomsDialog();
            }
        });


    }

    private void showSymptomsDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(PatientRegisterActivity.this);
        dialogBuilder.setTitle("Select Symptoms");
        dialogBuilder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String symptoms = "";
                ArrayList<SymptomResponse> symptomResponses = patientAdapter.getAllSymptoms();
                for (int i = 0; i < symptomResponses.size(); i++) {
                    symptoms += symptomResponses.get(i).getSname() + ",";
                    symptomsId += symptomResponses.get(i).getId() + ",";

                }
                symptoms = symptoms.substring(0, symptoms.length() - 1);
                symptomsId = symptomsId.substring(0, symptomsId.length() - 1);
                etSymptoms.setText(symptoms);
                dialog.dismiss();
            }
        });
        dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_symptoms, null);
        dialogBuilder.setView(dialogView);
        rvSymptoms = (RecyclerView) dialogView.findViewById(R.id.rvSymptoms);
        rvSymptoms.setHasFixedSize(true);
        rvSymptoms.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
        symptomPatientPresenter.symptomPatient();

    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<SymptomResponse> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (int i = 0; i < symptomResponses.size(); i++) {
            //if the existing elements contains the search input
            if (symptomResponses.get(i).getSname().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(symptomResponses.get(i));
            }
        }

        //calling a method of the adapter class and passing the filtered list
        patientAdapter.filterList(filterdNames);
    }

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
        Dialog dialog = new Dialog(PatientRegisterActivity.this);
        dialog.setContentView(R.layout.dialog_image);
        dialog.setTitle("Select Image");
        BaseTextView tvTakePhoto = (BaseTextView) dialog.findViewById(R.id.tvTakePhoto);
        BaseTextView tvGallery = (BaseTextView) dialog.findViewById(R.id.tvGallery);
        tvTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });
        tvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, ADDRESS_GALLERY_IMAGE);
            }
        });
        dialog.show();

    }

    private boolean validate() {
        if (etName.getText().toString().isEmpty()) {
            etName.setFocusable(true);
            etName.setError("Patient name cannot be empty");
            return false;
        } else if (etEmailId.getText().toString().isEmpty()) {
            etEmailId.setFocusable(true);
            etName.setError("Email Id cannot be empty");
            return false;
        } else if (etDob.getText().toString().isEmpty()) {
            etDob.setFocusable(true);
            etDob.setError("Date Of Birth cannot be empty");
            return false;
        } else if (etPhoneno.getText().toString().isEmpty()) {
            etDob.setFocusable(true);
            etDob.setError("Date Of Birth cannot be empty");
            return false;
        } else if (rbGender.getText().toString().isEmpty()) {
            Toast.makeText(PatientRegisterActivity.this, "Gender cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etPincode.getText().toString().isEmpty()) {
            etPincode.setFocusable(true);
            etPincode.setError("Pincode cannot be empty");
            return false;

        } else if (sBloodGroup.getSelectedItem().toString().equals("Select Blood Group")) {
            Toast.makeText(PatientRegisterActivity.this, "Please select you blood group", Toast.LENGTH_SHORT).show();
            return false;
        } else if (symptomsId.isEmpty()) {
            Toast.makeText(PatientRegisterActivity.this, "Please select patient symptoms", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etPassword.getText().toString().isEmpty() || etPassword.getText().length() < 6) {
            etPassword.setFocusable(true);
            etPassword.setError("Password length should be more than 6 characters");
            return false;
        } else if (!etPassword.getText().toString().equals(etCPassword.getText().toString())) {
            etCPassword.setFocusable(true);
            etCPassword.setError("Password doesn't match");
            return false;
        }
        return true;
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
    public void onNetworkException(Throwable e) {
        super.onNetworkException(e);
    }

    @Override
    public void onRegisterPatient(UserRegisterResponse userRegisterResponse) {
        if (userRegisterResponse.isStatus()) {
            Toast.makeText(PatientRegisterActivity.this, userRegisterResponse.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(PatientRegisterActivity.this, userRegisterResponse.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onSymptomPatient(SymptomResponseWrapper symptomResponseWrapper) {
        symptomResponses = new ArrayList<>();
        for (int i = 0; i < symptomResponseWrapper.data.size(); i++) {
            SymptomResponse symptomResponse = new SymptomResponse(symptomResponseWrapper.data.get(i).getId(),
                    symptomResponseWrapper.data.get(i).getSname(), false);
            symptomResponses.add(symptomResponse);
        }
        patientAdapter = new SymptomAdapter(symptomResponses);
        rvSymptoms.setAdapter(patientAdapter);

    }

    @Override
    public void getSymptoms(SymptomResponseWrapper symptomResponseWrapper) {

    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etDob.setText(sdf.format(myCalendar.getTime()));
    }
}
