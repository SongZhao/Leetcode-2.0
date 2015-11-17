
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
			
}
