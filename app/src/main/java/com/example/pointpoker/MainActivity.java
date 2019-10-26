package com.example.pointpoker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.nameText);
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.addUser("Roland");
        databaseHelper.addUser("Botond");
    }

    public void login(View view) {
        if(databaseHelper.userExist(editText.getText().toString())){
            toastMessage("Login successfull!");
            Intent intent = new Intent(this, VoteActivity.class);
            this.startActivity(intent);
        } else {
            toastMessage("Wrong username!");
        }

    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
