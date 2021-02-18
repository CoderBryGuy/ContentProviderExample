package com.example.contentproviderexample;

//public class MainActivity extends AppCompatActivity {
//    private static final String TAG = "MainActivity";
//    private ListView contactNames;
//    private static final int REQUEST_CODE_READ_CONTACTS = 1;
//    private static boolean READ_CONTACTS_GRANTED = false;
//    FloatingActionButton fab = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        contactNames = (ListView) findViewById(R.id.contact_names);
//        int hasReadContactsPermission = ContextCompat.checkSelfPermission(this, READ_CONTACTS);
//        Log.d(TAG, "onCreate: check self permission  =  " + hasReadContactsPermission);
//
//        if (hasReadContactsPermission == PackageManager.PERMISSION_GRANTED) {
//            Log.d(TAG, "onCreate: permission granted");
//            READ_CONTACTS_GRANTED = true;
//        } else {
//            Log.d(TAG, "onCreate: requesting permission");
//            ActivityCompat.requestPermissions(this, new String[]{READ_CONTACTS}, REQUEST_CODE_READ_CONTACTS);
//        }
//
//
//        fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "fab onClick:  starts");
//                if (READ_CONTACTS_GRANTED) {
//                    String[] projection = {ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};
//                    ContentResolver contentResolver = getContentResolver();
//                    Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
//                            projection,
//                            null,
//                            null,
//                            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY);
//
//                    if (cursor != null) {
//                        List<String> contacts = new ArrayList<>();
//                        while (cursor.moveToNext()) {
//                            contacts.add(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)));
//                        }
//                        cursor.close();
//                        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.contact_detail, R.id.name, contacts);
//                        contactNames.setAdapter(adapter);
//                    }
//                } else {
//                    Snackbar.make(view, "Please grant access to contacts", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                }
//                Log.d(TAG, "fab onClick: ends");
//            }
//        });
//
//        Log.d(TAG, "onCreate: ends");
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        Log.d(TAG, "onRequestPermissionsResult: starts");
//        switch (requestCode) {
//            case REQUEST_CODE_READ_CONTACTS: {
//                //if request is cancelled, the rest arrays are empty.
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    //permission granted granted, yay! Do the
//                    //contacts-related task you need to do
//                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
//                    READ_CONTACTS_GRANTED = true;
//                } else {
//                    //permission denied, boo! disable functionality
//                    //that depends on this permission
//
//                    Log.d(TAG, "onRequestPermissionsResult:  permission refused");
//                }
//                fab.setEnabled(READ_CONTACTS_GRANTED);
//            }
//        }
//        Log.d(TAG, "onRequestPermissionsResult: ends");
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView contactNames;
    private static final int REQUEST_CODE_READ_CONTACTS = 1;
    private static boolean READ_CONTACTS_GRANTED = false;
    FloatingActionButton fab = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contactNames = (ListView) findViewById(R.id.contact_names);

        int hasReadContactPermission = ContextCompat.checkSelfPermission(this, READ_CONTACTS);
        Log.d(TAG, "onCreate: checkSelfPermission = " + hasReadContactPermission);

        if (hasReadContactPermission == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onCreate: permission granted");
            READ_CONTACTS_GRANTED = true;
        } else {
            Log.d(TAG, "onCreate: requesting permission");
            ActivityCompat.requestPermissions(this, new String[]{READ_CONTACTS}, REQUEST_CODE_READ_CONTACTS);
        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "fab onClick: starts");
                if (READ_CONTACTS_GRANTED) {
                    String[] projection = {ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};
                    ContentResolver contentResolver = getContentResolver();
                    Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                            projection,
                            null,
                            null,
                            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY);

                    if (cursor != null) {
                        List<String> contacts = new ArrayList<String>();
                        while (cursor.moveToNext()) {
                            contacts.add(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
                        }
                        cursor.close();
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.contact_detail, R.id.name, contacts);
                        contactNames.setAdapter(adapter);
                    }

                } else {
                    Snackbar.make(view, "Please grant access to your Contacts", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                Log.d(TAG, "fab onClick: ends");
            }
        });
        Log.d(TAG, "onCreate: ends");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: starts");
        switch (requestCode) {
            case REQUEST_CODE_READ_CONTACTS: {
                // If request is cancelled, the rest arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    READ_CONTACTS_GRANTED = true;
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.d(TAG, "onRequestPermissionsResult: permission refused");
                }
//                fab.setEnabled(READ_CONTACTS_GRANTED);
            }
        }

        Log.d(TAG, "onRequestPermissionsResult: ends");
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
}
