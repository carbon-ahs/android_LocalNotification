package com.axiagroups.localnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String CHANNEL_1_ID = "CHANNEL_1_ID";
    public static final String CHANNEL_1_NAME = "CHANNEL_1_NAME";
    public static final String CHANNEL_2_ID = "CHANNEL_2_ID";
    public static final String CHANNEL_2_NAME = "CHANNEL_2_NAME";

    private NotificationManager mManager;
    public NotificationHelper(Context base) {
        super(base);
        createChannels();
    }

    private void createChannels() {
        NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID, CHANNEL_1_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel1);



        NotificationChannel channel2 = new NotificationChannel(CHANNEL_2_ID, CHANNEL_2_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        channel2.enableLights(true);
        channel2.enableVibration(true);
        channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel2);

    }

    public NotificationManager getManager() {
        if(mManager == null){
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
    public NotificationCompat.Builder getChannel1Notification(String title, String body){
        Intent resultIntent = new Intent(this, SecendViewActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 1, resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_1_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.baseline_one)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent);

    }
    public NotificationCompat.Builder getChannel2Notification(String title, String body){
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_2_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.baseline_two);

    }
}
