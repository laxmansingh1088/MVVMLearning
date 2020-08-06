package notifications.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.mvvmlearning.R;

import notifications.NotificationSampleActivity;
import notifications.broadcast.NotificationReceiver;
import notifications.constants.AppConstants;

import static notifications.constants.AppConstants.CHANNEL_1_ID;
import static notifications.constants.AppConstants.CHANNEL_3_ID;

public class MyForeGroundService extends Service {

    private static final String TAG_FOREGROUND_SERVICE = "FOREGROUND_SERVICE";

    public static final String ACTION_START_FOREGROUND_SERVICE = "ACTION_START_FOREGROUND_SERVICE";

    public static final String ACTION_STOP_FOREGROUND_SERVICE = "ACTION_STOP_FOREGROUND_SERVICE";

    public static final String ACTION_PAUSE = "ACTION_PAUSE";

    public static final String ACTION_PLAY = "ACTION_PLAY";

    public MyForeGroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG_FOREGROUND_SERVICE, "My foreground service onCreate().");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();

            switch (action) {
                case ACTION_START_FOREGROUND_SERVICE:
                    startForegroundService();
                    Toast.makeText(getApplicationContext(), "Foreground service is started.", Toast.LENGTH_LONG).show();
                    break;
                case ACTION_STOP_FOREGROUND_SERVICE:
                    stopForegroundService();
                    Toast.makeText(getApplicationContext(), "Foreground service is stopped.", Toast.LENGTH_LONG).show();
                    break;
                case AppConstants.ACCEPT_ACTION:
                    Toast.makeText(getApplicationContext(), "Accept", Toast.LENGTH_LONG).show();
                    break;
                case AppConstants.HANGUP_ACTION:
                    Toast.makeText(getApplicationContext(), "Hangup", Toast.LENGTH_LONG).show();
                    break;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    /* Used to build and start foreground service. */
    private void startForegroundService() {
        Log.d(TAG_FOREGROUND_SERVICE, "Start foreground service.");

        Intent activityIntent = new Intent(this, NotificationSampleActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);

        Intent acceptIntent = new Intent(this, MyForeGroundService.class);
        acceptIntent.setAction(AppConstants.ACCEPT_ACTION);
        PendingIntent pendingIntentAccept = PendingIntent.getBroadcast(this, 12348, acceptIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Intent hangupIntent = new Intent(this, MyForeGroundService.class);
        hangupIntent.setAction(AppConstants.HANGUP_ACTION);
        PendingIntent pendingIntentHangup = PendingIntent.getBroadcast(this, 12348, hangupIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_3_ID)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Hey")
                .setContentText("New Message")
                .setFullScreenIntent(contentIntent,true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOngoing(true)
                .setAutoCancel(false)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Accept", pendingIntentAccept)
                .addAction(R.drawable.notification, "Hangup", pendingIntentHangup)
                .build();

        // Start foreground service.
        startForeground(1, notification);
    }

    private void stopForegroundService() {
        Log.d(TAG_FOREGROUND_SERVICE, "Stop foreground service.");

        // Stop foreground service and remove the notification.
        stopForeground(true);

        // Stop the foreground service.
        stopSelf();
    }
}
