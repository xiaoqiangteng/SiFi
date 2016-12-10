package SiFi.nslam.com.Accurate_Text_Extraction;

import java.util.ArrayList;

/**
 * Core Class of text grouping
 * @author xiaoqiangteng
 */
public class Text_Sequence_Grouping {
	public float S_H = SiFi.nslam.com.Runner.SiFi.Similarity_Threshold;
	
	public ArrayList<ArrayList<ArrayList<String>>> textSequenceGrouping(ArrayList<ArrayList<String>> ts) {
		ArrayList<ArrayList<ArrayList<String>>> result = new ArrayList<ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<String>> group_result = new ArrayList<ArrayList<String>>();
		ArrayList<String> group_start = new ArrayList<String>();
		group_start = ts.get(1);
		ts.remove(1);
		group_result.add(ts.get(1));
		for (int i = 0; i < ts.size(); i++) {
			float similarity = 0;
			similarity = JaccardSimilarity(group_start, ts.get(i));
			if (similarity >= S_H) {
				group_result.add(ts.get(i));
			}
		}
		for (int i = 0; i < group_result.size(); i++) {
			ts.remove(group_result.get(i));
		}
		result.add(group_result);
		if (ts.size() == 0) {
			return result;
		}
		return textSequenceGrouping(ts);
	}
	
	/**
	 * Jaccard similarity computing
	 * @param Two text sequences
	 * @return	Jaccard similarity		
	 */
	public float JaccardSimilarity(ArrayList<String> st1, ArrayList<String> st2) {
		float result = -1;
		float intersection_number = 0;
		float union_number = 0;
		for (int i = 0; i < st2.size(); i++) {
			if (st1.contains(st2.get(i))) {
				intersection_number ++;
			}
		}
		union_number = st1.size() + st2.size() - intersection_number;
		result = intersection_number/union_number;
		return result;
	}
	
}
