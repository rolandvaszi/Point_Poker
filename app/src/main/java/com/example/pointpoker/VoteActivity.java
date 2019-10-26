package com.example.pointpoker;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointpoker.utils.Utils;

import java.util.Arrays;
import java.util.Vector;

public class VoteActivity extends AppCompatActivity {
    // for debugging
    private static final String TAG = "VotesActivity";

    private Vector<String> names = new Vector<>(Arrays.
            asList("0", "1", "2", "3",
                    "5", "8", "13", "20",
                    "40", "100", "?", "coffee"));

    private String vote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        initRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.vote = null;
        // TODO: get the project and the question name from the DB
    }

    private void initRecyclerView() {
        Log.d(TAG, "Init RecyclerView grid");

        RecyclerView recyclerView = findViewById(R.id.recyclerViewGrid);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter2Grid adapter = new RecyclerViewAdapter2Grid(this, names,
                new ClickListener() {
                    @Override
                    public void onPositionClicked(int position) {
                        String msg = "Your vote: ";
                        Context context = getApplicationContext();

                        // which button is pressed
                        String btn = names.get(position);
                        String coffee = names.lastElement();
                        if (btn.equals(coffee)) {
                            Utils.makeToast(context, msg + coffee);

                            vote = coffee;
                        } else {
                            Utils.makeToast(context, msg + btn);

                            vote = btn;
                        }
                    }
                });
        recyclerView.setAdapter(adapter);
    }

    public void vote(View view) {
        if (this.vote != null) {
            // TODO: save the vote into the DB
            Utils.startNewIntent(this, VotesActivity.class);
        } else {
            Utils.makeToast(this, "You didn't vote yet.");
        }
    }
}
