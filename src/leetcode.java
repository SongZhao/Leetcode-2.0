
import java.util.*;
import java.util.Arrays;
import java.util.HashSet;
public class leetcode {
//twosum
	//3sum
	///3sum close
	
	
	//4sum
	public static List<List<Integer>> fourSum(int[] num, int target)
	{
		Arrays.sort(num);
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		if (num == null || num.length < 4)
			return results;
		
		for(int i = 0; i < num.length -3; i++)
		{
			if(i > 0 && num[i] == num[i-1])
				continue;
			for (int e = num.length -1; e >= i+3; e--)
			{
				if(e < num.length -1 && num[e] == num[e+1])
					continue;
				
				int j = i+1;
				int k = e - 1;
				int patial_sum = num[i] + num[e];
				while(j < k)
				{
					int patial_sum2 = num[j]+num[k];
					if(j > i+1 && num[j] == num[j-1])
					{
						j++;
						continue;
					}
					if(k <e -1 && num[k] == num[k+1])
					{
						k--;
						continue;
					}
					if(patial_sum2+patial_sum <= target)
					{
						if(patial_sum2+patial_sum == target)
						{
							results.add(Arrays.asList(num[i], num[j], num[k],num[e]));
							j++;
						}
						else
							j++;
					}
					else
						k--;
				}
			}
		}
		return results;
	}
	
