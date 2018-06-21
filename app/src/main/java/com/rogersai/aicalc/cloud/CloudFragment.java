package com.rogersai.aicalc.cloud;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rogersai.aicalc.R;

public class CloudFragment extends Fragment {
    private static CloudFragment instance;
    private FragmentManager fm;
    private CloudBackend cloudBackend;
    private CloudView cloudView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cloud_layout_view, container, false);
        cloudView = view.findViewById(R.id.cloudView);
        fm = getActivity().getSupportFragmentManager();

        cloudBackend = CloudBackend.getInstance(this);
        FragmentTransaction ft = fm.beginTransaction();
        Fragment cb = (Fragment) cloudBackend;
        ft.add(cloudView.getId(), cb, "cloudBackend");
        ft.commit();
        return view;
    }

    public static CloudFragment getInstance() {
        if(instance == null){
            instance = new CloudFragment();
            instance.setCloudBackend(CloudBackend.getInstance(instance));
        }
        return instance;
    }

    public CloudView getCloudView() {
        return cloudView;
    }
    public CloudBackend getCloudBackend() {
        return cloudBackend;
    }

    public void setCloudBackend(CloudBackend cloudBackend) {
        this.cloudBackend = cloudBackend;
    }
}
