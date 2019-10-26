package com.example.pointpoker;

//<<<<<<< Database_connection
import android.content.Intent;
import android.database.Cursor;
//=======
//>>>>>>> master
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pointpoker.utils.Utils;

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
//<<<<<<< Database_connection
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
//=======
        // TODO: check if the user is in the DB
        Utils.startNewIntent(this, VoteActivity.class);
//>>>>>>> master
    }
}
