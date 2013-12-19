package com.mukhar;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.princeton.cs.introcs.In;

public class MotifFinderTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    private MotifFinder mf;

    private static DateFormat df = DateFormat.getDateTimeInstance();

    @Before
    public void setUp() throws Exception {
        mf = new MotifFinder();
        System.out.println("Started: " + df.format(new Date(System.currentTimeMillis())));
    }

    @After
    public void tearDown() {
        System.out.println("Ended  : " + df.format(new Date(System.currentTimeMillis())));
    }

    @Test
    public void testFindMedianString01() {
        In in = new In("src/com/mukhar/sample_medium_string_data.txt");
        String[] data = in.readAllStrings();

        int k = Integer.valueOf(data[1]);
        String[] Dna = Arrays.copyOfRange(data, 2, data.length - 2);

        String expected = data[data.length - 1];

        ArrayList<String> actual = mf.findMedianString(Dna, k);
        assertTrue(actual.contains(expected));
        System.out.println(actual);
    }

    @Test
    public void testFindMedianString02() {
        In in = new In("src/com/mukhar/medium_string_data.txt");
        String[] data = in.readAllStrings();

        int k = Integer.valueOf(data[1]);
        String[] Dna = Arrays.copyOfRange(data, 2, data.length - 2);

        String expected = data[data.length - 1];

        ArrayList<String> actual = mf.findMedianString(Dna, k);
        assertTrue(actual.contains(expected));
        System.out.println(actual);
    }

    @Test
    public void testFindMedianString03() {
        In in = new In("src/com/mukhar/dataset_38_7.txt");
        String[] data = in.readAllStrings();

        int k = Integer.valueOf(data[0]);
        String[] Dna = Arrays.copyOfRange(data, 1, data.length);

        ArrayList<String> actual = mf.findMedianString(Dna, k);
        System.out.println(actual);
    }
}
