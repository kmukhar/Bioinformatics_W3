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
			try (BufferedReader br = new BufferedReader(new FileReader(name),
					1048576)) {
				while (true) {
					kmers = new String[1024 * 256];
					int idx = 0;
					String s = "";
					while ((s = br.readLine()) != null && idx < kmers.length)
						kmers[idx++] = s;
					bestDistance = findMedianString(Dna, kmers, result,
							bestDistance);
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

	private int findMedianString(String[] Dna, String[] kmers,
			ArrayList<String> result, int bestDistance) {

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
