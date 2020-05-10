package temp_dagger.smartphone;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Singleton;

import temp_dagger.Network;

@Singleton
public class SmartPhone {

    private Battery battery;
    private SimCard simCard;
    private MemoryCard memoryCard;
    private Network network;
    private String time;

    @Inject
    public SmartPhone(Battery battery, SimCard simCard, MemoryCard memoryCard, Network network) {
        this.battery = battery;
        this.simCard = simCard;
        this.memoryCard = memoryCard;
        this.network = network;
        this.network.showNetworkStrength();
        Log.d("dagger", "SmartPhone initialized");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss");
        time = simpleDateFormat.format(Calendar.getInstance().getTime());

    }

    public void makeCall() {
        Log.d("dagger", "SmartPhone Call in progress.....");
        Log.d("dagger", "Date:-- " + time);
    }

}
