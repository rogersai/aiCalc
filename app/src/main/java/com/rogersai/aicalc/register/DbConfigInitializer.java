package com.rogersai.aicalc.register;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

public class DbConfigInitializer extends OrmLiteConfigUtil {
    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt");
    }
}
