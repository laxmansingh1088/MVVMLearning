package notifications.broadcast;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import notifications.NotificationSampleActivity;
import notifications.constants.AppConstants;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent closeIntent = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
                context.sendBroadcast(closeIntent);
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(1);

            }
        },200);


    //    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    //    notificationManager.cancel(1);

        Intent intentCallActivity = new Intent(context, NotificationSampleActivity.class);
        intentCallActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentCallActivity);

        if (AppConstants.ACCEPT_ACTION.equals(action)) {
            Toast.makeText(context, "Accept Pressed", Toast.LENGTH_SHORT).show();
        } else if (AppConstants.HANGUP_ACTION.equals(action)) {
            Toast.makeText(context, "Hangup Pressed", Toast.LENGTH_SHORT).show();
        }
    }
}