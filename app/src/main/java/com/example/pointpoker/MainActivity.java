package com.example.pointpoker;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.pointpoker.utils.Utils;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText editText;
    public static int user;
    private SharedPreferences mPrefs;
    private static final String PREFS_NAME = "PrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.nameText);
        databaseHelper = new DatabaseHelper(this);
        if(!databaseHelper.userExist("Roland")) {
            databaseHelper.addUser("Roland");
        }
        if(!databaseHelper.userExist("Botond")) {
            databaseHelper.addUser("Botond");
        }
        if(!databaseHelper.projectExist("Project1")) {
            databaseHelper.addProject("Project1");
        }
        if(!databaseHelper.taskExist("Project1", "Task1")) {
            databaseHelper.addTask("Task1", 1,1);
            databaseHelper.addTask("Task1", 1,2);
        }
        mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }

    public void login(View view) {
        if(databaseHelper.userExist(editText.getText().toString())){
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString("log_name", editText.getText().toString());
            editor.apply();
            Utils.makeToast(this, "Login successfull!");
            Utils.startNewIntent(this, FragmentHolder.class);
        } else {
            Utils.makeToast(this, "Wrong username!");
        }

    }
}
