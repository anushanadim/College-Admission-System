package com.example.anushanadim.collegeadmissionsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    EditText username,pass;
    Button login;
    RadioGroup radioGroup;
    RadioButton student,admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        username=findViewById(R.id.username);
        pass=findViewById(R.id.pass);
        login=findViewById(R.id.loginButton);
        radioGroup=findViewById(R.id.radioGroup);
        radioGroup.clearCheck();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                        int id=radioGroup.getCheckedRadioButtonId();

                        if(id==R.id.student)
                        {

                            String usernameStr=username.getText().toString();
                            String passStr=pass.getText().toString();
                            if((usernameStr.equals("Student"))&&(passStr.equals("abcd"))) {

                                Intent intent = new Intent(LoginPage.this, StudentStatus.class);
                                startActivity(intent);
                            }

                            else
                                Toast.makeText(LoginPage.this,"Wrong username or pass",Toast.LENGTH_SHORT).show();

                        }
                        else if(id==R.id.admin)
                        {

                            String usernameStr=username.getText().toString();
                            String passStr=pass.getText().toString();
                            if((usernameStr.equals("Admin"))&&(passStr.equals("abcd"))) {

                                Intent intent = new Intent(LoginPage.this, HomePage.class);
                                startActivity(intent);
                            }

                            else
                                Toast.makeText(LoginPage.this,"Wrong username or pass",Toast.LENGTH_SHORT).show();

                        }




            }
        });


    }
}

