package com.annie.justgymit;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class BranchesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = HomeActivity.class.getSimpleName();
    public String username,firstname,lastname,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches);

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
            Intent intent = new Intent(BranchesActivity.this,HomeActivity.class);
            String[] detailsArray=new String[]{firstname,lastname,email};
            intent.putExtra("detailsArray",detailsArray);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(BranchesActivity.this,ProfileActivity.class);
            String[] detailsArray=new String[]{firstname,lastname,email};
            intent.putExtra("detailsArray",detailsArray);
            startActivity(intent);
        } else if (id == R.id.nav_branches) {
            Intent intent = new Intent(BranchesActivity.this,BranchesActivity.class);
            String[] detailsArray=new String[]{firstname,lastname,email};
            intent.putExtra("detailsArray",detailsArray);
            startActivity(intent);
        } else if (id == R.id.nav_workouts) {
            Intent intent = new Intent(BranchesActivity.this,WorkoutsActivity.class);
            String[] detailsArray=new String[]{firstname,lastname,email};
            intent.putExtra("detailsArray",detailsArray);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
