package com.annie.justgymit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void signupClicked(View view) {
        Toast.makeText(this, "Creating account....", Toast.LENGTH_SHORT).show();
    }

    public void loginLinkClicked(View view) {
        Toast.makeText(this,"Directiong to login Activity....",Toast.LENGTH_SHORT).show();
    }
}
