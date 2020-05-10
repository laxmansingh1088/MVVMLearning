package temp_dagger.smartphone;

import android.util.Log;

import javax.inject.Inject;

public class ServiceProvider {
    @Inject
    public ServiceProvider() {
        Log.d("dagger", "ServiceProvider initialized");
    }
}
