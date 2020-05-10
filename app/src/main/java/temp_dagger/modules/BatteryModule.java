package temp_dagger.modules;

import android.util.Log;

import dagger.Module;
import dagger.Provides;
import temp_dagger.smartphone.Battery;

@Module
public class BatteryModule {

    int currentBatteryPercent;

    public BatteryModule(int currentBatteryPercent) {
        this.currentBatteryPercent = currentBatteryPercent;
        Log.d("dagger", "currentBatteryPercent---  " + this.currentBatteryPercent);
    }

    @Provides
    Battery providesBattery() {
        return new Battery();
    }


}
