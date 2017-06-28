package com.erisco.madridshops;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.squareup.picasso.Picasso;

import com.erisco.madridshops.activities.ShopListActivity;
import com.erisco.madridshops.services.ShopService;


public class MadridShopsApp extends MultiDexApplication {

    public static final String APP_NAME = MadridShopsApp.class.getCanonicalName();

    @Override
    public void onCreate() {
        super.onCreate();

        // init app

        // Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);

        ShopService.startRunningService(this);

        Resources resources = getResources();
        Intent intent = new Intent(this, ShopListActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Ticker")    // Set the "ticker" text which is sent to accessibility services.
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentTitle("You're spyed now")
                .setContentText("Notification Text")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        int notificationId = 0;
        notificationManager.notify(notificationId, notification);

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        // low memory: dump something
    }
}
