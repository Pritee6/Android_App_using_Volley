package com.example.assignment3disystems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String url = "https://www.breakingbadapi.com/api/characters?limit=10";
    RecyclerView recyclerView;
    DataAdapter adapter;
    ArrayList<DataList> dataLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DataAdapter(this);
        recyclerView.setAdapter(adapter);
        dataLists = new ArrayList<>();
        
        getData();
    }

    private void getData() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i <= response.length(); i++)
                    {
                        JSONObject jsonObject = response.getJSONObject(i);
                        DataList dataList = new DataList();
                        dataList.setImage(jsonObject.getString("img"));
                        dataList.setName(jsonObject.getString("name"));
                        dataList.setBirthday(jsonObject.getString("birthday"));
                        dataList.setOccupation(jsonObject.getString("occupation"));
                        dataLists.add(dataList);
                    }
                } catch (JSONException e) {
                }
                adapter.setData(dataLists);
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Error Occured", Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
