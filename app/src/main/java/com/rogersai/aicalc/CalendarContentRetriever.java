package com.rogersai.aicalc;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.util.Pair;

import com.rogersai.aicalc.symbol.atom.DateAtom;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.ArrayList;

public class CalendarContentRetriever {
    public static final String[] CALENDARS_PROJECTION = new String[]{
            CalendarContract.Calendars._ID,                      // 0
            CalendarContract.Calendars.ACCOUNT_NAME,             // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,    // 2
            CalendarContract.Calendars.OWNER_ACCOUNT             // 3
    };

    private static final int CALENDARS_PROJECTION_ID_INDEX = 0;
    private static final int CALENDARS_PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int CALENDARS_PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int CALENDARS_PROJECTION_OWNER_ACCOUNT_INDEX = 3;

    public static final String[] EVENTS_PROJECTION = new String[]{
            CalendarContract.Events._ID,        // 0
            CalendarContract.Events.TITLE,      // 1
            CalendarContract.Events.DTSTART,    // 2
//            CalendarContract.Events.DTEND,      // 3
//            CalendarContract.Events.ALL_DAY     // 4
    };

    private static final int EVENTS_PROJECTION_ID_INDEX = 0;
    private static final int EVENTS_PROJECTION_TITLE_INDEX = 1;
    private static final int EVENTS_PROJECTION_START_INDEX = 2;
//    private static final int EVENTS_PROJECTION_END_INDEX = 3;
//    private static final int EVENTS_PROJECTION_ALL_DAY_INDEX = 4;

    private final MainActivity mainActivity;

    private ContentResolver cr;

    public CalendarContentRetriever(MainActivity mainActivity) {
        this.cr = mainActivity.getContentResolver();
        this.mainActivity = mainActivity;
    }

    public void getCalendars() {
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


        System.out.print(">>> Getting Calendars");
        Cursor calendarsCursor = null;
        Uri calendarsUri = CalendarContract.Calendars.CONTENT_URI;
        calendarsCursor = cr.query(calendarsUri, CALENDARS_PROJECTION, null, null, null);
        while (calendarsCursor.moveToNext()) {
            long calID = 0;
            String displayName = null;
            String accountName = null;
            String ownerName = null;

            calID = calendarsCursor.getLong(CALENDARS_PROJECTION_ID_INDEX);
            displayName = calendarsCursor.getString(CALENDARS_PROJECTION_DISPLAY_NAME_INDEX);
            accountName = calendarsCursor.getString(CALENDARS_PROJECTION_ACCOUNT_NAME_INDEX);
            ownerName = calendarsCursor.getString(CALENDARS_PROJECTION_OWNER_ACCOUNT_INDEX);

            System.out.println(calID + " " + displayName + " " + accountName + " " + ownerName);
        }

    }

    public ArrayList<Pair<String, String>> getHolidays() {
        ArrayList<Pair<String, String>> holidays = new ArrayList<>();
        // TODO: Make getHolidays only refresh if necessary
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

        System.out.println(">>> Getting Holidays");
        Uri.Builder eventUriBuilder = Uri.parse("content://com.android.calendar/instances/when").buildUpon();
        long now = DateTime.now().getMillis();
        ContentUris.appendId(eventUriBuilder, DateTime.now().getMillis());
        ContentUris.appendId(eventUriBuilder, DateTime.now().plusYears(1).getMillis());
        String eventsSelection = "((" + CalendarContract.Calendars.CALENDAR_DISPLAY_NAME + " = ?))";
        String[] eventsSelectionArgs = new String[]{"Holidays in United States"};
        Cursor eventsCursor = cr.query(eventUriBuilder.build(), EVENTS_PROJECTION, eventsSelection, eventsSelectionArgs, "startDay ASC");
        while (eventsCursor.moveToNext()) {
            long eventID = 0;
            String title = null;
            long startMillis= 0;
//            String endDate = null;
//            String allDay = null;

            eventID = eventsCursor.getLong(EVENTS_PROJECTION_ID_INDEX);
            title = eventsCursor.getString(EVENTS_PROJECTION_TITLE_INDEX);
            startMillis = eventsCursor.getLong(EVENTS_PROJECTION_START_INDEX);
            DateTime startDate = new DateTime(startMillis, DateTimeZone.UTC);
            String startDateString = DateAtom.formatter.print(startDate).toUpperCase();
            System.out.println(title + " " + startDateString);
            holidays.add(new Pair(title, startDateString));
//            endDate = eventsCursor.getString(EVENTS_PROJECTION_END_INDEX);
//            allDay = eventsCursor.getString(EVENTS_PROJECTION_ALL_DAY_INDEX);

//            System.out.println(eventID + " " + title + " " + startDate);
        }
        return holidays;
    }

    public void testSelf() {
        System.out.println("++++ Calendar Content Test Starting ++++");
        getCalendars();
        ArrayList<Pair<String, String>> holidays = getHolidays();
        for (int i = 0; i < holidays.size(); i++) {
            System.out.println(holidays.get(i).toString());
        }

        System.out.println("++++ Calendar Content Receiver Done ++++");


    }
}
