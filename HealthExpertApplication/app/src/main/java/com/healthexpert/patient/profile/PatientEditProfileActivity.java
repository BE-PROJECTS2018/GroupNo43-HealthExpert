package com.healthexpert.patient.profile;

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
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.healthexpert.R;
import com.healthexpert.auth.patient.PatientRegisterActivity;
import com.healthexpert.auth.patient.PatientRegisterContracts;
import com.healthexpert.auth.patient.PatientRegisterPresenter;
import com.healthexpert.common.BaseActivity;
import com.healthexpert.common.Config;
import com.healthexpert.data.local.SharedPreferenceManager;
import com.healthexpert.data.remote.api.PatientRestService;
import com.healthexpert.data.remote.api.UserRestService;
import com.healthexpert.data.remote.models.requests.PatientRegisterRequest;
import com.healthexpert.data.remote.models.requests.PatientRequestNoIcon;
import com.healthexpert.data.remote.models.requests.PatientUpdateRequestNoIcon;
import com.healthexpert.data.remote.models.response.Patient;
import com.healthexpert.data.remote.models.response.PatientWrapper;
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

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Shivani on 1/3/18.
 */

public class PatientEditProfileActivity extends BaseActivity implements PatientProfileContract.PatientView, SymptomPatientContracts.SymptomPatientView {
    @Override
    public void onNetworkException(Throwable e) {
        super.onNetworkException(e);
    }

