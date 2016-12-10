package SiFi.nslam.com.TextCluster;

import java.util.ArrayList;
import java.util.List;

/**
 * Core Class of Clustering Algorithm
 * @author xiaoqiangteng
 */
public  class Program {
	
	private static int cluster_number = SiFi.nslam.com.Runner.SiFi.clusterNumber;
	
	/**
	 * Cluster algorithm
	 * @param Text sequences of a group
	 * @return	Text sequences of each cluster of a group
	 */
	public static ArrayList<ArrayList<ArrayList<String>>> clustering(ArrayList<ArrayList<String>> st) {
		ArrayList<ArrayList<ArrayList<String>>> result = new ArrayList<ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<String>> textSequenceCluster = new ArrayList<ArrayList<String>>();
		String [] docs = new String[st.size()];
		if (docs.length < 1){
            System.out.println("û���ĵ�����");
            System.exit(0);
        }

        //2����ʼ��TFIDF����������������ÿ���ĵ���TFIDFȨ��
        TFIDFMeasure tf = new TFIDFMeasure(docs, new Tokeniser());

        int K = cluster_number; //�۳�3������

        //3������k-means���������ݣ���һ���������飬��һά��ʾ�ĵ�������
        //�ڶ�ά��ʾ�����ĵ��ֳ��������д�
        double[][] data = new double[docs.length][];
        int docCount = docs.length; //�ĵ�����
        int dimension = tf.get_numTerms();//���дʵ���Ŀ
        for (int i = 0; i < docCount; i++){
            for (int j = 0; j < dimension; j++){
                data[i] = tf.GetTermVector2(i); //��ȡ��i���ĵ���TFIDFȨ������
            }
        }

        //4����ʼ��k-means�㷨����һ��������ʾ�������ݣ��ڶ���������ʾҪ�۳ɼ�����
        WawaKMeans kmeans = new WawaKMeans(data, K);
        //5����ʼ����
        kmeans.Start();

        //6����ȡ�����������
        WawaCluster[] clusters = kmeans.getClusters();
        for (int i = 0; i < clusters.length; i++) {
        	List<Integer> members = clusters[i].CurrentMembership;
            for (int j : members){
            	ArrayList<String> testSequence = new ArrayList<String>();
            	testSequence = arrayToArrayList(docs[j]);
            	textSequenceCluster.add(testSequence);
            }
            result.add(textSequenceCluster);
        }
		return result;
	}
	
	public String[] arrayListToArray(ArrayList<ArrayList<String>> st) {
		String [] docs = new String[st.size()];
		for (int i = 0; i < st.size(); i++) {
			String str = null;
			for (int j = 0; j < st.get(i).size(); j++) {
				if (j == st.get(i).size()-1) {
					str += st.get(i).get(j);
				}else {
					str += st.get(i).get(j) + " ";
				}
			}
			docs[i] = str;
		}
		return docs;
	}
	
	public static ArrayList<String> arrayToArrayList(String str) {
		ArrayList<String> result = new ArrayList<String>();
		String[] string = str.split(" ");
		for (int i = 0; i < string.length; i++) {
			result.add(string[i]);
		}
		return result;
	}
	
	public ArrayList<ArrayList<String>> arrayToArray(String[] docs) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> textSequence = new ArrayList<String>();
		for (int i = 0; i < docs.length; i++) {
			String[] str = docs[i].split(" ");
			for (int j = 0; j < str.length; j++) {
				textSequence.add(str[j]);
			}
			result.add(textSequence);
		}
		return result;
	}
	
}
