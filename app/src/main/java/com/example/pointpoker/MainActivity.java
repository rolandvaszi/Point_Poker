package com.example.pointpoker;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

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
        if(databaseHelper.userExist(editText.getText().toString())){
            Utils.makeToast(this, "Login successfull!");
            Utils.startNewIntent(this, FragmentHolder.class);
        } else {
            Utils.makeToast(this, "Wrong username!");
        }

    }
}
