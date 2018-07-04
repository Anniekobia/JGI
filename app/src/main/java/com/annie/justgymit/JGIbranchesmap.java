package com.annie.justgymit;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class JGIbranchesmap extends AppCompatActivity {

    private static final String TAG = "JGIbranchesmap";
    private static final int ERROR_DIALOG_REQUEST=9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jgibranchesmap);
        if (versionCheck()){
            onMapButtonClicked();
        }
    }

    public void onMapButtonClicked() {
        Button btnmap =(Button) findViewById(R.id.mapButton);
        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JGIbranchesmap.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean versionCheck(){
        Log.d(TAG, "Checking google service version");
        int available= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(JGIbranchesmap.this);
        if (available== ConnectionResult.SUCCESS){
            Log.d(TAG,"Google play services is working");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG,"An error that can be fixed occurred");
            Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(JGIbranchesmap.this,available,ERROR_DIALOG_REQUEST);
        }
        else{
            Toast.makeText(this,"You cant makemap requests",Toast.LENGTH_SHORT).show();
        }
        return false;
    }


}
