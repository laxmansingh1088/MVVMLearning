package dagger_room_contacts.di.modules;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger_room_contacts.contacts_app_database.ContactsDaggerAppDatabase;

@Module
public class RoomModule {

    @Provides
    @Singleton
    ContactsDaggerAppDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application, ContactsDaggerAppDatabase.class, "contactDBDagger").addCallback(callback).build();
    }


    RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            Log.d("laxman", "Room database onCreate");
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.d("laxman", "Room database onOpen");
        }
    };

}
