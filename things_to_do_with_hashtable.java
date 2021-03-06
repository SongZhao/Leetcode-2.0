import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class things_to_do_with_hashtable {

	//string.length()
	//queue.size()
	//array length
	//Hashtable is basically a datastructure to retain balues of key-value pair
	//		it didn't allow null for both key and value
	//		it is synchronized, only one thread can access in one time.(slower)
	
	//HashMap
	//		Accept key,value pair. 
	//		allows null for both key and value
	//		Hashmap最多只允许一条记录的键为null，不允许多条记录的值为null
	//		its unsynchronized, so it has better performance，but if mutiple instance
	//		write on the hashmap at the same time, the data will be incorrect.
	//		we can use Collections.synchronizedMap(HashMap map)
	
	//HashSet
	//		HashSet does not allow duplicate values.
	//		it uses add rather than put
	//		you can use contain method to check whetrher the ovject is 
	//		already available in hashset. hashset can be used where you want to maintain
	//		a unique list.
	
	//LinkedHashMap
	//		保存了记录的插入顺序，在用iterator遍历linkedhashmap的时候，先的到的记录是先插入的
	//		在遍历的时候会比hashmap慢。有hashmap的全部特性
	
	//TreeMap
	//		能把它保存的记录根据key排序，默认是按升序排序，也可以指定排序的comparetor。当用iterator遍历
	//		treeMap时，得到的记录是排过序的。 treemap的键和值都不能为空
	/*
	遍历Map有两种方法：  
	（1）map的keySet()方法获得键的集合，再调用键集合的iterator方法获得键的迭代器，以此迭代地取出Map中的键，用get方法获得键对应的值，便完成了Map的遍历。代码如下所示：  
	//使用迭代器遍历Map的键，根据键取值  
	        Iterator it = map.keySet().iterator();  
	        while (it.hasNext()){  
	           key = it.next();  
	           value = map.get(key);  
	           System.out.println("key: " + key + "; value: " + value );  
	        }  
	（2）使用Map的entrySet方法获得Map中记录的集合，每条对象都是一个Map.Entry对象，使用其getKey方法获得记录的键，使用其getValue方法获得记录的值。代码如下所示：  
	        //或者使用迭代器遍历Map的记录Map.Entry  
	        Map.Entry entry = null;  
	        it = map.entrySet().iterator();  
	        while (it.hasNext()){  
	           //一个Map.Entry代表一条记录  
	           entry = (Map.Entry)it.next();  
	           //通过entry可以获得记录的键和值  
	           //System.out.println("key: " + entry.getKey() + "; value: " + entry.getValue());   
	*/
	
	
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
	            if(!map.containsKey(slist[i]) && !map.containsValue(tlist[i]))                                                                 
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
	 
	 //world pattern II   #rec
	 //use recursive and hashmap
	 //pattern = "abab", str = "redblueredblue" should return true.
	 //pattern = "aaaa", str = "asdasdasdasd" should return true.
	 //pattern = "aabb", str = "xyzabcxzyabc" should return false. 

	 public boolean wordPatternMatch(String pattern, String str) 
	 {
	    Map<Character, String> map = new HashMap<>();
	    Set<String> set = new HashSet<>();
	    return isMatch(str, 0, pattern, 0, map, set);
	  }
 	  //i is start index for str, j is start index for pattern
	  boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
	    // base case
	    if (i == str.length() && j == pat.length()) return true;
	    if (i == str.length() || j == pat.length()) return false;
	    // get current pattern character
	    char c = pat.charAt(j);
	    // if the pattern character exists
	    if (map.containsKey(c)) {
	      String s = map.get(c);
	      // then check if we can use it to match str[i...i+s.length()]
	      if (!str.startsWith(s, i)) {
	        return false;
	      }
	      // if it can match, great, continue to match the rest
	      return isMatch(str, i + s.length(), pat, j + 1, map, set);
	    }
	    // pattern character does not exist in the map
	    for (int k = i; k < str.length(); k++) 
	    {
	      String p = str.substring(i, k + 1);
	      if (set.contains(p)) {
	        continue;
	      }
	      // create or update it
	      map.put(c, p);
	      set.add(p);
	      // continue to match the rest
	      if (isMatch(str, k + 1, pat, j + 1, map, set)) {
	        return true;
	      }
	      // backtracking
	      map.remove(c);
	      set.remove(p);
	    } 
	    // we've tried our best but still no luck
	    return false;
	  }
	
	  
	  //Longest Substring with At Most K Distinct Characters

	  public int lengthOfLongestSubstringKDistinct(String s, int k) {  
	      int start = 0;  
	      int maxLen = 0;  
	    
	      // Key: letter; value: the number of occurrences.  
	      Map<Character, Integer> map = new HashMap<Character, Integer>();  
	      int i;  
	      for (i = 0; i < s.length(); ++i) {  
	          char c = s.charAt(i);  
	          if (map.containsKey(c)) {  
	              map.put(c, map.get(c) + 1);  
	          } else {  
	              map.put(c, 1);  
	              //if map,size > k, it means we have more than k distinct chars
	              //then start from the start index we have to delete corresponding char in the map
	              //to make the map size reduce to k again
	              while (map.size() > k) 
	              {  
	                  char startChar = s.charAt(start++); //move the start index one slot forward. 
	                  int count = map.get(startChar);  
	                  if (count > 1) 
	                  {  
	                      map.put(startChar, count - 1);  
	                  } else 
	                  {  
	                      map.remove(startChar);  
	                  }  
	              }  
	          }  
	          //everytime we have a mapsize that is <= k, we take the max length we can get at that time.
	          maxLen = Math.max(maxLen, i - start + 1);  
	      }  
	    
	      return maxLen;  
	  }  
	  
	  /*Group Anagrams
	  Given an array of strings, group anagrams together.
		For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
	  Return:[
	    ["ate", "eat","tea"],
	    ["nat","tan"],
	    ["bat"]
	  ]*/
	  public List<List<String>> groupAnagrams(String[] strs) 
	  {
		  if (strs == null || strs.length == 0) 
			  return new ArrayList<List<String>>();
	      Map<String, List<String>> map = new HashMap<String, List<String>>();
	      Arrays.sort(strs); //sort it to lexicographic order.
	      for (String s : strs) 
	      {
	            char[] ca = s.toCharArray();
	            Arrays.sort(ca);
	            String keyStr = String.valueOf(ca); //make all anagrams to the same string, use this as key
	            if (!map.containsKey(keyStr)) 
	            	map.put(keyStr, new ArrayList<String>());
	            map.get(keyStr).add(s);
	       }
	       return new ArrayList<List<String>>(map.values());  //map.values() return the collection of map's value.
	   }
	  
	  
	 
	  
	  //Contains Duplicate II
	  //Given an array of integers and an integer k, find out whether there are two distinct indices 
	  //	i and j in the array such that nums[i] = nums[j] and the difference between i and j is at most k.
	  public boolean containsNearbyDuplicate(int[] nums, int k) 
	    {
	       Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	    for (int i = 0; i < nums.length; i++) 
	    {
	        if (map.containsKey(nums[i]) && (i - map.get(nums[i])) <= k) 
	        {
	            return true;
	        }

	        map.put(nums[i], i);
	    }

	    return false;

	    }
	  //Contains Duplicate III
	  //Given an array of integers, find out whether there are two distinct indices i and j in the array such that the 
	  //difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
	  
	  //treeset will maintain the order of element by the time being added, thus we can always maintain a treeSet with size K
	  //its like a sliding window and 
	  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t)
	  {
	    if (nums == null || nums.length == 0) return false;
	    TreeSet<Long> set = new TreeSet<>();
	    set.add((long) nums[0]);
	    for (int i = 1; i < nums.length; i++) {
	        if (i > k) set.remove((long) nums[i - k - 1]);
	        long left = (long) nums[i] - t;
	        long right = (long) nums[i] + t;
	        if (left <= right && !set.subSet(left, right + 1).isEmpty()) 
	        	return true;  //use subset(elment,elment) to see if any set value lie in that range.
	        set.add((long) nums[i]);
	    }
		    return false;
	  }

    //we can use array like hashtable sometimes.
    //Count Primes
	 //Count the number of prime numbers less than a non-negative number, n.
	 public int countPrimes(int n)
	 {
		int res = 0;
		boolean[] used = new boolean[n];
		for (int i = 2; i <= Math.sqrt(n); i++)
		{
		 if (!used[i - 1])
		 {
			int temp = i * i;
			while (temp < n)
			{
				used[temp - 1] = true;
				temp += i;
			}
		 }
		}
		for (int i = 2; i < n; i++)
		{
		if (!used[i - 1])
		{
			res++;
		}
		}
		return res;
	 }
	 
	 //Minimum Window Substring
	 public String minWindow(String s, String t) {
		    if(s == null || s.length() < t.length() || s.length() == 0){
		        return "";
		    }
		    HashMap<Character,Integer> map = new HashMap<Character,Integer>();
		    for(char c : t.toCharArray())
		    {
		        if(map.containsKey(c))
		        {
		            map.put(c,map.get(c)+1);
		        }else
		        {
		            map.put(c,1);
		        }
		    }
		    int left = 0;							//double pointer
		    int minLeft = 0;
		    int minLen = s.length()+1;
		    int count = 0;
		    for(int right = 0; right < s.length(); right++)
		    {
		        if(map.containsKey(s.charAt(right)))
		        {
		            map.put(s.charAt(right),map.get(s.charAt(right))-1);
		            if(map.get(s.charAt(right)) >= 0)
		            {
		                count ++;     //this count is the record if we have included all char from t in current substring
		            }
		            while(count == t.length())	//means current substring has t in it, now we can update the length.
		            {
		                if(right-left+1 < minLen)
		                {
		                    minLeft = left;
		                    minLen = right-left+1;
		                }
		                if(map.containsKey(s.charAt(left)))
		                {
		                    map.put(s.charAt(left),map.get(s.charAt(left))+1); //since we will increace left, which means we r removing char from the substring
		                    if(map.get(s.charAt(left)) > 0)						//so if the char is in t, we have to add t back into the map.
		                    {													//and if this key's value is larger than 0, means we doesnt't have enough char in the substring.
		                        count --;			
		                    }
		                }
		                left ++ ;
		            }
		        }
		    }
		    if(minLen>s.length())  
		    {  
		        return "";  
		    }  

		    return s.substring(minLeft,minLeft+minLen);
		}
		  
	 
	 //Repeated DNA Sequences
	 public List<String> findRepeatedDnaSequences(String s) {
		    Set seen = new HashSet(), repeated = new HashSet();
		    for (int i = 0; i + 9 < s.length(); i++) {
		        String ten = s.substring(i, i + 10);
		        if (!seen.add(ten))
		            repeated.add(ten);
		    }
		    return new ArrayList(repeated);
		}
	 //Valid Anagram
	 //we can convert the string into char[] and sort them then compare, but the array.sort function in java 
	 //	cost nlogn. while use this method its just n + t
	 // this method is the same method as in Bulls and Cows in things to do with math java.
	 public boolean isAnagram(String s, String t) {
         if (s.length() != t.length() ) return false;
    int[] alphabet = new int[26];
   
    for(int i = 0; i < s.length(); i++) {
        alphabet[s.charAt(i) - 'a']++;
    }
    for(int i = 0; i < t.length(); i++) {
        alphabet[t.charAt(i) - 'a']--;
        if(alphabet[t.charAt(i) -'a'] < 0) return false;
    }
    return true;

    }
	 
	 
	 //valid sudoku
	/* Collect the set of things we see, encoded as strings. For example:

		 '4' in row 7 is encoded as "(4)7".
		 '4' in column 7 is encoded as "7(4)".
		 '4' in the top-right block is encoded as "0(4)2".
		 Scream false if we ever fail to add something because it was already added (i.e., seen before).*/

	 public boolean isValidSudoku(char[][] board) {
	     Set seen = new HashSet();
	     for (int i=0; i<9; ++i) {
	         for (int j=0; j<9; ++j) {
	             if (board[i][j] != '.') {
	                 String b = "(" + board[i][j] + ")";
	                 if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i/3 + b + j/3))
	                     return false;
	             }
	         }
	     }
	     return true;
	 }
	
	 
	 //word ladder II
	 public List<List<String>> findLadders(String start, String end, Set<String> dict) 
	 {
	    // hash set for both ends
	    Set<String> set1 = new HashSet<String>();
	    Set<String> set2 = new HashSet<String>();

	    // initial words in both ends
	    set1.add(start);
	    set2.add(end);

	    // we use a map to help construct the final result
	    Map<String, List<String>> map = new HashMap<String, List<String>>();

	    // build the map
	    helper(dict, set1, set2, map, false);

	    List<List<String>> res = new ArrayList<List<String>>();
	    List<String> sol = new ArrayList<String>(Arrays.asList(start));

	    // recursively build the final result
	    generateList(start, end, map, sol, res);

	    return res;
	  }

	  boolean helper(Set<String> dict, Set<String> set1, Set<String> set2, Map<String, List<String>> map, boolean flip) {
	    if (set1.isEmpty()) {
	      return false;
	    }

	    if (set1.size() > set2.size()) {
	      return helper(dict, set2, set1, map, !flip);
	    }

	    // remove words on current both ends from the dict
	    dict.removeAll(set1);
	    dict.removeAll(set2);

	    // as we only need the shortest paths
	    // we use a boolean value help early termination
	    boolean done = false;

	    // set for the next level
	    Set<String> set = new HashSet<String>();

	    // for each string in end 1
	    for (String str : set1) {
	      for (int i = 0; i < str.length(); i++) {
	        char[] chars = str.toCharArray();

	        // change one character for every position
	        for (char ch = 'a'; ch <= 'z'; ch++) {
	          chars[i] = ch;

	          String word = new String(chars);

	          // make sure we construct the tree in the correct direction
	          String key = flip ? word : str;
	          String val = flip ? str : word;

	          List<String> list = map.containsKey(key) ? map.get(key) : new ArrayList<String>();

	          if (set2.contains(word)) {
	            done = true;

	            list.add(val);
	            map.put(key, list);
	          } 

	          if (!done && dict.contains(word)) {
	            set.add(word);

	            list.add(val);
	            map.put(key, list);
	          }
	        }
	      }
	    }

	    // early terminate if done is true
	    return done || helper(dict, set2, set, map, !flip);
	  }

	  void generateList(String start, String end, Map<String, List<String>> map, List<String> sol, List<List<String>> res) {
	    if (start.equals(end)) {
	      res.add(new ArrayList<String>(sol));
	      return;
	    }

	    // need this check in case the diff between start and end happens to be one
	    // e.g "a", "c", {"a", "b", "c"}
	    if (!map.containsKey(start)) {
	      return;
	    }

	    for (String word : map.get(start)) {
	      sol.add(word);
	      generateList(word, end, map, sol, res);
	      sol.remove(sol.size() - 1);
	    }
	  }
}
