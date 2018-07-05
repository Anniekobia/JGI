package com.annie.justgymit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.annie.justgymit.data.model.LoginPost;
import com.annie.justgymit.data.model.RegisterPost;
import com.annie.justgymit.data.remote.APIService;
import com.annie.justgymit.data.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = null ;
    private TextView loginResponse;
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText emailfield=findViewById(R.id.email);
        final EditText passwordfield=findViewById(R.id.signinpassword);
        Button submitBtn = (Button) findViewById(R.id.signinbutton);
        loginResponse = (TextView) findViewById(R.id.login_response);

        mAPIService = APIUtils.getAPIService();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailfield.getText().toString().trim();
                String password = passwordfield.getText().toString().trim();
                sendPost(email, password);
            }
        });
    }
    public void sendPost(String email,String password) {
        mAPIService.saveLoginPost(email,password).enqueue(new Callback<LoginPost>() {
            @Override
            public void onResponse(Call<LoginPost> call, Response<LoginPost> response) {

                if(response.isSuccessful()) {
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                    if (response.body().getStatus()==1){
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }
                    else if(response.body().getStatus()==2){
                        String msgdisplayed=response.body().getMessage();
                        if(loginResponse.getVisibility() == View.GONE) {
                            loginResponse.setVisibility(View.VISIBLE);
                        }
                        loginResponse.setText(msgdisplayed);
                        //showResponse(response.body().getMessage());
                    }
                    else{
                        String msgdisplayed=response.body().getMessage();
                        if(loginResponse.getVisibility() == View.GONE) {
                            loginResponse.setVisibility(View.VISIBLE);
                        }
                        loginResponse.setText(msgdisplayed);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginPost> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    public void recoverPasswordClicked(View view) {
        Toast.makeText(this,"Recovering Password",Toast.LENGTH_SHORT).show();
    }
}
