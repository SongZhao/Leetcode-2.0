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





    //Word ladder
    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        Set<String> reached = new HashSet<String>();
        reached.add(beginWord);
        wordDict.add(endWord);
        int distance = 1;
        while (!reached.contains(endWord)) {
            Set<String> toAdd = new HashSet<String>();
            for (String each : reached) {
                for (int i = 0; i < each.length(); i++) {
                    char[] chars = each.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String word = new String(chars);
                        if (wordDict.contains(word)) {
                            toAdd.add(word);
                            wordDict.remove(word);
                        }
                    }
                }
            }
            distance++;
            if (toAdd.size() == 0) return 0;
            reached = toAdd;
        }
        return distance;
    }

    //word break
    //Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can
    // be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary
    // does not contain duplicate words.
    /*
    For example, given
    s = "leetcode",
    dict = ["leet", "code"].

    Return true because "leetcode" can be segmented as "leet code".*/
    public boolean wordBreak(String s, List<String> dict) {

        boolean[] f = new boolean[s.length() + 1];

        f[0] = true;

        for(int i=1; i <= s.length(); i++){
            for(int j=0; j < i; j++){
                if(f[j] && dict.contains(s.substring(j, i))){
                    f[i] = true;
                    break;
                }
            }
        }
        return f[s.length()];
    }

    /*#467
    Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz",
    so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".

    Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s.
    In particular, your input is the string p and you need to output the number of different non-empty substrings of p in
    the string s.
     */
    /*
    The max number of unique substring ends with a letter equals to the length of max contiguous substring ends with that letter.
    Example "abcd", the max number of unique substring ends with 'd' is 4, apparently they are "abcd", "bcd", "cd" and "d".
    If there are overlapping, we only need to consider the longest one because it covers all the possible substrings. Example: "abcdbcd",
    the max number of unique substring ends with 'd' is 4 and all substrings formed by the 2nd "bcd" part are covered in the 4 substrings already.
    No matter how long is a contiguous substring in p, it is in s since s has infinite length.
    Now we know the max number of unique substrings in p ends with 'a', 'b', ..., 'z' and those substrings are all in s.
    Summary is the answer, according to the question.
     */
    public int findSubstringInWraproundString(String p) {
        // count[i] is the maximum unique substring end with ith letter.
        // 0 - 'a', 1 - 'b', ..., 25 - 'z'.
        int[] count = new int[26];
        int maxLengthCur = 0;

        for (int i = 0; i < p.length(); i++) {
            int len = 1;
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || (p.charAt(i - 1) - p.charAt(i) == 25)))
                maxLengthCur++;
            else
                maxLengthCur = 1;

            int index = p.charAt(i) - 'a';
            count[index] = Math.max(count[index], maxLengthCur);
        }

        // Sum to get result
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += count[i];
        }
        return sum;
    }
}