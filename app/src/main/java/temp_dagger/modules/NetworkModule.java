package temp_dagger.modules;

import javax.inject.Inject;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import temp_dagger.Network;
import temp_dagger.smartphone.Networkstrength;

@Module
public abstract class NetworkModule {

    @Binds
    abstract Network bindNetworkStrength(Networkstrength networkstrength);

}



/*   Two ways to provide Network interface dependency to SmartPhone Class
@Module
public class NetworkModule {

    @Provides
    Network provideNetworkStrength(Networkstrength networkstrength) {
        networkstrength.showNetworkStrength();
        return networkstrength;
    }

}*/
