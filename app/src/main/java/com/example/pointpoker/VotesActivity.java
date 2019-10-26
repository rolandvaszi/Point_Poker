package com.example.pointpoker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class VotesActivity extends AppCompatActivity {
    // for debugging
    private static final String TAG = "VotesActivity";

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> numbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votes);

        getDatas();
        initRecyclerView();
    }

    private void getDatas() {
        // TODO: get data from the DB
        this.names.add("Joco");
        this.numbers.add("7");
    }

    private void initRecyclerView() {
        Log.d(TAG, "Init RecyclerView list");

        RecyclerView recyclerView = findViewById(R.id.recyclerViewList);
        RecyclerViewAdapter2List adapter = new RecyclerViewAdapter2List(this, this.names, this.numbers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
