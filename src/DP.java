/**
 * Created by Zhao on 11/3/2016.
 */
public class DP {

    /*Given a string S and a string T, count the number of distinct subsequences of T in S.
    A subsequence of a string is a new string which is formed from the original string by deleting some (can be none)
    of the characters without disturbing the relative positions of the remaining characters.
    (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
    Here is an example:
    S = "rabbbit", T = "rabbit"

    Return 3.*/
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


    /*Unique Binary Search Trees
    Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
    For example,
    Given n = 3, there are a total of 5 unique BST's
     */
    M1
    public int numTrees(int n) {
        int [] dp = new int[n+1];
        dp[0]= 1;
        dp[1] = 1;
        for(int level = 2; level <=n; level++)
            for(int root = 1; root<=level; root++)
                dp[level] += dp[level-root]*dp[root-1];
        return dp[n];
    }
    M2
    public static int numTrees1(int n)
    {
        int[] map = new int[n+1];
        return numTrees_helper(map, n);
    }
    public static int numTrees_helper(int[] map, int n)
    {
        if(map[n] != 0)
            return map[n];

        if(n == 1||n==0)
        {
            map[n] = 1;
            return 1;
        }

        int count = 0;

        for(int i = 1; i <= n; i++)
        {
            count += numTrees_helper(map, i-1)*numTrees_helper(map, n-i);
        }

        map[n] = count;
        return count;
    }

    //Given n = 3, your program should return all 5 unique BST's shown below.
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1,n);
    }

    public List<TreeNode> generateTrees(int start,int end){
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
                    root.right=rightSubTree;`
                    trees.add(root);
                }
            }
        }
        return trees;
    }
}
