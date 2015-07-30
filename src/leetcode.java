
import java.util.*;
import java.util.Arrays;

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
			return head.next;
		}
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
		for(j <= nums.length -1; j++ )
		{
			if(nums[j] != val)
			{
				nums[i] = nums[j];
				i++;
			}
		}
		return i;
	}

}