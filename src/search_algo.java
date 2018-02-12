

import java.util.List;  


public class search_algo {
	//Bubble sort    space o(1)
	public void bubblesort(int[] numbers)  //Best O(n), avg O(n^2), worst o(n^2)
	{
		boolean numberSwitched;
		do
		{
			numbersSwitched = false;
			for(int i = 0; i < numbers.length - 1; i++)
			{
				if(number[i+1] < number[i])
				{
					int tmp = numbers[i+1];
					numbers[i+1] = numbers[i];
					number[i] = tmp;
					numberSwitched = true;
				}
			}
		}while(numberSwitched);
	}
	
	
	//insert sort   space o(1)
	public List<Integer> inserSort(List<Integer> numbers) //Best o(n), avg o(n^2), worst o(n^2)
	{
		List<Integer> sortedList = new LinkedList<>();
		
		originalList: for(Integer number : numbers)
		{
			for(int i = 0; i < sortedList.size(); i++)
			{
				if(number < sortedList.get(i))
				{
					sortedList.add(i,number);
					continue originalList;
				}
			}
			sortedList.add(sortedList.size(), number); // add to the end of the list
		}
		return sortedList;
	}
	
	//quick sort    space o(log(n))
	public List<Integer> quicksort(List<Integer> numbers)//best&avg 0(n log(n)), worst o(n^2)
	{
		if(numbers.size() < 2)
		{
			return numbers;
		}
	
		Integer pivot = numbers.get(0);			//the choice of the pivot can make a difference
		List<Integer> lower = new ArrayList<>();	
		List<Integer> higher = new ArrayList<>();
		
		for(int i = 1; i < numbers.size(); i++)
		{
			if(numbers.get(i) < pivot)
				lower.add(numbers.get(i));
			else
				higher.add(numbers.get(i));
		}
		List<Integer> sorted = quicksort(lower);
		
		sorted.add(pivot);
		sorted.addAll(quicksort(higher));
		
		return sorted;
	}
	
	//merge sort     space o(n)   best&avg&worst  o(nlog(n))
	public List<Integer> mergesort(List<Integer> values)
	{
		if(values.size() < 2)
			return values;
		
		List<Integer> leftHalf = values.subList(0, values.size()/2);
		List<Integer> rightHalf = values.subList(values.size()/2, values.size());
		
		return merge(mergesort(leftHalf), mergesort(rightHalf));
	}
	private List<Integer> merge(List<Integer> left, List<Integer> right)
	{
		int leftPtr = 0;
		int rightPtr = 0;
		
		List<Integer> merged = new ArrayList<>(left.size() + right.size());
		
		while(leftPtr < left.size() && rigthPtr < right.size())
		{
			if(left.get(leftPtr) < right.get(rightPtr))
			{
				merged.add(left.get(leftPtr));
				leftPtr++;
			}
			else
			{
				merged.add(right.get(rightPtr));
				rightPtr++;
			}
		}
		return merged;
	}
	
	
	//heap sort space o(1), best&avg&worst o(nlog n)
	private static int N;
	public void Heapsort(int A[])
	{
		buildheap(A);
		for(int i = N; i > 0; i --)
		{
			swap(A,0,i);  //swap the root(maximum value) of the heap with the last element of the heap
			N = N - 1;	 //decrease the size of the heap so the sorted value wont be touched
			maxheap(A,0);
		}
	}
	public void buildheap(int A[])  //build heap need o(n)
	{
		N = A.length -1;
		for(int i = N/2; i >= 0; i--) //i = N/2 -> i is the index of the last parent 
			maxheap(A, i);
	}
	public void maxheap(int A[], int i)  //maxheap need o(log n) and it will be called n times 
	{
		int left = 2*i;         //i is the index of the parent,  left and right are its child.
		int right = 2*i + 1;	//we want to make sure that parent > its child
		int max = i;
		if(left <= N && A[left] > A[i])
			max = left;
		if(right <= N && A[right] > A[max])
			max = right;
		
		if(max != i)
		{
			swap(A, i, max);
			maxheap(A, max);
		}
	}
	public void swap(int A[], int i, int j)
	{
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
	
	
	//shell sort space o(1)  best o(n) avg&worst o((nlogn)^2)
	public static void shellsort(int[ ] a)
	  {
	    int j;
	    for( int gap = a.length / 2; gap > 0; gap /= 2 )
	    {
	      for( int i = gap; i < a.length; i++ )
	      {
	         int tmp = a[i];
	         for( j = i; j >= gap && tmp < a[ j - gap ]; j -= gap )
	         {
	           a[ j ] = a[ j - gap ];
	         }
	         a[ j ] = tmp;
	      }
	    }
	  }
	
	//Radix sort    Best Case O(k*n); Average Case O(k*n); Worst Case O(k*n),  space(n+k)
	public static void RadixSort(int[] A)
	{
		int i, m = A[0], exp = 1, n = A.length;
		int[] b = new int [10];
		for(i = 1; i < n; i++)    //find the largest number
		{
			if(A[i] > m)
				m = A[i];
		}
		while(m/exp > 0)		 //
		{
			int[] bucket = new int [10];
			int[] output = new int[n];
			for(i =0; i < n; i++)			//store the count of the occurrences in bucket[]
				bucket[(A[i]/exp)%10]++;    //(A[i]/exp)%10 will get the least significant number depend on exp's value
			for(i = 1; i < 10; i++)			//change bucket[i] so it now contains actual position of this digit in output[]
				bucket[i] += bucket[i-1];
			for(i = n-1; i >=0; i--)		//build the output array
				output[--bucket[(A[i]/exp)%10]] = A[i];
			for(i = 0; i<n; i++)			//copy output array
				A[i] = b[i];
			exp *=10;
		}
	}
	
	
	
	//When defining an array you msut provide its size, JVM must know the size
	//of the array when it constructed. 
	//Master theorem
	/*
	Theorem 5.1 Let a be an integer greater than or equal to 1 and b be a real number greater than
	1. Let c beapositive real number and d a nonnegative real number. Given a recurrence of the
	form
	T(n) =  aT(n/b) + n^c if n > 1
	T(n) = 		d if n = 1
	then for n a power of b,
	1. if logb a<c, T(n) = Θ(n^c),
	2. if logb a = c, T(n) = Θ(n^c log n),
	3. if logb a>c, T(n) = Θ(n^(logb base a)).
	*/
	
	
	
}
