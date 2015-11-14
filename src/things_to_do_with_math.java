
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
}
