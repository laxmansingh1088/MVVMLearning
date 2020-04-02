package room.contacts_app_database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import room.dao.ContactDAO;
import room.models.ContactModel;


@Database(entities = {ContactModel.class}, version = 1)
public abstract class ContactsAppDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDao();

}
