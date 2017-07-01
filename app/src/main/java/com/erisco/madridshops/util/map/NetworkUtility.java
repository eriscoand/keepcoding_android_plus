package com.erisco.madridshops.util.map;


import android.content.Context;
import android.net.ConnectivityManager;

import java.lang.ref.WeakReference;

public class NetworkUtility {
    private WeakReference<Context> context;

    public NetworkUtility(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    public boolean isOnline() {
        ConnectivityManager cm =  (ConnectivityManager) context.get().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}
