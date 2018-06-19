package com.rogersai.aicalc.register;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.rogersai.aicalc.R;

public class RegisterDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "register.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<RegisterEntry, Integer> registerDao = null;
    private RuntimeExceptionDao<RegisterEntry, Integer> registerRuntimeDao = null;

    public RegisterDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
        getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(RegisterDatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, RegisterEntry.class);
        } catch (java.sql.SQLException e) {
            Log.e(RegisterDatabaseHelper.class.getName(), "Can't create a database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public Dao<RegisterEntry, Integer> getDao() throws SQLException {
        if (registerDao == null) {
            registerDao = getDao(RegisterEntry.class);
        }
        return registerDao;
    }

    public RuntimeExceptionDao<RegisterEntry, Integer> getRegisterDao(){
        if (registerRuntimeDao == null) {
            registerRuntimeDao = getRuntimeExceptionDao(RegisterEntry.class);
        }
        return registerRuntimeDao;
    }

    @Override
    public void close(){
        super.close();
        registerDao = null;
        registerRuntimeDao = null;
    }
}
