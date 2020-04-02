package room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import room.models.ContactModel;

@Dao
public interface ContactDAO {


    @Insert
    public long addContact(ContactModel contactModel);

    @Update
    public void updateContact(ContactModel contactModel);

    @Delete
    public void deleteContact(ContactModel contactModel);


    @Query("select * from contacts")
    public List<ContactModel> getAllContacts();

    @Query("select * from contacts where contact_id==:contactId")
    public ContactModel getContact(long contactId);

}
