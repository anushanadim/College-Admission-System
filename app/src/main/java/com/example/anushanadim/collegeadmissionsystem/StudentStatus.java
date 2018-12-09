package com.example.anushanadim.collegeadmissionsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentStatus extends AppCompatActivity {

    EditText form;
    Button ok;
    TextView name,formNo,course,regDate,num,add,amt,examDate,qualified;
    DatabaseHelper databaseHelper=new DatabaseHelper(StudentStatus.this,DatabaseHelper.DATABASE_NAME ,null ,1 );
    List<SearchStudentStatus> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_status);

        form=findViewById(R.id.form);
        ok=findViewById(R.id.ok);
        name=findViewById(R.id.name);
        formNo=findViewById(R.id.formNo);
        course=findViewById(R.id.course);
        regDate=findViewById(R.id.regDate);
        num=findViewById(R.id.num);
        add=findViewById(R.id.add);
        amt=findViewById(R.id.amt);
        examDate=findViewById(R.id.examDate);
        qualified=findViewById(R.id.qualified);

        name.setVisibility(View.INVISIBLE);
        formNo.setVisibility(View.INVISIBLE);
        course.setVisibility(View.INVISIBLE);
        regDate.setVisibility(View.INVISIBLE);
        num.setVisibility(View.INVISIBLE);
        add.setVisibility(View.INVISIBLE);
        amt.setVisibility(View.INVISIBLE);
        examDate.setVisibility(View.INVISIBLE);
        qualified.setVisibility(View.INVISIBLE);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int formInt=Integer.valueOf( form.getText().toString());
                list=databaseHelper.search(formInt);

                name.setText("NAME:  "+list.get(0).name);
                formNo.setText("FORM NO:  "+list.get(0).formNo);
                course.setText("COURSE:  "+list.get(0).course);
                regDate.setText("REG DATE:  "+list.get(0).regDate);
                num.setText("NUMBER:  "+list.get(0).num);
                add.setText("ADDRESS:  "+list.get(0).add);
                amt.setText("AMOUNT:  "+list.get(0).amt);
                examDate.setText("EXAM DATE:  "+list.get(0).examDate);
                qualified.setText("QUALIFIED:  "+list.get(0).qualified);
                name.setVisibility(View.VISIBLE);
                formNo.setVisibility(View.VISIBLE);
                course.setVisibility(View.VISIBLE);
                regDate.setVisibility(View.VISIBLE);
                num.setVisibility(View.VISIBLE);
                add.setVisibility(View.VISIBLE);
                amt.setVisibility(View.VISIBLE);
                examDate.setVisibility(View.VISIBLE);
                qualified.setVisibility(View.VISIBLE);




            }
        });
    }


}
