package com.example.pointpoker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pointpoker.utils.Utils;

public class VoteActivity extends AppCompatActivity {

    private String vote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.vote = null;
        // TODO: get the project and the question name from the DB
    }

    public void vote(View view) {
        if (this.vote != null) {
            // TODO: save the vote into the DB
            Utils.startNewIntent(this, VotesActivity.class);
        } else {
            Utils.makeToast(this, "You didn't vote yet.");
        }
    }

    public void btnClick(View view) {
        String msg = "Your vote: ";
        if (view instanceof ImageButton) {
            Utils.makeToast(getApplicationContext(), msg + "'coffee'");

            this.vote = "coffee";
        } else {
            CharSequence btnText = ((Button) view).getText();
            Utils.makeToast(getApplicationContext(), msg + btnText);

            this.vote = btnText.toString();
        }
    }
}
