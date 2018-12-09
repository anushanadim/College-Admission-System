package com.example.anushanadim.collegeadmissionsystem;

import android.content.Intent;
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

public class Qualified extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner course;
    Button submit,ok;
    String courseList[]={"Course","B.tech","MBA","BBA","MCA"};
    NameAdapter adapter;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    List<Name> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualified);

        course=findViewById(R.id.course);
        submit=findViewById(R.id.submit);
        ok=findViewById(R.id.ok);
        nameList=new ArrayList<>();
        recyclerView=findViewById(R.id.name_recycle_view);

        course.setOnItemSelectedListener(this);
        ArrayAdapter array=new ArrayAdapter(this,android.R.layout.simple_spinner_item,courseList);
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        course.setAdapter(array);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseHelper=new DatabaseHelper(Qualified.this,DatabaseHelper.DATABASE_NAME,null,1);
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


                for(Name nameClass : adapter.checkedNameList)
                {
                    int form=(nameClass.getFormNo());
                    databaseHelper.update(form );
                }

                Intent intent=new Intent(Qualified.this,HomePage.class);
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
