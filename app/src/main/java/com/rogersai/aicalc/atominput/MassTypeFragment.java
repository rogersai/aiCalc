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

public class MassTypeFragment extends Fragment implements UnitLister, TypeFragment{
    private static final String[] BASIC_MASS_UNITS = {"kg", "g", "oz", "lb"};
    private static final Map<String, String[]> MASS_SUPERUNITS= new HashMap<String, String[]>();
    private static final Map<String, String[]> MASS_SUBUNITS= new HashMap<String, String[]>();
//    private static final String[] BASIC_VOLUME_UNITS = {"L", "mL", "cp", "gal"};
//    private static final String[] BASIC_LENGTH_UNITS = {"m", "cm", "mm", "in", "ft", "mi"};
//    private static final String[] METRIC_PREFIXES = {"p", "n", "u", "m", "c", "d", "da", "h", "k", "M", "G", "T"};

    private String currentValue;
    private View currentUnitView;
    private View view;

    static {
        MASS_SUPERUNITS.put("kg", new String[]{"Mg", "Gg", "Tg"});
        MASS_SUBUNITS.put("kg", new String[]{"g", "mg", "lb", "oz"});


        MASS_SUPERUNITS.put("g", new String[]{"kg", "lb", "oz"});
        MASS_SUBUNITS.put("g", new String[]{"cg","mg"});


        MASS_SUPERUNITS.put("oz", new String[]{"lb", "kg"});
        MASS_SUBUNITS.put("oz", new String[]{"g", "mg"});

        MASS_SUPERUNITS.put("lb", new String[]{"kg", "ton"});
        MASS_SUBUNITS.put("lb", new String[]{"oz", "g"});
    }
    private static MassTypeFragment instance;
    private FragmentManager fm;
    private CloudView superUnitView, subUnitView;
    private LinearLayout baseUnitView;
    private ConstraintLayout typeContainer;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mass_type_view, container, false);
        fm = getActivity().getSupportFragmentManager();

        superUnitView = view.findViewById(R.id.massSuperUnitView);
        baseUnitView = view.findViewById(R.id.massBaseUnitView);
        subUnitView = view.findViewById(R.id.massSubUnitView);

        initialize();
//        view.setVisibility(View.INVISIBLE);

        return view;
    }

    public void initialize() {
        FragmentTransaction ft = fm.beginTransaction();
        BasicUnitItem item = null;
        for (String unit : BASIC_MASS_UNITS) {
            item = BasicUnitItem.newInstance(unit, this);
            ft.add(baseUnitView.getId(), item, unit + "Item");
        }
        ft.commit();
    }

    public void listUnits(String baseUnit) {
        superUnitView.removeAllViewsInLayout();
        subUnitView.removeAllViewsInLayout();
        FragmentTransaction ft = fm.beginTransaction();
        for (String superUnit : MASS_SUPERUNITS.get(baseUnit)){
            SubSuperUnitItem item = SubSuperUnitItem.newInstance(superUnit);
            ft.add(superUnitView.getId(), item, superUnit + "SuperItem");
        }
        for (String subUnit : MASS_SUBUNITS.get(baseUnit)){
            SubSuperUnitItem item = SubSuperUnitItem.newInstance(subUnit);
            ft.add(subUnitView.getId(), item, subUnit + "SubItem");
        }
        ft.commit();
        System.out.println("Unit Listing Test");
    }

    public static MassTypeFragment getInstance() {
        if(instance == null){
            instance = new MassTypeFragment();
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

    @Override
    public View getCurrentUnitView() {
        return currentUnitView;
    }

    @Override
    public void setCurrentUnitView(View currentUnitView) {
        this.currentUnitView = currentUnitView;
    }
}
