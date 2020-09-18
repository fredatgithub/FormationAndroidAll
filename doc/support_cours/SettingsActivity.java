package fr.orsys.gestionclient;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceActivity;


public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }


}
