package com.example.pointpoker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VotesFragment extends Fragment {
    // for debugging
    private static final String TAG = "VotesFragment";

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> numbers = new ArrayList<>();

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Fragment Votes started");

        view = inflater.inflate(R.layout.activity_votes, container, false);

        getDatas();
        initRecyclerView();

        return view;
    }

    private void getDatas() {
        // TODO: get data from the DB
        this.names.add("Joco");
        this.numbers.add("7");
    }

    private void initRecyclerView() {
        Log.d(TAG, "Init RecyclerView list");

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewList);
        RecyclerViewAdapter2List adapter = new RecyclerViewAdapter2List(view.getContext(), this.names, this.numbers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
}
