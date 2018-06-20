package com.rogersai.aicalc.symbol;

import com.rogersai.aicalc.symbol.atom.NumberAtom;

import java.util.Objects;

public class Symbol {
    protected String category;
    protected String type;

    public Symbol() {
        category = "category";
        type = "type";
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return Objects.equals(category, symbol.category) &&
                Objects.equals(type, symbol.type);
    }

    @Override public String toString() {
        return type + " " + category;
    }
}
