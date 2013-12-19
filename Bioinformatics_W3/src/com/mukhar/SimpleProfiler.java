package com.mukhar;

import java.util.ArrayList;

public class SimpleProfiler extends Profiler {
    /**
     * Compute the profile for a set of kmers. First column in the profile is the probabilities for
     * the nucleobase 'A', and each element (row) in the column is the probability that 'A' appears
     * in that position. Row 1 is 'C', row 2 is 'G', and row 3 is 'T'.
     * 
     * @param motif a set of kmers
     * @return A probability profile for the set of kmers
     */
    public double[][] getProfile(ArrayList<String> motif) {
        char[][] c = new char[motif.size()][];
        int idx = 0;
        for (String s : motif)
            c[idx++] = s.toCharArray();

        double[][] profile = new double[c[0].length][4];

        for (int i = 0; i < c[0].length; i++) {
            int[] counts = new int[] { 0, 0, 0, 0 };
            getProfileCounts(c, i, counts);
            for (int j = 0; j < 4; j++)
                profile[i][j] = ((double) counts[j]) / ((double) c.length);
        }

        return profile;
    }
}
