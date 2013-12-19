package com.mukhar;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.introcs.In;

public class ProfilerTest {

	private Profiler p;

	@Before
	public void setUp() throws Exception {
		p = new SimpleProfiler();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindMostProbable01() {
		In in = new In("src/com/mukhar/sample_profile_most_data.txt");
		String[] data = in.readAllStrings();
		String text = data[1];
		int k = Integer.valueOf(data[2]);
		double[][] profile = new double[k][4];
		int start = 7;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < 4; j++) {
				profile[i][j] = Double.valueOf(data[start++]);

			}
		}
		ArrayList<String> actual = p.findMostProbable(text, k, profile);
		System.out.println(actual);
		System.out.println(data[8 + k * 4]);
		assertTrue(actual.contains(data[8 + k * 4]));
	}

	@Test
	public void testFindMostProbable02() {
		In in = new In("src/com/mukhar/profile_most_data.txt");
		String[] data = in.readAllStrings();
		String text = data[1];
		int k = Integer.valueOf(data[2]);
		double[][] profile = new double[k][4];
		int start = 7;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < 4; j++) {
				profile[i][j] = Double.valueOf(data[start++]);

			}
		}
		ArrayList<String> actual = p.findMostProbable(text, k, profile);
		System.out.println(actual);
		System.out.println(data[8 + k * 4]);
		assertTrue(actual.contains(data[8 + k * 4]));
	}

	@Test
	public void testFindMostProbable03() {
		In in = new In("src/com/mukhar/dataset_39_3.txt");
		String[] data = in.readAllStrings();
		String text = data[0];
		int k = Integer.valueOf(data[1]);
		double[][] profile = new double[k][4];
		int start = 6;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < 4; j++) {
				profile[i][j] = Double.valueOf(data[start++]);

			}
		}
		ArrayList<String> actual = p.findMostProbable(text, k, profile);
		System.out.println(actual);
	}
}
