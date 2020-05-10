package dagger_room_contacts.contacts_app_database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import dagger_room_contacts.dao.ContactDaggerDAO;
import dagger_room_contacts.models.ContactDaggerModel;


@Database(entities = {ContactDaggerModel.class}, version = 1)
public abstract class ContactsDaggerAppDatabase extends RoomDatabase {
    public abstract ContactDaggerDAO getContactDao();

}
