package com.mukhar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MotifEnumeration {
    public ArrayList<String> findMotifs(ArrayList<String> dna, int k, int d) {
        ArrayList<String> result = new ArrayList<>();
        // for each k-mer a in Dna
        // for each k-mer a' differing from a by at most d mutations
        String size = (k < 10 ? "0" + k : "" + k);
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/mukhar/kmersOfSize"
                + size + ".txt"), 524288)) {
            String s = "";
            while ((s = br.readLine()) != null) {
                boolean found = true;
                for (String strDna : dna) {
                    int[] matches = PatternFinder.closeMatches(s, strDna, d);
                    // if a' appears in each string from Dna with at most d
                    // mutations
                    if (matches.length == 0) {
                        found = false;
                        break;
                    }
                }
                if (found)
                    result.add(s);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // output a'
        return result;
    }
}
