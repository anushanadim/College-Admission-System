package com.example.anushanadim.collegeadmissionsystem;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListViewHolder> {


   List<StudentList> studentListArrayList;

    public StudentListAdapter(List<StudentList> studentListArrayList) {
        this.studentListArrayList = studentListArrayList;
    }




    @NonNull
    @Override
    public StudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_design,parent,false);
        StudentListViewHolder studentListViewHolder=new StudentListViewHolder(view);
        return studentListViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull StudentListViewHolder holder, int position) {


        holder.name.setText(studentListArrayList.get(position).getName());
        String numStr=String.valueOf(studentListArrayList.get(position).getNum());
        holder.num.setText(numStr);
        String formNoStr=String.valueOf(studentListArrayList.get(position).getFormNo());
        holder.formNo.setText(formNoStr);
        holder.add.setText(studentListArrayList.get(position).getAdd());
        holder.course.setText(studentListArrayList.get(position).getCourse());

    }

    @Override
    public int getItemCount() {
        return studentListArrayList.size();    }




}
