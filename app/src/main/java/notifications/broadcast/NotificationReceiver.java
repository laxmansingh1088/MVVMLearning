package notifications.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import notifications.constants.AppConstants;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (AppConstants.ACCEPT_ACTION.equals(action)) {
            Toast.makeText(context, "Accept Pressed", Toast.LENGTH_SHORT).show();
        } else if (AppConstants.HANGUP_ACTION.equals(action)) {
            Toast.makeText(context, "Hangup Pressed", Toast.LENGTH_SHORT).show();
        }
    }
}