package com.rogersai.aicalc.register;

import android.app.Application;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.table.TableUtils;
import com.rogersai.aicalc.MainActivity;
import com.rogersai.aicalc.R;
import com.rogersai.aicalc.backend.CalcBackend;

import java.sql.SQLException;
import java.util.List;

public class RegisterBackend extends Fragment {
    // TODO: Populate registers from database on app start
    private static RegisterBackend instance;
    private FragmentManager fm;
    private RegisterFragment registerFragment;

    private RegisterDatabaseHelper dbHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fm = getActivity().getSupportFragmentManager();
        getHelper();
        System.out.println("GETHELPER CALLED IN ONCREATEVIEW*****88888");
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        populate();
    }

    public static RegisterBackend getInstance(RegisterFragment rf) {
       if (instance == null) {
           instance = new RegisterBackend();
           instance.setRegisterFragment(rf);
       }
       return instance;
    }
    public static RegisterBackend getInstance() {
        if (instance == null) {
            instance = new RegisterBackend();
        }
        return instance;
    }

    public void add(RegisterEntry re) {
        RuntimeExceptionDao<RegisterEntry, Integer> registerDao = getHelper().getRegisterDao();
        System.out.println("Adding entry " + re.getId() + ": " + re.getFormulaString());
        registerDao.create(re);
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment registerItem = RegisterItem.newInstance(re);
        fragmentTransaction.add(registerFragment.getRegisterView().getId(), registerItem, "reg" + re.getId());
        fragmentTransaction.commit();

    }
    public void add(String formulaString) {
        RegisterEntry re = new RegisterEntry(formulaString);
        add(re);
    }
    public void remove(int id) {
        RuntimeExceptionDao<RegisterEntry, Integer> registerDao = getHelper().getRegisterDao();
        System.out.println("Removing entry " + id);
        registerDao.deleteById(id);
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fm.findFragmentByTag("reg" + id));
        ft.commit();
    }
    public void clear() {
        try {
            TableUtils.clearTable(getHelper().getConnectionSource(), RegisterEntry.class);
        } catch (SQLException e) {
            System.out.println("Failed to clear table: " + e.getMessage());
        }
        registerFragment.getRegisterView().removeAllViewsInLayout();
    }
    public void calculateAll() {
        RuntimeExceptionDao<RegisterEntry, Integer> registerDao = getHelper().getRegisterDao();
        for(RegisterEntry re : registerDao.queryForAll()) {
            int id = re.getId();
            System.out.println("Register ID: " + "reg" + id);
            RegisterItem ri = (RegisterItem) fm.findFragmentByTag("reg" + id);
            ri.calculate();
        }
    }

    public void populate() {
        RuntimeExceptionDao<RegisterEntry, Integer> registerDao = getHelper().getRegisterDao();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        for(RegisterEntry re : registerDao.queryForAll()) {
            int id = re.getId();
            RegisterItem registerItem = RegisterItem.newInstance(re);
            Fragment ri = (Fragment) registerItem;
            fragmentTransaction.add(registerFragment.getRegisterView().getId(), registerItem, "reg" + re.getId());
        }
        fragmentTransaction.commit();

    }

    public void testSelf() {
        String tag = "";
        int itemID = 0;
        for (int i = 0; i <=20; i++) {
            tag = "tag" + i;
            add(new RegisterEntry(tag));
        }
    }
    private RegisterDatabaseHelper getHelper() {
        if (dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(getContext(), RegisterDatabaseHelper.class);
        }
        return dbHelper;
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

    public void setRegisterFragment(RegisterFragment registerFragment) {
        this.registerFragment = registerFragment;
    }

    public void setFm(FragmentManager fm) {
        this.fm = fm;
    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if(dbHelper != null) {
//            OpenHelperManager.releaseHelper();
//            dbHelper = null;
//        }
//    }

}
