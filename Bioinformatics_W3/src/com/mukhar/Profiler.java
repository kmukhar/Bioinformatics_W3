package com.mukhar;

import java.util.ArrayList;

public abstract class Profiler {

    public abstract double[][] getProfile(ArrayList<String> motif);

    public ArrayList<String> findMostProbable(String text, int k, double[][] profile) {
        ArrayList<String> result = new ArrayList<>();
        double max = -1.0;
        int start = 0;
        while (start + k <= text.length()) {
            String s = text.substring(start, start + k);
            double score = 1.0;
            for (int i = 0; i < s.length(); i++) {
                String sub = s.substring(i, i + 1);
                switch (sub) {
                case "A":
                    score *= profile[i][0];
                    break;
                case "C":
                    score *= profile[i][1];
                    break;
                case "G":
                    score *= profile[i][2];
                    break;
                case "T":
                    score *= profile[i][3];
                    break;
                }
            }
            if (score > max) {
                max = score;
                result.clear();
                result.add(s);
            } else if (score == max) {
                result.add(s);
            }
            start++;
        }

        return result;
    }

    /**
     * A count array for each nucleobase in column j of the char array
     * 
     * @param c the char array representing the nucleobases in a set of kmers
     * @param j the column to compute counts for
     * @param counts an array of counts of each nucleobase, is order ACGT
     */
    public void getProfileCounts(char[][] c, int j, int[] counts) {
        for (int i = 0; i < c.length; i++) {
            switch (c[i][j]) {
            case 'A':
                counts[0]++;
                break;
            case 'C':
                counts[1]++;
                break;
            case 'G':
                counts[2]++;
                break;
            case 'T':
                counts[3]++;
                break;
            }
        }
    }
}