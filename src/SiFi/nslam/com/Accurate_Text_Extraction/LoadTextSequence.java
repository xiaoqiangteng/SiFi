package SiFi.nslam.com.Accurate_Text_Extraction;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadTextSequence {
	
	public ArrayList<ArrayList<String>> loadTextSequences(Path inpath) throws FileNotFoundException {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> textSequence = new ArrayList<String>();
		Scanner scanner = new Scanner(inpath.toFile());
		while (scanner.hasNextLine()){
			String line = scanner.nextLine().trim();
			if (line.equals("")){
				continue;
			} else {
				String[] lineSplit = line.split(" ");
				for (int i = 0; i < lineSplit.length; i++) {
					textSequence.add(String.valueOf(lineSplit[i]));
				}
				result.add(textSequence);
			}
		}
		scanner.close();
		return result;
	}
	
}