    BaseEditText etName, etDob, etHeight, etWeight, etEmailId, etPhoneno, etPassword, etCPassword;
    BaseEditText etOccupation, etSymptoms, etHistory, etInvestigations;
    BaseEditText etPincode, etMotherName, etMotherSymptoms, etFatherName, etFatherSymptoms;
    String symptomsId = "";
    AutoCompleteTextView etCity;
    CircleImageView ivProfile;
    RecyclerView rvSymptoms;
    AppCompatSpinner sBloodGroup;
    ArrayList<SymptomResponse> symptomResponses;
    String path = "";
    SymptomAdapter patientAdapter;
    RadioGroup rgGender;
    BaseButton bSubmit;
    CoordinatorLayout clLayout;
    Calendar myCalendar = Calendar.getInstance();
    PatientProfilePresenter profilePresenter;
    SymptomPatientPresenter symptomPatientPresenter;
    BaseRadioButton rbGender;
    private static final int ADDRESS_CAMERA_IMAGE = 1850;
    private static final int ADDRESS_GALLERY_IMAGE = 1851;
    Patient patient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile_edit);
        patient = getIntent().getParcelableExtra("patient");
        initViews();
        setViews();
    }

    private void setViews() {
        etName.setText(patient.getName());
        etCity.setText(patient.getCity());
        etPincode.setText(patient.getPincode());
        etHeight.setText(patient.getHeight());
        etWeight.setText(patient.getWeight());
        etPhoneno.setText(patient.getPhoneno());
        for(int i = 0 ;i<sBloodGroup.getCount();i++){
            if(sBloodGroup.getItemAtPosition(i).equals(patient.getBloodgroup())){
                sBloodGroup.setSelection(i);
                break;
            }
        }
        
        etHistory.setText(patient.getHistory());
        etInvestigations.setText(patient.getInvestigations());
        etOccupation.setText(patient.getOccupation());
        etMotherName.setText(patient.getMothername());
        etMotherSymptoms.setText(patient.getMothersymptoms());
        etFatherName.setText(patient.getFathername());
        etFatherSymptoms.setText(patient.getFathersymptoms());
        etDob.setText(patient.getDob());
        if (!patient.getPhoto().isEmpty())
            Picasso.with(getApplicationContext()).load(Config.BASE_URL + patient.getPhoto()).fit().into(ivProfile);
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
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void initViews() {
        clLayout = (CoordinatorLayout) findViewById(R.id.clLayout);
        etName = (BaseEditText) findViewById(R.id.etFullName);
        etEmailId = (BaseEditText) findViewById(R.id.etEmailId);
        etOccupation = (BaseEditText) findViewById(R.id.etOccupation);
        etHeight = (BaseEditText) findViewById(R.id.etHeight);
        etWeight = (BaseEditText) findViewById(R.id.etWeight);
        etName = (BaseEditText) findViewById(R.id.etName);
        etDob = (BaseEditText) findViewById(R.id.etDob);
        etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PatientEditProfileActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        etHeight = (BaseEditText) findViewById(R.id.etHeight);
        etWeight = (BaseEditText) findViewById(R.id.etWeight);
        etEmailId = (BaseEditText) findViewById(R.id.etEmailId);
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
        ivProfile = (CircleImageView) findViewById(R.id.ivProfile);
        bSubmit = (BaseButton) findViewById(R.id.bSubmit);
        PatientRestService patientRestService = RetrofitObj.getInstance().create(PatientRestService.class);
        UserRestService userRestService = RetrofitObj.getInstance().create(UserRestService.class);
        profilePresenter = new PatientProfilePresenter(patientRestService, this);
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
                        profilePresenter.updatePatient(RequestBody.create(MediaType.parse("text/plain"),etName.getText().toString().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),etDob.getText().toString().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),rbGender.getText().toString().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),etHeight.getText().toString().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),etWeight.getText().toString().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),etEmailId.getText().toString().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),etPhoneno.getText().toString().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),etOccupation.getText().toString().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),sBloodGroup.getSelectedItem().toString().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),symptomsId.getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),etHistory.getText().toString().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),etInvestigations.getText().toString().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),etCity.getText().toString().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),etPincode.getText().toString().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),etMotherName.getText().toString().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),etMotherSymptoms.getText().toString().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),etFatherName.getText().toString().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),etFatherSymptoms.getText().toString().getBytes()),
                                reqFile,
                                RequestBody.create(MediaType.parse("text/plain"),FirebaseInstanceId.getInstance().getToken().getBytes()),
                                RequestBody.create(MediaType.parse("text/plain"),new SharedPreferenceManager(getApplicationContext()).getAccessToken().getBytes()));
                    } else {
                        profilePresenter.updatePatientNoIcon(new PatientUpdateRequestNoIcon(etName.getText().toString(),
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
                                FirebaseInstanceId.getInstance().getToken(),
                                new SharedPreferenceManager(getApplicationContext()).getAccessToken()));
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

        etSymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSymptomsDialog();
            }
        });
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageDialog();
            }
        });

    }

    private void showSymptomsDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(PatientEditProfileActivity.this);
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
                    Picasso.with(getApplicationContext()).load(cameraImageUri).fit().into(ivProfile);
                    break;
                case ADDRESS_GALLERY_IMAGE:
                    Uri selectedImageGallery = data.getData();
                    path = getRealPathFromURI(selectedImageGallery);
                    Picasso.with(getApplicationContext()).load(selectedImageGallery).fit().into(ivProfile);


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
        Dialog dialog = new Dialog(PatientEditProfileActivity.this);
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
            Toast.makeText(PatientEditProfileActivity.this, "Gender cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etPincode.getText().toString().isEmpty()) {
            etPincode.setFocusable(true);
            etPincode.setError("Pincode cannot be empty");
            return false;

        } else if (sBloodGroup.getSelectedItem().toString().equals("Select Blood Group")) {
            Toast.makeText(PatientEditProfileActivity.this, "Please select you blood group", Toast.LENGTH_SHORT).show();
            return false;
        } else if (symptomsId.isEmpty()) {
            Toast.makeText(PatientEditProfileActivity.this, "Please select patient symptoms", Toast.LENGTH_SHORT).show();
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

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etDob.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onPatient(PatientWrapper patientWrapper) {

    }

    @Override
    public void onUpdate(UserRegisterResponse userRegisterResponse) {
        Snackbar.make(clLayout, userRegisterResponse.getMessage(), Snackbar.LENGTH_SHORT).show();

    }
}
