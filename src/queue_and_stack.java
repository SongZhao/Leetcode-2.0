public class queue_stack{
	
	//Largest Rectangle in Histogram
	//Given n non-negative integers representing the histogram's bar height 
	//where the width of each bar is 1, find the area of largest rectangle in 
	//the histogram.
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        Stack<Integer> myStack = new Stack<>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){    // its <= here because we wanna include the last bar.
            int h = (i == len ? 0 : heights[i]);   //handle the case where height[i] is null.
            if(myStack.isEmpty() || h >= heights[myStack.peek()]) //if cur bar height is higher than the highest height in stack
            {  
                myStack.push(i);                          //  push it into the stack
            }
            else
            {                                      //if the cur bar height is not the highest
                while(!myStack.isEmpty() && h < heights[myStack.peek()])
                {
                    int tp = myStack.pop();                       //  pop the highest height from the stack, 
                                                            //  and calculate the area 
                                                            //  use that height as the height of the rectangle 
                                                            //  from i - s.peek() to i(i is exclusive so we need -1)
                    maxArea = Math.max(maxArea, heights[tp] * (myStack.isEmpty() ? i : i - 1 - myStack.peek()));  //handle the case where stack is empty.
                }
                myStack.push(i);
            }
        }
        return maxArea;
    }


	//Maximal Rectangle
	//Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
    //For example, given the following matrix:
	//1 0 1 0 0
	//1 0 1 1 1
	//1 1 1 1 1
	//1 0 0 1 0
	//return 6
	public int maximalRectangle(char[][] matrix) 
	{
		if(matrix == null|| maxtrix.length == 0 || matrix[0].length == 0)
			return 0;
		int col = matrix[0].length;
		int row = matrix.length;
		int[] h = new int[col+1];  //number of adjacent 1's for each col
		h[col] = 0;
		int max = 0;
		for(int nrow = 0; nrow < row; nrow++)
		{
			Stack<Integer> s = new Stack<Integer>();
			for(int i = 0; i < col + 1; i++)  
			{
				if(i < col)
				{
					if(matrix[nrow][i] == '1')
						h[i]++;
					else
						h[i] = 0;
				}
				if(s.isEmpty() || h[s.peek()] < h(i))
					s.push(i)
				else
				{
					while(!s.isEmpty() && h[i] < h[s.peek()])
					{
						int top = s.pop();
						int area = h[top]*(s.isEmpty()?i:(i-1-s.peek()));
						max = Math.max(max,area);
					}
					s.push(i);
				}
			}
		}
		return max;
	}






}