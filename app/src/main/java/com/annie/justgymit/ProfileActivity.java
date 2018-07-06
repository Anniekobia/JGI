package com.annie.justgymit;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.annie.justgymit.data.model.RegisterPost;
import com.annie.justgymit.data.model.UpdateUserDetailsPost;
import com.annie.justgymit.data.remote.APIService;
import com.annie.justgymit.data.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = ProfileActivity.class.getSimpleName();
    private APIService mAPIService;
    public String username,firstname,lastname,email;
    final EditText ageet=findViewById(R.id.ageEt);
    final EditText genderet=findViewById(R.id.genderEt);
    final EditText weightet=findViewById(R.id.weightEt);
    final EditText weightgoalet=findViewById(R.id.weightgoalEt);
    final EditText homegymet=findViewById(R.id.homegymEt);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent i = getIntent();
        String[] arraydetails = i.getStringArrayExtra("detailsArray");
        username=arraydetails[0]+" "+arraydetails[1];
        firstname=arraydetails[0];
        lastname=arraydetails[1];
        email =arraydetails[2];
        TextView txtProfileName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_header_title);
        txtProfileName.setText(username);
        EditText txtProfileNameSettings = findViewById(R.id.profileusernameEt);
        txtProfileNameSettings.setText(username);

        //updateuserdetails
        Button submitBtn =findViewById(R.id.profilesettingsbtn);
        mAPIService = APIUtils.getAPIService();
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer age = Integer.parseInt(ageet.getText().toString());
                String gender = genderet.getText().toString().trim();
                Integer weight = Integer.parseInt(weightet.getText().toString());
                Integer weightgoal = Integer.parseInt(weightgoalet.getText().toString());
                String homegym = homegymet.getText().toString().trim();
            }
        });
    }
    public void sendPost(final Integer age, final String gender, final Integer weight, final Integer weightgoal,final String homegym) {
        mAPIService.saveUserDetailsPost(age, gender,weight,weightgoal,homegym).enqueue(new Callback<UpdateUserDetailsPost>() {
            @Override
            public void onResponse(Call<UpdateUserDetailsPost> call, Response<UpdateUserDetailsPost> response) {

                if(response.isSuccessful()) {
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                    if (response.body().getStatus()==1){
                        Integer age = response.body().getAge();
                        String gender = response.body().getGender();
                        Integer weight = response.body().getWeight();
                        Integer weightgoal = response.body().getWeightgoal();
                        String homegym= response.body().getHomegym();

                        ageet.setText(age);
                        genderet.setText(gender);
                        weightet.setText(weight);
                        weightgoalet.setText(weightgoal);
                        homegymet.setText(homegym);
                    }else{
                        //nothing happens
                    }

                }
            }

            @Override
            public void onFailure(Call<UpdateUserDetailsPost> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(ProfileActivity.this,HomeActivity.class);
            String[] detailsArray=new String[]{firstname,lastname,email};
            intent.putExtra("detailsArray",detailsArray);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(ProfileActivity.this,ProfileActivity.class);
            String[] detailsArray=new String[]{firstname,lastname,email};
            intent.putExtra("detailsArray",detailsArray);
            startActivity(intent);
        } else if (id == R.id.nav_branches) {
            Intent intent = new Intent(ProfileActivity.this,BranchesActivity.class);
            String[] detailsArray=new String[]{firstname,lastname,email};
            intent.putExtra("detailsArray",detailsArray);
            startActivity(intent);
        } else if (id == R.id.nav_workouts) {
            Intent intent = new Intent(ProfileActivity.this,WorkoutsActivity.class);
            String[] detailsArray=new String[]{firstname,lastname,email};
            intent.putExtra("detailsArray",detailsArray);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
