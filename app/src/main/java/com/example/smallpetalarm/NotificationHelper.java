package com.example.smallpetalarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {

    public static final String channelID = "channelID";
    public static final String channelNm = "channelNm";

    private NotificationManager notificationManager;

    public NotificationHelper(Context base) {
        super(base);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)

    public void createChannels() {
        NotificationChannel ch1 = new NotificationChannel(channelID, channelNm, NotificationManager.IMPORTANCE_DEFAULT);
        ch1.enableLights(true);
        ch1.enableVibration(true);
        ch1.setLightColor(R.color.black);
        ch1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(ch1);
    }

    public NotificationManager getManager() {
        if(notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    public NotificationCompat.Builder getChannelNoticiation() {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("알람")
                .setContentText("알람매니저 실행 중")
                .setSmallIcon(R.drawable.ic_heart);
    }
}
