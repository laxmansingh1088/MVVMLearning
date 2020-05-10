package dagger_room_contacts.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dagger_room_contacts.models.ContactDaggerModel;

@Dao
public interface ContactDaggerDAO {

    @Insert
    public long addContact(ContactDaggerModel contactModel);

    @Update
    public void updateContact(ContactDaggerModel contactModel);

    @Delete
    public void deleteContact(ContactDaggerModel contactModel);

    @Query("select * from contactsdagger")
    public List<ContactDaggerModel> getAllContacts();

    @Query("select * from contactsdagger where contact_id==:contactId")
    public ContactDaggerModel getContact(long contactId);

}
