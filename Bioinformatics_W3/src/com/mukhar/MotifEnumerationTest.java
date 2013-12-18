package com.mukhar;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.princeton.cs.introcs.In;

public class MotifEnumerationTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	private MotifEnumeration mf;

	@Before
	public void setUp() throws Exception {
		mf = new MotifEnumeration();
	}

	@Test
	public void testFindMotifs01() {
		In in = new In("src/com/mukhar/sample_motif_enumeration_data.txt");
		in.readLine(); // ignore "INPUT"
		String[] intParams = in.readLine().split(" "); // k and d
		int k = Integer.parseInt(intParams[0]);
		int d = Integer.parseInt(intParams[1]);

		ArrayList<String> dna = new ArrayList<>();
		while (in.hasNextLine()) {
			String s = in.readLine();
			if (s.equalsIgnoreCase("Output"))
				break;
			dna.add(s);
		}

		String[] e = in.readLine().split(" ");
		ArrayList<String> expected = new ArrayList<>();
		for (String s : e)
			expected.add(s);
		in.close();

		Collections.sort(expected);
		ArrayList<String> actual = mf.findMotifs(dna, k, d);
		Collections.sort(actual);
		System.out.println(expected);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testFindMotifs02() {
		In in = new In("src/com/mukhar/motif_enumeration_data.txt");
		in.readLine(); // ignore "INPUT"
		String[] intParams = in.readLine().split(" "); // k and d
		int k = Integer.parseInt(intParams[0]);
		int d = Integer.parseInt(intParams[1]);

		ArrayList<String> dna = new ArrayList<>();
		while (in.hasNextLine()) {
			String s = in.readLine();
			if (s.equalsIgnoreCase("Output"))
				break;
			dna.add(s);
		}

		String[] e = in.readLine().split(" ");
		ArrayList<String> expected = new ArrayList<>();
		for (String s : e)
			expected.add(s);
		in.close();

		Collections.sort(expected);
		ArrayList<String> actual = mf.findMotifs(dna, k, d);
		Collections.sort(actual);
		System.out.println(expected.toString().replace(",", ""));
		System.out.println(actual.toString().replace(",", ""));
		assertEquals(expected, actual);
	}

	@Test
	public void testFindMotifs03() {
		In in = new In("src/com/mukhar/dataset_36_7.txt");
		String[] intParams = in.readLine().split(" "); // k and d
		int k = Integer.parseInt(intParams[0]);
		int d = Integer.parseInt(intParams[1]);

		ArrayList<String> dna = new ArrayList<>();
		while (in.hasNextLine()) {
			String s = in.readLine();
			dna.add(s);
		}

		in.close();

		ArrayList<String> actual = mf.findMotifs(dna, k, d);
		Collections.sort(actual);
		System.out.println(actual.toString().replace(",", ""));
	}

	@Test
	public void testFindMotifs04() {
		In in = new In("src/com/mukhar/15_4_implanted_motif_2.txt");

		ArrayList<String> dna = new ArrayList<>();
		while (in.hasNextLine()) {
			String s = in.readLine();
			dna.add(s);
		}

		in.close();

		ArrayList<String> actual = mf.findMotifs(dna, 15, 4);
		Collections.sort(actual);
		System.out.println(actual.toString().replace(",", ""));
	}
}
