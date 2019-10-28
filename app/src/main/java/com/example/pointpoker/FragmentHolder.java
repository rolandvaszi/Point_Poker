package com.example.pointpoker;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.pointpoker.utils.Utils;

public class FragmentHolder extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);
        Utils.loadFragment(new VoteFragment(), getSupportFragmentManager());
    }
}
