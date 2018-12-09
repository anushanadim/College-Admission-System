package com.example.anushanadim.collegeadmissionsystem;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ScheduleExam extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner course;
    EditText date;
    String courseList[]={"Course","B.tech","MBA","BBA","MCA"};
    Button dateButton,ok,submit;
    DatePickerDialog datePickerDialog;
    NameAdapter adapter;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    List<Name> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_exam);

        course=findViewById(R.id.course);
        date=findViewById(R.id.date);
        dateButton=findViewById(R.id.dateButton);
        ok=findViewById(R.id.ok);
        submit=findViewById(R.id.submit);

        nameList=new ArrayList<>();
        recyclerView=findViewById(R.id.name_recycle_view);

        course.setOnItemSelectedListener(this);
        ArrayAdapter array=new ArrayAdapter(this,android.R.layout.simple_spinner_item,courseList);
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        course.setAdapter(array);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c=Calendar.getInstance();
                int year=c.get(Calendar.YEAR);
                int month=c.get(Calendar.MONTH);
                int day=c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog=new DatePickerDialog(ScheduleExam.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        date.setText(dayOfMonth+"-"+month+"-"+year);

                    }
                },year,month,day);

                datePickerDialog.show();

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseHelper=new DatabaseHelper(ScheduleExam.this,DatabaseHelper.DATABASE_NAME,null,1);
                String courseStr=course.getSelectedItem().toString();
                String where="course='"+courseStr+"'";
                nameList=databaseHelper.showName(where);
                adapter=new NameAdapter(nameList);
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String dateStr=date.getText().toString();
            for(Name nameClass : adapter.checkedNameList)
            {
               int form=(nameClass.getFormNo());
               databaseHelper.update(form,dateStr );
            }

                Intent intent=new Intent(ScheduleExam.this,HomePage.class);
                startActivity(intent);

            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
