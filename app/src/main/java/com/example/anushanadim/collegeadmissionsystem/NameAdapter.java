package com.example.anushanadim.collegeadmissionsystem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

public class NameAdapter extends RecyclerView.Adapter<NameViewHolder> {

    List<Name> nameList;
    List<Name> checkedNameList=new ArrayList<>();

    public NameAdapter(List<Name> nameList) {
        this.nameList = nameList;
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.name_recycler_view_design,parent,false);
        NameViewHolder nameViewHolder=new NameViewHolder(view);
        return nameViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NameViewHolder holder, int position) {

        holder.name.setText(nameList.get(position).getName());
        int formNoInt=nameList.get(position).getFormNo();
        holder.formNo.setText(String.valueOf(formNoInt));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {

                CheckBox checkBox=(CheckBox)view;

                if(checkBox.isChecked())
                {
                    checkedNameList.add(nameList.get(pos));
                }
                else
                {
                    checkedNameList.remove(nameList.get(pos));
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }
}
