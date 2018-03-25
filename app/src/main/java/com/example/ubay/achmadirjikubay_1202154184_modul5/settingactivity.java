package com.example.ubay.achmadirjikubay_1202154184_modul5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class settingactivity extends AppCompatActivity {
    public static final String KEY_PREF_EXAMPLE_SWITCH = "example_switch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingFragment())
                .commit();

    }
}
