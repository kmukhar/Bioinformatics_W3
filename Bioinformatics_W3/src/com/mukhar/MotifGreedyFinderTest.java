package com.mukhar;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.introcs.In;

public class MotifGreedyFinderTest {
    int k;

    int t;

    ArrayList<String> dna;

    ArrayList<String> expected;

    private MotifFinder mf;

    @Before
    public void setUp() throws Exception {
        k = 0;
        t = 0;
        dna = new ArrayList<>();
        expected = new ArrayList<>();
        mf = new MotifFinder();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGreedyMotifSearch01() {
        readTestData("src/com/mukhar/sample_greedy_data.txt");
        assertEquals(3, k);
        assertEquals(5, t);
        assertEquals("CAATAATATTCG", dna.get(4));
        assertEquals("CAA", expected.get(4));

        ArrayList<String> actual = mf.greedyMotifSearch(dna, k, t, new SimpleProfiler());
        assertArrayEquals(expected.toArray(), actual.toArray());
        System.out.println("testGreedyMotifSearch01");
        System.out.println("Expected: " + expected);
        System.out.println("Actual  : " + actual);
    }

    @Test
    public void testGreedyMotifSearch02() {
        readTestData("src/com/mukhar/greedy_data.txt");
        assertEquals(12, k);
        assertEquals(25, t);
        assertEquals("CATCACGCAATGCGAACGACTGAAGAAGGCAAGGACAGTTACGCAACCTATCATG"
                + "CGTAGATCAAGGTAATCGGGACCGGTCTGGAATTTAGGAGTGTTGTTAT"
                + "CGCAACCTGCGATCATAACATCCTCTTATTGCCTATAAACCGACCCTGACCG", dna.get(24));
        assertEquals("TGCGTAGATCAA", expected.get(24));

        ArrayList<String> actual = mf.greedyMotifSearch(dna, k, t, new SimpleProfiler());
        assertArrayEquals(expected.toArray(), actual.toArray());
        System.out.println("testGreedyMotifSearch02");
        System.out.println("Expected: " + expected);
        System.out.println("Actual  : " + actual);
    }

    @Test
    public void testGreedyMotifSearch03() {
        readQuizData("src/com/mukhar/dataset_39_5.txt");
        ArrayList<String> actual = mf.greedyMotifSearch(dna, k, t, new SimpleProfiler());
        System.out.println("testGreedyMotifSearch03");
        System.out.println("Actual  : " + actual.toString().replace(",", ""));
    }

    @Test
    public void testGreedyMotifSearch04() {
        readTestData("src/com/mukhar/greedy_pseudo_sample.txt");
        assertEquals(3, k);
        assertEquals(5, t);
        assertEquals("CAATAATATTCG", dna.get(4));
        assertEquals("TTC", expected.get(4));

        ArrayList<String> actual = mf.greedyMotifSearch(dna, k, t, new LaplaceProfiler());
        assertArrayEquals(expected.toArray(), actual.toArray());
        System.out.println("testGreedyMotifSearch04");
        System.out.println("Expected: " + expected);
        System.out.println("Actual  : " + actual);
    }

    @Test
    public void testGreedyMotifSearch05() {
        readTestData("src/com/mukhar/greedy_pseudo.txt");
        assertEquals(12, k);
        assertEquals(25, t);
        assertEquals("CATTTCTATAAAGCTACAATAATAATCCGCGCTGTCGGCAGACGTGGTACCGACCC"
                + "TACTCCTACCGTTTGAGAGATGGAGGGTCTTCCCTGAACTAACGGCATGCATGA"
                + "GAGGGGTACGACCCTGGTACTTCTGAAACCAGCATCCGCGGCGACG", dna.get(24));
        assertEquals("CTTCCCTGAACT", expected.get(24));

        ArrayList<String> actual = mf.greedyMotifSearch(dna, k, t, new LaplaceProfiler());
        assertArrayEquals(expected.toArray(), actual.toArray());
        System.out.println("testGreedyMotifSearch05");
        System.out.println("Expected: " + expected);
        System.out.println("Actual  : " + actual);
    }

    @Test
    public void testGreedyMotifSearch06() {
        readQuizData("src/com/mukhar/dataset_40_9.txt");
        ArrayList<String> actual = mf.greedyMotifSearch(dna, k, t, new SimpleProfiler());
        System.out.println("testGreedyMotifSearch06");
        System.out.println("Actual  : " + actual.toString().replace(",", ""));
    }

    public void readTestData(String name) {
        In in = new In(name);
        in.readLine(); // ignore Input
        k = in.readInt();
        t = in.readInt();
        in.readLine();
        for (int i = 0; i < t; i++)
            dna.add(in.readLine());
        in.readLine(); // ignore Output
        for (int i = 0; i < t; i++)
            expected.add(in.readLine());
    }

    public void readQuizData(String name) {
        In in = new In(name);
        k = in.readInt();
        t = in.readInt();
        in.readLine();
        for (int i = 0; i < t; i++)
            dna.add(in.readLine());
    }
}
