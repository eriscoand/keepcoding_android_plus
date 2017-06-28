package com.erisco.madridshops.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

public class MainThread {
    public static void run(@NonNull final Runnable codeToRunOnMainThread) {
        if (codeToRunOnMainThread == null) {
            return;
        }

        Handler uiHandler = new Handler(Looper.getMainLooper());
        uiHandler.post(codeToRunOnMainThread);
    }

}
