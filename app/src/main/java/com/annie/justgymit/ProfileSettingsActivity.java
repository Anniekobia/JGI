package com.annie.justgymit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ProfileSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        Spinner spinner_gym_locations = findViewById(R.id.gymlocationspinner);
        ArrayAdapter <CharSequence> adapter_gym_locations = ArrayAdapter.createFromResource(this,R.array.gym_locations_array,android.R.layout.simple_spinner_item);
        adapter_gym_locations.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gym_locations.setAdapter(adapter_gym_locations);
    }

}
