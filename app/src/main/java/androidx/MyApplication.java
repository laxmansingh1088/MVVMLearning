package androidx;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import dagger_room_contacts.di.components.ContactAppComponent;
import dagger_room_contacts.di.components.DaggerContactAppComponent;
import dagger_room_contacts.di.modules.ApplicationModule;
import temp_dagger.component.DaggerSmartPhoneComponent;
import temp_dagger.component.SmartPhoneComponent;
import temp_dagger.modules.BatteryModule;

import static notifications.constants.AppConstants.CHANNEL_1_ID;
import static notifications.constants.AppConstants.CHANNEL_2_ID;
import static notifications.constants.AppConstants.CHANNEL_3_ID;

public class MyApplication extends Application {

    public static MyApplication instance;
    private SmartPhoneComponent smartPhoneComponent;
    private ContactAppComponent contactAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        buildSmartPhone();
        buildContactAppComponent();
        createNotificationChannels();
    }


    public static MyApplication getInstance() {
        return instance;
    }


    private void buildContactAppComponent() {
        contactAppComponent = DaggerContactAppComponent.builder().applicationModule(new ApplicationModule(instance)).build();
    }

    private void buildSmartPhone() {
        if (smartPhoneComponent == null) {
            smartPhoneComponent = DaggerSmartPhoneComponent.builder()
                    .batteryModule(new BatteryModule(100))
                    .build();
        }
    }

    public SmartPhoneComponent getSmartPhoneComponent() {
        return smartPhoneComponent;
    }

    public ContactAppComponent getContactAppComponent() {
        return contactAppComponent;
    }




    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");
            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("This is Channel 2");

            NotificationChannel channel3 = new NotificationChannel(
                    CHANNEL_3_ID,
                    "Channel 3",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel3.setDescription("This is Channel 3");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
            manager.createNotificationChannel(channel3);
        }
    }
}
