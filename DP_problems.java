public class DP_problems{

    //when to use DP
    // 1. asking max/min
    // 2. asking yes/no
    // 3. to count something.

    //when it comes to DP, the first thing for us to figure out is the format of the sub problem
    //(or the state of each sub problem). The format of the sub problem can be helpful when we are 
    //trying to come up with the recursive relation.
    //find a way to divided the original problem into the sub problems and use the solutions of the 
    //sub problems to somehow create the solution of the original one.
    



    /*
    / A DP is an algorithmic technique which is usually based on a recurrent formula and one starting states.
    / a sub solution of the problem is constructed from previously found ones. 
    / Define states


    */
    /*Knapsack problem
    //To consider all subsets of items, there can be two cases for every item
    /       1. the item is in optimal set
    /       2. the item is not in optimal set
    / thus the maximum value can be obtained from n items is max of following two values
    /       1. maximum value obtained by n-1 items and W weight(exculding the item)
    /       2. nth item + maximum value obtained by n-1 items and W - (nth) wight.
    /
    */
    //https://www.kancloud.cn/kancloud/pack/70125
    //fd


    //climbStairsing stairs
    public int climbStairs(int n) {
        int[] tmp = new int[n];
        if (n < 2){
            return 1;
        }
        tmp[0] = 1;
        tmp[1] = 2;
        for (int i = 2; i < n; i++){
            tmp[i] = tmp[i-1] + tmp[i-2];
        }
        return tmp[n-1];
    }
    //top-down recursion solution
    //Note that we have to use a mem to memorize all the 
    // results we have went through, otherwise this recursion
    // enter way too much branches and cause LTE.
    HashMap<Integer, Integer> mem = new HashMap<Integer, Integer>();
    public int climbStairs(int n) {
        int steps;
        if(n == 0)
            return 1;
        if(n == 1)
            return 1;
        if(mem.containsKey(n))
            return mem.get(n);
        steps = climbStairs(n-1) + climbStairs(n-2);
        mem.put(n, steps);
        return steps;
    }
    
    //#53 Maximum Subarray
    //Given an integer array nums, find the contiguous subarray which has
    // the largest sum and return its sum
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

    //121 Best Time to buy and sell
    // This problem can reduce to the Maximum subarray problem
    // since at most 1 transaction is allowed, it can be treated
    // as find the largest sum of a contiguous subarray
    public int maxProfit(int[] prices) {
        if(prices.length < 2)
            return 0;
        int[] diff = new int[prices.length -1];
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i <= prices.length - 2; i++)
        {
            diff[i] = prices[i+1] - prices[i];
            sum = Math.max(diff[i], sum+diff[i]);
            max = Math.max(sum, max);
        }
        if (max < 0)
            return 0;
        else
            return max;
    }
    //746 Min cost climbing Stairs
    //like the climb stair problem, but find the minimum instead
    public int minCostClimbingStairs(int[] cost) {
        int[] A = new int[cost.length];
        A[0] = cost[0];
        A[1] = cost[1];
        for(int i = 2; i <= cost.length - 1; i++)
        {
            A[i] = Math.min(A[i-1], A[i-2]) + cost[i];
        }
        return Math.min(A[A.length-1], A[A.length-2]);
    }

    //198 House Robber
    //This problem can be reduce to the climb problem
    public int rob(int[] nums) {
        if(nums.length <=0 || nums == null)
        {
            return 0;
        }
        if(nums.length == 1)
            return nums[0];
        if(nums.length == 2)
            return Math.max(nums[0], nums[1]);
        int[] A =new int[nums.length];
        A[0] = nums[0];
        A[1] = nums[1];
        A[2] = nums[2] + nums[0];
        for(int i = 3; i < nums.length; i++)
        {
            A[i] = Math.max(A[i-2], A[i-3]) + nums[i];
        }
        return Math.max(A[A.length-1], A[A.length-2]);
    }
    //This problem can also be solved use similiar idea of 
    // knapsack problem.
    public static int rob(int[] nums) 
    {
        int ifRobbedPrevious = 0;   // max monney can get if rob current house
        int ifDidntRobPrevious = 0; // max money can get if not rob current house  
        // We go through all the values, we maintain two counts, 1) if we rob this cell, 2) if we didn't rob this cell
        for(int i=0; i < nums.length; i++) 
        {
            // If we rob current cell, previous cell shouldn't be robbed. So, add the current value to previous one.
            int currRobbed = ifDidntRobPrevious + nums[i];
            // If we don't rob current cell, then the count should be max of the previous cell robbed and not robbed
            int currNotRobbed = Math.max(ifDidntRobPrevious, ifRobbedPrevious); 
            // Update values for the next round
            ifDidntRobPrevious  = currNotRobbed;
            ifRobbedPrevious = currRobbed;
        }
        return Math.max(ifRobbedPrevious, ifDidntRobPrevious);
    }

    //413. Arthmetic Slices
    public int numberOfArithmeticSlices(int[] A) {
        int curr = 0, sum = 0;
        for (int i=2; i<A.length; i++)
            if (A[i]-A[i-1] == A[i-1]-A[i-2]) {
                curr += 1;
                sum += curr;
            } else {
                curr = 0;
            }
        return sum;
    }

    //#303. Range Sum Query - Immutable
    //idea is simple sum(i,j) = sum(0,j) - sum(0,i-1)
    int[] nums;
    public NumArray(int[] nums) {
        for(int i = 1; i < nums.length; i++)
            nums[i] += nums[i - 1];
        
        this.nums = nums;
    }

    public int sumRange(int i, int j) {
        if(i == 0)
            return nums[j];
        
        return nums[j] - nums[i - 1];
    }

    //#338. Counting Bits
    //the binray pattern is obvious.
    public int[] countBits(int num) 
    {
        int[] bits = new int[num + 1];    
        for(int i = 1; i <= num; i++)
        {
            bits[i] = bits[i/2];
            if(i%2 == 1) 
                bits[i]++; 
        }
        return bits;
    }
    //bitwise operation. i>>1 is as the same as i/2
    //i&1 is the same as i%2
    public int[] countBits(int num) {
        int[] f = new int[num + 1];
        for (int i=1; i<=num; i++) 
            f[i] = f[i >> 1] + (i & 1);
        return f;
    }
    //647. Palindromic Substrings
    /*Given a string, your task is to count how many 
    palindromic substrings in this string.
    The substrings with different start indexes or end 
    indexes are counted as different substrings even they 
    consist of same characters.*/
    public int countSubstrings(String s) {
        int n = s.length();
        int res = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                if(dp[i][j]) 
                    ++res;
            }
        }
        return res;
    }
    











    
    //Regular Expression Matching
    //'.' Matches any single character.
    //'*' Matches zero or more of the preceding element.
    //isMatch("aab", "c*a*b") → true
    //recursion approach
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() && 
                               (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){ //condition when we have a *
            return (isMatch(text, pattern.substring(2)) ||      //condition when * represents 0
                    (first_match && isMatch(text.substring(1), pattern)));  //condition when * represents 1 or more.
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1)); //when we don't have a star.
        }
    }
    //dp approach
    //ref: https://discuss.leetcode.com/topic/40371/easy-dp-java-solution-with-detailed-explanation/2
    public boolean isMatch(String s, String p) {

        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;        //empty string match empty string
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i-1) == '*') {
                dp[0][i] =  dp[0][i-2];          // can cancel stuff like 'a*' to match null string
            }
        }
        for (int i = 0 ; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.') {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i+1][j+1] = (dp[i][j+1] || dp[i+1][j-1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }


    //Wildcard Matching
    //'?' Matches any single character.
    //'*' Matches any sequence of characters (including the empty sequence).
    public boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        int ikeeper = 0;
        int jkeeper = -1;
        while(i < s.length())
        {
            if(j < p.length() && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j)))
            {
                i++;    //if j is valid and p[j] is '?'' or s[i] = p[j], increment both index;
                j++;
            }
            else if(j < p.length() && p.charAt(j) == '*')
            {
                ikeeper = i;    //record both current i and j 
                jkeeper = j;    
                j++;            //increment j, assume * stands for empty here
            }
            else if(jkeeper != -1) //if jkeeper isnt 0, which means we had a '*'
            {
                j = jkeeper + 1; //the reason we want to record '*' location is 
                ikeeper++;       //because we wanna roll back, if the first clause 
                i = ikeeper;     //doesnt work out. so basiclly its a backtrack,                                      
                                 //which we treat '*' as empty seq and if not treat
            }                    //it as the char locate in ikeeper, or loop this clause till it finds next s[i] that matches p[j].
            else
                return false;   //if j > p.length&&we didnt see any * in p
        }
        while(j < p.length() && p.charAt(j) == '*') //we might have more * left in p when we loop over s. if those left chars are all '*' then its a match. otherwise return false.
            j++;
        return j == p.length();
    }
    //dp approach
    public boolean isMatch(String s, String p) {
        boolean[][] match = new boolean[s.length()+1][p.length()+1];  
        match[0][0]= true;
 
        for(int i=1;i<=p.length();i++)
            if(p.charAt(i-1)=='*')
                match[0][i]= match[0][i-1];

        for(int i=1;i<=s.length();i++)
            for(int j=1;j<=p.length();j++){
                if(p.charAt(j-1)!='*')
                    match[i][j]=match[i-1][j-1] && (p.charAt(j-1)=='?' || s.charAt(i-1)== p.charAt(j-1));
                else
                    match[i][j]=match[i][j-1] || match[i-1][j] ;
            }
        return match[s.length()][p.length()];
    }



    //Longest Valid Parentheses
    //stack approach
    public int longestValidParentheses(String s) {
        if(s.length() == 0 || s == null)
            return 0;
        char[] inc = s.toCharArray();
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        int count = 0;
        for(int i =0; i < inc.length; i++)
        {
            if(s.charAt(i) == '(')
                stack.push(i);
            else
            {
                if(!stack.isEmpty() && s.charAt(stack.peek()) == '(')
                {
                    stack.pop();
                    count = stack.isEmpty()? i + 1 : i - stack.peek();
                    max = Math.max(count, max);
                }
                else 
                    stack.push(i);                  //also need to push ')' to set as left boundary 
            }
        }
        return max;
    }
    //dp approach
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
    //left-right approach, which only use 1 space
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    //Unique path
    //backwards
    int[][] upcache;
    public int uniquePaths(int m, int n) 
    {
        //use cache to save time
        upcache = new int[m][n];
        return up(m - 1, n - 1);
    }
    public int up(int m, int n)
    {
        if(m == 0 || n== 0)
            return 1;
        if(upcache[m][n] != 0)
            return upcache[m][n];
        int path = up(m-1, n) + up(m, n-1);
        upcache[m][n] = path;
        return path;
    }

    //forwards
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(i == 0 && j ==0)
                    dp[i][j] = 1;
                if(j == 0 || i == 0)
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                    
            }
        }
        return dp[m-1][n-1];
    }

    //Unique path II
    //with obstacle
    //neat style
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //this one is much liek the coin change problem
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for(int[] row : obstacleGrid)
        {
            for(int j = 0; j < width; j++)
            {
                if(row[j] == 1)
                    dp[j] = 0;
                else if(j > 0)
                    dp[j] += dp[j-1];
            }
        }
        return dp[width - 1];
    }
    //old style
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(obstacleGrid[i][j] == 1)
                    dp[i][j] = 0;
                else if(i == 0 && j ==0)
                    dp[i][j] = 1;
                else if(j == 0)
                    dp[i][j] = dp[i-1][j];
                else if(i == 0)
                    dp[i][j] = dp[i][j-1];
                else
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                    
            }
        }
        return dp[m-1][n-1];
    }



    //Minimum Path Sum
    //backwards
    int[][] dp;
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        dp = new int[m][n];
        return minSum(m-1,n-1, grid);
    }
    public int minSum(int m, int n, int[][] grid)
    {   
        if(dp[m][n] != 0)               //this line is important for speeding up
            return dp[m][n];
        int result = grid[m][n];
        if(m == 0 && n == 0)
        {
            dp[0][0] = result;
            return result;
        }
        else if(m == 0)
        {
            dp[m][n] = result + minSum(m, n-1, grid);
            return result + minSum(m, n-1, grid);
        }
        else if(n == 0)
        {
            dp[m][n] = result + minSum(m-1, n, grid);
            return result + minSum(m-1, n, grid);
        }
        else {
            dp[m][n] = result + Math.min(minSum(m, n - 1, grid), minSum(m - 1, n, grid));
            return result + Math.min(minSum(m, n - 1, grid), minSum(m - 1, n, grid));
        }
    }
    //forwards approach
    public int minPathSum(int[][] grid) {
        if(grid.length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        //we get sum of first row and col first because the rule of adding is
        //add blocks that are left / above.
        for(int i = 1; i < m; i++)  //sum of the first col
        {
            grid[i][0] += grid[i-1][0];
        }
        for(int j = 1; j < n; j++)  //sum of the first row
        {
            grid[0][j] += grid[0][j-1];
        }
        for(int i = 1; i < m; i++)
        {
            for(int j = 1; j < n; j++)
            {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }
        return grid[m-1][n-1];
    }

    //Edit Distance
    int[][] minDistanceMP;
    //LEVENSHTEIN DISTANCE ALGO
    public int minDistance(String word1, String word2) {
        minDistanceMP = new int[word1.length()][word2.length()];
        return LD(word1, word1.length(), word2, word2.length());
    }
    public int LD(String s, int len_s, String t, int len_t)
    {
        //base case
        if(len_s == 0)
            return len_t;
        if(len_t == 0)
            return len_s;
        //check the map
        if(minDistanceMP[len_s - 1][len_t - 1] > 0)
            return minDistanceMP[len_s-1][len_t-1];
        //test if last char of the string matches
        int cost;
        if(s.charAt(len_s - 1) == t.charAt(len_t-1))
            cost = 0;
        else
            cost = 1;
        // return minium of felete char from s, delete char from t, and delete char from both
        int res = Math.min(LD(s, len_s - 1, t, len_t) + 1,
                            Math.min(LD(s, len_s, t, len_t - 1) + 1, 
                                    LD(s, len_s - 1, t, len_t - 1) + cost));
        minDistanceMP[len_s - 1][len_t - 1] = res;
        return res;
    }


    //Maximal Rectangle
    //Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
    //For example, given the following matrix:
    //1 0 1 0 0
    //1 0 1 1 1
    //1 1 1 1 1
    //1 0 0 1 0
    //return 6
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] height = new int[n];
        int res = 0;
        Arrays.fill(right, n);
        Arrays.fill(height, 0);
        for (int i = 0; i < m; i++) {
            int cur_left = 0, cur_right = n;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], cur_left);
                } else {
                    cur_left = j + 1;
                    left[j] = 0;
                }
            }
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], cur_right);
                } else {
                    cur_right = j;
                    right[j] = n;
                }
            }
            for (int j = 0; j < n; j++) {
                res = Math.max(res, (right[j] - left[j])*height[j]);
            }
        }
        return res;
    }

    //Decode ways
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1; //fixed value for the case where substring(0,2) is > 9 and < 27
        dp[1] = s.charAt(0) != '0' ? 1 : 0;//no way to decode first 0
        for(int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i-1, i)); //char at i-1
            int second = Integer.valueOf(s.substring(i-2, i));//char(i-2,i-1)
            if(first >= 1 && first <= 9) {
               dp[i] += dp[i-1];  
            }
            if(second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }






    //Unique Binary Search Trees
    public int numTrees(int n) 
    {
        int [] dp = new int[n+1];
        dp[0]= 1;
        dp[1] = 1;
        //dp[n] = dp[0]*dp[n-1] + dp[1]*dp[n-2] + ... dp[n-1]*dp[0];
        for(int i = 2; i <=n; i++)   
            for(int j = 1; j<=i; j++)
                dp[i] += dp[i-j]*dp[j-1];
        return dp[n];
    } 
    //improve above function with BST asymmetric property
    {
        int j = 0;
        for (; j < (i - 1) / 2; j ++) 
            dp[i] += dp[j] * dp[(i - 1) - j] * 2;  
        int temp = dp[j] * dp[(i - 1) - j];
        dp[i] += (j == i - 1 - j) ? temp : temp * 2;
    }



    //Unique Binary Search Trees II
    public List<TreeNode> generateTrees(int n) 
    {
        return generateTrees(1,n);
    }

    public List<TreeNode> generateTrees(int start,int end)
    {             
        List<TreeNode> trees = new ArrayList<TreeNode>();
        if(start>end){  trees.add(null); return trees;}

        for(int rootValue=start;rootValue<=end;rootValue++){
            List<TreeNode> leftSubTrees=generateTrees(start,rootValue-1);
            List<TreeNode> rightSubTrees=generateTrees(rootValue+1,end);

            for(TreeNode leftSubTree:leftSubTrees){
                for(TreeNode rightSubTree:rightSubTrees){
                    TreeNode root=new TreeNode(rootValue);
                    root.left=leftSubTree;
                    root.right=rightSubTree;
                    trees.add(root);
                }
            }
        }
        return trees;
    }
    //dp
    //https://leetcode.com/problems/unique-binary-search-trees-ii/discuss/
    public List<TreeNode> generateTrees(int n) {
        
        List<TreeNode>[] res = new List[n+1];
        res[0] = new ArrayList();
        if(n == 0) return res[0];
        res[0].add(null);
        res[1] = new ArrayList();
        res[1].add(new TreeNode(1));
        for(int i=2;i<=n;i++){
            res[i] = new ArrayList();
            for(int j=1;j<=i;j++){
                for(TreeNode nodeL: res[j-1]){
                    for(TreeNode nodeR: res[i-j]){
                        TreeNode node = new TreeNode(j);
                        node.left = nodeL;
                        node.right = clone(nodeR, j);
                        res[i].add(node);
                    }
                }
            }
        }
        return res[n];
    }


    static TreeNode clone(TreeNode node, int offset){
        if(node == null) return null;
        TreeNode newNode = new TreeNode(node.val + offset);
        newNode.left = clone(node.left, offset);
        newNode.right = clone(node.right, offset);
        return newNode;
    }





    //Interleaving String
    // Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

    // For example,
    // Given:
    // s1 = "aabcc",
    // s2 = "dbbca",

    // When s3 = "aadbbcbcac", return true.
    // When s3 = "aadbbbaccc", return false.
    public boolean isInterleave(String s1, String s2, String s3) {

    if ((s1.length()+s2.length())!=s3.length()) return false;

    boolean[][] matrix = new boolean[s2.length()+1][s1.length()+1];

    matrix[0][0] = true;

    for (int i = 1; i < matrix[0].length; i++){
        matrix[0][i] = matrix[0][i-1]&&(s1.charAt(i-1)==s3.charAt(i-1));
    }

    for (int i = 1; i < matrix.length; i++){
        matrix[i][0] = matrix[i-1][0]&&(s2.charAt(i-1)==s3.charAt(i-1));
    }

    for (int i = 1; i < matrix.length; i++){
        for (int j = 1; j < matrix[0].length; j++){
            matrix[i][j] = (matrix[i-1][j]&&(s2.charAt(i-1)==s3.charAt(i+j-1)))
                    || (matrix[i][j-1]&&(s1.charAt(j-1)==s3.charAt(i+j-1)));
        }
    }

    return matrix[s2.length()][s1.length()];

    }



    //115. Distinct Subsequences
    public int numDistinct(String S, String T) {
        // array creation
        int[][] mem = new int[T.length()+1][S.length()+1];

        // filling the first row: with 1s
        for(int j=0; j<=S.length(); j++) {
            mem[0][j] = 1;
        }

        // the first column is 0 by default in every other rows but the first, which we need.

        for(int i=0; i<T.length(); i++) {
            for(int j=0; j<S.length(); j++) {
                if(T.charAt(i) == S.charAt(j)) {
                    mem[i+1][j+1] = mem[i][j] + mem[i+1][j];
                } else {
                    mem[i+1][j+1] = mem[i+1][j];
                }
            }
        }

        return mem[T.length()][S.length()];
    }
    //120. Triangle
    public int minimumTotal(List<List<Integer>> triangle)
    {
        int level = triangle.size();
        List<Integer> result = new ArrayList(triangle.get(level-1));
        for(int i = level - 2; i >= 0; i--)
            for(int j = 0; j <= i; j++)
                result.set(j,
                    Math.min(result.get(j),result.get(j+1)) + 
                    triangle.get(i).get(j));
        return result.get(0);        
    }

    //750
    /*Given a grid where each entry is only 0 or 1, find the number of corner rectangles.
    A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. 
    Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.
    */
    public int countCornerRectangles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for (int x = 0; x < m; x++) {
            for (int y = x + 1; y < m; y++) {
                int cnt = 0;
                for (int z = 0; z < n; z++) {
                    if (grid[x][z] == 1 && grid[y][z] == 1) {
                        cnt++;
                    }
                }
                ans += cnt * (cnt - 1) / 2;  //add up the combination of distinct 1 pair in same column
            }
        }
        return ans;
    }


    /*
    Given a string, your task is to count how many palindromic substrings in this string.

    The substrings with different start indexes or end indexes are counted as different 
    substrings even they consist of same characters.
   */

    int count =1;
    public int countSubstrings(String s) {
        if(s.length()==0) 
            return 0;
        for(int i=0; i<s.length()-1; i++){
            checkPalindrome(s,i,i);     //To check the palindrome of odd length palindromic sub-string
            checkPalindrome(s,i,i+1);   //To check the palindrome of even length palindromic sub-string
        }
        return count;
    }    

    private void checkPalindrome(String s, int i, int j) {
        while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)){    //Check for the palindrome string 
            count++;    //Increment the count if palindromin substring found
            i--;    //To trace string in left direction
            j++;    //To trace string in right direction
        }
    }

    /*
    Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.

    Example 1:
    Input: s1 = "sea", s2 = "eat"
    Output: 231
    Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
    Deleting "t" from "eat" adds 116 to the sum.
    At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
    */
    /**
    * dp[i][j] = a[i] == b[j] ? dp[i + 1][j + 1] :
    *            min(a[i] + dp[i + 1][j],  // delete a[i] + minimumDeleteSum(a.substr(i+1), b.substr(j))
    *                b[j] + dp[i][j + 1])  // delete b[j] + minimumDeleteSum(a.substr(i), b.substr(j+1))
    */
    
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length(), MAX = Integer.MAX_VALUE;
        char[] a = s1.toCharArray(), b = s2.toCharArray();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (i < m || j < n)
                    dp[i][j] = i < m && j < n && a[i] == b[j] ?
                        dp[i + 1][j + 1] : Math.min((i < m ? a[i] + dp[i + 1][j] : MAX), (j < n ? b[j] + dp[i][j + 1] : MAX));
            }
        }
        return dp[0][0];
    }
    //636 Maximum Length of Pair Chain
    /*
    /You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
    /Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
    /Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
    /Input: [[1,2], [2,3], [3,4]]
    /Output: 2
    /Explanation: The longest chain is [1,2] -> [3,4]
    /
    */
    //greedy solution 
    public int findLongestChain(int[][] pairs) {
        int re=0,form=Integer.MIN_VALUE;
        Arrays.sort(pairs, (p1, p2) -> (p1[1] - p2[1]));
        for(int i=0;i<pairs.length;i++){
            if(pairs[i][0]>form){
                form=pairs[i][1];
                re++;
            }
        }
        return re;
    }
    //DP
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0) return 0;
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], pairs[i][0] > pairs[j][1]? dp[j] + 1 : dp[j]);
            }
        }
        return dp[pairs.length - 1];
    }
}

