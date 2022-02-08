package com.example.assignment3disystems;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataViewHolder> {

    private ArrayList<DataList> dataLists;
    private Context context;

    public DataAdapter(Context context) {

        dataLists = new ArrayList<>();
        this.context = context;
    }

    public void setData(ArrayList<DataList> dataLists)
    {
        this.dataLists = dataLists;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_view_list_items, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

        DataList dataList = dataLists.get(position);
        Picasso.get().load(dataList.image).into(holder.image);
        holder.name.setText("Name: " + dataList.name);
        holder.birthday.setText("Birthday: " + dataList.birthday);
        holder.occupation.setText("Occupation: " + dataList.occupation);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, dataList.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataLists.size();
    }
}
