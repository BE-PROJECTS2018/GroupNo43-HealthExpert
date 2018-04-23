package com.healthexpert.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Shivani on 12/4/2015.
 */
public class SharedPreferenceManager {
    private SharedPreferences settings;

    private static final String PREFS_NAME = "HealtExpertPrefs";
    private static final String PREFS_MOBILENO = "mobileno";
    private static final String PREFS_CATEGORY = "category";
    private static final String PREFS_ACCESS_TOKEN = "accessToken";
    private static final String PREFS_MAINPAGE = "mainpage";
    private static String PREFS_DEVICE_TOKEN = "devicetoken";
    private static String PREFS_FUSER_TOKEN = "firebasetoken";



    public SharedPreferenceManager(Context mContext) {
        settings = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveMobileNo(String mobile) {
        settings.edit().putString(PREFS_MOBILENO, mobile).apply();
    }


    public void saveCategory(int category) {
        settings.edit().putInt(PREFS_CATEGORY, category).apply();
    }

    public void saveMainPage(int page) {
        settings.edit().putInt(PREFS_MAINPAGE, page).apply();
    }

    public void saveAccessToken(String accessToken) {
        settings.edit().putString(PREFS_ACCESS_TOKEN, accessToken).apply();
    }
    public void saveFirebaseToken(String accessToken) {
        settings.edit().putString(PREFS_FUSER_TOKEN, accessToken).apply();
    }

    public String getMobileNo() {
        return settings.getString(PREFS_MOBILENO, null);
    }

    public String getAccessToken() {
        return settings.getString(PREFS_ACCESS_TOKEN, null);
    }

    public int getCategory() {
        return settings.getInt(PREFS_CATEGORY, 0);
    }

    public int getMainPage() {
        return settings.getInt(PREFS_MAINPAGE, 0);
    }

    public void saveDeviceToken(String token) {
        settings.edit().putString(PREFS_DEVICE_TOKEN, token).apply();
    }

    public String getDeviceToken() {
        return settings.getString(PREFS_DEVICE_TOKEN, null);
    }
    public void saveImage(String token) {
        settings.edit().putString("image", token).apply();
    }

    public String getImage() {
        return settings.getString("image", "");
    }
    public void saveName(String token) {
        settings.edit().putString("name", token).apply();
    }

    public String getName() {
        return settings.getString("name", "");
    }
    public void saveEmailId(String token) {
        settings.edit().putString("emailid", token).apply();
    }

    public String getEmailId() {
        return settings.getString("emailid", "");
    }

    public void removeAccessToken() {
        settings.edit().remove(PREFS_ACCESS_TOKEN).apply();
    }

    public void removeCategory() {
        settings.edit().remove(PREFS_CATEGORY).apply();
    }

    public void removeMainPage() {
        settings.edit().remove(PREFS_MAINPAGE).apply();
    }

    public void removeAllToken() {
        settings.edit().clear().apply();
    }


}
