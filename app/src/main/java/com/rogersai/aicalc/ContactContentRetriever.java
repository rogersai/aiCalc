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
import java.util.HashMap;
import java.util.Map;

public class ContactContentRetriever {
    // Define projection for birthday query
    private static final String[] BIRTHDAY_PROJECTION = new String[]{
            ContactsContract.Data.RAW_CONTACT_ID, // 0
            ContactsContract.Data.DATA1          // 1
    };
    private static final int BIRTHDAY_PROJECTION_RAW_CONTACT_ID_INDEX = 0;
    private static final int BIRTHDAY_PROJECTION_DATA1_INDEX = 1;

    private static final String BIRTHDAY_SORT_ORDER = ContactsContract.Contacts.Entity.RAW_CONTACT_ID + " ASC";

    // Define projection for contacts query
    private static final String[] CONTACT_PROJECTION = new String[]{
            ContactsContract.RawContacts._ID, // 0
            ContactsContract.RawContacts.CONTACT_ID,          // 1
    };
    private static final int CONTACT_PROJECTION_ID_INDEX = 0;
    private static final int CONTACT_PROJECTION_CONTACT_ID_INDEX = 1;

    private static final String CONTACT_SORT_ORDER = ContactsContract.RawContacts._ID + " ASC";

    // Define projection for name query
    private static final String[] NAME_PROJECTION = new String[]{
            ContactsContract.Data.RAW_CONTACT_ID, // 0
            ContactsContract.Data.DATA2          // 1
    };
    private static final int NAME_PROJECTION_RAW_CONTACT_ID_INDEX = 0;
    private static final int NAME_PROJECTION_FIRST_NAME_INDEX = 1;

    private static final String NAME_SORT_ORDER = ContactsContract.Contacts.Entity.RAW_CONTACT_ID + " ASC";

    // Define projection for contact data query
    private static final String[] CONTACT_DATA_PROJECTION = new String[]{
            ContactsContract.Data.RAW_CONTACT_ID, // 0
            ContactsContract.Data.DATA1,          // 1
            ContactsContract.Data.MIMETYPE,       // 2
    };
    private static final int CONTACT_DATA_PROJECTION_RAW_CONTACT_ID_INDEX = 0;
    private static final int CONTACT_DATA_PROJECTION_DATA1_INDEX = 1;
    private static final int CONTACT_DATA_PROJECTION_MIMETYPE_INDEX = 2;

    private static final String CONTACT_DATA_SORT_ORDER = ContactsContract.Contacts.Entity.RAW_CONTACT_ID + " ASC";

    private MainActivity mainActivity;
    private ContentResolver cr;

    public ContactContentRetriever(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.cr = mainActivity.getContentResolver();
    }

    public Map<String, String> getBirthdays() {
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
        System.out.println(">>> Getting contact birthdays");
        // Build map of raw contact IDs to Birthdays
        Cursor birthdaysCursor = null;
        Uri birthdaysUri = ContactsContract.Data.CONTENT_URI;
        String birthdaySelection = ContactsContract.Data.MIMETYPE + "=?" + " AND " + ContactsContract.Data.DATA2 + "=?";
        String[] birthdaySelectionArgs = new String[]{String.valueOf(ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE),
                                                      String.valueOf(ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY)};
        birthdaysCursor = cr.query(birthdaysUri, BIRTHDAY_PROJECTION, birthdaySelection, birthdaySelectionArgs, BIRTHDAY_SORT_ORDER);
        Map<Long, String> rawToBirthday = new HashMap<>();
        while (birthdaysCursor.moveToNext()) {
            long rawContactID = 0;
            String birthday = null;

            rawContactID = birthdaysCursor.getLong(BIRTHDAY_PROJECTION_RAW_CONTACT_ID_INDEX);
            birthday = birthdaysCursor.getString(BIRTHDAY_PROJECTION_DATA1_INDEX);
            rawToBirthday.put(new Long(rawContactID), birthday);
            System.out.println(rawContactID + " " + birthday);
        }
        birthdaysCursor.close();

        // Build a map of raw contact IDs to contact IDs
        Map<Long, Long> rawToContact = new HashMap<>();
        for (Long l : rawToBirthday.keySet()) {
            Cursor contactsCursor = null;
            Uri contactsUri = ContactsContract.RawContacts.CONTENT_URI;
            String contactSelection = ContactsContract.RawContacts.CONTACT_ID + "=?";
            String[] contactSelectionArgs = new String[]{String.valueOf(l.toString())};
            contactsCursor = cr.query(contactsUri, CONTACT_PROJECTION, contactSelection, contactSelectionArgs, CONTACT_SORT_ORDER);
            while (contactsCursor.moveToNext()) {
                long rawContactID = 0;
                long contactID = 0;

                rawContactID = contactsCursor.getLong(CONTACT_PROJECTION_ID_INDEX);
                contactID = contactsCursor.getLong(CONTACT_PROJECTION_CONTACT_ID_INDEX);
                rawToContact.put(new Long(rawContactID), new Long(contactID));
                System.out.println(rawContactID + " " + contactID);
            }
            contactsCursor.close();
        }

        // Build a map of raw contact IDs to first names
        Map<Long, String> rawToName = new HashMap<>();
        for (long l : rawToBirthday.keySet()) {
            long contactID = Long.valueOf(rawToContact.get(l));
            Cursor namesCursor = null;
            Uri namesUri = ContactsContract.Data.CONTENT_URI;
            String nameSelection = ContactsContract.Data.CONTACT_ID + "=?" + " AND " + ContactsContract.Data.MIMETYPE + "=?";
            String[] namesSelectionArgs = new String[]{String.valueOf(contactID),
                                                       ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE};
            namesCursor = cr.query(namesUri, NAME_PROJECTION, nameSelection, namesSelectionArgs, NAME_SORT_ORDER);
            while (namesCursor.moveToNext()) {
                long rawContactID = 0;
                String givenName = null;

                rawContactID = namesCursor.getLong(NAME_PROJECTION_RAW_CONTACT_ID_INDEX);
                givenName = namesCursor.getString(NAME_PROJECTION_FIRST_NAME_INDEX);
                rawToName.put(rawContactID, givenName);
                System.out.println(rawContactID + " " + givenName);
            }

        }

        // Build an array of first name and birthday pairs
        Map<String, String> contactBirthdays = new HashMap<>();
        for (Long l : rawToBirthday.keySet()) {
            if (rawToName.keySet().contains(l)){
                contactBirthdays.put(rawToName.get(l), rawToBirthday.get(l));
            }
        }

        for (String s : contactBirthdays.keySet()) {
            System.out.println(s + " " + contactBirthdays.get(s));
        }
        return contactBirthdays;
    }
}
