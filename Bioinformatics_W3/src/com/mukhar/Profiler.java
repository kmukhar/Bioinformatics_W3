package com.mukhar;

import java.util.ArrayList;

public class Profiler {
	public ArrayList<String> findMostProbable(String text, int k,
			double[][] profile) {
		ArrayList<String> result = new ArrayList<>();
		double max = Double.MIN_VALUE;
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
}
