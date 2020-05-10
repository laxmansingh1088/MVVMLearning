package temp_dagger.smartphone;

import android.util.Log;

import javax.inject.Inject;

public class Battery {

    public Battery() {
        Log.d("dagger", "Battery initialized");
    }


    public void showBatteryName() {
        Log.d("dagger", "Exide Battery");
    }
}
