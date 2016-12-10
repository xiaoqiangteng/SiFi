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
            System.out.println("没有文档输入");
            System.exit(0);
        }

        //2、初始化TFIDF测量器，用来生产每个文档的TFIDF权重
        TFIDFMeasure tf = new TFIDFMeasure(docs, new Tokeniser());

        int K = cluster_number; //聚成3个聚类

        //3、生成k-means的输入数据，是一个联合数组，第一维表示文档个数，
        //第二维表示所有文档分出来的所有词
        double[][] data = new double[docs.length][];
        int docCount = docs.length; //文档个数
        int dimension = tf.get_numTerms();//所有词的数目
        for (int i = 0; i < docCount; i++){
            for (int j = 0; j < dimension; j++){
                data[i] = tf.GetTermVector2(i); //获取第i个文档的TFIDF权重向量
            }
        }

        //4、初始化k-means算法，第一个参数表示输入数据，第二个参数表示要聚成几个类
        WawaKMeans kmeans = new WawaKMeans(data, K);
        //5、开始迭代
        kmeans.Start();

        //6、获取聚类结果并输出
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
