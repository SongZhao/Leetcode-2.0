import java.util.Arrays;

public class things_to_do_with_arrays {

	
	
	
	 //Bulls and Cows
	  //create 2 array that to record the appearance of each digit that didnt match.
	  public String getHint(String secret, String guess) 
	  {
	        int bull = 0, cow = 0;

	        int[] sarr = new int[10];
	        int[] garr = new int[10];

	        for(int i = 0; i < secret.length(); i++){
	            if(secret.charAt(i) != guess.charAt(i)){
	                sarr[secret.charAt(i)-'0']++;
	                garr[guess.charAt(i)-'0']++;
	            }else{
	                bull++;
	            }
	        }

	        for(int i = 0; i <= 9; i++){
	            cow += Math.min(sarr[i], garr[i]);  //calculate cow number.
	        }

	        return (bull + "A" + cow + "B");
	    }
	
	
	//Contains Duplicate III
	//Given an array of integers, find out whether there are two distinct indices i and j in the array such that the 
	//difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
	
	//the idea is to create a new type to store the value and position as a pair.
	//sort the array by implement comparable function
	//then use a nested loop to compare element by element, but notice that inner loop's condition is started
	//from out loop's index+1 and ends by the abs.difference of two node. thus the complexity is not n^2. #optimize nested loop.
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) 
	{
	    if(nums.length<2||k<1||t<0) 
	    	return false;
	    ValuePosPair[] valPosArr = new ValuePosPair[nums.length];
	    for(int i =0;i<nums.length;i++) 
	    	valPosArr[i] = new ValuePosPair(nums[i],i); 
	    Arrays.sort(valPosArr); 
	    for(int i=0;i<valPosArr.length;i++){
	        for(int j=i+1;j<valPosArr.length&&(Math.abs((long)valPosArr[j].val-(long)valPosArr[i].val)<=(long)t);j++){
	            if(Math.abs(valPosArr[j].pos-valPosArr[i].pos)<=k) 
	            {
	                System.out.println((long)valPosArr[j].val + " " + (long)valPosArr[i].val);
	                return true; 
	            }
	        }
	    }
	    return false;
	}
	class ValuePosPair implements Comparable<ValuePosPair> {

		int val;
		int pos;

		ValuePosPair(int v, int p) {
			val = v;
			pos = p;
		}

		public int compareTo(ValuePosPair x) {
			return this.val - x.val;
		}

	}

	//Majority element
	//Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
	//You may assume that the array is non-empty and the majority element always exist in the array.

	public int majorityElement1(int[] nums)
	{
		if (nums == null || nums.length == 0) return 0;
		int cnt = 0;
		int candidate = 0;
		for(int i = 0; i < nums.length; i++) {
			if (cnt == 0) {
				candidate = nums[i];
				cnt = 1;
			} else if (nums[i] == candidate) {
				cnt++;
			} else {
				cnt--;
			}
		}
		return candidate;
	}

	public int majorityElement2(int[] nums) {
		if (nums.length == 1) return nums[0];
		int leftM = majorityElement(Arrays.copyOfRange(nums, 0, nums.length/2));
		int rightM = majorityElement(Arrays.copyOfRange(nums, nums.length/2, nums.length));
		int counterLeft = 0;
		for (int num : nums) {
			if (num == leftM) counterLeft++;
			if (counterLeft > nums.length/2) return leftM;
		}
		return rightM;
	}

	public int majorityElement3(int[] nums) {
		Arrays.sort(nums);
		return nums[nums.length/2];
	}
	//Majority element II
	//this time we are trying to find elements that apppear >= n/3
	//note that >= n/3 means there are at most two elements that can
	//satisfy this

    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return new ArrayList<Integer>();
        List<Integer> result = new ArrayList<Integer>();
        int number1 = nums[0], number2 = nums[0], count1 = 0, count2 = 0, len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1)
                count1++;
            else if (nums[i] == number2)
                count2++;
            else if (count1 == 0) {
                number1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                number2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1)
                count1++;
            else if (nums[i] == number2)
                count2++;
        }
        if (count1 > len / 3)
            result.add(number1);
        if (count2 > len / 3)
            result.add(number2);
        return result;
    }

	//Pascal'sTriangle
    //Given numRows, generate the first numRows of Pascal's triangle.
	public List<List<Integer>> generate(int numRows)
	{
		List<List<Integer>> allrows = new ArrayList<List<Integer>>();
		ArrayList<Integer> row = new ArrayList<Integer>();
		for(int i=0;i<numRows;i++)
		{
			row.add(0, 1);
			for(int j=1;j<row.size()-1;j++)
				row.set(j, row.get(j)+row.get(j+1));
			allrows.add(new ArrayList<Integer>(row));
		}
		return allrows;

	}


	//Pascal'sTriangle II
	public List<Integer> getRow(int rowIndex) {
		Integer[] result =  new Integer[rowIndex + 1];
		Arrays.fill(result, 0);
		result[0] = 1;
		for(int i = 1; i < rowIndex + 1; i++)
			for(int j = i; j >= 1; j--)
				result[j] += result[j - 1];
		return Arrays.asList(result);
	}
}
