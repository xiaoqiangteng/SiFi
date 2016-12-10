package SiFi.nslam.com.Runner;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import SiFi.nslam.com.Accurate_Text_Extraction.LoadTextSequence;
import SiFi.nslam.com.Accurate_Text_Extraction.Text_Sequence_Grouping;
import SiFi.nslam.com.TextCluster.Program;

public class SiFi {
	
	public static Path textSequencePath = null;
	public static float Similarity_Threshold = (float) 0.5;
	public static int clusterNumber = 5;
	
	public static void main(String[] args) throws FileNotFoundException {
		textSequencePath = Paths.get("E:/programmings/programming softwares/eclipse-jee-mars-R-win32/workspace/SiFi/data", "text_sequence.data");
		
		//Load text sequences
		LoadTextSequence loadtextsequence = new LoadTextSequence();
		ArrayList<ArrayList<String>> textSequences = new ArrayList<ArrayList<String>>();
		textSequences = loadtextsequence.loadTextSequences(textSequencePath);
		
		//Grouping of text sequences
		Text_Sequence_Grouping textSequenceGrouping = new Text_Sequence_Grouping();
		ArrayList<ArrayList<ArrayList<String>>> textSequenceGroups = new ArrayList<ArrayList<ArrayList<String>>>();
		textSequenceGroups = textSequenceGrouping.textSequenceGrouping(textSequences);
		
		//Run the estimation algorithm to solve MLE problem and estimate the accurate texts for each group
		ArrayList<String> accurateTexts = new ArrayList<String>(); //Accurate texts of a group
		ArrayList<ArrayList<String>> accurateTextsAllGroup = 
				new ArrayList<ArrayList<String>>(); //Accurate texts of all groups
		ArrayList<ArrayList<ArrayList<String>>> clusteredTextSequence = 
				new ArrayList<ArrayList<ArrayList<String>>>(); //After clustering, the text sequences of a group
		ArrayList<ArrayList<ArrayList<ArrayList<String>>>> clusteredGroup = 
				new ArrayList<ArrayList<ArrayList<ArrayList<String>>>>(); ////After clustering, the text sequences of all groups
		
		//Cluster for each group
		for (int i = 0; i < textSequenceGroups.size(); i++) {
			clusteredTextSequence = Program.clustering(textSequenceGroups.get(i));
			clusteredGroup.add(clusteredTextSequence);
		}
		
		
		
	}

}
