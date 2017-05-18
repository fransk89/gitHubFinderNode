package com.fjcaballero.gitHubFinder.util;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



public class Util {


	/**
	* Sort a Hashmap descendantly.
	* 
	* @param List list
	* 
	* @return boolean
	* 
	*/
	public static <K, V extends Comparable<? super V>> HashMap<K, V> sortHashMap( Map<K, V> map )
	{
	   
		HashMap<K, V> result = null;
		if (map != null
				&& !map.isEmpty()) {
			
			List<Map.Entry<K, V>> list =	new LinkedList<Entry<K, V>>( map.entrySet() );
		    Collections.sort( list, new Comparator<Map.Entry<K, V>>()
		    {
		        
		        public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
		        {
		            return ( o1.getValue() ).compareTo( o2.getValue() );
		        }
		    } );
		
		    Collections.reverse(list);
		    
		    result = new LinkedHashMap<K, V>();
		    for (Map.Entry<K, V> entry : list)
		    {
		        result.put( entry.getKey(), entry.getValue() );
		    }
		}
		
	    return result;
	}
		   
	   /**
		* Check if the list is not empty.
		* 
		* @param List list
		* 
		* @return boolean
		* 
		*/
	   public static boolean isEmpty(List list) {
		   return (null == list || list.isEmpty());
	   }
	   
	   /**
		* Check if the mapa is not empty.
		* 
		* @param List list
		* 
		* @return boolean
		* 
		*/
	   public static boolean isEmpty(Map mapa) {
		   return (null == mapa || mapa.isEmpty());
	   }
	   
	   /**
		* Check if the string is not empty.
		* 
		* @param List list
		* 
		* @return boolean
		* 
		*/
	   public static boolean isEmpty(String string) {
		   return (null == string || string.length() == 0);
	   } 
}
