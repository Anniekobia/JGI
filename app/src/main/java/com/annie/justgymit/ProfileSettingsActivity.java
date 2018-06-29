package com.annie.justgymit;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ProfileSettingsActivity extends AppCompatActivity{
    private EditText gym_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        Spinner spinner_gym_locations = findViewById(R.id.gymlocationspinner);
        ArrayAdapter<CharSequence> adapter_gym_locations = ArrayAdapter.createFromResource(this, R.array.gym_locations_array, android.R.layout.simple_spinner_item);
        adapter_gym_locations.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gym_locations.setAdapter(adapter_gym_locations);

        spinner_gym_locations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_gym_location = (String) parent.getItemAtPosition(position);
                Toast.makeText(ProfileSettingsActivity.this, selected_gym_location, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void showDatePicker(View view) {
        DialogFragment dob_fragment=new DatePickerFragment();
        dob_fragment.show(getSupportFragmentManager(),getString(R.string.dobpicker));
    }

    public void processDatePickerResult(int year, int month, int day) {
        String	month_string=Integer.toString(month	+	1);
        String	day_string	=Integer.toString(day);
        String	year_string	=Integer.toString(year);
        String	dateMessage	=(month_string	+"/"+day_string	+"/"+year_string);
        gym_location= findViewById(R.id.age);
        gym_location.setText(dateMessage);
        Toast.makeText(this,	dateMessage,Toast.LENGTH_SHORT).show();
    }
}

