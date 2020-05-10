package temp_dagger.smartphone;

import android.util.Log;

import javax.inject.Inject;

import temp_dagger.Network;

public class Networkstrength implements Network {

    @Inject
    public Networkstrength() {
    }

    @Override
    public void showNetworkStrength() {
        Log.d("dagger", "Network Strength is 4");
    }
}
