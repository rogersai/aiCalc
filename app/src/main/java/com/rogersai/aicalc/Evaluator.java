package com.rogersai.aicalc;

import com.rogersai.aicalc.symbol.Symbol;
import com.rogersai.aicalc.symbol.atom.NumberAtom;
import com.rogersai.aicalc.symbol.grouper.Grouper;
import com.rogersai.aicalc.symbol.grouper.LParenGrouper;
import com.rogersai.aicalc.symbol.operator.Operator;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Evaluator {
    public Symbol evaluate (ArrayList<Symbol> input) {
        ArrayList<Symbol> workingList = input;
        Symbol lParen = new LParenGrouper();
        // Perform grouping
        while (workingList.contains(lParen)){
           workingList = evaluateInnermostGroup(workingList);
        }
        // Perform operations
        while (workingList.size() > 1) {
            // Perform multiplication and division
            //System.out.println("Performing Multiplication");
            for (int i = 0; i < workingList.size(); i++) {
                if (workingList.get(i).getType().equals("multiplication") || workingList.get(i).getType().equals("division")) {
                    Operator op = (Operator) workingList.get(i);
                    Symbol result = op.operate(workingList.get(i - 1), workingList.get(i + 1));
                    workingList.set(i, result);
                    workingList.remove(i + 1);
                    workingList.remove(i - 1);
                    i--;
                }
            }
            // Perform addition and subtraction
            //System.out.println("Performing Addition");
            for (int i = 0; i < workingList.size(); i++) {
                if (workingList.get(i).getType().equals("addition") || workingList.get(i).getType().equals("subtraction")) {
                    Operator op = (Operator) workingList.get(i);
                    Symbol result = op.operate(workingList.get(i - 1), workingList.get(i + 1));
                    workingList.set(i, result);
                    workingList.remove(i + 1);
                    workingList.remove(i - 1);
                    i--;
                }
            }
        }
        return workingList.get(0);
    }

    private ArrayList<Symbol> evaluateInnermostGroup(ArrayList<Symbol> workingList) {
        // Find innermost group
        int[] innerGroup = findInnermostGroup(workingList);
        int groupStart = innerGroup[0];
        int groupStop = innerGroup[1];
        // Get contents of innermost group
        ArrayList<Symbol> groupList = new ArrayList<>(workingList.subList(groupStart + 1, groupStop));
        for (int i = 0; i < groupList.size(); i++){
            System.out.print(groupList.get(i).toString());
        }
        System.out.println();
        // Evaluate innermost group
        Symbol result = evaluate(groupList);
        System.out.println("Evaluates to " + result.toString());
        // Replace innermost group with result
        workingList.set(groupStart, result);
        for (int k = groupStop; k > groupStart; k = k){
            workingList.remove(k);
            k = k-1;
        }
        // Print new expression
        for (int m = 0; m < workingList.size(); m++){
            System.out.print(workingList.get(m).toString());
        }
        System.out.println();
        return workingList;
    }

    public int[] findInnermostGroup(ArrayList<Symbol> workingList) {
        int[] innerGroup = new int[2];
        int groupStart = 0;
        int groupStop = 0;

        for (int i = 0; i < workingList.size(); i++){
            if (workingList.get(i).getType().equals("lparen")) {
                groupStart = i;
                System.out.println("( Found at " + i);
            }
            if (workingList.get(i).getType().equals("rparen")) {
                groupStop = i;
                System.out.println(") Found at " + i);
                innerGroup[0] = groupStart;
                innerGroup[1] = groupStop;
                System.out.print("(" + groupStart + "," + groupStop + ")");
                System.out.println(" Grouped");
                return innerGroup;
            }
        }
        innerGroup[0] = groupStart;
        innerGroup[1] = workingList.size();
        System.out.print("(" + groupStart + "," + groupStop + ")");
        System.out.println(" Grouped");
        return innerGroup;
    }
}
