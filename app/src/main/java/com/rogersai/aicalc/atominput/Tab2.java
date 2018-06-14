package com.rogersai.aicalc.atominput;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rogersai.aicalc.CloudGenerator;
import com.rogersai.aicalc.R;

import java.util.ArrayList;

public class Tab2 extends Fragment implements CloudGenerator{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       return inflater.inflate(R.layout.tab2, container, false) ;
    }
    @Override
    public ArrayList<String> generateCloudItems() {
        ArrayList<String> cloudList = new ArrayList<>();
        cloudList.add("Date");
        return cloudList;
    }
}
