package com.tsystems.javaschool.tasks.subsequence;

import java.util.ArrayList;
import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequenc which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        // TODO: Implement the logic here

        if (x == null || y == null){
            throw new IllegalArgumentException();
        }

        List<Object> coll = new ArrayList<>();

        boolean result = true;
        int limiter = 0;

        for (int i=0; i<x.size(); i++){
            for (int j=0; j<y.size(); j++){
                if (j>=i && j>=limiter && x.get(i).equals(y.get(j))){
                    coll.add(y.get(j));
                    limiter = j;
                    break;
                }
            }
        }

        if (x.size() == coll.size()) {
            for (int i = 0; i < x.size(); i++) {
                if (x.get(i).equals(coll.get(i))) {
                    result = true;
                } else {
                    result = false;
                }
            }
        }   else {
            result = false;
        }

        return result;
    }
}
