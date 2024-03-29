package com.example.pointpoker;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointpoker.utils.Utils;

import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;

import static android.content.Context.MODE_PRIVATE;

public class VoteFragment extends Fragment {
    // for debugging
    private static final String TAG = "VoteFragment";
    private Vector<String> names = new Vector<>(Arrays.
            asList("0", "1", "2", "3",
                    "5", "8", "13", "20",
                    "40", "100", "?", "coffee"));
    private String vote;
    private View view;
    DatabaseHelper db;
    private String logUser;

    @Override
    public void onResume() {
        super.onResume();

    }

    TextView projectText;
    TextView questioText;

    private SharedPreferences mPrefs;
    private static final String PREFS_NAME = "PrefsFile";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Fragment Vote started");
        this.vote = null;
        view = inflater.inflate(R.layout.activity_vote, container, false);
        initRecyclerView();
        view.findViewById(R.id.voteBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vote != null) {
                    // TODO: save the vote into the DB
                    Utils.loadFragment(new VotesFragment(), getActivity().getSupportFragmentManager());
                } else {
                    Utils.makeToast(v.getContext(), "You didn't vote yet.");
                }
            }
        });
        db = new DatabaseHelper(this.getContext());
        projectText = (TextView) view.findViewById(R.id.projectText);
        projectText.setText(db.getProject());
        questioText = (TextView) view.findViewById(R.id.questionText);
        questioText.setText(db.getTask());

        return view;
    }

    private void initRecyclerView() {
        Log.d(TAG, "Init RecyclerView grid");
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewGrid);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 4);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter2Grid adapter = new RecyclerViewAdapter2Grid(view.getContext(), names,
                new ClickListener() {
                    @Override
                    public void onPositionClicked(int position) {
                        String msg = "Your vote: ";
                        Context context = view.getContext();

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

}
