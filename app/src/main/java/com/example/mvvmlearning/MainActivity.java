package com.example.mvvmlearning;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mvvmlearning.broadcast.PhoneStateBroadcastReceiver;
import com.example.mvvmlearning.viewmodels.MainActivityViewModel;
import com.example.mvvmlearning.viewmodels.MainActivityViewModelFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import dagger_room_contacts.activities.ContactsDaggerActivity;
import databinding.DataBindigActivity;
import ebookshop_mvvm.activities.BooksActivity;
import image_compress.ImageCompressActivity;
import notifications.NotificationSampleActivity;
import paging.PagingActivity;
import retrofit.RetrofitActivity;
import room.activities.ContactsActivity;
import rx_java.RxJavaActivity;
import temp_dagger.TempDaggerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MainActivityViewModel mainActivityViewModel;
    private TextView txt_count;
    private Button btn_room;
    private Button btn_data_binding;
    private Button ebook_shop;
    private Button image_compress;
    private Button btn_rxJava;
    private Button btn_save_contact, btn_delete_contact, btn_retrofit, temp_dagger,
            dagger_room_contacts, paging, notification, hilt_mvvm;
    PhoneStateBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("FCM", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        Log.d("FCMKey", token);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });


        mainActivityViewModel = ViewModelProviders.of(this,
                new MainActivityViewModelFactory(8)).get(MainActivityViewModel.class);

        txt_count = findViewById(R.id.txt_count);
        btn_room = findViewById(R.id.btn_room);
        btn_room.setOnClickListener(this);
        btn_data_binding = findViewById(R.id.btn_data_binding);
        btn_data_binding.setOnClickListener(this);
        btn_save_contact = findViewById(R.id.btn_save_contact);
        btn_save_contact.setOnClickListener(this);
        btn_delete_contact = findViewById(R.id.btn_delete_contact);
        btn_delete_contact.setOnClickListener(this);
        ebook_shop = findViewById(R.id.ebook_shop);
        ebook_shop.setOnClickListener(this);
        image_compress = findViewById(R.id.image_compress);
        image_compress.setOnClickListener(this);
        btn_rxJava = findViewById(R.id.btn_rxJava);
        btn_rxJava.setOnClickListener(this);
        btn_retrofit = findViewById(R.id.btn_retrofit);
        btn_retrofit.setOnClickListener(this);
        temp_dagger = findViewById(R.id.temp_dagger);
        temp_dagger.setOnClickListener(this);
        dagger_room_contacts = findViewById(R.id.dagger_room_contacts);
        dagger_room_contacts.setOnClickListener(this);
        paging = findViewById(R.id.paging);
        paging.setOnClickListener(this);
        notification = findViewById(R.id.notification);
        notification.setOnClickListener(this);
        hilt_mvvm = findViewById(R.id.hilt_mvvm);
        hilt_mvvm.setOnClickListener(this);


        LiveData<Integer> integerLiveData = mainActivityViewModel.getInitialCount();
        integerLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                txt_count.setText(String.valueOf(integer));
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityViewModel.getCount();
            }
        });


        IntentFilter filter = new IntentFilter("android.intent.action.PHONE_STATE");

        receiver = new PhoneStateBroadcastReceiver();
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_room:
                Intent roomIntent = new Intent(this, ContactsActivity.class);
                startActivity(roomIntent);
                break;

            case R.id.btn_data_binding:
                Intent dataBindingIntent = new Intent(this, DataBindigActivity.class);
                startActivity(dataBindingIntent);
                break;

            case R.id.btn_save_contact:
                addContact("Phone by Web.com", "+919990757820");
                break;

            case R.id.btn_delete_contact:
                deleteContact(this, "Phone by Web.com", "");
                break;

            case R.id.ebook_shop:
                Intent bookshopIntent = new Intent(this, BooksActivity.class);
                startActivity(bookshopIntent);
                break;

            case R.id.image_compress:
                Intent imageCompressIntent = new Intent(this, ImageCompressActivity.class);
                startActivity(imageCompressIntent);
                break;

            case R.id.btn_rxJava:
                Intent rxIntent = new Intent(this, RxJavaActivity.class);
                startActivity(rxIntent);
                break;

            case R.id.btn_retrofit:
                Intent retrofitIntent = new Intent(this, RetrofitActivity.class);
                startActivity(retrofitIntent);
                break;


            case R.id.temp_dagger:
                Intent tempdaggerIntent = new Intent(this, TempDaggerActivity.class);
                startActivity(tempdaggerIntent);
                break;


            case R.id.dagger_room_contacts:
                Intent daggerroomIntent = new Intent(this, ContactsDaggerActivity.class);
                startActivity(daggerroomIntent);
                break;

            case R.id.paging:
                Intent pagingIntent = new Intent(this, PagingActivity.class);
                startActivity(pagingIntent);
                break;

            case R.id.notification:
                Intent notificationIntent = new Intent(this, NotificationSampleActivity.class);
                startActivity(notificationIntent);
                break;

            case R.id.hilt_mvvm:
                Intent hiltIntent = new Intent(this, HiltMVVMActivity.class);
                startActivity(hiltIntent);
                break;

            default:
                break;
        }
    }


    private void addContact(String name, String phone) {
        ContentValues values = new ContentValues();
        Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);//��������
        values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name);
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

        //��data�����绰����
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        values.put(Phone.NUMBER, phone);
        values.put(Phone.TYPE, Phone.TYPE_MOBILE);
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
    }


    /*
     * Delete contact by it's display name.
     * */
    private void deleteContact(Context context, String givenName, String familyName) {
        // First select raw contact id by given name and family name.
        long rawContactId = getRawContactIdByName(context, givenName, familyName);

        ContentResolver contentResolver = context.getContentResolver();

        //******************************* delete data table related data ****************************************
        // Data table content process uri.
        Uri dataContentUri = ContactsContract.Data.CONTENT_URI;

        // Create data table where clause.
        StringBuffer dataWhereClauseBuf = new StringBuffer();
        dataWhereClauseBuf.append(ContactsContract.Data.RAW_CONTACT_ID);
        dataWhereClauseBuf.append(" = ");
        dataWhereClauseBuf.append(rawContactId);

        // Delete all this contact related data in data table.
        contentResolver.delete(dataContentUri, dataWhereClauseBuf.toString(), null);


        //******************************** delete raw_contacts table related data ***************************************
        // raw_contacts table content process uri.
        Uri rawContactUri = ContactsContract.Data.CONTENT_URI;

        // Create raw_contacts table where clause.
        StringBuffer rawContactWhereClause = new StringBuffer();
        rawContactWhereClause.append(ContactsContract.RawContacts._ID);
        rawContactWhereClause.append(" = ");
        rawContactWhereClause.append(rawContactId);

        // Delete raw_contacts table related data.
        contentResolver.delete(rawContactUri, rawContactWhereClause.toString(), null);

        //******************************** delete contacts table related data ***************************************
        // contacts table content process uri.
        Uri contactUri = ContactsContract.Data.CONTENT_URI;

        // Create contacts table where clause.
        StringBuffer contactWhereClause = new StringBuffer();
        contactWhereClause.append(ContactsContract.Contacts._ID);
        contactWhereClause.append(" = ");
        contactWhereClause.append(rawContactId);

        // Delete raw_contacts table related data.
        contentResolver.delete(contactUri, contactWhereClause.toString(), null);

    }


    /* Get raw contact id by contact given name and family name.
     *  Return raw contact id.
     * */
    private long getRawContactIdByName(Context context, String givenName, String familyName) {
        ContentResolver contentResolver = context.getContentResolver();

        // Query raw_contacts table by display name field ( given_name family_name ) to get raw contact id.

        // Create query column array.
        String queryColumnArr[] = {ContactsContract.RawContacts._ID};

        // Create where condition clause.
        String displayName = givenName + " " + familyName;
        String whereClause = ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME + " = '" + displayName.trim() + "'";

        // Query raw contact id through RawContacts uri.
        Uri rawContactUri = ContactsContract.Data.CONTENT_URI;

        // Return the query cursor.
        Cursor cursor = contentResolver.query(rawContactUri, queryColumnArr, whereClause, null, null);

        long rawContactId = -1;

        if (cursor != null) {
            // Get contact count that has same display name, generally it should be one.
            int queryResultCount = cursor.getCount();
            // This check is used to avoid cursor index out of bounds exception. android.database.CursorIndexOutOfBoundsException
            if (queryResultCount > 0) {
                // Move to the first row in the result cursor.
                cursor.moveToFirst();
                // Get raw_contact_id.
                rawContactId = cursor.getLong(cursor.getColumnIndex(ContactsContract.RawContacts._ID));
            }
        }

        return rawContactId;
    }
}
