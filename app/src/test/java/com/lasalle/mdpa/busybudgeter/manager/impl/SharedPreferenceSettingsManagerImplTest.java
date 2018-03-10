package com.lasalle.mdpa.busybudgeter.manager.impl;

import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.inject.Inject;

import toothpick.testing.ToothPickRule;

import static junit.framework.Assert.assertEquals;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SharedPreferenceSettingsManagerImplTest {
    @Rule
    public ToothPickRule toothPickRule = new ToothPickRule(this, "SharedPreferenceSettingsManagerImpl");

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private SharedPreferences sharedPreferences;

    @Mock
    private SharedPreferences.Editor sharedPreferencesEditor;

    @Inject SharedPreferenceSettingsManagerImpl sharedPreferenceSettingsManager;

    @Before
    public void setup() {
        toothPickRule.inject(this);
    }

    @Test
    public void checkWriteStringIntoSharedPreferencesWorksProperly()
    {
        when(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor);

        String key = "my_key";
        String value = "my_value";

        sharedPreferenceSettingsManager.storeStringSettingValue(key, value);

        verify(sharedPreferences, times(1)).edit();
        verify(sharedPreferencesEditor, times(1)).putString(eq(key), eq(value));
        verify(sharedPreferencesEditor, times(1)).commit();
    }

    @Test
    public void checkWriteIntIntoSharedPreferencesWorksProperly()
    {
        when(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor);

        String key = "my_key";
        int value = 10;

        sharedPreferenceSettingsManager.storeIntSettingValue(key, value);

        verify(sharedPreferences, times(1)).edit();
        verify(sharedPreferencesEditor, times(1)).putInt(eq(key), eq(value));
        verify(sharedPreferencesEditor, times(1)).commit();
    }

    @Test
    public void readExistingStringFromSharedPreferences()
    {
        String key = "my_key";
        String value = "my_value";

        when(sharedPreferences.getString(eq(key), anyString())).thenReturn(value);

        String returnedValue = sharedPreferenceSettingsManager.getStringFromKey(key, "");
        assertEquals(value, returnedValue);
    }

    @Test
    public void readNonExistingStringFromSharedPreferences()
    {
        String key = "my_key";
        String defaultValue = "hola";

        when(sharedPreferences.getString(not(eq(key)), anyString())).thenReturn(defaultValue);

        String returnedValue = sharedPreferenceSettingsManager.getStringFromKey("testing", defaultValue);
        assertEquals(defaultValue, returnedValue);
    }

    @Test
    public void readExistingIntFromSharedPreferences()
    {
        String key = "my_key";
        int value = 10;

        when(sharedPreferences.getInt(eq(key), anyInt())).thenReturn(value);

        int returnedValue = sharedPreferenceSettingsManager.getIntFromKey(key, 0);
        assertEquals(value, returnedValue);
    }

    @Test
    public void readNonExistingIntFromSharedPreferences()
    {
        String key = "my_key";
        int defaultValue = -10;

        when(sharedPreferences.getInt(not(eq(key)), anyInt())).thenReturn(defaultValue);

        int returnedValue = sharedPreferenceSettingsManager.getIntFromKey("testing", defaultValue);
        assertEquals(defaultValue, returnedValue);
    }

}
