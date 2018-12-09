package com.example.anushanadim.collegeadmissionsystem;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ViewSchedule extends AppCompatActivity {

    EditText date;
    Button dateButton,ok;
    DatePickerDialog datePickerDialog;
    DatabaseHelper databaseHelper;

    StudentListAdapter adapter;
    RecyclerView recyclerView;
    List<StudentList> studentListAraay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);

        date=findViewById(R.id.date);
        dateButton=findViewById(R.id.dateButton);
        ok=findViewById(R.id.ok);

        studentListAraay=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler_view);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c=Calendar.getInstance();
                int year=c.get(Calendar.YEAR);
                int month=c.get(Calendar.MONTH);
                int day=c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog=new DatePickerDialog(ViewSchedule.this, new DatePickerDialog.OnDateSetListener() {
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

                databaseHelper=new DatabaseHelper(ViewSchedule.this,DatabaseHelper.DATABASE_NAME , null,1 );
                String dateStr=date.getText().toString();
                String where="examDate='"+dateStr+"'";
                studentListAraay=databaseHelper.showAll(where);

                adapter=new StudentListAdapter(studentListAraay);
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);


            }
        });

    }
}
