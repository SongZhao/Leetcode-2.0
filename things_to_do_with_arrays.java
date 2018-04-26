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
     
    /*idea #1
    / just record first element and go through the list. use a variable to count the record's appearance
    / if current element = record, then increase the count, otherwise decrease the count. if the count is 
    / already 0 and element != record, assign the element to the record. If there is a majority element
    / then it must equals to the record.     O(n)
    /
    / idea #2
    / use recursive. break the list in two and in each layer count the results returned from the lower layer
 	/ O(log(n))
    */
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
			if (num == leftM) 
				counterLeft++;
			if (counterLeft > nums.length/2) 
				return leftM;
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
	
	/*idea
    / keep two record, go through the list, if we meet a element 
    / different with the record, reduce both record count by 1. if
    / the record count is 0, assign current element to the record.
    / go through the list the second time to see if both record appeared
	/ n/3 times.
	*/


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
	//implementation 2
	public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> row, pre = null;
		for (int i = 0; i < numRows; i++) {
			row = new ArrayList<Integer>();
			for (int j = 0; j <= i; j++)
				if (j == 0 || j == i)                       //first and last elements r always 1
					row.add(1);
				else
					row.add(pre.get(j - 1) + pre.get(j));
			pre = row;
			res.add(row);
		}
		return res;
    }


	//Pascal'sTriangle II
	public List<Integer> getRow(int rowIndex) {
		Integer[] result =  new Integer[rowIndex + 1];
		Arrays.fill(result, 0);
		result[0] = 1;
		for(int i = 1; i < rowIndex + 1; i++)   //start from 1 because res[0] is fixed to 1
			for(int j = i; j >= 1; j--)         //always set the last element as 1, then recusivley updates
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
	//Set Matrix Zeroes
	//Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
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
			int slow = nums[0];     //point to first node
			int fast = nums[nums[0]]; //point to the second node
			while (slow != fast)		//meet in a loop
			{
				slow = nums[slow];	//speed = 1
				fast = nums[nums[fast]]; //speed = 2
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

	//rotate array
	public void rotate(int[] nums, int k) {
	    if(nums == null || nums.length < 2){
	        return;
	    }
	    k = k % nums.length;
	    reverse(nums, 0, nums.length - k - 1);
	    reverse(nums, nums.length - k, nums.length - 1);
	    reverse(nums, 0, nums.length - 1);   
	}

	private void reverse(int[] nums, int i, int j){
	    int tmp = 0;       
	    while(i < j){
	        tmp = nums[i];
	        nums[i] = nums[j];
	        nums[j] = tmp;
	        i++;
	        j--;
	    }
	}

	//Spiral Matrix
	// pointers and shrink them one by one
	public List<Integer> spiralOrder(int[][] matrix) {
         List<Integer> res = new ArrayList<Integer>();

        if (matrix.length == 0) {
            return res;
        }

        int rowBegin = 0;
        int rowEnd = matrix.length-1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j ++) {
                res.add(matrix[rowBegin][j]);
            }
            rowBegin++;

            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j ++) {
                res.add(matrix[j][colEnd]);
            }
            colEnd--;

            if (rowBegin <= rowEnd) {
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j --) {
                    res.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;

            if (colBegin <= colEnd) {
                // Traver Up
                for (int j = rowEnd; j >= rowBegin; j --) {
                    res.add(matrix[j][colBegin]);
                }
            }
            colBegin ++;
        }
        return res;
    }
    /*Insert Interval
    Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
	You may assume that the intervals were initially sorted according to their start times.
	Example 1:
	Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
	Example 2:
	Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].*/
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> res = new ArrayList<Interval>();
    for(int i = 0; i < intervals.size(); i++)
    {
        Interval ptr = intervals.get(i);    //create a ptr copy
        if(ptr.start > newInterval.end)     //if this copy.start is after cur.end
        {
            res.add(newInterval);           //put cur in the list 
            newInterval = ptr;              // make cur = copy
        }
        else if(ptr.end < newInterval.start)    //else if copy.end is befor the cur.start
        {
            res.add(ptr);                       //put copy in the list
        }
        else                                    //if the copy is overlap with cur
        {
            newInterval.start = Math.min(ptr.start, newInterval.start);     //make new cur start and new cur end
            newInterval.end = Math.max(ptr.end, newInterval.end);           // we dont merge it now, since we dont know if
                                                                            // next intvl is overlaped or not, leave the decision 
                                                                            // to next loop (Notice : we only add cur in the first
                                                                            // clause, thus we might add it after the while loop if
                                                                            // it doesnt happen there)
        }
    }
    res.add(newInterval); //if the merge happenend till the end of the loop, we need to add it here.
    return res;
	}


	//Search a 2D Matrix
	public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0)
            return false;
        int start = 0, rows = matrix.length, cols = matrix[0].length;
        int end = rows*cols - 1;
        //n * m matrix convert to an array => matrix[x][y] => a[x * m + y]
		//current row = index/cols, current col = index%cols;
        while(start <= end) //must be <= for the  case where start = end
        {
            int mid = (start + end)/2;
            if(matrix[mid/cols][mid%cols] == target)
                return true;
            if(matrix[mid/cols][mid%cols] < target)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return false;
    }

    //Sort Colors
    //tricky three pointer
    public void sortColors(int[] nums) {
         int p1 = 0, p2 = nums.length - 1, index = 0;
         while (index <= p2) 
            {
                if (nums[index] == 0) {
                    nums[index] = nums[p1];
                    nums[p1] = 0;
                    p1++;
                }
                if (nums[index] == 2) {
                    nums[index] = nums[p2];
                    nums[p2] = 2;
                    p2--;
                    index--; //decrease index by one or use while instead of if.
                    		 // because we need to examine if the one been swaped out
                    		 // is 2 or not. if we dont we are gonna miss that since
                    		 // the index pointer is moving to the right.
                }
                index++;
        }
    }

    //Remove Duplicates from Sorted Array II
    public int removeDuplicates(int[] nums) {
        int i = 0;  //two pointers i and n. i is the slow one
	    for (int n : nums)
	        if (i < 2 || n > nums[i-2])
	            nums[i++] = n;
	    return i;
    }

    

    //Longest Consecutive Sequence
    //given [100, 4, 200, 1, 3, 2], return 4
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
            for(int n : nums) {
                set.add(n);
            }
            int best = 0;
            for(int n : set) {
                if(!set.contains(n - 1)) {  // only check for one direction
                    int m = n + 1;
                    while(set.contains(m)) {
                        m++;
                    }
                    best = Math.max(best, m - n);
                }
            }
            return best;
    }

    //Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        if (num == null || num.length == 0) {
   			 return 0;
        int low = 0;
        int high = nums.length - 1;
        while(low < high)
        {
            int m = low + (high - low)/2;
            if (mid > 0 && num[mid] < num[mid - 1]) {	//prune results that if one is small than the 
                return num[mid];						// item before it, it must be the smallest
            if(nums[m] < nums[high])
            {
                high = m;
            }
            else if(nums[m] > nums[high])
            {
                low = m + 1;
            }
        }
        return nums[low];
    }
    //Find Minimum in Rotated Sorted Array II (with array has duplicates in it)
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while(low < high)
        {
            int m = low + (high - low)/2;
            if(nums[m] < nums[high])
            {
                high = m;
            }
            else if(nums[m] > nums[high])
            {
                low = m + 1;
            }
            else
            {
                high--;
            }
        }
        return nums[low];
    }

    //Search in Rotated Sorted Array
    public int search(int[] nums, int target) {
                  int start = 0;
    int end = nums.length - 1;   
    while (start <= end) {
        int mid = (end + start) / 2;
        // System.out.format("start=%d,mid=%d,end=%d\n",start,mid,end);
        if (nums[mid] == target) return mid;
  
        if (nums[start] <= nums[mid]) { //mid is at the left of the rotate point
            if (nums[start] <= target && target < nums[mid]) {  //target is at the left of the mid
                //must have nums[start] <= target to elimilate the case where 
                //  there is start = mid
                end = mid - 1;
            } else {                                            //target is at the right of the mid
                start = mid + 1;
            }
        } else {
            if (nums[mid] < target && target <= nums[end]) {    //mid is at the right of the rotate point
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
    }
    
    return -1;
    }

    //Find Peak Element 
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] < nums[mid + 1]) 		//find a peak by always going up.
            {
                left = mid + 1;
            } 
            else 
            {
                right = mid;
            }
    }
    return left;
    }

    //Summary Ranges
    public List<String> summaryRanges(int[] nums) {
        List<String> list=new ArrayList();
        if(nums.length==1){
            list.add(nums[0]+"");
            return list;
        }
        for(int i=0;i<nums.length;i++){
            int a=nums[i];
            while(i+1<nums.length&&(nums[i+1]-nums[i])==1){
                i++;
            }
            if(a!=nums[i]){
                list.add(a+"->"+nums[i]);
            }else{
                list.add(a+"");
            }
        }
        return list;
    }
    //Product of Array Except Self
    //Given an array of n integers where n > 1, nums, return an array output such that output[i] 
    //is equal to the product of all the elements of nums except nums[i].
	//Solve it without division and in O(n).	
    public int[] productExceptSelf(int[] nums) {
	    int n = nums.length;
	    int[] res = new int[n];
	    res[0] = 1;
	    for (int i = 1; i < n; i++) {
	        res[i] = res[i - 1] * nums[i - 1];
	    }
	    int right = 1;
	    for (int i = n - 1; i >= 0; i--) {
	        res[i] *= right;
	        right *= nums[i];
	    }
	    return res;
    }

    //Third Maximum Number
    //can do this navively by keeping 3 var to record top 3 value
    //this one use queue and set. Using priority que, so the one
    //we poll is the smallest.  
    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (!set.contains(i)) {
                pq.offer(i);
                set.add(i);
                if (pq.size() > 3) {
                    set.remove(pq.poll());
                }
            }
        }
        if (pq.size() < 3) {
            while (pq.size() > 1) {
                pq.poll();
            }
        }
        return pq.peek();
    }


    //Find All Duplicates in an Array
    //Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
	//Find all the elements that appear twice in this array.
	//Could you do it without extra space and in O(n) runtime?
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int index = Math.abs(nums[i])-1;
            if (nums[index] < 0)
                res.add(Math.abs(index+1));
            nums[index] = -nums[index];			//we alt the original array to indicate the difference.
        }
        return res;
    }



    //K-diff Pairs in an Array
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0)   return 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                //count how many elements in the array that appear more than twice.
                if (entry.getValue() >= 2) {
                    count++;
                } 
            } else {
                if (map.containsKey(entry.getKey() + k)) {
                    count++;
                }
            }
        }     
        return count;
    }

    //Subarray Sum Equals K
    public int subarraySum(int[] nums, int k) {
         int sum = 0, result = 0;
            Map<Integer, Integer> preSum = new HashMap<>();
            preSum.put(0, 1);

            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (preSum.containsKey(sum - k)) {
                    result += preSum.get(sum - k);
                }
                preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
            }
        
        return result;
    }

    //Maximum Subarray
    public int maxSubArray(int[] nums) {
    	int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++)
        {
           sum = Math.max(nums[i], sum + nums[i]);
           max = Math.max(sum, max);
        }
        return max;
    }


    //Shortest Unsorted Continuous Subarray
    public int findUnsortedSubarray(int[] A) {
     	int i = 0, j = -1, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    	//a sorted array means that leftmost is the min
    	// and right most is the max
	    for (int l = 0, r = nums.length - 1; r >= 0; l++, r--) {
	        max = Math.max(max, nums[l]);
	        if (nums[l] != max) 
	        	j = l;
	        
	        min = Math.min(min, nums[r]);
	        if (nums[r] != min) 
	        	i = r;
	    }
	    return (j - i + 1);
    }

    //Sliding Window Maximum
    //#queue using linked list
    public int[] maxSlidingWindow(int[] nums, int k) {
	    int n = nums.length;
	    if (n == 0) {
	        return nums;
	    }
	    int[] result = new int[n - k + 1];
	    LinkedList<Integer> dq = new LinkedList<>();
	    for (int i = 0; i < n; i++) {
	        if (!dq.isEmpty() && dq.peek() < i - k + 1) {
	            dq.poll();
	        }
	        while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
	            dq.pollLast();
	        }
	        dq.offer(i);
	        if (i - k + 1 >= 0) {
	            result[i - k + 1] = nums[dq.peek()];
	        }
	    }
	    return result;
	}

	//trap rain water
	
	/*
	/ think it more like from sides to center. use sides as one of the wall, and start 
	/ from the lowest side, switch over to another side if this side is higher than another
	/ because lower wall decides the capacity.
	*/
	public int trap(int[] height) {
        int left = 0, right = height.length - 1, leftmax = 0, rightmax = 0, area = 0;
        while(left < right)
        {
            if(height[left] > height[right])
            {
                rightmax = Math.max(height[right], rightmax);
                area += rightmax - height[right--];
            }
            else
            {
                leftmax = Math.max(height[left], leftmax);
                area += leftmax - height[left++];
            }
        }
        return area;
    }


    //Maximum Product Subarray
    //Find the contiguous subarray within an array (containing at least one number) which has the largest product.
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i=0; i<nums.length; i++){
            if(nums[i] < 0){ int tmp = imax; imax = imin; imin = tmp;}
            imax = Math.max(imax*nums[i], nums[i]);
            imin = Math.min(imin*nums[i], nums[i]);
            
            max = Math.max(max, imax);
        }
        return max;
    }

    //3Sum closest
    //Given an array S of n integers, find three integers in S such that the sum is closest to 
    //a given number, return the sum of the three integers. You may assume each input would 
    //have exactly one solution

    /*idea
    / This is just like 3 sum. but instead of finding a match we use a variable to keep track of 
    / the result. the compare condition would be the |previous record - target| larger than 
    / |current value - target|. return the result after running all combination 
    /  
    */
    public int threeSumClosest(int[] num, int target) {
	    int result = num[0] + num[1] + num[num.length - 1];
	    Arrays.sort(num);
	    for (int i = 0; i < num.length - 2; i++) {
	    	if(i == 0 || nums[i]!= nums[i - 1]) //remove the duplicates since we already sorted the array
	    	{
		    	//note here we are using start = i + 1 to eliminate cases that already examed.
		        int start = i + 1, end = num.length - 1;   
		        while (start < end) 
		        {
		            int sum = num[i] + num[start] + num[end];
		            if (sum > target) {
		                end--;
		            } else {
		                start++;
		            }
		            if (Math.abs(sum - target) < Math.abs(result - target)) {
		                result = sum;
		            }
		        }
		    }
		}
	    return result;
	}


	//4Sum
	//given an array and a target, find all unique euadruplets in the 
	// array which gives the sum of the target.
	public List<List<Integer>> fourSum(int[] num, int target) 
	{
	    ArrayList<List<Integer>> ans = new ArrayList<>();
	    if(num.length<4)
	    	return ans;
	    Arrays.sort(num);
	    for(int i=0; i<num.length-3; i++){
	        if(num[i]+num[i+1]+num[i+2]+num[i+3]>target)
	        	break; //first candidate too large, search finished
	        if(num[i]+num[num.length-1]+num[num.length-2]+num[num.length-3]<target)
	        	continue; //first candidate too small
	        if(i>0&&num[i]==num[i-1])continue; //prevents duplicate result in ans list
	        for(int j=i+1; j<num.length-2; j++){
	            if(num[i]+num[j]+num[j+1]+num[j+2]>target)break; //second candidate too large
	            if(num[i]+num[j]+num[num.length-1]+num[num.length-2]<target)continue; //second candidate too small
	            if(j>i+1&&num[j]==num[j-1])continue; //prevents duplicate results in ans list
	            int low=j+1, high=num.length-1;
	            while(low<high){
	                int sum=num[i]+num[j]+num[low]+num[high];
	                if(sum==target){
	                    ans.add(Arrays.asList(num[i], num[j], num[low], num[high]));
	                    while(low<high&&num[low]==num[low+1])
	                    	low++; //skipping over duplicate on low
	                    while(low<high&&num[high]==num[high-1])
	                    	high--; //skipping over duplicate on high
	                    low++; 
	                    high--;
	                }
	                //move window
	                else if(sum<target)
	                	low++; 
	                else 
	                	high--;
	            }
	        }
    }
   


}
