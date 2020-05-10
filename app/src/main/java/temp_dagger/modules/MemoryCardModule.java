package temp_dagger.modules;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import temp_dagger.smartphone.MemoryCard;

@Module
public class MemoryCardModule {

    @Provides
    static MemoryCard providesMemoryCard() {
        return new MemoryCard();
    }

}
