package com.example.anushanadim.collegeadmissionsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static android.provider.Contacts.SettingsColumns.KEY;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="CollegeAdmission.db";
    public static final String TABLE_NAME="Students";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_FORMNO="formNo";
    public static final String COLUMN_COURSE="course";
    public static final String COLUMN_REG_DATE="regDate";
    public static final String COLUMN_NUM="num";
    public static final String COLUMN_ADDRESS="address";
    public static final String COLUMN_AMT="amt";
    public static final String COLUMN_EXAM_DATE="examDate";
    public static final String COLUMN_QUALIFIED="qualified";


    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable="CREATE TABLE "+TABLE_NAME+
                " ("+COLUMN_FORMNO+" Integer ,"
                +COLUMN_NAME+" Text,"
                +COLUMN_COURSE+" Text,"
                +COLUMN_REG_DATE+" Text,"
                +COLUMN_NUM+" Integer, "
                +COLUMN_ADDRESS+" Text,"
                +COLUMN_AMT+" Integer,"
                +COLUMN_EXAM_DATE+ " Text,"
                +COLUMN_QUALIFIED+" Text);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+";");
        onCreate(db);
    }

    public long addData(String name,int formNo, String course, String date, int num, String add, int amt)
    {

        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_FORMNO,formNo);
        contentValues.put(COLUMN_NAME,name);
        contentValues.put(COLUMN_COURSE,course);
        contentValues.put(COLUMN_REG_DATE,date);
        contentValues.put(COLUMN_NUM,num);
        contentValues.put(COLUMN_ADDRESS,add);
        contentValues.put(COLUMN_AMT,amt);
        contentValues.put(COLUMN_EXAM_DATE,"null");
        contentValues.put(COLUMN_QUALIFIED,"false");

       long rowInserted= db.insert(TABLE_NAME,null,contentValues);
       /*if(rowInserted==-1)
           onCreate(db);*/

       return rowInserted;


    }

    public List<StudentList> show()
    {
        List<StudentList> studentListArrayList=new ArrayList<>();

        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+";",null);

        StringBuffer stringBuffer=new StringBuffer();

        while (cursor.moveToNext())
        {
            StudentList studentList=new StudentList();

            String name=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            int num=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NUM));
            int formNo=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FORMNO));
            String add=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS));
            String course=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE));

            studentList.setName(name);
            studentList.setNum(num);
            studentList.setFormNo(formNo);
            studentList.setAdd(add);
            studentList.setCourse(course);

            stringBuffer.append(studentList);
            studentListArrayList.add(studentList);
        }

        return studentListArrayList;

    }

    public List<StudentList> showAll(String where)
    {
        List<StudentList> studentListArrayList=new ArrayList<>();

        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE " + where+ ";",null);

        StringBuffer stringBuffer=new StringBuffer();

        while (cursor.moveToNext())
        {
            StudentList studentList=new StudentList();

            String name=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            int num=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NUM));
            int formNo=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FORMNO));
            String add=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS));
            String course=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE));

            studentList.setName(name);
            studentList.setNum(num);
            studentList.setFormNo(formNo);
            studentList.setAdd(add);
            studentList.setCourse(course);

            stringBuffer.append(studentList);
            studentListArrayList.add(studentList);
        }

        return studentListArrayList;

    }

    public List<Name> showName(String where)
    {
        List<Name> nameList=new ArrayList<>();

        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE " + where+ ";",null);

        StringBuffer stringBuffer=new StringBuffer();

        while (cursor.moveToNext())
        {
            Name nameClass=new Name();

            String name=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            int formNo=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FORMNO));

            nameClass.setName(name);
            nameClass.setFormNo(formNo);

            stringBuffer.append(nameClass);
            nameList.add(nameClass);
        }

        return nameList;

    }

    public void update(int formNo,String date)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_EXAM_DATE, date);
        db.update(TABLE_NAME,contentValues ,"formNo="+formNo ,null );
    }
    public void update(int formNo )
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_QUALIFIED,"true");
        db.update(TABLE_NAME,contentValues ,"formNo="+formNo ,null );
    }
    public List<SearchStudentStatus> search(int form)
    {

        List<SearchStudentStatus> searchStudentStatuseList=new ArrayList<>();
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE formNo=" + form+ ";",null);

        StringBuffer stringBuffer=new StringBuffer();

        while (cursor.moveToNext())
        {
            SearchStudentStatus searchStudentStatus=new SearchStudentStatus();

            String name=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            int num=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NUM));
            int formNo=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FORMNO));
            String add=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS));
            String course=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE));
            String regDate=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_REG_DATE));
            String examDate=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXAM_DATE));
            String qualified=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUALIFIED));
            int amt=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AMT));

            searchStudentStatus.setName(name);
            searchStudentStatus.setNum(num);
            searchStudentStatus.setFormNo(formNo);
            searchStudentStatus.setAdd(add);
            searchStudentStatus.setCourse(course);
            searchStudentStatus.setRegDate(regDate);
            searchStudentStatus.setExamDate(examDate);
            searchStudentStatus.setQualified(qualified);
            searchStudentStatus.setAmt(amt);

            stringBuffer.append(searchStudentStatus);
            searchStudentStatuseList.add(searchStudentStatus);

        }

        return searchStudentStatuseList;

    }

}
