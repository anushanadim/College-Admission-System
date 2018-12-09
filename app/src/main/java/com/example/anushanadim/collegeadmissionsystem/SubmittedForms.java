package com.example.anushanadim.collegeadmissionsystem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SubmittedForms extends AppCompatActivity {

    StudentListAdapter adapter;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    List<StudentList> studentListAraay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitted_forms);

        studentListAraay=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler_view);

        databaseHelper=new DatabaseHelper(this,DatabaseHelper.DATABASE_NAME,null,1);
        studentListAraay=databaseHelper.show();
        adapter=new StudentListAdapter(studentListAraay);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
