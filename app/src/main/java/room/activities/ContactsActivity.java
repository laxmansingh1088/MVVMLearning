package room.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mvvmlearning.R;
import com.example.mvvmlearning.databinding.ActivityContactsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import room.ButtonEvents;
import room.adapters.ContactsAdapter;
import room.contacts_app_database.ContactsAppDatabase;
import room.interfaces.RecyclerViewOnClickListener;
import room.models.ContactModel;

public class ContactsActivity extends AppCompatActivity implements View.OnClickListener, RecyclerViewOnClickListener {


    private ContactsAppDatabase contactsAppDatabase;
    private RecyclerView mRvcontacts;
    private ContactsAdapter mContactsAdapter;
    private List<ContactModel> contactModelList = new ArrayList<>();

    private ActivityContactsBinding activityContactsBinding;

    private FloatingActionButton fab_contacts;


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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        setTitle("Room Example");

        activityContactsBinding= DataBindingUtil.setContentView(this,R.layout.activity_contacts);

        fab_contacts = findViewById(R.id.fab_contacts);
        fab_contacts.setOnClickListener(this);


        mRvcontacts = activityContactsBinding.rvContacts;/*findViewById(R.id.rv_contacts);*/
        mRvcontacts.setLayoutManager(new LinearLayoutManager(this));
        mContactsAdapter = new ContactsAdapter(this, contactModelList, this);
        mRvcontacts.setAdapter(mContactsAdapter);


        contactsAppDatabase = Room.databaseBuilder(getApplicationContext(), ContactsAppDatabase.class, "contactDB").addCallback(callback).build();
        new FetchContacts().execute();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.fab_contacts:
                showContactAddOrUpdateDialog(false, null);
                break;

            default:
                break;

        }
    }

    @Override
    public void onRecycleViewClick(int position, ButtonEvents buttonEvents) {
        ContactModel contactModel = contactModelList.get(position);
        switch (buttonEvents) {
            case EDIT:
                showContactAddOrUpdateDialog(true, contactModel);
                break;

            case DELETE:
                new DeleteContact().execute(contactModel);
                break;

        }
    }


    private void showContactAddOrUpdateDialog(final boolean isEditable, final ContactModel contactModel) {

        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        final ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.contact_add_update_dialog, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();

        TextView tv_title = dialogView.findViewById(R.id.tv_title);
        final TextView et_name = dialogView.findViewById(R.id.et_name);
        final TextView et_email = dialogView.findViewById(R.id.et_email);
        TextView btn_cancel = dialogView.findViewById(R.id.btn_cancel);
        TextView btn_save = dialogView.findViewById(R.id.btn_save);

        if (isEditable) {
            tv_title.setText("Edit Contact");
            et_email.setText(contactModel.getContactEmail());
            et_name.setText(contactModel.getContactName());
        } else {
            tv_title.setText("Add Contact");
        }

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                ContactModel contactModelLocal = new ContactModel();
                if (isEditable) {
                    contactModelLocal.setContactName(name);
                    contactModelLocal.setContactEmail(email);
                    contactModelLocal.setContactId(contactModel.getContactId());
                    new UpdateContact().execute(contactModelLocal);
                } else {
                    contactModelLocal.setContactName(name);
                    contactModelLocal.setContactEmail(email);
                    new AddContact().execute(contactModelLocal);
                }
                alertDialog.dismiss();
            }
        });


        alertDialog.show();
    }


    private void refreshUI() {
        contactModelList.clear();
        new FetchContacts().execute();
    }


    class FetchContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            contactModelList.addAll(contactsAppDatabase.getContactDao().getAllContacts());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mContactsAdapter.notifyDataSetChanged();
            super.onPostExecute(aVoid);
        }
    }

    class AddContact extends AsyncTask<ContactModel, Void, Void> {


        @Override
        protected Void doInBackground(ContactModel... args) {
            contactsAppDatabase.getContactDao().addContact(args[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            refreshUI();
        }
    }


    class UpdateContact extends AsyncTask<ContactModel, Void, Void> {


        @Override
        protected Void doInBackground(ContactModel... args) {
            contactsAppDatabase.getContactDao().updateContact(args[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            refreshUI();
        }
    }


    class DeleteContact extends AsyncTask<ContactModel, Void, Void> {


        @Override
        protected Void doInBackground(ContactModel... args) {
            contactsAppDatabase.getContactDao().deleteContact(args[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            refreshUI();
        }
    }


}

