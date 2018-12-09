package com.example.anushanadim.collegeadmissionsystem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class NameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView name,formNo;
    CheckBox checkBox;

    ItemClickListener itemClickListener;

    public NameViewHolder(View itemView) {
        super(itemView);

        name=(TextView)itemView.findViewById(R.id.name);
        formNo=(TextView)itemView.findViewById(R.id.formNo);
        checkBox=(CheckBox)itemView.findViewById(R.id.checkbox);

        checkBox.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition() );
    }
}
