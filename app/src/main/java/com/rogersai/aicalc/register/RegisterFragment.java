package com.rogersai.aicalc.register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.table.TableUtils;
import com.rogersai.aicalc.R;
import com.rogersai.aicalc.backend.CalcBackend;

import java.sql.SQLException;
import java.util.List;

public class RegisterFragment extends Fragment {
    private CalcBackend calc;
    private FragmentManager fragmentManager;
    private RegisterDatabaseHelper dbHelper;

    private RegisterView registerView;

    private Button buttonClear;
    private Button buttonCalc;

    public static RegisterFragment newInstance() {
       RegisterFragment layout = new RegisterFragment();
       return layout;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_layout_view, container, false);

        calc = CalcBackend.getInstance();
        fragmentManager = getFragmentManager();
        getHelper();

        registerView = (RegisterView) view.findViewById(R.id.registerView);
        buttonClear = view.findViewById(R.id.buttonClearRegister);
        buttonCalc = view.findViewById(R.id.buttonCalcRegister);

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.clearRegister();
            }
        });
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.testRegister();
            }
        });

        return view;
    }

    private void testDB() {
        RuntimeExceptionDao<RegisterEntry, Integer> registerDao = getHelper().getRegisterDao();
        List<RegisterEntry> list = registerDao.queryForAll();
        StringBuilder sb= new StringBuilder();
        sb.append("Found ").append(list.size()).append(" entries in DB in ").append("testDB").append("()\n");
        for(RegisterEntry re : list) {
            sb.append("#").append(re.getId()).append(": ").append(re.getFormulaString()).append('\n');
        }
        System.out.println(sb.toString());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(dbHelper != null) {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }

    private RegisterDatabaseHelper getHelper() {
        if (dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(getContext(), RegisterDatabaseHelper.class);
        }
        return dbHelper;
    }

    public void add(RegisterEntry re) {
        RuntimeExceptionDao<RegisterEntry, Integer> registerDao = getHelper().getRegisterDao();
        System.out.println("Adding entry " + re.getId() + ": " + re.getFormulaString());
        registerDao.create(re);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment registerItem = RegisterItem.newInstance(re);
        fragmentTransaction.add(registerView.getId(), registerItem, "reg" + re.getId());
        fragmentTransaction.commit();

    }
    public void add(String formulaString) {
        RegisterEntry re = new RegisterEntry(formulaString);
        add(re);
    }

    public void clear() {
        try {
            TableUtils.clearTable(dbHelper.getConnectionSource(), RegisterEntry.class);
        } catch (SQLException e) {
            System.out.println("Failed to clear table: " + e.getMessage());
        }
        registerView.removeAllViewsInLayout();
    }

    public void testSelf() {
        String tag = "";
        int itemID = 0;
        for (int i = 0; i <=20; i++) {
            tag = "tag" + i;
            add(new RegisterEntry(tag));
        }
    }

    public void remove(int id) {
        RuntimeExceptionDao<RegisterEntry, Integer> registerDao = getHelper().getRegisterDao();
        System.out.println("Removing entry " + id);
        registerDao.deleteById(id);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.remove(fragmentManager.findFragmentByTag("reg" + id));
        ft.commit();
    }
}
