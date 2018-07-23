package com.rogersai.aicalc.register;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "registerEntries")
public class RegisterEntry {
    private static int nextID = 0;

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String formulaString;

    public RegisterEntry(){
    }

    public RegisterEntry(String formulaString) {
//        this.id = nextID;
        this.formulaString = formulaString;
//        nextID++;
    }

    public int getId() {
        return id;
    }

    public String getFormulaString() {
        return formulaString;
    }

//    public static void setNextID(int id) {
//        nextID = id;
//    }

}
