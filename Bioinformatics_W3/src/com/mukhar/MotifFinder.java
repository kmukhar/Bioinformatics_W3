package com.mukhar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.princeton.cs.introcs.In;

public class MotifFinder {
    public ArrayList<String> findMedianString(String[] Dna, int k) {
        String size = (k < 10 ? "0" : "") + k;
        String name = "src/com/mukhar/kmersOfSize" + size + ".txt";
        String[] kmers = new String[] {};
        ArrayList<String> result = new ArrayList<>();
        int bestDistance = Integer.MAX_VALUE;
        if (k <= 11) {
            In in = new In(name);
            kmers = in.readAllStrings();

            bestDistance = findMedianString(Dna, kmers, result, bestDistance);
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(name), 1048576)) {
                while (true) {
                    kmers = new String[1024 * 256];
                    int idx = 0;
                    String s = "";
                    while ((s = br.readLine()) != null && idx < kmers.length)
                        kmers[idx++] = s;
                    bestDistance = findMedianString(Dna, kmers, result, bestDistance);
                    if (br.readLine() == null)
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // output BestPattern
        return result;
    }

    private int findMedianString(String[] Dna, String[] kmers, ArrayList<String> result,
            int bestDistance) {

        // for each k-mer Pattern from AAA…AA to TTT…TT
        for (String pattern : kmers) {
            int distance = distance(pattern, Dna, bestDistance);
            if (distance == bestDistance) {
                result.add(pattern);
            }
            if (distance < bestDistance) {
                bestDistance = distance;
                result.clear();
                result.add(pattern);
            }
        }
        return bestDistance;
    }

    public ArrayList<String> greedyMotifSearch(ArrayList<String> dnaList, int k, int t,
            Profiler profiler) {
        // form a set of k-mers BestMotifs by selecting 1st k-mers in each
        // string from Dna
        ArrayList<String> bestMotif = new ArrayList<>();
        for (String dna : dnaList)
            bestMotif.add(dna.substring(0, k));
        int bestScore = score(bestMotif, profiler);

        // for each k-mer in the 1st string from Dna
        String dna = dnaList.get(0);
        int start = 0;
        ArrayList<String> motifs = new ArrayList<>();

        while (start + k <= dna.length()) {
            // Motif <- kmer
            motifs.clear();
            motifs.add(dna.substring(start, start + k));

            for (int i = 1; i < t; i++) {
                // form Profile from motifs Motif1, ..., Motifi - 1
                double[][] newProfile = profiler.getProfile(motifs);
                String dna_i = dnaList.get(i);

                // Motifi <- Profile-most probable k-mer in the i-th string in Dna
                ArrayList<String> kmers = profiler.findMostProbable(dna_i, k, newProfile);
                // Motifs <- (Motif1, ..., Motif)
                motifs.add(kmers.get(0));

            }
            if (score(motifs, profiler) < bestScore) {
                bestMotif.clear();
                bestMotif.addAll(motifs);
                bestScore = score(motifs, profiler);
            }
            ++start;
        }

        // output BestMotifs
        return bestMotif;
    }

    int score(ArrayList<String> motif, Profiler profiler) {
        char[][] c = new char[motif.size()][];
        int idx = 0;
        for (String s : motif)
            c[idx++] = s.toCharArray();
        int score = 0;

        for (int j = 0; j < c[0].length; j++) {
            int colScore = c.length;
            int[] counts = new int[] { 0, 0, 0, 0 };
            profiler.getProfileCounts(c, j, counts);
            score += colScore - max(counts);
        }
        return score;
    }

    /**
     * return the max value in an array
     * 
     * @param counts the array that holds values
     * @return the max value in the array
     */
    private int max(int[] counts) {
        int max = 0;
        for (int i : counts)
            if (i > max)
                max = i;
        return max;
    }

    private int distance(String pattern, String[] dna, int bestDistance) {
        int result = 0;
        for (String text : dna) {
            result += PatternFinder.countMinMismatches(pattern, text);
            if (result >= bestDistance)
                break;
        }
        return result;
    }
}
