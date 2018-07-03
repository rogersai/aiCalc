package com.rogersai.aicalc;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.util.Pair;

import java.util.ArrayList;

public class ContactContentRetriever {
    private static final String[] CONTACTS_PROJECTION = new String[]{
            ContactsContract.Contacts._ID, // 0
            ContactsContract.Contacts.DISPLAY_NAME, // 1
    };
    private static final int CONTACTS_PROJECTION_ID_INDEX = 0;
    private static final int CONTACTS_PROJECTION_DISPLAY_NAME_INDEX = 1;

    private static final String[] RAW_CONTACT_PROJECTION = new String[]{
            ContactsContract.Profile._ID, // 0
            ContactsContract.Profile.DISPLAY_NAME, // 1
    };

    private static final String[] CONTACT_DATA_PROJECTION = new String[]{
            ContactsContract.Profile._ID, // 0
            ContactsContract.Profile.DISPLAY_NAME, // 1
    };

    private MainActivity mainActivity;
    private ContentResolver cr;

    public ContactContentRetriever(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.cr = mainActivity.getContentResolver();
    }

    public void getContacts() {
        if (ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        System.out.println(">>> Getting contacts");
        Cursor contactsCursor = null;
        Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;
        contactsCursor = cr.query(contactsUri, CONTACTS_PROJECTION, null, null, null);
        while (contactsCursor.moveToNext()) {
            long contactID = 0;
            String contactName = null;

            contactID = contactsCursor.getLong(CONTACTS_PROJECTION_ID_INDEX);
            contactName = contactsCursor.getString(CONTACTS_PROJECTION_DISPLAY_NAME_INDEX);

            System.out.println(contactID + " " + contactName);
        }
    }
    public Cursor getRawContactCursor() {

        return null;
    };

    public ArrayList<Pair<String, String>> getBirthdays() {
        if (ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        System.out.println(">>> Getting birthdays");
        Cursor birthdaysCursor = null;
        Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;
        birthdaysCursor = cr.query(contactsUri, CONTACTS_PROJECTION, null, null, null);
        // TODO: Implement
        return null;
    }

    public void testSelf() {
        System.out.println("++++ Contact Content Test Starting ++++");
        getContacts();
        System.out.println("++++ Contact Content Test Done ++++");

    }
}
