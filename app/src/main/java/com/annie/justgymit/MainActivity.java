package com.annie.justgymit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.annie.justgymit.data.model.RegisterPost;
import com.annie.justgymit.data.remote.APIService;
import com.annie.justgymit.data.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView fieldsemptyResponse;
    private TextView emailusedResponse;
    private APIService mAPIService;
    private static final String TAG = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText firstnameEt = (EditText) findViewById(R.id.firstname);
        final EditText lastnameEt = (EditText) findViewById(R.id.lastname);
        final EditText emailEt = (EditText) findViewById(R.id.email);
        final EditText passwordEt = (EditText) findViewById(R.id.password);
        Button submitBtn = (Button) findViewById(R.id.signupbutton);
        fieldsemptyResponse = (TextView) findViewById(R.id.fieldsempty_response);
        emailusedResponse = (TextView) findViewById(R.id.emailused_response);

        mAPIService = APIUtils.getAPIService();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = firstnameEt.getText().toString().trim();
                String lastname = lastnameEt.getText().toString().trim();
                String email = emailEt.getText().toString().trim();
                String password = passwordEt.getText().toString().trim();
                sendPost(firstname, lastname,email,password);
            }
        });
    }
    public void sendPost(final String firstname, final String lastname, final String email, String password) {
        mAPIService.savePost(firstname, lastname,email,password).enqueue(new Callback<RegisterPost>() {
            @Override
            public void onResponse(Call<RegisterPost> call, Response<RegisterPost> response) {

                if(response.isSuccessful()) {
                    //showResponse(response.body().toString());
                    //validateRegistration(response.body().toString());
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                    if (response.body().getStatus()==1){
                        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                        String[] detailsArray=new String[]{firstname,lastname,email};
                        intent.putExtra("detailsArray",detailsArray);
                        startActivity(intent);
                    }
                    else if(response.body().getStatus()==2){
                        String msgdisplayed=response.body().getMessage();
                        if(emailusedResponse.getVisibility() == View.GONE) {
                            emailusedResponse.setVisibility(View.VISIBLE);
                        }
                        emailusedResponse.setText(msgdisplayed);
                        //showResponse(response.body().getMessage());
                    }
                    else if(response.body().getStatus()==3){
                        String msgdisplayed=response.body().getMessage();
                        if(fieldsemptyResponse.getVisibility() == View.GONE) {
                            fieldsemptyResponse.setVisibility(View.VISIBLE);
                        }
                        fieldsemptyResponse.setText(msgdisplayed);
                    }
                    else if(response.body().getStatus()==4){
                        String msgdisplayed=response.body().getMessage();
                        if(fieldsemptyResponse.getVisibility() == View.GONE) {
                            fieldsemptyResponse.setVisibility(View.VISIBLE);
                        }
                        fieldsemptyResponse.setText(msgdisplayed);
                    }
                    else {
                        if(fieldsemptyResponse.getVisibility() == View.GONE) {
                            fieldsemptyResponse.setVisibility(View.VISIBLE);
                        }
                        fieldsemptyResponse.setText("Please fill in all the fields and use an unregistered email");
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterPost> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    public void loginLinkClicked(View view) {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
