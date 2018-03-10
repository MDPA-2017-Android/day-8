package com.lasalle.mdpa.busybudgeter.manager.impl;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPreferenceSettingsManagerImpl {

    @Inject SharedPreferences sharedPreferences;


    public void storeStringSettingValue(String key, String value) {
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString(key, value);
        sharedPreferencesEditor.commit();
    }

    public void storeIntSettingValue(String key, int value) {
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putInt(key, value);
        sharedPreferencesEditor.commit();
    }

    public String getStringFromKey(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public int getIntFromKey(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }
}
