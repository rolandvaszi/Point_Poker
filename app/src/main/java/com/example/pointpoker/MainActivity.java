package com.example.pointpoker;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pointpoker.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private EditText loginText;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginText = findViewById(R.id.nameText);
    }

    public void login(View view) {
        // TODO: check if the user is in the DB
        Utils.startNewIntent(this, VoteActivity.class);
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
