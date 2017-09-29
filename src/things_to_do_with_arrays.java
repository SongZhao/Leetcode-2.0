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
	
	
	//Contains Duplicate III//
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
	//You may assume that the array is non-empty and the majority element always exist in the array  .

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
	/*given an array of n integers nums and a target, find the number of index triplets i, j, k with
	0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
	For example, given nums = [-2, 0, 1, 3], and target = 2.
	Return 2. Because there are two triplets which sums are less than 2:*/

	public int threeSumSmaller(int[] nums, int target) {
		count = 0;
		Arrays.sort(nums);
		int len = nums.length;

		for(int i=0; i<len-2; i++) {
			int left = i+1, right = len-1;
			while(left < right) {
				if(nums[i] + nums[left] + nums[right] < target) {
					count += right-left;
					left++;
				} else {
					right--;
				}
			}
		}
		return count;
	}
	/*Design and implement a TwoSum class. It should support the following operations: add and find.
	add - Add the number to an internal data structure.
	find - Find if there exists any pair of numbers which sum is equal to the value.
	For example,

	add(1);
	add(3);
	add(5);
	find(4) -> true
	find(7) -> false*/

	public class TwoSum{

		private List<Integer> list = new ArrayList<Integer>();
		private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		public void add(int number)
		{
			if (map.containsKey(number))
				map.put(number, map.get(number)+1);
			else
				map.put(number, 1);
			list.add(number);
		}
		public boolean find(int value)
		{
			for (int i = 0; i < list.size(); i++)
			{
				int num1 = list.get(i), num2 = value - num1;
				if((num1 == num2 && map.get(num1) > 1))||(num1 != num2 && map.containsKey(num2))
					return true;

			}
			return false;
		}
	}

	/*Next Permutation
	Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
	If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
	The replacement must be in-place, do not allocate extra memory.
	Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
	1,2,3 → 1,3,2
	3,2,1 → 1,2,3
	1,1,5 → 1,5,1
	 */
	public void nextPermutation(int[] nums) {
		if(nums.length == 0 || nums.length == 1)
			return;
		int i = nums.length - 1;
		for(; i > 0 && nums[i-1] >= nums[i]; i--);
		if(i == 0)
		{
			Arrays.sort(nums);
			return;
		}
		int j = nums.length - 1;
		for(; j > 0 && nums[i-1] >= nums[j]; j--);
		int tmp = nums[j];
		nums[j] = nums[i-1];
		nums[i-1] = tmp;
		Arrays.sort(nums, i, nums.length);
	}


	/*First Missing Positive
	Given an unsorted integer array, find the first missing positive integer.

	For example,
	Given [1,2,0] return 3,
	and [3,4,-1,1] return 2.

	Your algorithm should run in O(n) time and uses constant space.*/
	public int firstMissingPositive(int[] nums) {
		for(int i = 0; i < nums.length; i++)
		{
			int val = nums[i];
			while(val <= nums.length && val > 0 && nums[val-1] != val) //ie. if val = 2  >> nums[1] = 2;
			{
				int tmp = nums[val - 1];
				nums[val - 1] = val;
				nums[i] = tmp;
				val = tmp;
			}
		}
		System.out.println((nums.length == 0)? "x": nums[0]);
		for(int i = 0; i < nums.length; i++)
		{
			if(nums[i] != i+1)
				return i+1;
		}
		return nums.length +1;
	}

	Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
	public void setZeroes(int[][] matrix) {
		boolean fr = false,fc = false;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j] == 0) {
					if(i == 0) fr = true;
					if(j == 0) fc = true;
					matrix[0][j] = 0;
					matrix[i][0] = 0;
				}
			}
		}
		for(int i = 1; i < matrix.length; i++) {
			for(int j = 1; j < matrix[0].length; j++) {
				if(matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		if(fr) {
			for(int j = 0; j < matrix[0].length; j++) {
				matrix[0][j] = 0;
			}
		}
		if(fc) {
			for(int i = 0; i < matrix.length; i++) {
				matrix[i][0] = 0;
			}
		}

	}
	//Find the Duplicate Number
	//Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at
	// least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
	//method 1 binary search
	public int findDuplicate(int[] nums) {
		int low = 1;
		int high = nums.length -1;

		while (low < high)
		{   int mid = low+(high-low)/2;
			int count = 0;
			for (int i : nums)
			{
				if (i <= mid)
					count += 1;
			}
			if (count <= mid)
				low = mid+1;
			else
				high = mid;}
		return low;
	}
	//method 2   two pointer, treat the array like a linked list
	public int findDuplicate2(int[] nums) {
		if (nums.length > 1)
		{
			int slow = nums[0];
			int fast = nums[nums[0]];
			while (slow != fast)
			{
				slow = nums[slow];
				fast = nums[nums[fast]];
			}

			fast = 0;
			while (fast != slow)
			{
				fast = nums[fast];
				slow = nums[slow];
			}
			return slow;
		}
		return -1;
	}

	/*rotate Image
	You are given an nxn 2D matrix representing an image
	rotate the image by 90 degrees(clock wise)
	 */
	public void rotate(int[][] matrix) {
		int s = 0, e = matrix.length - 1;
		while(s < e){                           //flip the matrix upside down
			int[] temp = matrix[s];
			matrix[s] = matrix[e];
			matrix[e] = temp;
			s++; e--;
		}

		for(int i = 0; i < matrix.length; i++){             //change the symmetry on diagonal
			for(int j = i+1; j < matrix[i].length; j++){
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
	}
}
