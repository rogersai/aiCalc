package com.rogersai.aicalc.atominput;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rogersai.aicalc.R;
import com.rogersai.aicalc.cloud.CloudView;

import java.util.HashMap;
import java.util.Map;

public class LengthTypeFragment extends Fragment implements UnitLister , TypeFragment{
    private static final String[] BASIC_LENGTH_UNITS = {"km", "m", "cm", "ft", "in"};
    private static final Map<String, String[]> LENGTH_SUPERUNITS = new HashMap<String, String[]>();
    private static final Map<String, String[]> LENGTH_SUBUNITS = new HashMap<String, String[]>();
//    private static final String[] BASIC_VOLUME_UNITS = {"L", "mL", "cp", "gal"};
//    private static final String[] BASIC_LENGTH_UNITS = {"m", "cm", "mm", "in", "ft", "mi"};
//    private static final String[] METRIC_PREFIXES = {"p", "n", "u", "m", "c", "d", "da", "h", "k", "M", "G", "T"};

    private String currentValue;
    private View currentUnitView;
    private View view;

    static {
        LENGTH_SUPERUNITS.put("km", new String[]{"mi", "Mm"});
        LENGTH_SUBUNITS.put("km", new String[]{"m", "cm", "yd", "ft", "in"});

        LENGTH_SUPERUNITS.put("m", new String[]{"km", "mi"});
        LENGTH_SUBUNITS.put("m", new String[]{"cm", "mm", "um"});

        LENGTH_SUPERUNITS.put("cm", new String[]{"m", "in", "ft"});
        LENGTH_SUBUNITS.put("cm", new String[]{"mm", "um", "nm", "pm"});

        LENGTH_SUPERUNITS.put("ft", new String[]{"yd", "mi", "m", "km"});
        LENGTH_SUBUNITS.put("ft", new String[]{"in", "cm", "mm"});

        LENGTH_SUPERUNITS.put("in", new String[]{"ft", "mi", "m", "km"});
        LENGTH_SUBUNITS.put("in", new String[]{"cm", "mm"});
    }
    private static LengthTypeFragment instance;
    private FragmentManager fm;
    private CloudView superUnitView, subUnitView;
    private LinearLayout baseUnitView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.length_type_view, container, false);
        fm = getActivity().getSupportFragmentManager();

        superUnitView = view.findViewById(R.id.lengthSuperUnitView);
        baseUnitView = view.findViewById(R.id.lengthBaseUnitView);
        subUnitView = view.findViewById(R.id.lengthSubUnitView);

        initialize();
        view.setVisibility(View.INVISIBLE);

        return view;
    }

    public void initialize() {
        FragmentTransaction ft = fm.beginTransaction();
        BasicUnitItem item = null;
        for (String unit : BASIC_LENGTH_UNITS) {
            item = BasicUnitItem.newInstance(unit, this);
            ft.add(baseUnitView.getId(), item, unit + "Item");
        }
        ft.commit();
    }

    public void listUnits(String baseUnit) {
        superUnitView.removeAllViewsInLayout();
        subUnitView.removeAllViewsInLayout();
        FragmentTransaction ft = fm.beginTransaction();
        for (String superUnit : LENGTH_SUPERUNITS.get(baseUnit)){
            SubSuperUnitItem item = SubSuperUnitItem.newInstance(superUnit);
            ft.add(superUnitView.getId(), item, superUnit + "SuperItem");
        }
        for (String subUnit : LENGTH_SUBUNITS.get(baseUnit)){
            SubSuperUnitItem item = SubSuperUnitItem.newInstance(subUnit);
            ft.add(subUnitView.getId(), item, subUnit + "SubItem");
        }
        ft.commit();
        System.out.println("Unit Listing Test");
    }

    public static LengthTypeFragment getInstance() {
        if(instance == null){
            instance = new LengthTypeFragment();
        }
        return instance;
    }

//    public void setCurrentUnitView(View currentUnitView) {
//        this.currentUnitView = currentUnitView;
//    }
//
//    public View getCurrentUnitView() {
//        return currentUnitView;
//    }

    public View getView() {
        return view;
    }

    public View getCurrentUnitView() {
        return currentUnitView;
    }

    public void setCurrentUnitView(View currentUnitView) {
        this.currentUnitView = currentUnitView;
    }
}
