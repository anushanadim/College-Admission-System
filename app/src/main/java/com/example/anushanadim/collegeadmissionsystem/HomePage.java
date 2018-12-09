package com.example.anushanadim.collegeadmissionsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        drawerLayout = findViewById(R.id.my_drawer);
        navigationView = findViewById(R.id.nav_view);
        text=findViewById(R.id.texts);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);

                switch (item.getItemId())
                {
                    case R.id.register:
                        Intent intent=new Intent(HomePage.this,RegistrationForm.class);
                        startActivity(intent);
                        break;

                    case R.id.submittedForms:
                        Intent intent1=new Intent(HomePage.this,SubmittedForms.class);
                        startActivity(intent1);
                        break;

                    case  R.id.scheduleDate:
                        Intent intent2=new Intent(HomePage.this,ScheduleExam.class);
                        startActivity(intent2);
                        break;

                    case R.id.viewSchedule:
                        Intent intent3=new Intent(HomePage.this,ViewSchedule.class);
                        startActivity(intent3);
                        break;

                    case R.id.qualified:
                        Intent intent4=new Intent(HomePage.this,Qualified.class);
                        startActivity(intent4);
                        break;

                    case R.id.viewQualified:
                        Intent intent5=new Intent(HomePage.this,ViewQualified.class);
                        startActivity(intent5);
                        break;

                }

                drawerLayout.closeDrawers();
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}

