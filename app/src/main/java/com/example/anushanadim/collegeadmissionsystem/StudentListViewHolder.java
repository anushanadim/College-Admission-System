package com.example.anushanadim.collegeadmissionsystem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class StudentListViewHolder extends RecyclerView.ViewHolder {

    TextView name,num,formNo,add,course;

    public StudentListViewHolder(View itemView) {
        super(itemView);

        name=(TextView)itemView.findViewById(R.id.name);
        num=(TextView)itemView.findViewById(R.id.num);
        formNo=(TextView)itemView.findViewById(R.id.formNo);
        add=(TextView)itemView.findViewById(R.id.add);
        course=(TextView)itemView.findViewById(R.id.course);

    }
}
