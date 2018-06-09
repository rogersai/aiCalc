package com.rogersai.aicalc;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "elements")
public class Elements {

    @DatabaseField
    private String name;
    @DatabaseField
    private String symbol;

}
