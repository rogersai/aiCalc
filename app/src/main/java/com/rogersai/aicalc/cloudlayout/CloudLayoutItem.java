package com.rogersai.aicalc.cloudlayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rogersai.aicalc.R;

public class CloudLayoutItem extends Fragment {
    private String itemText;
    private int itemID;
    private static int nextID = 0;

    private TextView textView;
    private TextView inputView;
    private FragmentManager fragmentManager;

    public static CloudLayoutItem newInstance(String text) {
        CloudLayoutItem item = new CloudLayoutItem();
        Bundle args = new Bundle();
        args.putString("itemText", text);
        args.putInt("itemID", nextID);
        item.setArguments(args);
        nextID++;
        return item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cloud_layout_item, container, false);

        textView = (TextView) view.findViewById(R.id.itemTextView);
        inputView = (TextView) getActivity().findViewById(R.id.inputView);

        if(getArguments() != null) {
            itemText = this.getArguments().getString("itemText");
        }

        textView.setText(itemText);

        fragmentManager = getFragmentManager();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText()+ getItemText());
            }
        });
        return view;
    }

    public int getItemID() {
        return itemID;
    }

    public String getItemText() {
        return itemText;
    }
}
