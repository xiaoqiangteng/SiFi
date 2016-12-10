package SiFi.nslam.com.TextCluster;

import java.util.Dictionary;
import java.util.Hashtable;


    /// <summary>
    /// ”√”⁄“∆≥˝Õ£÷π¥ 
    /// </summary>
	public class StopWordsHandler
	{		
		public static String[] stopWordsList=new String[] {"the", "a", "an"};

		private static Hashtable _stopwords=null;

		public static Object AddElement(Dictionary collection,Object key, Object newValue)
		{
			Object element = collection.get(key);
			collection.put(key, newValue);
			return element;
		}

		public static boolean IsStopword(String str)
		{
			
			//int index=Array.BinarySearch(stopWordsList, str)
			return _stopwords.containsKey(str.toLowerCase());
		}
	

		static  
		{
			if (_stopwords == null)
			{
				_stopwords = new Hashtable();
				double dummy = 0;
				for(String word:stopWordsList){
					_stopwords.put(word, dummy);
				}
				/*foreach (String word in stopWordsList)
				{
					AddElement(_stopwords, word, dummy);
				}*/
			}
		}
	}

