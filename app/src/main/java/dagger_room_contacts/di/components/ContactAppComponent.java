package dagger_room_contacts.di.components;

import javax.inject.Singleton;

import dagger.Component;
import dagger_room_contacts.activities.ContactsDaggerActivity;
import dagger_room_contacts.di.modules.ApplicationModule;
import dagger_room_contacts.di.modules.RoomModule;

@Singleton
@Component(modules = {ApplicationModule.class, RoomModule.class})
public interface ContactAppComponent {
    void inject(ContactsDaggerActivity contactsDaggerActivity);
}
