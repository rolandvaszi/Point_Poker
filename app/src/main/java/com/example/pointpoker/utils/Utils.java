package com.example.pointpoker.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pointpoker.R;

public final class Utils {

    public static void makeToast(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void startNewIntent(Context context, Class cls) {
        final Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static void loadFragment(Fragment fragment, FragmentManager fm) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.holderLayout, fragment, fragment.getTag());
        fragmentTransaction.commit();
    }
}
