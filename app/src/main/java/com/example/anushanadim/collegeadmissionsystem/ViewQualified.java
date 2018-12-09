package com.example.anushanadim.collegeadmissionsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ViewQualified extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner course;
    Button ok;
    String courseList[]={"Course","B.tech","MBA","BBA","MCA"};

    DatabaseHelper databaseHelper;

    StudentListAdapter adapter;
    RecyclerView recyclerView;
    List<StudentList> studentListAraay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_qualified);

        course=findViewById(R.id.course);
        ok=findViewById(R.id.ok);

        studentListAraay=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler_view);

        course.setOnItemSelectedListener(this);
        ArrayAdapter array=new ArrayAdapter(this,android.R.layout.simple_spinner_item,courseList);
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        course.setAdapter(array);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseHelper=new DatabaseHelper(ViewQualified.this,DatabaseHelper.DATABASE_NAME , null,1 );
                String courseStr=course.getSelectedItem().toString();
                String where="course='"+courseStr+"' AND qualified='true'";
                studentListAraay=databaseHelper.showAll(where);

                adapter=new StudentListAdapter(studentListAraay);
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);


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
