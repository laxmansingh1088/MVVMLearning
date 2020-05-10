package temp_dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import temp_dagger.AnotherActivity;
import temp_dagger.TempDaggerActivity;
import temp_dagger.modules.BatteryModule;
import temp_dagger.modules.MemoryCardModule;
import temp_dagger.modules.NetworkModule;
import temp_dagger.smartphone.SmartPhone;

@Singleton
@Component(modules = {MemoryCardModule.class, BatteryModule.class, NetworkModule.class})
public interface SmartPhoneComponent {
    // SmartPhone getSmartPhone();

    void inject(TempDaggerActivity tempDaggerActivity);

    void inject(AnotherActivity anotherActivity);
}
