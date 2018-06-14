package com.rogersai.aicalc.atominput;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.rogersai.aicalc.AtomTab;
import com.rogersai.aicalc.CloudGenerator;
import com.rogersai.aicalc.R;
import com.rogersai.aicalc.backend.CalcBackend;

import java.util.ArrayList;

public class DateAtomTab extends AtomTab implements CloudGenerator{
    private CalcBackend calc;

    private ScrollView monthPicker;
    private LinearLayout monthList;

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
        monthPicker = view.findViewById(R.id.monthPicker);
        monthList = view.findViewById(R.id.monthList);

        calc = CalcBackend.getInstance();

        return view;
    }

    @Override
    public ArrayList<String> generateCloudItems() {
        ArrayList<String> cloudList = new ArrayList<>();
        cloudList.add("Date");
        System.out.println(getSelectedMonth());
        return cloudList;
    }

    private String getSelectedMonth() {
        for(int i = 0; i < monthList.getChildCount(); i  ++){
            if(isCenteredInView(monthList.getChildAt(i), monthPicker)) {
                return ((TextView)monthList.getChildAt(i)).getText().toString();
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
