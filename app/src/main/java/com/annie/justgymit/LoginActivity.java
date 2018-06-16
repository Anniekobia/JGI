package com.annie.justgymit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginClicked(View view) {
        Toast.makeText(this,"Logging in.....",Toast.LENGTH_SHORT).show();
    }

    public void recoverPasswordClicked(View view) {
        Toast.makeText(this,"Recovering Password",Toast.LENGTH_SHORT).show();
    }
}
