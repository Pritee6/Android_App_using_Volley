package com.example.assignment3disystems;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataViewHolder extends RecyclerView.ViewHolder {

    LinearLayout linearlayout;
    ImageView image;
    TextView name;
    TextView birthday;
    TextView occupation;

    public DataViewHolder(@NonNull View itemView) {
        super(itemView);

        linearlayout = itemView.findViewById(R.id.linearlayout);
        image = itemView.findViewById(R.id.image);
        name = itemView.findViewById(R.id.txt_name);
        birthday = itemView.findViewById(R.id.txt_birthday);
        occupation = itemView.findViewById(R.id.txt_occupation);
    }
}
