package com.rogersai.aicalc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TapeLayoutItem extends Fragment {
    private String itemText;
    private int itemID;

    private TextView textView;
    private TextView inputView;
    private Button closeButton;
    private LinearLayout linearLayout;
    private FragmentManager fragmentManager;
    static int nextID = 0;

    public static TapeLayoutItem newInstance(String text) {
        TapeLayoutItem item = new TapeLayoutItem();
        Bundle args = new Bundle();
        args.putString("itemText", text);
        args.putInt("itemID", nextID);
        item.setArguments(args);
        nextID++;
        return item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tape_layout_item, container, false);

        textView = (TextView) view.findViewById(R.id.itemTextView);
        closeButton= (Button) view.findViewById(R.id.closeButton);
        inputView = (TextView) getActivity().findViewById(R.id.inputView);

        if(getArguments() != null) {
            itemText = this.getArguments().getString("itemText");
            itemID = this.getArguments().getInt("itemID");
        }

        System.out.println("tape" + this.getItemID() + " created");
        textView.setText(itemText);

        fragmentManager = getFragmentManager();
        linearLayout = getActivity().findViewById(R.id.tapeLayout);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                System.out.println("tape" + itemID + " removed");
                fragmentTransaction.remove(getFragmentManager().findFragmentByTag("tape" + itemID));
                fragmentTransaction.commit();
            }
        });
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
