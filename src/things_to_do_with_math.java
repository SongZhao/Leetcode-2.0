
public class things_to_do_with_math {
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
	 
	 
	 
	 //Integer to English Words
	 /*123 -> "One Hundred Twenty Three"
		12345 -> "Twelve Thousand Three Hundred Forty Five"
		1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
	  */
	 private final String[] lessThan20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", 
			 "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	 private final String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	 private final String[] thousands = {"", "Thousand", "Million", "Billion"};

	 public String numberToWords(int num) {
	     if (num == 0)
	         return "Zero";
	     int i = 0;
	     String words = "";

	     while (num > 0) {
	         if (num % 1000 != 0)
	             words = helper(num % 1000) + thousands[i] + " " + words;
	         num /= 1000;
	         i++;
	     }

	     return words.trim();
	 }

	 private String helper(int num) {
	     if (num == 0)
	         return "";
	     else if (num < 20)
	         return lessThan20[num] + " ";
	     else if (num < 100)
	         return tens[num / 10] + " " + helper(num % 10);
	     else
	         return lessThan20[num / 100] + " Hundred " + helper(num % 100);
	 }
	 
	 //Integer to Roman
	 //roman has no mulplication, only adding and minusing.
	 public String intToRoman(int num) 
	 {
		 int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1}; 
		 //the reason we list out 90,40,9 and 4 in this array is 
         //because they r special cases.
		 String[] strs = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
         StringBuilder sb = new StringBuilder();

         for(int i=0;i<values.length;i++) {
        	 while(num >= values[i]) {
        		 num -= values[i];
        		 sb.append(strs[i]);
        	 }
         }
         return sb.toString();
	 }
	 //roman to integer.
	 public int romanToInt(String s) {
	        //I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1,000
	        //In this system, a letter placed after another of greater value adds (thus XVI or xvi is 16)
	        //whereas a letter placed before another of greater value subtracts (thus XC or xc is 90).
	        //use this to handle 90,40,9,4
		 	int result = 0;
	        char[] sChar = s.toCharArray();
	        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	        map.put('I',1);
	        map.put('V',5);
	        map.put('X',10);
	        map.put('L',50);
	        map.put('C',100);
	        map.put('D',500);
	        map.put('M',1000);
	        if(sChar.length == 1) return map.get(sChar[0]);
	        for(int i = 0; i < sChar.length-1; i++){
	            if( map.get(sChar[i]) >= map.get(sChar[i+1]) )
	                result += map.get(sChar[i]);
	            else
	                result -= map.get(sChar[i]);
	         }
	         result += map.get(sChar[sChar.length-1]);
	         return result;
	    }
	 
	 //Rectangle Area
	 public int computeAreaJoin(int A, int B, int C, int D, int E, int F, int G, int H) {
		    int hTop = Math.min(D, H);
		    int hBot = Math.max(B, F);
		    int wTop = Math.min(C, G);
		    int wBot = Math.max(A, E);
		    if (hTop < hBot || wTop < wBot) {
		        return 0;
		    } else {
		        return (hTop - hBot) * (wTop - wBot);
		    }
		}
		// A U B = A + B - A * B
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
	    return (C-A)*(D-B) + (G-E)*(H-F) - computeAreaJoin(A,B,C,D,E,F,G,H);
	}
	
	
	/*Add Digits
	Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
	For example:
	Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.*/
	public int addDigits(int num) {
        if (num == 0){
            return 0;
        }
        return num % 9 == 0 ? 9 : (num % 9);
    }
	
	//Ugly Number
	/*Write a program to check whether a given number is an ugly number.

	Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.

	Note that 1 is typically treated as an ugly number.*/
	public boolean isUgly(int num) {
        //num = Math.abs(num);
        if(num == 0)
            return false;
        while(num%2 == 0 || num%3 == 0 || num%5 == 0)
        {
            if(num%2 == 0)
                num = num/2;
            if(num%3 == 0)
                num = num/3;
            if(num%5 == 0)
                num = num/5;
        }
        if(num == 1)
            return true;
        else
            return false;
    }
	
	//Best Meeting Point 
	/*A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
			For example, given three people living at (0,0), (0,4), and (2,2):
			1 - 0 - 0 - 0 - 1
			|   |   |   |   |
			0 - 0 - 0 - 0 - 0
			|   |   |   |   |
			0 - 0 - 1 - 0 - 0*/
	public int minTotalDistance(int[][] grid) {
        List<Integer> ipos = new ArrayList<Integer>();
        List<Integer> jpos = new ArrayList<Integer>();
        // 统计出有哪些横纵坐标
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    ipos.add(i);
                    jpos.add(j);
                }
            }
        }
        int sum = 0;
        // 计算纵坐标到纵坐标中点的距离，这里不需要排序，因为之前统计时是按照i的顺序
        for(Integer pos : ipos){
            sum += Math.abs(pos - ipos.get(ipos.size() / 2));
        }
        // 计算横坐标到横坐标中点的距离，这里需要排序，因为统计不是按照j的顺序
        Collections.sort(jpos);
        for(Integer pos : jpos){
            sum += Math.abs(pos - jpos.get(jpos.size() / 2));
        }
        return sum;
    }
			
}
