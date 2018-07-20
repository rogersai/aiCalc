package com.rogersai.aicalc.atominput;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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

public class VolumeTypeFragment extends Fragment implements UnitLister , TypeFragment{
    private static final String[] BASIC_MASS_UNITS = {"kL", "g", "oz", "lb"};
    private static final Map<String, String[]> VOLUME_SUPERUNITS = new HashMap<String, String[]>();
    private static final Map<String, String[]> VOLUME_SUBUNITS = new HashMap<String, String[]>();
    private static final String[] BASIC_VOLUME_UNITS = {"L", "mL", "cp", "gal"};
//    private static final String[] BASIC_LENGTH_UNITS = {"m", "cm", "mm", "in", "ft", "mi"};
//    private static final String[] METRIC_PREFIXES = {"p", "n", "u", "m", "c", "d", "da", "h", "k", "M", "G", "T"};

    private String currentValue;
    private View currentUnitView;
//    private View currentUnitView;
    private View view;

    static {
        VOLUME_SUPERUNITS.put("L", new String[]{"kL", "ML", "GL", "TL"});
        VOLUME_SUBUNITS.put("L", new String[]{"cL", "qt", "cp", "mL", "uL"});

        VOLUME_SUPERUNITS.put("mL", new String[]{"L", "oz", "cp"});
        VOLUME_SUBUNITS.put("mL", new String[]{"uL", "nL"});

        VOLUME_SUPERUNITS.put("cp", new String[]{"pt", "qt", "gal", "L"});
        VOLUME_SUBUNITS.put("cp", new String[]{"Ts", "ts", "oz", "mL"});

        VOLUME_SUPERUNITS.put("gal", new String[]{});
        VOLUME_SUBUNITS.put("gal", new String[]{"L", "qt", "pt", "cp", "oz"});
    }
    private static VolumeTypeFragment instance;
    private FragmentManager fm;
    private CloudView superUnitView, subUnitView;
    private LinearLayout baseUnitView;
    private ConstraintLayout typeContainer;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.volume_type_view, container, false);
        fm = getActivity().getSupportFragmentManager();

        superUnitView = view.findViewById(R.id.volumeSuperUnitView);
        baseUnitView = view.findViewById(R.id.volumeBaseUnitView);
        subUnitView = view.findViewById(R.id.volumeSubUnitView);

        initialize();
        view.setVisibility(View.INVISIBLE);

        return view;
    }

    public void initialize() {
        FragmentTransaction ft = fm.beginTransaction();
        BasicUnitItem item = null;
        for (String unit : BASIC_VOLUME_UNITS) {
            item = BasicUnitItem.newInstance(unit, this);
            ft.add(baseUnitView.getId(), item, unit + "Item");
        }
        ft.commit();
    }

    public void listUnits(String baseUnit) {
        superUnitView.removeAllViewsInLayout();
        subUnitView.removeAllViewsInLayout();
        FragmentTransaction ft = fm.beginTransaction();
        for (String superUnit : VOLUME_SUPERUNITS.get(baseUnit)){
            SubSuperUnitItem item = SubSuperUnitItem.newInstance(superUnit);
            ft.add(superUnitView.getId(), item, superUnit + "SuperItem");
        }
        for (String subUnit : VOLUME_SUBUNITS.get(baseUnit)){
            SubSuperUnitItem item = SubSuperUnitItem.newInstance(subUnit);
            ft.add(subUnitView.getId(), item, subUnit + "SubItem");
        }
        ft.commit();
        System.out.println("Unit Listing Test");
    }

    public static VolumeTypeFragment getInstance() {
        if(instance == null){
            instance = new VolumeTypeFragment();
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
