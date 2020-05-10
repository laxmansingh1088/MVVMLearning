package androidx;

import android.app.Application;

import dagger_room_contacts.di.components.ContactAppComponent;
import dagger_room_contacts.di.components.DaggerContactAppComponent;
import dagger_room_contacts.di.modules.ApplicationModule;
import temp_dagger.component.DaggerSmartPhoneComponent;
import temp_dagger.component.SmartPhoneComponent;
import temp_dagger.modules.BatteryModule;

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
}
