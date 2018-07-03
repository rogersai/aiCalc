package com.rogersai.aicalc.atominput;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.rogersai.aicalc.AtomTab;
import com.rogersai.aicalc.CalendarContentRetriever;
import com.rogersai.aicalc.CloudGenerator;
import com.rogersai.aicalc.R;
import com.rogersai.aicalc.backend.CalcBackend;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DateAtomTab extends AtomTab implements CloudGenerator{
    private CalcBackend calc;
    private FragmentManager fm;

    private ScrollView dayPicker, monthPicker, yearPicker;

    private LinearLayout dayList, monthList, yearList;

    private TextView dayView, monthView, yearView;

    private ConstraintLayout dateViewLayout;

    private static DateAtomTab instance;
//    private TextView inputView, outputView;
//    private Parser parser;
//    private Evaluator evaluator;

    public static DateAtomTab getInstance() {
        if (instance == null) {
            instance = new DateAtomTab();
        }
        return instance;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.date_atom_tab, container, false) ;

        fm = getActivity().getSupportFragmentManager();

        dayPicker = view.findViewById(R.id.dayPicker);
        monthPicker = view.findViewById(R.id.monthPicker);
        yearPicker = view.findViewById(R.id.yearPicker);

        dayList = view.findViewById(R.id.dayList);
        monthList = view.findViewById(R.id.monthList);
        yearList = view.findViewById(R.id.yearList);

        dayView = view.findViewById(R.id.dayView);
        monthView = view.findViewById(R.id.monthView);
        yearView = view.findViewById(R.id.yearView);

        dateViewLayout = view.findViewById(R.id.dateViewLayout);

        dayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected = getSelectedSpinnerItem(dayList, dayPicker);
                dayView.setText(selected);

            }
        });
        monthList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected = getSelectedSpinnerItem(monthList, monthPicker);
                monthView.setText(selected);

            }
        });
        yearList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected = getSelectedSpinnerItem(yearList, yearPicker);
                yearView.setText(selected);

            }
        });
        dateViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input();
            }
        });
        calc = CalcBackend.getInstance();

        initialize();

        return view;
    }

    private void initialize() {
        final GregorianCalendar gc = new GregorianCalendar();

        for (int i = 1; i < 32; i++) {
            TextView dayView = new TextView(getContext());
            dayView.setText(Integer.toString(i));
            dayList.addView(dayView);
        }

        dayList.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        System.out.println("Daylist height: " + dayList.getMeasuredHeight());
//        System.out.println("Scroll to: " + Math.round( (float) day / 30.0f * dayList.getMeasuredHeight()));
//        dayPicker.scrollTo(0, Math.round(dayList.getY() + dayList.getMeasuredHeight() /2) );
        dayPicker.post(new Runnable() {
            @Override
            public void run() {
                int day = gc.get(gc.DAY_OF_MONTH);
                dayPicker.scrollTo(0, Math.round( (float) day / 31.0f * dayList.getMeasuredHeight()));
            }
        });
//        for (int i = 0; i < 3000; i++) {
//            TextView monthView = new TextView(getContext());
//            monthView.setText(Integer.toString(i));
//            monthList.addView(monthView);
//        }
        monthPicker.post(new Runnable() {
            @Override
            public void run() {
                int month = gc.get(gc.MONTH);
                monthPicker.scrollTo(0, Math.round( month / 12.0f *  monthList.getMeasuredHeight() ));
            }
        });


        int year = gc.get(gc.YEAR);
        for (int i = year - 100; i < year + 100; i++) {
            TextView yearView = new TextView(getContext());
            yearView.setText(Integer.toString(i));
            yearList.addView(yearView);
        }
        yearPicker.post(new Runnable() {
            @Override
            public void run() {
                yearPicker.scrollTo(0, Math.round( yearList.getMeasuredHeight() / 2));
            }
        });
    }
    @Override
    public ArrayList<Pair<String, String>> generateCloudItems() {
        CalendarContentRetriever ccr = calc.getCalendarCR();
        ArrayList<Pair<String, String>> cloudList = ccr.getHolidays();
        cloudList.add(0, new Pair("Date", "Date"));
//        System.out.print(getSelectedSpinnerItem(dayList, dayPicker));
//        System.out.print(getSelectedSpinnerItem(monthList, monthPicker));
//        System.out.println(getSelectedSpinnerItem(yearList, yearPicker));
        return cloudList;
    }

    public void input(){
        String date = dayView.getText().toString() + monthView.getText().toString() + yearView.getText().toString();
        calc.input(date);
    }

    private String getSelectedSpinnerItem(LinearLayout list, ScrollView scroll) {
        for(int i = 0; i < list.getChildCount(); i  ++){
            if(isCenteredInView(list.getChildAt(i), scroll)) {
                return ((TextView)list.getChildAt(i)).getText().toString();
            }
        }
        return "ERR";
    }

    private boolean isInsideView(View view, View container) {
        Rect bounds = new Rect();
        container.getDrawingRect(bounds);

        float top = view.getY();
        float bottom = top + view.getHeight();
        float left = view.getX();
        float right = left + view.getWidth();

        if(bounds.top <= top && bounds.bottom >= bottom && bounds.left <= left && bounds.right >= right) {
            return true;
        } else{
            return false;
        }
    }

    private boolean isCenteredInView(View view, View container) {
        Rect bounds = new Rect();
        container.getDrawingRect(bounds);
        float midX = (bounds.left + bounds.right) / 2;
        float midY = (bounds.top + bounds.bottom) / 2;

        float top = view.getY();
        float bottom = top + view.getHeight();
        float left = view.getX();
        float right = left + view.getWidth();

        if(midY >= top && midY <= bottom && midX >= left && midX <= right) {
            return true;
        } else{
            return false;
        }
    }
}
