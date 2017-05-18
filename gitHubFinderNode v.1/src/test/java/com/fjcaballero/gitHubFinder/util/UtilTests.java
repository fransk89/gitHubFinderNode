package com.fjcaballero.gitHubFinder.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class UtilTests {
	
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
   	
	@Test
    public void testSortHashMap() {		
		
		HashMap <String, Integer> map = null;
		HashMap <String, Integer> mapOutput = null;
		ArrayList <String> listSorted = null;
		
		assertNull(Util.sortHashMap(null));
		assertNull(Util.sortHashMap(new HashMap()));
		
		map = new HashMap <String, Integer>();
		assertNull(Util.sortHashMap(map));
		
		map.put("Java", 2);
		map.put("JavaScript", 3);
		mapOutput = Util.sortHashMap(map);
		
		if (!mapOutput.isEmpty()) {
			listSorted = new ArrayList ();
		    for (Map.Entry<String, Integer> entry : mapOutput.entrySet())
		    {
		    	listSorted.add((String) entry.getKey());
		    }
		}		
				
		assertEquals("JavaScript", (String) listSorted.get(0));		
		assertEquals("Java", (String) listSorted.get(1));	
	}
	
	@Test
    public void testIsEmptyList() {		
		
		List list = null;
		assertEquals(true, Util.isEmpty(list));
		
		list = new ArrayList<Object>();
		assertEquals(true, Util.isEmpty(list));
		
		list.add(new String("someValue"));
		assertEquals(false, Util.isEmpty(list));
	}
	
	@Test
    public void testIsEmptyMap() {		
		
		Map <Integer, String> map = null;
		assertEquals(true, Util.isEmpty(map));
				
		map = new HashMap<Integer, String>();
		assertEquals(true, Util.isEmpty(map));
		
		map.put(2, new String("someValue"));
		assertEquals(false, Util.isEmpty(map));
	}
	
	@Test
    public void testIsEmptyString() {		
		
		String  string = null;
		assertEquals(true, Util.isEmpty(string));
				
		string = new String();
		assertEquals(true, Util.isEmpty(string));
		
		string = "stringTest";
		assertEquals(false, Util.isEmpty(string));
	}
}