	//add two number
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2)
	{
		int carry = 0;
		ListNode head = new ListNode(0);
		ListNode res = head;
		int first;
		int second;
		while(l1 != null||l2 != null||carry != 0)
		{
			first = (l1 == null)?0:l1.val;
			second = (l2 == null)?0:l2.val;
			carry = carry + first + second;
			head.next = new ListNode(carry%10);
			head = head.next;
			carry = carry/10;
			l1 = (l1 == null)?null:l1.next;
			l2 = (l2 == null)?null:l2.next;
			
		}
		return res.next; //here should be return res.next instead of res cuz res it self is just a head node with 0 in it.
	}
	
	//add binery string
	public static String addBinary(String a, String b)
	{
		if(a == null|| a.length() == 0)
			return b;
		if(b == null|| b.length() == 0)
			return a;
		
		
		StringBuilder sb = new StringBuilder();
		int n = a.length() - 1;
		int m = b.length() - 1;
		int carry = 0;
		while(m >=0 || n >= 0||carry!=0)
		{
			int sum = carry;
			if(m>=0)
				sum += b.charAt(m--) - '0';
			if(n >= 0)
				sum += a.charAt(n--) - '0';
			sb.append(sum%2);
			carry = sum/2;
		}
		return sb.reverse().toString();
	}
	
	//mul two number
	public static String multiply(String num1, String num2)
	{
		if(num1.equals("0")||num2.equals("0"))
			return "0";
		String res = "";
		int carry = 0;
		int m = num1.length();
		int n = num2.length();
		for(int i = 0; i < m + n -1; i++)
		{
			int product = 0;
			for(int j = 0; j < Math.min(m, i+1); j++)
			{
				if( i - j < n) //multiply each digit in num2 with current num1 digit
					product = product + (num1.charAt(m - j - 1) - '0')*(num2.charAt(n - 1 - (i - j)) - '0');
			}
			carry = product + carry;
			res = String.valueOf((carry%10)) + res; //simple way to put it at the head of the string.
			carry = carry/10;
		}
		if(carry == 0)
			return res;
		return String.valueOf(carry)+res;
	}
	
	//Plus One
	public int[] plusOne(int[] digits)
	{
		if(digits == null||digits.length == 0)
			return new int[0];
		for(int i = digits.length - 1; i >= 0; i--)
		{
			if(digits[i] < 9)
			{
				digits[i] += 1;
				return digits;
			}
			else
				digits[i] = 0;
		}
		
		//if we completed the loop and didnt return, this means we have an '1' carry out and all other bits are 0;
		int[] res = new int[digits.length + 1];
		res[0] = 1;
		return res;
	}
	
	//Longest Substring without repeating characters
	public int lengthOfLongestSubstring(String s)
	{
        HashMap<Character, Integer> cache = new HashMap<Character, Integer>();
		if(s == null|| s.length() == 0)
			return 0;
		if(s.length() == 1)
			return 1;
		int start = 0;
		int end = 1;
		int max = 1;
		cache.put(s.charAt(0), 0);
		while(end != s.length())
		{
			if(cache.containsKey(s.charAt(end)) && cache.get(s.charAt(end)) >= start)//we need this following condition 
			{																		//  to prevent start pointer going backwards.	
				start = cache.get(s.charAt(end))+1; 
			}
			max = Math.max(max, end - start +1);
			cache.put(s.charAt(end), end);
			end++;
		}
		return max;
	}
	
	//median of Two sorted arrays
	public double findMedianSortedArrays(int[] nums1, int[] nums2)
	{
		//leetcode.com/discuss/41621/very-concise-iterative-solution-with-detailed-explanation
	}
	
	
	//Longest Palindromic Substring
	private int maxLength = 1;
	private int maxIndex = 0;
	public String longestPalindrome(String s)
	{
		int length = s.length();
		for (int i = 0; i < length; i++)
		{
			findPalindrome(s, length, i, 0);
			findPalindrome(s,length,i,1);
			
		}
		return s.substring(maxIndex, maxIndex+maxLength);
	}
	private void findPalindrome(String s, int length, int i, int shift)
	{
		int left = i;
		int right = i + shift;
		while(left >= 0 && right < length && s.charAt(left) == s.charAt(right))
		{
			if((right - left +1) > maxLength)
			{
				maxLength = right - left + 1;
				maxIndex = left;
			}
			left --;
			right ++;
		}

	}
	
	
	//zig zag
	//use direction var;
	
	//reverse integer
	//the reason we use MAX/10 instead of res*10, is the overflow will get wrong results.
	
	//convert string into int
	public int myAtoi(String str)
	{
		int index = 0, sign = 1, total = 0;
		if(str.length() == 0)
			return 0;
		while(str.charAt(index) == ' ' && index < str.length())
			index++;
		
		if(str.charAt(index) == '+' || str.charAt(index)== '-')
		{
			sign = str.charAt(index) == '+' ? 1 : -1;
			index++;
		}
		
		while(index < str.length())
		{
			int digit = str.charAt(index) - '0';
			if(digit < 0 || digit > 9)
				break;
			if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE%10 < digit)
				return sign == 1?Integer.MAX_VALUE : Integer.MIN_VALUE;
			total = 10*total + digit;
			index++;
		}
		return total*sign;
	}
	
	
	//Letter Combinations of a Phone Number
	private final String[] letters = {
			"",
			"",
			"abc",
			"def",
			"ghi",
			"jkl",
			"mno",
			"pqrs",
			"tuv",
			"wxyz",
	};
	public List<String> letterCombinations(String digits)
	{
		List<String> result = new ArrayList<String>();
		if(digits.length() == 0)
			return result;
		result.add("");
		for(char c : digits.toCharArray())
		{
			String inc = letters[c - '0'];
			if(inc.length() == 0)
				continue;
			List<String> tmp = new ArrayList<String>();
			for(char l : inc.toCharArray())
			{
				for(String s : result)
				{
					tmp.add(s + l);
				}
			}
			result = tmp;
		}
		return result;
	}
	
	//Remove Nth node from end of list.
	public ListNode removeNthFromEnd(ListNode head, int n)
	{
		ListNode first = head;
		ListNode second = head;
		while(n > 0)
		{
			first = first.next;
			n--;
		}
		if(first == null)
			return second.next;
		while(first.next != null)
		{
			first = first.next;
			second = second.next;
		}
		second.next = second.next.next;
		return head;
	}
	
	//Generate parenthesis
	public List<String> generateParenthesis(int n)
	{
		List<String> list = new ArrayList<String>();
		backtrack(list, "", 0,0,n);
		return list;
	}
	public void backtrack(List<String> list, String s, int open, int close, int max)
	{
		if(s.length() == max)
			list.add(s);
		if(open < max)
			backtrack(list, s + '(', open + 1, close, max);
		if(close < open)
			backtrack(list, s + ')', open, close, max);
	}
	
	//Merge two sorted lists
	//the dumb way
	public ListNode mergeTwoList(ListNode l1, ListNode l2)
	{
		ListNode head = new ListNode(0);
		ListNode p = head;
		while(l1 != null && l2 != null)
		{
			if(l1.val < l2.val)
			{
				p.next = l1;
				l1 = l1.next;
				p = p.next;
			}
			else
			{
				p.next = l2;
				l2 = l2.next;
				p = p.next;
			}
			if(l1 == null)
				p.next = l2;
			else
				p.next = l1;
			
		}
		return head.next;
	}
	//Merge k sorted lists
	//M1 devide and conquer
	public ListNode mergeTwoLists(ListNode l1, ListNode l2)    //merge two list the rec way!!!!!!!
	{
		if(l1 == null)
			return l2;
		if(l2 == null)
			return l1;
		
		if(l1.val < l2.val)
		{
			l1.next = mergeTwoLists(l1.next,l2);
			return l1;
		}
		else
		{
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
	}
	public ListNode mergeKLists(ListNode[] lists)
	{
		int len = lists.length;
		if(lists.length == 0)
			return null;
		if(lists.length == 1)
			return lists[0];
		while(len > 1)
		{
			for(int i = 0; i < len; i++)
			{
				lists[i] = mergeTwoLists(lists[i], lists[len - i -1]);
			}
			len = (len/2 + len%2);
		}
		return lists[0];
	}
	
	//Swap nodes in pairs
	public ListNode swapPairs(ListNode head)
	{
		ListNode virtue = new ListNode(0);
		ListNode pvirtue = virtue;
		pvirtue.next = head;
		ListNode swap, postswap;
		while(pvirtue.next != null && pvirtue.next.next != null)
		{
			swap = pvirtue.next;
			postswap = swap.next.next;
			pvirtue.next = swap.next;
			pvirtue.next.next = swap;
			swap.next = postswap;
			pvirtue = swap;
		}
		return virtue.next;
	}
	
	//Reverse nodes in k-group
	public ListNode reverseKGroup(ListNode head, int k)
	{
		ListNode Virtue = new ListNode(0);
		ListNode pVirtue = Virtue;
		pVirtue.next = head;
		ListNode swapp1, swapp2;
		ListNode end = new ListNode(0);
		if(head == null)
			return head;
		if(k == 1)
			return head;
		end = head;    //end is the first node of the section that needs to be swapped
		while(true)
		{
			int i = k;
			while(i > 0)
			{
				if (end == null)
					return Virtue.next;
				end = end.next;
				i--;
				if(end == null && i != 0) //double check if the end is null, because it can be null and not cause return(for swapping)
					return Virtue.next;
			}
			swapp1 = pVirtue.next;
			while(swapp1 != end)
			{
				swapp2 = swapp1;
				while(swapp2.next.next != end)
				{
					swapp2 = swapp2.next;
				}
				pVirtue.next = swapp2.next;
				pVirtue.next.next = swapp1;
				swapp2.next = end;
				pVirtue = pVirtue.next;
			}
			pVirtue = swapp1;
		}
	}
	
	//remove duplicates from sorted array
	public int removeDuplicates(int[] nums)
	{
		int i = 0;
		int j = i;
		if(nums.length ==0 || nums.length == 1)
			return nums.length;
		while(j < nums.length -1)
		{
			j++;
			if(nums[i]!=nums[j])
				nums[++i] = nums[j];
		}
		return i+1;
	}
	
	//remove element
	public int removeElement(int[] nums, int val)
	{
		int i = 0, j = 0;
		for(;j <= nums.length -1; j++ )
		{
			if(nums[j] != val)
			{
				nums[i] = nums[j];
				i++;
			}
		}
		return i;
	}
	//Implement strStr()
	public int strStr(String haystack, String needle)
	{
		if(needle.length() == 0)
			return 0;
		for(int i = 0; i <= haystack.length() - needle.length(); i++)
		{
			String subs = haystack.substring(i, i + needle.length());
			if(subs.equals(needle))
				return i;
		}
		return -1;
	}
	
	//divide two Integers
	//first convert it to long type so we can handle int's overflow in long
	public int divide(int dividend, int divisor)
	{
		int sign = ((dividend < 0)^(divisor < 0))?-1:1;
		long sdividend = Math.abs((long)dividend), sdivisor = Math.abs((long)divisor);
		long quotient = 0;
		long t = 0;
		for(int i = 31; i >= 0; i--)
		{
			if((t+(sdivisor<<i))<=sdividend)
			{
				t +=(sdivisor << i);
				quotient ^= (long)1<<i;
			}
		}
		if(sign < 0)
			quotient = -quotient;
		if(quotient > Integer.MAX_VALUE)
			quotient = Integer.MAX_VALUE;
		if(quotient < Integer.MIN_VALUE)
			quotient = Integer.MIN_VALUE;
		return (int)quotient;
	}
	
	//Substring with concatenation of all words
	public List<Integer> findSubstring(String S, String[] L)
	{
		List<Integer> res = new ArrayList<Integer>();
		if(S == null || L == null || L.length == 0)
			return res;
		int len = L[0].length();
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(String w : L)
		{
			map.put(w, map.containsKey(w)?map.get(w)+1: 1);
		}
		for(int i = 0; i <= S.length() - L.length*len; i++)  //this boundary here is '<=', because s.length() - L.length*len + 1 is the start index of the last possible section.
		{
			Map<String, Integer> copy = new HashMap<String, Integer>(map);
			for(int j = 0; j < L.length; j++)
			{
				String str = S.substring(i+j*len, i+j*len+len);
				if(copy.containsKey(str))
				{
					int count = copy.get(str);
					if(count == 1)
						copy.remove(str);
					else
						copy.put(str, count-1);
					if(copy.isEmpty())
					{
						res.add(i);
						break;
					}
				}
				else
					break;
			}
		}
		return res;
	}
	
	//Next permutation
	public void nextPermutation(int[] num)
	{
		int leng =num.length;
		if(leng < 2)
			return;
		int i = leng -1;
		for(; i > 0 && num[i-1] >= num[i]; i--); //find the relative max value
		if(i == 0)
		{
			Arrays.sort(num);
			return;
		}
		int j = leng - 1;
		for(;num[i-1]>=num[j]; j--);
		int tmp = num[j];
		num[j] = num[i-1];
		num[i-1] = tmp;
		Arrays.sort(num, i, leng);
			
	}
	
	
	//Longest valid parentheses
	//we push index into the stack to keep track of the length
	public int longestValidParentheses(String s)
	{
		if(s.length() == 0 || s.length() == 1)
			return 0;
		Stack<Integer> stack = new Stack<Integer>();
		int max = 0, count;
		for(int i = 0; i < s.length() - 1; i++)
		{
			if(s.charAt(i) == '(')
				stack.push(i);
			else
			{
				if(!stack.isEmpty() && s.charAt(stack.peek()) == '(')
				{
					stack.pop();
					count = (stack.isEmpty())? i + 1: i - stack.peek();
					max = Math.max(count, max);
				}
				else
					stack.push(i);
			}
		}
		return max;
	}
	
	
	//Search in rotated sorted array
	public int search(int[] nums, int target)
	{
		int l = 0, h = nums.length - 1;
		while( l <= h)    //here has to be <=, other wise there would be a case where nums only has one number
		{
			int m = (l+h)/2;
			if(target == nums[m])
				return m;
			if(target == nums[l])
				return l;
			if(target == nums[h])
				return h;
			if(nums[l] < nums[m])
			{
				if(target < nums[m] && target > nums[l])
				{
					h = m - 1;
					l = l + 1;
				}
				else
				{
					l = m + 1;
					h = h-1;
				}
			}
			else
			{
				if(target > nums[m] && target < nums[h])
				{
					l = m + 1;
					h = h - 1;
				}
				else
				{
					l = l + 1;
					h = h - 1;
				}
			}
		}
		return -1;
	}
	
	//search in rotated sorted arrayII
	public boolean searchII(int[]nums, int target)
	{
		int l = 0, h = nums.length - 1;
		while(l <= h)
		{
			if(nums[l] == target)
				return true;
			if(nums[h] == target)
				return true;
			if(nums[l] == nums[h])
			{
				l++;
				h--;
			}
			int m = (l+h)/2;
			if(nums[m] == target)
				return true;
			
			if(nums[l] <= nums[m])
			{
				if(target > nums[l] && target < nums[m])
				{
					l++;
					h = m -1;
				}
				else
				{
					l = m + 1;
					h--;
				}
			}
			else
			{
				if(target > nums[m] && target < nums[h])
				{
					l = m + 1;
					h--;
				}
				else
				{
					l++;
					h = m -1;
				}
			}
		}
		return false;
	}
	//search for a range
	public int[] searchRange(int[] nums, int target)
	{
		int a = -1, b = -1;
		if(nums.length == 0)
			return new int[]{-1,-1};
		int l = 0, h = nums.length - 1;
		while(l <= h)
		{
			if(target == nums[l])
			{
				a = l;
				break;
			}
			if(target == nums[h])
			{
				a = h;
				break;
			}
			int m = (l+h)/2;
			if(target == m)
			{
				a = m;
				break;
			}
			if(target < nums[m])
			{
				l++;
				h = m -1;
			}
			if(target > nums[m])
			{
				l = m + 1;
				h--;
			}
		}
		b = a;
		for(; a - 1 >= 0 && nums[a-1]==target; a--);
		for(; b+1 <= nums.length - 1 && nums[b+1] == target; b++);
		return new int[]{a,b};
	}
	
	//Search insert position
	//Method 1
	public int searchInsert(int[] nums, int target)
	{
		int l = 0, h = nums.length - 1;
		while(l <= h)
		{
			int m = (l+h)/2;
			if(target == nums[l])
				return l;
			if(target == nums[m])
				return m;
			if(target < nums[m]) //unlike previous binary search, this time we only change one index.
				h = m - 1;
			if(target > nums[m])
				l = m + 1;
		}
		return l;
	}
	
	//method 2
	//use recuision, its just like the previous one, but another way to address the while loop's end condition.
	public int searchInsertHelper(int[] nums, int low, int high, int target)
	{
		if(low > high)
			return low;
		int mid = (low+high)/2;
		if(nums[mid] == target)
			return mid;
		else if(nums[mid] > target)
			return searchInsertHelper(nums, low, mid - 1, target);
		else
			return searchInsertHelper(nums, mid + 1, low, target);
	}
	public int searchInsert2(int[] nums, int target)
	{
		return searchInsertHelper(nums, 0, nums.length-1, target);
	}
	
	
	//valid sudoku
	public static boolean isValidSudoku(char[][] board)
	{	
		HashSet[] row = new HashSet[9];
		HashSet[] col = new HashSet[9];
		HashSet[] Grid = new HashSet[9];
		
		for(int i = 0; i < 9; i++)
		{
			row[i] = new HashSet<Character>();
			col[i] = new HashSet<Character>();
			Grid[i] = new HashSet<Character>();
		}
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(board[i][j] != '.')
				{
					if(row[i].contains(board[i][j])||col[j].contains(board[i][j])||Grid[3*(i/3)+j/3].contains(board[i][j]))
						return false;
					else
					{
						row[i].add(board[i][j]);
						col[j].add(board[i][j]);
						Grid[3*(i/3)+j/3].add(board[i][j]);
					}
				
				}
			}
		}
		return true;
	}
	
	//sudoku solver
	public void solveSudoku(char[][] board)
	{
		if(board == null || board.length == 0)
			return;
		solve(board);
	}
	public boolean solve(char[][] board)
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[0].length; j++)
			{
				if(board[i][j] =='.')
				{
					for(char c = '1'; c <= '9'; c++)
					{
						if(isValid(board,i,j,c))
						{
							board[i][j] = c;
							if(solve(board))
								return true;
							else
								board[i][j] = '.';
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	public boolean isValid(char[][] board, int i, int j, char c)
	{
		for(int row = 0; row < 9; row++)
		{
			if(board[row][j] == c)
				return false;
		}
		for(int col = 0; col < 9; col++)
		{
			if(board[i][col] == c)
				return false;
		}
		for(int row = (i/3)*3; row < ((i/3)*3+3); row++)
		{
			for(int col = (j/3)*3; col < (j/3)*3+3; col++)
			{
				if(board[row][col] == c)
					return false;
			}
		}
		return true;
	}
	
	
	//Count and say
	public String countAndSay(int n)
	{
		if(n == 1)
			return "1";
		String nToChar = countAndSay(n-1);
		StringBuilder  sb = new StringBuilder();
		int len = nToChar.length();
		int off = 1;
		int count = 1;
		char cur = nToChar.charAt(0);
		while(off != len)
		{
			if(nToChar.charAt(off) == cur)
			{
				off++;
				count++;
			}
			else
			{
				sb.append(count + "" + cur);
				cur = nToChar.charAt(off++); //off++ means off only increment after assign ntos.charAt(off) to cur; 
				count = 1;
			}
		}
		sb.append(count+""+cur);
		return sb.toString();
	}
	
	//First missing positive
	//for a n length array, there r at most n positive numbers, everytime we encounter an positive number, put it into
	//Corresponding location
	public int firstMissingPositive(int[] nums) 
	{
		for(int i = 0; i < nums.length; i++)
		{
			int val = nums[i];
			while(val <= nums.length && val > 0 && nums[val-1] != val) //ie. if val = 2  >> nums[1] = 2;
	                                                            //we need a while loop to keep swaping to correct states b4 we move to next index.
			{
				int tmp = nums[val - 1];
				nums[val - 1] = val;
				nums[i] = tmp;
				val = tmp;                                              //keep val updated for the while loop condition.
			}
		}
		for(int i = 0; i < nums.length; i++)
		{
			if(nums[i] != i+1)
				return i+1;
		}
		return nums.length +1;
	}
	
	public int trap(int[] A)
	{
		int leng = A.length;
		int left = 0, right = A.length - 1, area = 0, secHeight = 0;
		while(left < right)
		{
			if(A[left] < A[right])
			{
				secHeight = Math.max(A[left], secHeight);
				area += secHeight - A[left++];
			}
			else
			{
				secHeight = Math.max(A[right], secHeight);
				area += secHeight - A[right--];
			}
		}
		return area;
	}
	
	public boolean isMatch(String s, String p)
	{
		int i = 0;
		int j = 0;
		int ikeeper = 0;
		int jkeeper = 0;
		while(i < s.length())
		{
			if(j < p.length() && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j)))
			{
				i++;
				j++;
			}
			else if(j < p.length() && p.charAt(j) = '*')
			{
				ikeeper = i;
				jkeeper = j;
				j++;
			}
			else if(jkeeper != 1)
			{
				j = jkeeper +1;
				ikeeper++;
				i = ikeeper;
			}
			else
				return false;
		}
		while(j< p.length() && p.charAt(j) == '*')
			j++;
		return j==p.length();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}