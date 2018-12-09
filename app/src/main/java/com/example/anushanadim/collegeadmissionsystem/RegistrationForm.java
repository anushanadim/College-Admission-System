package com.example.anushanadim.collegeadmissionsystem;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class RegistrationForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText formNo,name,add,num,amt,date;
    Button submit,dateButton;
    String courseList[]={"Course","B.tech","MBA","BBA","MCA"};
    Spinner course;
    DatePickerDialog datePickerDialog;
    DatabaseHelper db=new DatabaseHelper(this,DatabaseHelper.DATABASE_NAME,null,1);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        formNo=findViewById(R.id.formNo);
        name=findViewById(R.id.name);
        add=findViewById(R.id.add);
        num=findViewById(R.id.num);
        amt=findViewById(R.id.amount);
        submit=findViewById(R.id.submit);
        course=findViewById(R.id.course);
        date=findViewById(R.id.date);
        dateButton=findViewById(R.id.dateButton);

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

                datePickerDialog=new DatePickerDialog(RegistrationForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        date.setText(dayOfMonth+"-"+month+"-"+year);

                    }
                },year,month,day);

                datePickerDialog.show();

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameStr=name.getText().toString();
                int formNoInt=Integer.valueOf(formNo.getText().toString());
                String courseStr=course.getSelectedItem().toString();
                String dateStr=date.getText().toString();
                int numInt=Integer.valueOf(num.getText().toString());
                String addStr=add.getText().toString();
                int amtInt=Integer.valueOf(amt.getText().toString());
                long rowInserted=db.addData(nameStr,formNoInt,courseStr,dateStr,numInt,addStr,amtInt);

                if(rowInserted==-1)
                Toast.makeText(RegistrationForm.this,"Sorry",Toast.LENGTH_SHORT).show();

                formNo.setText("");
                name.setText("");
                date.setText("");
                num.setText("");
                add.setText("");
                amt.setText("");

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
