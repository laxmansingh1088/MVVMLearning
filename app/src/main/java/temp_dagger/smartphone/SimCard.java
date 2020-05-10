package temp_dagger.smartphone;

import android.util.Log;

import javax.inject.Inject;

public class SimCard {
    private ServiceProvider serviceProvider;

    @Inject
    public SimCard(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
        Log.d("dagger", "Simcard initialized");
    }
}
