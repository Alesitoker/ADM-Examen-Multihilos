package com.iessaladillo.alejandro.adm_examenmultihilos;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class App extends Application {
 
    @Override
    public void onCreate() {
        super.onCreate();
        createChannel();
    }
 
    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(
                    Constants.CHANNEL_ID,
                    getString(R.string.app_main_channel_name),
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(getString(R.string.app_main_channel_name));

            NotificationManager notificationManager = 
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        } 
    }
 
}