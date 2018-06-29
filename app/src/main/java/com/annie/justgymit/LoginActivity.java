package com.annie.justgymit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText usernamefield,passwordfield;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernamefield=findViewById(R.id.username);
        passwordfield=findViewById(R.id.signinpassword);
    }

    public void loginClicked(View view) {
        String username=usernamefield.getText().toString();
        String password=passwordfield.getText().toString();
        String type ="Login";
        DatabaseWorker dbworker=new DatabaseWorker(this);
        dbworker.execute(type,username,password);
        //Toast.makeText(this,"Logging in.....",Toast.LENGTH_SHORT).show();
    }

    public void recoverPasswordClicked(View view) {
        Toast.makeText(this,"Recovering Password",Toast.LENGTH_SHORT).show();
    }
}
