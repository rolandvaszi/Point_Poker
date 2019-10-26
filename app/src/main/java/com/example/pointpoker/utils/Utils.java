package com.example.pointpoker.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public final class Utils {

    public static void makeToast(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void startNewIntent(Context context, Class cls) {
        final Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }
}
