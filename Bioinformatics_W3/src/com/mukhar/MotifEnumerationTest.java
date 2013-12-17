package com.mukhar;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mukhar.commons.*;

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
	public void testFindMotifs() {
		ArrayList<String> dna = new ArrayList<>();
		int k = 0;
		int d = 0;
		assertEquals(0, mf.findMotifs(dna, k, d).size());
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
}
