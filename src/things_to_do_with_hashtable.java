import java.util.HashMap;

public class things_to_do_with_hashtable {

	//string.length()
	//queue.size()
	//Hashtable is basically a datastructure to retain balues of key-value pair
	//		it didn't allow null for both key and value
	//		it is synchronized, only one thread can access in one time.
	
	//HashMap
	//		Accept key,value pair.
	//		allows null for both key and value
	//		its unsynchronized, so it has better performance
	
	//HashSet
	//		HashSet does not allow duplicate values.
	//		it uses add rather than put
	//		you can use contain method to check whetrher the ovject is 
	//		already available in hashset. hashset can be used where you want to maintain
	//		a unique list.
	
	
	
	//Isomorphic Strings
	//Two strings are isomorphic if the characters in s can be replaced to get t.
	 public boolean isIsomorphic(String s, String t) {
	        HashMap<Character,Character> map = new HashMap<Character,Character>();
	        if(s.length() != t.length())
	            return false;
	        char[] slist = s.toCharArray();
	        char[] tlist = t.toCharArray();
	        for(int i = 0; i <= s.length()-1; i++)
	        {
	        	//if the key and the value r both not in the map, add them to
	            if(!map.containsKey(slist[i]) && !map.containsValue(tlist[i]))                                                                 //the mapp
	                map.put(slist[i],tlist[i]);
	            //the thing to care about to check if the value is already in the map
	            else if(!map.containsKey(slist[i]) && map.containsValue(tlist[i])) 
	                return false;                                                  
	            else
	                {
	                    
	                    if(map.get(slist[i]) == tlist[i])
	                        continue;
	                    else
	                        return false;
	                }
	        }
	        return true;
	    }
}
